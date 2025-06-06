package com.ruoyi.erp.purchase.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.base.constant.ServiceConstants;
import com.ruoyi.erp.base.service.BaseDocService;
import com.ruoyi.erp.basic.types.TransType;
import com.ruoyi.erp.financial.service.MerchantBalanceService;
import com.ruoyi.erp.purchase.domain.bo.PurchaseTradeBo;
import com.ruoyi.erp.purchase.domain.bo.PurchaseTradeDetailBo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseOrder;
import com.ruoyi.erp.purchase.domain.entity.PurchaseRefund;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTrade;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTradeDetail;
import com.ruoyi.erp.purchase.domain.vo.PurchaseTradeVo;
import com.ruoyi.erp.purchase.mapper.PurchaseOrderMapper;
import com.ruoyi.erp.purchase.mapper.PurchaseTradeMapper;
import com.ruoyi.erp.warehouse.service.InventoryHistoryService;
import com.ruoyi.erp.warehouse.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 采购入库单Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class PurchaseTradeService extends BaseDocService<PurchaseTradeDetail> {

    private final PurchaseTradeMapper purchaseTradeMapper;
    private final PurchaseTradeDetailService purchaseTradeDetailService;
    private final MerchantBalanceService merchantBalanceService;
    private final InventoryService inventoryService;
    private final InventoryHistoryService inventoryHistoryService;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final PurchaseRefundService purchaseRefundService;

    /**
     * 查询采购入库单
     */
    public PurchaseTradeVo queryById(Long id){
        PurchaseTradeVo purchaseTradeVo = purchaseTradeMapper.selectVoById(id);
        purchaseTradeVo.setDetails(purchaseTradeDetailService.queryByPid(id));
        return purchaseTradeVo;
    }

    /**
     * 查询采购入库单列表
     */
    public TableDataInfo<PurchaseTradeVo> queryPageList(PurchaseTradeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PurchaseTrade> lqw = buildQueryWrapper(bo);
        Page<PurchaseTradeVo> result = purchaseTradeMapper.selectVoPage(pageQuery.build(), lqw);
        List<Long> idList = new LinkedList<>();
        List<String> docNoList = new LinkedList<>();
        List<PurchaseTradeVo> records = result.getRecords();
        records.forEach(it -> {
            idList.add(it.getId());
            docNoList.add(it.getDocNo());
        });
        List<PurchaseRefund> purchaseRefunds = purchaseRefundService.getRefundNoByOrderNoAndOrderId(idList, docNoList);
        Map<String,List<String>> map = new HashMap<>();
        purchaseRefunds.forEach(it -> {
            List<String> strings = map.get(it.getTradeNo());
            if(strings == null){
                strings = new LinkedList<>();
                map.put(it.getTradeNo(), strings);
            }
            strings.add(it.getDocNo());

        });
        records.forEach(it -> {
            if(map.get(it.getDocNo()) != null){
                it.setRefundNoList(map.get(it.getDocNo()));
            }
        });
        return TableDataInfo.build(result);
    }

    /**
     * 查询采购入库单列表
     */
    public List<PurchaseTradeVo> queryList(PurchaseTradeBo bo) {
        LambdaQueryWrapper<PurchaseTrade> lqw = buildQueryWrapper(bo);
        return purchaseTradeMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PurchaseTrade> buildQueryWrapper(PurchaseTradeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PurchaseTrade> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, PurchaseTrade::getOrderId, bo.getOrderId());
        lqw.between(params.get("beginDocDate") != null && params.get("endDocDate") != null,
            PurchaseTrade::getDocDate ,params.get("beginDocDate"), params.get("endDocDate"));
        lqw.between(params.get("beginBillDate") != null && params.get("endBillDate") != null,
            PurchaseTrade::getDocDate ,params.get("beginBillDate"), params.get("endBillDate"));
        lqw.eq(StringUtils.isNotBlank(bo.getDocNo()), PurchaseTrade::getDocNo, bo.getDocNo());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), PurchaseTrade::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getCheckedStatus() != null, PurchaseTrade::getCheckedStatus, bo.getCheckedStatus());
        lqw.eq(bo.getRefundStatus() != null, PurchaseTrade::getRefundStatus, bo.getRefundStatus());
        lqw.eq(bo.getBankAccountId() != null, PurchaseTrade::getBankAccountId, bo.getBankAccountId());
        lqw.eq(bo.getMerchantId() != null, PurchaseTrade::getMerchantId, bo.getMerchantId());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增采购入库单
     */
    public void insertByBo(PurchaseTradeBo bo) {
        PurchaseTrade add = MapstructUtils.convert(bo, PurchaseTrade.class);
        List<PurchaseTradeDetailBo> detailBoList = bo.getDetails();
        List<PurchaseTradeDetail> addDetailList = MapstructUtils.convert(detailBoList, PurchaseTradeDetail.class);
        Long sameWarehouseId = getSameWarehouseId(addDetailList);
        add.setWarehouseId(sameWarehouseId);
        purchaseTradeMapper.insert(add);
        bo.setId(add.getId());
        addDetailList.forEach(it -> {
            it.setPid(add.getId());
        });
        // 创建入库单明细
        purchaseTradeDetailService.saveDetails(addDetailList);
    }

    /**
     * 修改采购入库单
     */
    public void updateByBo(PurchaseTradeBo bo) {
        PurchaseTrade update = MapstructUtils.convert(bo, PurchaseTrade.class);
        List<PurchaseTradeDetail> detailList = MapstructUtils.convert(bo.getDetails(), PurchaseTradeDetail.class);
        Long sameWarehouseId = getSameWarehouseId(detailList);
        update.setWarehouseId(sameWarehouseId);
        purchaseTradeMapper.updateById(update);
        // 保存入库单明细
        detailList.forEach(it -> it.setPid(bo.getId()));
        purchaseTradeDetailService.saveDetails(detailList);
    }

    /**
     * 批量删除采购入库单
     */
    public void deleteByIds(Collection<Long> ids) {
        purchaseTradeMapper.deleteBatchIds(ids);
    }



    @Transactional
    public void pass(PurchaseTradeBo bo) {
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }

        merchantBalanceService.doTrade(bo, TransType.PURCHASE_TRADE);
        inventoryService.add(bo.getDetails());
        inventoryHistoryService.saveInventoryHistory(bo, ServiceConstants.InventoryHistoryBizType.PURCHASE,true);
        this.updateOrderStockStatus(bo);
    }

    private void updateOrderStockStatus(PurchaseTradeBo bo) {
        QueryWrapper<PurchaseOrder> qw = new QueryWrapper<>();
        qw.eq("doc_no",  bo.getOrderNo());
        qw.eq("checked_status",1);
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectOne(qw);
        if(purchaseOrder !=null){
            purchaseOrder.setStockStatus(1);
        }
        purchaseOrderMapper.updateById(purchaseOrder);
    }

    public List<PurchaseTrade> getTradeNoByOrderNoAndOrderId(List<Long> idList, List<String> orderNoList) {
        if(idList.isEmpty() || orderNoList.isEmpty()){
            return new LinkedList<>();
        }
        QueryWrapper<PurchaseTrade> qw = new QueryWrapper<>();
        qw.in("order_no", orderNoList);
        qw.in("order_id", idList);
        List<PurchaseTrade> purchaseTrades = purchaseTradeMapper.selectList(qw);
        return purchaseTrades;
    }
}

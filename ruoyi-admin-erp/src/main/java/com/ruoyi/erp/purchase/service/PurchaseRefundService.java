package com.ruoyi.erp.purchase.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.erp.base.constant.ServiceConstants;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.erp.base.service.BaseDocService;
import com.ruoyi.erp.basic.types.TransType;
import com.ruoyi.erp.financial.service.MerchantBalanceService;
import com.ruoyi.erp.purchase.domain.bo.PurchaseRefundDetailBo;
import com.ruoyi.erp.purchase.domain.entity.*;
import com.ruoyi.erp.purchase.mapper.PurchaseTradeMapper;
import com.ruoyi.erp.warehouse.service.InventoryHistoryService;
import com.ruoyi.erp.warehouse.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.purchase.domain.bo.PurchaseRefundBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseRefundVo;
import com.ruoyi.erp.purchase.mapper.PurchaseRefundMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 采购退货单Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class PurchaseRefundService extends BaseDocService<PurchaseRefundDetail> {

    private final PurchaseRefundMapper purchaseRefundMapper;
    private final MerchantBalanceService merchantBalanceService;
    private final InventoryService inventoryService;
    private final InventoryHistoryService inventoryHistoryService;
    private final PurchaseRefundDetailService purchaseRefundDetailService;
    private final PurchaseTradeMapper purchaseTradeMapper;



    /**
     * 查询采购退货单
     */
    public PurchaseRefundVo queryById(Long id){
        PurchaseRefundVo purchaseRefundVo = purchaseRefundMapper.selectVoById(id);
        purchaseRefundVo.setDetails(purchaseRefundDetailService.queryByPid(id));
        return purchaseRefundVo;
    }
    /**
     * 查询采购退货单列表
     */
    public TableDataInfo<PurchaseRefundVo> queryPageList(PurchaseRefundBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PurchaseRefund> lqw = buildQueryWrapper(bo);
        Page<PurchaseRefundVo> result = purchaseRefundMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询采购退货单列表
     */
    public List<PurchaseRefundVo> queryList(PurchaseRefundBo bo) {
        LambdaQueryWrapper<PurchaseRefund> lqw = buildQueryWrapper(bo);
        return purchaseRefundMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PurchaseRefund> buildQueryWrapper(PurchaseRefundBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PurchaseRefund> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getTradeId() != null, PurchaseRefund::getTradeId, bo.getTradeId());
        lqw.eq(StringUtils.isNotBlank(bo.getDocNo()), PurchaseRefund::getDocNo, bo.getDocNo());
        lqw.eq(bo.getDocDate() != null, PurchaseRefund::getDocDate, bo.getDocDate());
        lqw.eq(bo.getCheckedStatus() != null, PurchaseRefund::getCheckedStatus, bo.getCheckedStatus());
        lqw.eq(bo.getMerchantId() != null, PurchaseRefund::getMerchantId, bo.getMerchantId());
        lqw.eq(bo.getGoodsQty() != null, PurchaseRefund::getGoodsQty, bo.getGoodsQty());
        lqw.eq(bo.getGoodsAmount() != null, PurchaseRefund::getGoodsAmount, bo.getGoodsAmount());
        lqw.eq(bo.getOtherExpensesAmount() != null, PurchaseRefund::getOtherExpensesAmount, bo.getOtherExpensesAmount());
        lqw.eq(bo.getDiscountAmount() != null, PurchaseRefund::getDiscountAmount, bo.getDiscountAmount());
        lqw.eq(bo.getActualAmount() != null, PurchaseRefund::getActualAmount, bo.getActualAmount());
        lqw.eq(bo.getPaidAmount() != null, PurchaseRefund::getPaidAmount, bo.getPaidAmount());
        lqw.between(params.get("beginBillDate")!= null && params.get("endBillDate") != null,
            PurchaseRefund::getDocDate, params.get("beginBillDate"), params.get("endBillDate"));
        lqw.orderByDesc(BaseEntity::getUpdateTime);
        return lqw;
    }

    /**
     * 新增采购退货单
     */
    public void insertByBo(PurchaseRefundBo bo) {
        PurchaseRefund add = MapstructUtils.convert(bo, PurchaseRefund.class);
        List<PurchaseRefundDetailBo> detailBoList = bo.getDetails();
        List<PurchaseRefundDetail> addDetailList = MapstructUtils.convert(detailBoList, PurchaseRefundDetail.class);
        Long sameWarehouseId = getSameWarehouseId(addDetailList);
        add.setWarehouseId(sameWarehouseId);
        //add.setCheckedStatus(0);
        add.setTradeNo(bo.getTradeNo());
        purchaseRefundMapper.insert(add);
        bo.setId(add.getId());
        addDetailList.forEach(it -> {
            it.setPid(add.getId());
        });
        // 创建入库单明细
        purchaseRefundDetailService.saveDetails(addDetailList);
    }

    /**
     * 修改采购退货单
     */
    public void updateByBo(PurchaseRefundBo bo) {
        PurchaseRefund update = MapstructUtils.convert(bo, PurchaseRefund.class);
        List<PurchaseRefundDetail> detailList = MapstructUtils.convert(bo.getDetails(), PurchaseRefundDetail.class);
        Long sameWarehouseId = getSameWarehouseId(detailList);
        update.setWarehouseId(sameWarehouseId);
        purchaseRefundMapper.updateById(update);
        // 保存入库单明细
        detailList.forEach(it -> it.setPid(bo.getId()));
        purchaseRefundDetailService.saveDetails(detailList);
    }

    /**
     * 批量删除采购退货单
     */
    public void deleteByIds(Collection<Long> ids) {
        purchaseRefundMapper.deleteBatchIds(ids);
    }

    /**
     * 退货单完成
     * @param bo
     */
    @Transactional
    public void pass(PurchaseRefundBo bo) {
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        merchantBalanceService.doRefund(bo, TransType.PURCHASE_RETURN);
        inventoryService.subtract(bo.getDetails());
        inventoryHistoryService.saveInventoryHistory(bo, ServiceConstants.InventoryHistoryBizType.PURCHASE_REFUND,true);
        this.refund(bo);

    }
    private void refund(PurchaseRefundBo bo){
        QueryWrapper<PurchaseTrade> qw = new QueryWrapper<>();
        qw.eq("doc_no", bo.getTradeNo());
        qw.eq("checked_status",1);
        PurchaseTrade purchaseTrade = purchaseTradeMapper.selectOne(qw);
        if(purchaseTrade !=null){
            purchaseTrade.setRefundStatus(1);
            //设置退款金额为入库单的实际付款金额
            BigDecimal refundAmount = purchaseTrade.getRefundAmount();
            if(refundAmount==null){
                refundAmount = bo.getActualAmount();
            }else {
                refundAmount = refundAmount.add(bo.getActualAmount());
            }
            purchaseTrade.setRefundAmount(refundAmount);
            purchaseTradeMapper.updateById(purchaseTrade);
        }
    }
    public List<PurchaseRefund> getRefundNoByOrderNoAndOrderId(List<Long> idList, List<String> docNoList) {
        if(idList.isEmpty() || docNoList.isEmpty()){
            return new LinkedList<>();
        }
        QueryWrapper<PurchaseRefund> qw = new QueryWrapper<>();
        qw.in("trade_no", docNoList);
        qw.in("trade_id", idList);
        return purchaseRefundMapper.selectList(qw);
    }
}

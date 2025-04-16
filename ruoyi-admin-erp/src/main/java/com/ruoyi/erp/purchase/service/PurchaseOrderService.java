package com.ruoyi.erp.purchase.service;

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
import com.ruoyi.erp.purchase.domain.bo.PurchaseOrderDetailBo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseOrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.purchase.domain.bo.PurchaseOrderBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseOrderVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseOrder;
import com.ruoyi.erp.purchase.mapper.PurchaseOrderMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

/**
 * 采购订单Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class PurchaseOrderService extends BaseDocService<PurchaseOrderDetail> {

    private final PurchaseOrderMapper purchaseOrderMapper;
    private final PurchaseOrderDetailService purchaseOrderDetailService;
    private final MerchantBalanceService merchantBalanceService;

    /**
     * 查询采购订单
     */
    public PurchaseOrderVo queryById(Long id){
        PurchaseOrderVo purchaseOrderVo = purchaseOrderMapper.selectVoById(id);
        purchaseOrderVo.setDetails(purchaseOrderDetailService.queryByPid(id));
        return purchaseOrderVo;
    }

    /**
     * 查询采购订单列表
     */
    public TableDataInfo<PurchaseOrderVo> queryPageList(PurchaseOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PurchaseOrder> lqw = buildQueryWrapper(bo);
        Page<PurchaseOrderVo> result = purchaseOrderMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询采购订单列表
     */
    public List<PurchaseOrderVo> queryList(PurchaseOrderBo bo) {
        LambdaQueryWrapper<PurchaseOrder> lqw = buildQueryWrapper(bo);
        return purchaseOrderMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PurchaseOrder> buildQueryWrapper(PurchaseOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PurchaseOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getDocNo()), PurchaseOrder::getDocNo, bo.getDocNo());
        lqw.between(params.get("beginDocDate") != null && params.get("endDocDate") != null,
            PurchaseOrder::getDocDate ,params.get("beginDocDate"), params.get("endDocDate"));
        lqw.between(params.get("beginDeliveryDate") != null && params.get("endDeliveryDate") != null,
            PurchaseOrder::getDeliveryDate ,params.get("beginDeliveryDate"), params.get("endDeliveryDate"));
        lqw.between(params.get("beginBillDate")!= null && params.get("endBillDate") != null,
            PurchaseOrder::getDocDate, params.get("beginBillDate"), params.get("endBillDate"));
        lqw.eq(bo.getCheckedStatus() != null, PurchaseOrder::getCheckedStatus, bo.getCheckedStatus());
        lqw.eq(bo.getStockStatus() != null, PurchaseOrder::getStockStatus, bo.getStockStatus());
        lqw.orderByDesc(BaseEntity::getUpdateTime);
        return lqw;
    }

    /**
     * 新增采购订单
     */
    public void insertByBo(PurchaseOrderBo bo) {
        // 创建入库单
        PurchaseOrder add = MapstructUtils.convert(bo, PurchaseOrder.class);
        List<PurchaseOrderDetailBo> detailBoList = bo.getDetails();
        List<PurchaseOrderDetail> addDetailList = MapstructUtils.convert(detailBoList, PurchaseOrderDetail.class);
        Long sameWarehouseId = getSameWarehouseId(addDetailList);
        add.setWarehouseId(sameWarehouseId);
        purchaseOrderMapper.insert(add);
        bo.setId(add.getId());
        addDetailList.forEach(it -> {
            it.setPid(add.getId());
        });
        // 创建入库单明细
        purchaseOrderDetailService.saveDetails(addDetailList);
    }

    /**
     * 修改采购订单
     */
    public void updateByBo(PurchaseOrderBo bo) {
        PurchaseOrder update = MapstructUtils.convert(bo, PurchaseOrder.class);
        List<PurchaseOrderDetail> detailList = MapstructUtils.convert(bo.getDetails(), PurchaseOrderDetail.class);
        Long sameWarehouseId = getSameWarehouseId(detailList);
        update.setWarehouseId(sameWarehouseId);
        purchaseOrderMapper.updateById(update);
        // 保存入库单明细
        detailList.forEach(it -> it.setPid(bo.getId()));
        purchaseOrderDetailService.saveDetails(detailList);
    }

    /**
     * 批量删除采购订单
     */
    public void deleteByIds(Collection<Long> ids) {
        purchaseOrderMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void pass(PurchaseOrderBo bo) {
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        if(bo.getPrepayAmount()!=null && bo.getBankAccountId()!=null){
            merchantBalanceService.doOrder(bo, TransType.PURCHASE_ORDER);
        }
    }
}

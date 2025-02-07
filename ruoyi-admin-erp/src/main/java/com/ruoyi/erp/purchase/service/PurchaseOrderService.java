package com.ruoyi.erp.purchase.service;

import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.purchase.domain.bo.PurchaseOrderBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseOrderVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseOrder;
import com.ruoyi.erp.purchase.mapper.PurchaseOrderMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 采购订单Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class PurchaseOrderService {

    private final PurchaseOrderMapper purchaseOrderMapper;

    /**
     * 查询采购订单
     */
    public PurchaseOrderVo queryById(Long id){
        return purchaseOrderMapper.selectVoById(id);
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
        lqw.eq(StringUtils.isNotBlank(bo.getBillNo()), PurchaseOrder::getBillNo, bo.getBillNo());
        lqw.eq(bo.getBillDate() != null, PurchaseOrder::getBillDate, bo.getBillDate());
        lqw.eq(bo.getDeliveryDate() != null, PurchaseOrder::getDeliveryDate, bo.getDeliveryDate());
        lqw.eq(bo.getCheckedStatus() != null, PurchaseOrder::getCheckedStatus, bo.getCheckedStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getCheckedBy()), PurchaseOrder::getCheckedBy, bo.getCheckedBy());
        lqw.eq(bo.getStockStatus() != null, PurchaseOrder::getStockStatus, bo.getStockStatus());
        lqw.eq(bo.getMerchantId() != null, PurchaseOrder::getMerchantId, bo.getMerchantId());
        lqw.eq(bo.getGoodsQty() != null, PurchaseOrder::getGoodsQty, bo.getGoodsQty());
        lqw.eq(bo.getProcessedQty() != null, PurchaseOrder::getProcessedQty, bo.getProcessedQty());
        lqw.eq(bo.getGoodsAmount() != null, PurchaseOrder::getGoodsAmount, bo.getGoodsAmount());
        lqw.eq(bo.getOtherExpensesAmount() != null, PurchaseOrder::getOtherExpensesAmount, bo.getOtherExpensesAmount());
        lqw.eq(bo.getDiscountAmount() != null, PurchaseOrder::getDiscountAmount, bo.getDiscountAmount());
        lqw.eq(bo.getActualAmount() != null, PurchaseOrder::getActualAmount, bo.getActualAmount());
        lqw.eq(bo.getPrepayAmount() != null, PurchaseOrder::getPrepayAmount, bo.getPrepayAmount());
        return lqw;
    }

    /**
     * 新增采购订单
     */
    public void insertByBo(PurchaseOrderBo bo) {
        PurchaseOrder add = MapstructUtils.convert(bo, PurchaseOrder.class);
        purchaseOrderMapper.insert(add);
    }

    /**
     * 修改采购订单
     */
    public void updateByBo(PurchaseOrderBo bo) {
        PurchaseOrder update = MapstructUtils.convert(bo, PurchaseOrder.class);
        purchaseOrderMapper.updateById(update);
    }

    /**
     * 批量删除采购订单
     */
    public void deleteByIds(Collection<Long> ids) {
        purchaseOrderMapper.deleteBatchIds(ids);
    }
}

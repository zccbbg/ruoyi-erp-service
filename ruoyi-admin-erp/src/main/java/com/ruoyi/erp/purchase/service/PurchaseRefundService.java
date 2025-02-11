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
import com.ruoyi.erp.purchase.domain.bo.PurchaseRefundBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseRefundVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseRefund;
import com.ruoyi.erp.purchase.mapper.PurchaseRefundMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 采购退货单Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class PurchaseRefundService {

    private final PurchaseRefundMapper purchaseRefundMapper;

    /**
     * 查询采购退货单
     */
    public PurchaseRefundVo queryById(Long id){
        return purchaseRefundMapper.selectVoById(id);
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
        lqw.eq(bo.getDeductedAmount() != null, PurchaseRefund::getDeductedAmount, bo.getDeductedAmount());
        lqw.eq(bo.getDueAmount() != null, PurchaseRefund::getDueAmount, bo.getDueAmount());
        return lqw;
    }

    /**
     * 新增采购退货单
     */
    public void insertByBo(PurchaseRefundBo bo) {
        PurchaseRefund add = MapstructUtils.convert(bo, PurchaseRefund.class);
        purchaseRefundMapper.insert(add);
    }

    /**
     * 修改采购退货单
     */
    public void updateByBo(PurchaseRefundBo bo) {
        PurchaseRefund update = MapstructUtils.convert(bo, PurchaseRefund.class);
        purchaseRefundMapper.updateById(update);
    }

    /**
     * 批量删除采购退货单
     */
    public void deleteByIds(Collection<Long> ids) {
        purchaseRefundMapper.deleteBatchIds(ids);
    }
}

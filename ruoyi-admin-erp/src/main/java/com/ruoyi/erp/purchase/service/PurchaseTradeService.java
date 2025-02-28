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
import com.ruoyi.erp.purchase.domain.bo.PurchaseTradeBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseTradeVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTrade;
import com.ruoyi.erp.purchase.mapper.PurchaseTradeMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 采购入库单Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class PurchaseTradeService {

    private final PurchaseTradeMapper purchaseTradeMapper;

    /**
     * 查询采购入库单
     */
    public PurchaseTradeVo queryById(Long id){
        return purchaseTradeMapper.selectVoById(id);
    }

    /**
     * 查询采购入库单列表
     */
    public TableDataInfo<PurchaseTradeVo> queryPageList(PurchaseTradeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PurchaseTrade> lqw = buildQueryWrapper(bo);
        Page<PurchaseTradeVo> result = purchaseTradeMapper.selectVoPage(pageQuery.build(), lqw);
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
        lqw.eq(StringUtils.isNotBlank(bo.getDocNo()), PurchaseTrade::getDocNo, bo.getDocNo());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), PurchaseTrade::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getDocDate() != null, PurchaseTrade::getDocDate, bo.getDocDate());
        lqw.eq(bo.getCheckedStatus() != null, PurchaseTrade::getCheckedStatus, bo.getCheckedStatus());
        lqw.eq(bo.getRefundStatus() != null, PurchaseTrade::getRefundStatus, bo.getRefundStatus());
        lqw.eq(bo.getRefundAmount() != null, PurchaseTrade::getRefundAmount, bo.getRefundAmount());
        lqw.eq(bo.getPaidAmount() != null, PurchaseTrade::getPaidAmount, bo.getPaidAmount());
        lqw.eq(bo.getBankAccountId() != null, PurchaseTrade::getBankAccountId, bo.getBankAccountId());
        lqw.eq(bo.getMerchantId() != null, PurchaseTrade::getMerchantId, bo.getMerchantId());
        lqw.eq(bo.getGoodsQty() != null, PurchaseTrade::getGoodsQty, bo.getGoodsQty());
        lqw.eq(bo.getGoodsAmount() != null, PurchaseTrade::getGoodsAmount, bo.getGoodsAmount());
        lqw.eq(bo.getOtherExpensesAmount() != null, PurchaseTrade::getOtherExpensesAmount, bo.getOtherExpensesAmount());
        lqw.eq(bo.getDiscountAmount() != null, PurchaseTrade::getDiscountAmount, bo.getDiscountAmount());
        lqw.eq(bo.getActualAmount() != null, PurchaseTrade::getActualAmount, bo.getActualAmount());
        return lqw;
    }

    /**
     * 新增采购入库单
     */
    public void insertByBo(PurchaseTradeBo bo) {
        PurchaseTrade add = MapstructUtils.convert(bo, PurchaseTrade.class);
        purchaseTradeMapper.insert(add);
    }

    /**
     * 修改采购入库单
     */
    public void updateByBo(PurchaseTradeBo bo) {
        PurchaseTrade update = MapstructUtils.convert(bo, PurchaseTrade.class);
        purchaseTradeMapper.updateById(update);
    }

    /**
     * 批量删除采购入库单
     */
    public void deleteByIds(Collection<Long> ids) {
        purchaseTradeMapper.deleteBatchIds(ids);
    }
}

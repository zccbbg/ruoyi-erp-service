package com.ruoyi.erp.financial.service;

import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.erp.base.domain.bo.BaseOrderBo;
import com.ruoyi.erp.base.domain.bo.BaseTradeBo;
import com.ruoyi.erp.base.domain.bo.BaseVoucherBo;
import com.ruoyi.erp.basic.types.TransType;
import com.ruoyi.erp.financial.domain.bo.TransHistoryBo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.financial.domain.bo.MerchantBalanceBo;
import com.ruoyi.erp.financial.domain.vo.MerchantBalanceVo;
import com.ruoyi.erp.financial.domain.entity.MerchantBalance;
import com.ruoyi.erp.financial.mapper.MerchantBalanceMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

/**
 * 商家余额Service业务层处理
 *
 * @author zcc
 * @date 2025-02-19
 */
@RequiredArgsConstructor
@Service
public class MerchantBalanceService {

    private final MerchantBalanceMapper merchantBalanceMapper;
    private final TransHistoryService transHistoryService;

    /**
     * 查询商家余额
     */
    public MerchantBalanceVo queryById(Long id){
        return merchantBalanceMapper.selectVoById(id);
    }

    public MerchantBalance queryByMerchantId(Long merchantId){
        LambdaQueryWrapper<MerchantBalance> lqw = Wrappers.lambdaQuery();
        lqw.eq(MerchantBalance::getMerchantId, merchantId);
        return merchantBalanceMapper.selectOne(lqw);

    }

    /**
     * 查询商家余额列表
     */
    public TableDataInfo<MerchantBalanceVo> queryPageList(MerchantBalanceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MerchantBalance> lqw = buildQueryWrapper(bo);
        Page<MerchantBalanceVo> result = merchantBalanceMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商家余额列表
     */
    public List<MerchantBalanceVo> queryList(MerchantBalanceBo bo) {
        LambdaQueryWrapper<MerchantBalance> lqw = buildQueryWrapper(bo);
        return merchantBalanceMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MerchantBalance> buildQueryWrapper(MerchantBalanceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MerchantBalance> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMerchantId() != null, MerchantBalance::getMerchantId, bo.getMerchantId());
        lqw.eq(bo.getInitialBalance() != null, MerchantBalance::getInitialBalance, bo.getInitialBalance());
        lqw.eq(bo.getBalance() != null, MerchantBalance::getBalance, bo.getBalance());
        lqw.orderByDesc(BaseEntity::getUpdateTime);
        return lqw;
    }

    /**
     * 新增商家余额
     */
    public void insertByBo(MerchantBalanceBo bo) {
        MerchantBalance add = MapstructUtils.convert(bo, MerchantBalance.class);
        merchantBalanceMapper.insert(add);
    }

    /**
     * 修改商家余额
     */
    public void updateByBo(MerchantBalanceBo bo) {
        MerchantBalance update = MapstructUtils.convert(bo, MerchantBalance.class);
        merchantBalanceMapper.updateById(update);
    }

    /**
     * 批量删除商家余额
     */
    public void deleteByIds(Collection<Long> ids) {
        merchantBalanceMapper.deleteBatchIds(ids);
    }

    private TransHistoryBo getTransHistoryBo(BaseVoucherBo bo ,String transType){
        TransHistoryBo transHistoryBo = new TransHistoryBo();
        transHistoryBo.setMerchantId(bo.getMerchantId());
        transHistoryBo.setTotalAmount(bo.getTotalAmount());
        transHistoryBo.setTransType(transType);
        transHistoryBo.setRelatedNo(bo.getVoucherNo());
        transHistoryBo.setDiscountAmount(bo.getDiscountAmount());
        transHistoryBo.setBankAccountId(bo.getBankAccountId());
        transHistoryBo.setRelatedId(bo.getId());
        transHistoryBo.setPaidAmount(bo.getPaidAmount());
        return transHistoryBo;
    }

    private TransHistoryBo getTransHistoryBo(BaseOrderBo bo, String transType) {
        TransHistoryBo transHistoryBo = new TransHistoryBo();
        transHistoryBo.setMerchantId(bo.getMerchantId());
        transHistoryBo.setTransType(transType);
        transHistoryBo.setRelatedNo(bo.getDocNo());
        transHistoryBo.setBankAccountId(bo.getBankAccountId());
        transHistoryBo.setRelatedId(bo.getId());
        return transHistoryBo;
    }

    private TransHistoryBo getTransHistoryBo(BaseTradeBo bo, String transType) {
        TransHistoryBo transHistoryBo = new TransHistoryBo();
        transHistoryBo.setMerchantId(bo.getMerchantId());
        transHistoryBo.setTransType(transType);
        transHistoryBo.setRelatedNo(bo.getDocNo());
        transHistoryBo.setBankAccountId(bo.getBankAccountId());
        transHistoryBo.setRelatedId(bo.getId());
        transHistoryBo.setPaidAmount(bo.getPaidAmount());
        transHistoryBo.setDiscountAmount(bo.getDiscountAmount());
        transHistoryBo.setActualAmount(bo.getActualAmount());
        BigDecimal goodsAmount = bo.getGoodsAmount() == null ? BigDecimal.ZERO : bo.getGoodsAmount();
        BigDecimal otherExpenses = bo.getOtherExpensesAmount() == null ? BigDecimal.ZERO : bo.getOtherExpensesAmount();
        transHistoryBo.setTotalAmount(goodsAmount.add(otherExpenses));
        return transHistoryBo;
    }

    @Transactional
    public void doVoucher(BaseVoucherBo bo,String voucherType) {
        MerchantBalance merchantBalance = queryByMerchantId(bo.getMerchantId());
        TransHistoryBo transHistoryBo = this.getTransHistoryBo(bo, voucherType);
        BigDecimal balanceChange = bo.getTotalAmount();
        if(voucherType.equals(TransType.PAYMENT_VOUCHER)){
            balanceChange = balanceChange.negate();
        }
        transHistoryBo.setBalanceChange(bo.getTotalAmount());
        this.updateBalance(merchantBalance, balanceChange, transHistoryBo);
    }

    @Transactional
    public void doOrder(BaseOrderBo bo,String transType) {
        MerchantBalance merchantBalance = queryByMerchantId(bo.getMerchantId());
        BigDecimal balanceChange = bo.getPrepayAmount();
        if(transType.equals(TransType.PURCHASE_TRADE)){
            balanceChange = balanceChange.negate();
        }

        TransHistoryBo transHistoryBo = this.getTransHistoryBo(bo, transType);
        transHistoryBo.setBalanceChange(balanceChange);
        this.updateBalance(merchantBalance, balanceChange, transHistoryBo);
    }

    @Transactional
    public void doTrade(BaseTradeBo bo, String transType) {
        BigDecimal actualAmount = Objects.requireNonNullElse(bo.getActualAmount(), BigDecimal.ZERO);
        BigDecimal paidAmount = Objects.requireNonNullElse(bo.getPaidAmount(), BigDecimal.ZERO);
        MerchantBalance merchantBalance = queryByMerchantId(bo.getMerchantId());
        BigDecimal balanceChange;
        if(transType.equals(TransType.PURCHASE_TRADE)){
            balanceChange = actualAmount.subtract(paidAmount);
        }else {
            balanceChange = paidAmount.subtract(actualAmount);
        }
        TransHistoryBo transHistoryBo = this.getTransHistoryBo(bo, transType);
        transHistoryBo.setBalanceChange(balanceChange);
        this.updateBalance(merchantBalance, balanceChange, transHistoryBo);
    }

    private void updateBalance(MerchantBalance merchantBalance, BigDecimal balanceChange, TransHistoryBo transHistoryBo) {
        if (merchantBalance == null) {
            merchantBalance = new MerchantBalance();
            merchantBalance.setMerchantId(transHistoryBo.getMerchantId());
            merchantBalance.setInitialBalance(BigDecimal.ZERO);
            merchantBalance.setBalance(balanceChange);
            merchantBalanceMapper.insert(merchantBalance);
            transHistoryBo.setBeforeBalance(BigDecimal.ZERO);
            transHistoryBo.setAfterBalance(balanceChange);
        }else {
            BigDecimal beforeBalance = merchantBalance.getBalance();
            BigDecimal afterBalance = beforeBalance.add(balanceChange);
            merchantBalance.setBalance(afterBalance);
            merchantBalanceMapper.updateById(merchantBalance);
            transHistoryBo.setBeforeBalance(beforeBalance);
            transHistoryBo.setAfterBalance(afterBalance);
        }
        transHistoryService.insertByBo(transHistoryBo);
    }
}

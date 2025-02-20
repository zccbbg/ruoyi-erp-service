package com.ruoyi.erp.financial.service;

import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.erp.financial.domain.bo.ReceiptVoucherBo;
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

    @Transactional
    public void add(ReceiptVoucherBo bo) {
        Long merchantId = bo.getMerchantId();
        MerchantBalance merchantBalance = queryByMerchantId(merchantId);
        TransHistoryBo transHistoryBo = new TransHistoryBo();
        transHistoryBo.setMerchantId(merchantId);
        transHistoryBo.setTransType("收款单");
        transHistoryBo.setTotalAmount(bo.getTotalAmount());
        transHistoryBo.setRelatedNo(bo.getVoucherNo());
        transHistoryBo.setDiscountAmount(bo.getDiscountAmount());
        transHistoryBo.setBankAccountId(bo.getBankAccountId());
        transHistoryBo.setRelatedId(bo.getId());
        transHistoryBo.setPaidAmount(bo.getPaidAmount());
        transHistoryBo.setBalanceChange(bo.getTotalAmount());
        if (merchantBalance == null) {
            merchantBalance = new MerchantBalance();
            merchantBalance.setMerchantId(merchantId);
            merchantBalance.setInitialBalance(BigDecimal.ZERO);
            merchantBalance.setBalance(bo.getTotalAmount());
            merchantBalanceMapper.insert(merchantBalance);
            transHistoryBo.setBeforeBalance(BigDecimal.ZERO);
            transHistoryBo.setAfterBalance(bo.getTotalAmount());
        }else {
            BigDecimal beforeBalance = merchantBalance.getBalance();
            BigDecimal afterBalance = beforeBalance.add(bo.getTotalAmount());
            merchantBalance.setBalance(afterBalance);
            merchantBalanceMapper.updateById(merchantBalance);
            transHistoryBo.setBeforeBalance(beforeBalance);
            transHistoryBo.setAfterBalance(afterBalance);
        }
        transHistoryService.insertByBo(transHistoryBo);
    }
}

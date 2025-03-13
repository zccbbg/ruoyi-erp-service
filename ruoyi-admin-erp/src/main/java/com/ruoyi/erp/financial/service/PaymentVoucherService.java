package com.ruoyi.erp.financial.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.basic.types.TransType;
import com.ruoyi.erp.financial.domain.bo.PaymentVoucherBo;

import com.ruoyi.erp.financial.domain.entity.PaymentVoucher;
import com.ruoyi.erp.financial.domain.vo.PaymentVoucherVo;
import com.ruoyi.erp.financial.mapper.PaymentVoucherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 收款单Service业务层处理
 *
 * @author zcc
 * @date 2025-02-14
 */
@RequiredArgsConstructor
@Service
public class PaymentVoucherService {

    private final PaymentVoucherMapper paymentVoucherMapper;
    private final MerchantBalanceService merchantBalanceService;

    /**
     *
     * 分页查询付款单列表
     * @param pageQuery
     * @return
     */
    public TableDataInfo<PaymentVoucherVo> queryPageList(PaymentVoucherBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PaymentVoucher> lqw = buildQueryWrapper(bo);
        Page<PaymentVoucherVo> result = paymentVoucherMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);

    }

    /**
     * 查询收款单列表
     * @param bo
     * @return
     */
    public List<PaymentVoucherVo> queryList(PaymentVoucherBo bo) {
        LambdaQueryWrapper<PaymentVoucher> lqw = buildQueryWrapper(bo);
        return paymentVoucherMapper.selectVoList(lqw);
    }

    /**
     * 根据id查询收款单
     * @param id
     * @return
     */
    public PaymentVoucherVo queryById(Long id) {
        return paymentVoucherMapper.selectVoById(id);
    }

    public void insertByBo(PaymentVoucherBo bo) {
        PaymentVoucher add = MapstructUtils.convert(bo, PaymentVoucher.class);
        paymentVoucherMapper.insert(add);
        bo.setId(add.getId());

    }
    public void updateByBo(PaymentVoucherBo bo) {
        PaymentVoucher update = MapstructUtils.convert(bo, PaymentVoucher.class);
        paymentVoucherMapper.updateById(update);
    }
    public void deleteByIds(List<Long> ids) {
        paymentVoucherMapper.deleteBatchIds(ids);
    }

    public void finish(PaymentVoucherBo bo) {
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        merchantBalanceService.doVoucher(bo, TransType.PAYMENT_VOUCHER);
    }

    private LambdaQueryWrapper<PaymentVoucher> buildQueryWrapper(PaymentVoucherBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PaymentVoucher> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getVoucherNo()), PaymentVoucher::getVoucherNo, bo.getVoucherNo());
        lqw.between(params.get("beginTransDate") != null && params.get("endTransDate") != null,
            PaymentVoucher::getTransDate ,params.get("beginTransDate"), params.get("endTransDate"));
        lqw.eq(bo.getMerchantId() != null, PaymentVoucher::getMerchantId, bo.getMerchantId());
        lqw.eq(bo.getBankAccountId() != null, PaymentVoucher::getBankAccountId, bo.getBankAccountId());
        lqw.orderByDesc(BaseEntity::getUpdateTime);
        return lqw;
    }



}

package com.ruoyi.erp.base.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseRefundBo<T extends BaseRefundDetailBo> extends BaseBillBo<T>{
    private Long tradeId;
    private Long bankAccountId;
    /**
     * 已支付退款金额
     */
    private BigDecimal paidAmount;
    private String tradeNo;



}

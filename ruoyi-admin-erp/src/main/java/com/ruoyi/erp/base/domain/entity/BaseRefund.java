package com.ruoyi.erp.base.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseRefund extends BaseBill{
    private Long tradeId;

    /**
     * 已支付退款金额
     */
    private BigDecimal paidAmount;

    private String tradeNo;

    private Long bankAccountId;
}

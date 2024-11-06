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
    private BigDecimal paidRefundAmount;

    /**
     * 已抵扣退款金额
     */
    private BigDecimal deductedRefundAmount;

    /**
     * 未付金额
     * 未付金额=实际金额-已付退款金额-已抵扣退款金额
     */
    private BigDecimal dueAmount;
}

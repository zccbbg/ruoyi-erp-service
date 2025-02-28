package com.ruoyi.erp.base.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTrade extends BaseBill{
    private Long orderId;
    private String orderNo;

    private Integer refundStatus;

    private BigDecimal refundAmount;

    /**
     * 支付金额
     */
    private BigDecimal paidAmount;
    private Long bankAccountId;
}

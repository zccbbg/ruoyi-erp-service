package com.ruoyi.erp.base.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTrade extends BaseBill{
    private Long orderId;

    private Integer paymentStatus;

    private Integer refundStatus;

    private BigDecimal refundAmount;

    private BigDecimal deductedRefundAmount;

    private BigDecimal deductedDepositAmount;
}

package com.ruoyi.erp.base.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseRefund extends BaseBill{
    private Long tradeId;

    /**
     * 已付金额
     */
    private BigDecimal paidAmount;

    /**
     * 已抵扣金额
     */
    private BigDecimal deductedAmount;

    /**
     * 未付金额
     * 未付金额=实际金额-已付金额-已抵扣金额
     */
    private BigDecimal dueAmount;
}

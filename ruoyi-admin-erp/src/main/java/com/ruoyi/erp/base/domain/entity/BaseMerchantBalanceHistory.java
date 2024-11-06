package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;

public class BaseMerchantBalanceHistory {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 往来单位id
     */
    private Long merchantId;

    private Long transId;

    /**
     * 预付金额
     */
    private BigDecimal prepaidAmount;

    /**
     * 支付金额=抵扣金额+预付金额
     */
    private BigDecimal paidAmount;

    private BigDecimal deductedAmount;

    /**
     * 预付款
     */
    private BigDecimal beforePrepayAmount;

    private BigDecimal afterPrepayAmount;

    /**
     * 剩余金额
     */
    private BigDecimal beforeDueAmount;
    private BigDecimal afterDueAmount;
}

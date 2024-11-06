package com.ruoyi.erp.balance.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;

public class MerchantBalance {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 往来单位id
     */
    private Long merchantId;

    /**
     * 预付款
     */
    private BigDecimal prepayAmount;

    /**
     * 剩余金额
     */
    private BigDecimal dueAmount;

    /**
     * 账户类型：供应商或客户
     */
    private String balanceType;
}

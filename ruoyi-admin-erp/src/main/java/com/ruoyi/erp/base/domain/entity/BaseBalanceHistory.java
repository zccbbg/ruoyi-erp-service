package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.core.domain.BaseHistoryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseBalanceHistory extends BaseHistoryEntity {
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

    private Integer transType;

    /**
     * 当前支付预付金费额
     */
    private BigDecimal paidPrepayAmount;
    /**
     * 当前支付抵扣金额
     */
    private BigDecimal paidDueAmount;
    /**
     * 当前支付带退款金额
     */
    private BigDecimal paidDueRefundAmount;

    /**
     * 支付金额=抵扣金额+预付金额-退款金额
     */
    private BigDecimal paidTotalAmount;



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

    /**
     * 剩余带退款金额
     */
    private BigDecimal beforeDueRefundAmount;
    private BigDecimal afterDueRefundAmount;
}

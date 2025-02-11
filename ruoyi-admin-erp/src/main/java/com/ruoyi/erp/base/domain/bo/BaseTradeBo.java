package com.ruoyi.erp.base.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseTradeBo<T extends BaseTradeDetailBo>  extends BaseBillBo<T> {

    private Long orderId;

    private Integer paymentStatus;

    private Integer refundStatus;

    private BigDecimal refundAmount;

    /**
     * 退款抵扣
     */
    private BigDecimal deductedRefundAmount;

    /**
     * 预付款抵扣
     */
    private BigDecimal deductedPrepayAmount;

    /**
     * 支付金额
     */
    private BigDecimal paidAmount;

    /**
     * 剩余金额=实际金额-支付金额-退款抵扣-预付款抵扣
     */
    private BigDecimal dueAmount;
}

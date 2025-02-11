package com.ruoyi.erp.base.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
public class BaseTradeVo<T extends BaseTradeDetailVo> extends BaseBillVo<T> {
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

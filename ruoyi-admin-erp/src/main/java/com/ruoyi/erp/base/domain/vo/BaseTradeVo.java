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
    private String orderNo;

    private Integer refundStatus;

    private BigDecimal refundAmount;

    /**
     * 支付金额
     */
    private BigDecimal paidAmount;
    private Long bankAccountId;
}

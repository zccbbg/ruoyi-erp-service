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
    private String orderNo;

    private Integer refundStatus;

    private BigDecimal refundAmount;

    /**
     * 支付金额
     */
    private BigDecimal paidAmount;
    private Long bankAccountId;
}

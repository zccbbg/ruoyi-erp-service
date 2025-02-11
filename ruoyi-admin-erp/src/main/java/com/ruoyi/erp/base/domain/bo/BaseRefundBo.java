package com.ruoyi.erp.base.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseRefundBo<T extends BaseRefundDetailBo> extends BaseBillBo<T>{
    private Long tradeId;

    /**
     * 已支付退款金额
     */
    private BigDecimal paidAmount;

    /**
     * 已抵扣退款金额
     */
    private BigDecimal deductedAmount;

    /**
     * 未付金额
     * 未付金额=实际金额-已付退款金额-已抵扣退款金额
     */
    private BigDecimal dueAmount;
}

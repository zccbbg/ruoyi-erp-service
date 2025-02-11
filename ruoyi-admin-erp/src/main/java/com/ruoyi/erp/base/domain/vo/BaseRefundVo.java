package com.ruoyi.erp.base.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
public class BaseRefundVo<T extends BaseRefundDetailVo> extends BaseBillVo<T> {
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

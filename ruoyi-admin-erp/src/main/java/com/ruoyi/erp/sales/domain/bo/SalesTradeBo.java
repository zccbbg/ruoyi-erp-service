package com.ruoyi.erp.sales.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseTradeBo;
import com.ruoyi.erp.sales.domain.bo.SalesTradeDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesTrade;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SalesTrade.class, reverseConvertGenerate = false)
public class SalesTradeBo<T extends SalesTradeDetailBo> extends BaseTradeBo<T> {
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

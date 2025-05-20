package com.ruoyi.erp.base.domain.vo;

import com.ruoyi.erp.base.domain.entity.BaseDoc;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseBillVo<T extends BaseBillDetailVo> extends BaseDocVo<T> {

    /**
     * 其他费用金额
     */
    private BigDecimal otherExpensesAmount;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 实际金额(货品金额+其他费用-优惠金额)
     */
    private BigDecimal actualAmount;
    /**
     * 供应商id
     */
    private Long merchantId;
    /**
     * 预付金额
     */
    private BigDecimal prepayAmount;
}

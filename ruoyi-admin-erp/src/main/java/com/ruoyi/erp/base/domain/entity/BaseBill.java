package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;


@Data
@EqualsAndHashCode(callSuper = true)
public class BaseBill extends BaseDoc {

    /**
     * 其他费用金额
     */
    @TableField(updateStrategy= FieldStrategy.ALWAYS)
    private BigDecimal otherExpensesAmount;
    /**
     * 优惠金额
     */
    @TableField(updateStrategy=FieldStrategy.ALWAYS)
    private BigDecimal discountAmount;
    /**
     * 实际金额(货品金额+其他费用-优惠金额)
     */
    private BigDecimal actualAmount;
    /**
     * 供应商id
     */
    private Long merchantId;
}

package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseBillDetail extends BaseEntity {
    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 规格id
     */
    private Long skuId;
    /**
     * 数量
     */
    private BigDecimal qty;
    /**
     * 含税价
     */
    private BigDecimal priceWithTax;
    /**
     * 不含税价
     */
    private BigDecimal priceWithoutTax;
    /**
     * 税
     */
    private BigDecimal taxAmount;
    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    private String remark;
}

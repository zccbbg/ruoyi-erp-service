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
     * 入库数量
     */
    private BigDecimal quantity;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 含税交个
     */
    private BigDecimal taxPrice;

    /**
     * 税
     */
    private BigDecimal tax;
    /**
     * 税率
     */
    private BigDecimal taxRate;

    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 含税金额
     */
    private BigDecimal taxAmount;
    /**
     * 备注
     */
    private String remark;
}

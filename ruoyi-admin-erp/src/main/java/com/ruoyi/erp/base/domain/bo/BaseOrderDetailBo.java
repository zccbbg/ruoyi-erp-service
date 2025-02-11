package com.ruoyi.erp.base.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseOrderDetailBo extends BaseEntity {
    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 父id
     */
    @NotNull(message = "父id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long pid;

    /**
     * sku id
     */
    @NotNull(message = "sku id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal qty;

    /**
     * 已处理数量
     */
    @NotNull(message = "已处理数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal processedQty;

    /**
     * 不含税价
     */
    @NotNull(message = "不含税价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal priceWithoutTax;

    /**
     * 税费
     */
    @NotNull(message = "税费不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal taxAmount;

    /**
     * 税率
     */
    @NotNull(message = "税率不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal taxRate;

    /**
     * 含税价
     */
    @NotNull(message = "含税价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal priceWithTax;

    /**
     * 总金额
     */
    @NotNull(message = "总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;
}

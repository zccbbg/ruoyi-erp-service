package com.ruoyi.erp.base.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDocDetailBo extends BaseEntity {
    /**
     * 入库单号
     */
    @NotNull(message = "父id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long pid;
    /**
     *
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 规格id
     */
    @NotNull(message = "规格id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 入库数量
     */
    @NotNull(message = "出入库数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal qty;

    /**
     * 金额
     */
    private BigDecimal totalAmount;
    /**
     * 备注
     */
    private String remark;

    /**
     * 所属仓库
     */
    private Long warehouseId;

    /**
     * 更新前数量
     */
    private BigDecimal beforeQty;
    /**
     * 更新后数量
     */
    private BigDecimal afterQty;
}

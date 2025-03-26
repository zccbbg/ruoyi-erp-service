package com.ruoyi.erp.warehouse.domain.query;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseHistoryEntity;
import com.ruoyi.erp.warehouse.domain.entity.InventoryHistory;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 库存记录业务对象 wms_inventory_history
 *
 * @author zcc
 * @date 2024-07-22
 */

@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = InventoryHistory.class, reverseConvertGenerate = false)
@Data
public class InventoryHistoryQuery extends BaseHistoryEntity {

    /**
     *
     */
    private Long id;

    /**
     * 操作id（出库、入库、库存移动表单id）
     */
    private Long optId;

    /**
     * 操作单号（入库、出库、移库、盘库单号）
     */
    @NotNull(message = "操作单号（入库、出库、移库、盘库单号）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String optNo;

    /**
     * 汇总类型
     */
    private Integer summaryType;

    /**
     * 操作类型
     */
    private Integer optType;

    /**
     * 物料ID
     */
    @NotNull(message = "物料ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 库存变化
     */
    @NotNull(message = "库存变化不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal qty;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 所属仓库
     */
    @NotNull(message = "所属仓库不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long warehouseId;

    private String goodsName;
    private String goodsNo;
    private String skuName;
    private String skuNo;

    private String startTime;
    private String endTime;

}

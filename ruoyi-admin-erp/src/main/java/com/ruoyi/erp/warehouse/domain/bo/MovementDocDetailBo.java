package com.ruoyi.warehouse.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseDocDetailBo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.warehouse.domain.entity.Inventory;
import com.ruoyi.warehouse.domain.entity.MovementDocDetail;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库存移动详情业务对象 wms_movement_order_detail
 *
 * @author zcc
 * @date 2024-08-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMappers({
    @AutoMapper(target = MovementDocDetail.class, reverseConvertGenerate = false),
    @AutoMapper(target = Inventory.class, reverseConvertGenerate = false)
})
public class MovementDocDetailBo extends BaseDocDetailBo {

    /**
     * 源仓库
     */
    @NotNull(message = "源仓库不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sourceWarehouseId;

    /**
     * 目标仓库
     */
    @NotNull(message = "目标仓库不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long targetWarehouseId;

}

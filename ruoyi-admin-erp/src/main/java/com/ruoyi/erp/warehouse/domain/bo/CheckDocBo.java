package com.ruoyi.erp.warehouse.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseDocBo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.erp.warehouse.domain.entity.CheckDoc;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库存盘点单据业务对象 wms_check_order
 *
 * @author zcc
 * @date 2024-08-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = CheckDoc.class, reverseConvertGenerate = false)
public class CheckDocBo extends BaseDocBo<CheckDocDetailBo> {
    /**
     * 仓库id
     */
    @NotNull(message = "仓库不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long warehouseId;
}

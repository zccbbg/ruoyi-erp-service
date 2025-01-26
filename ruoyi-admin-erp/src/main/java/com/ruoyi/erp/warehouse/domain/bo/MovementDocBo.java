package com.ruoyi.erp.warehouse.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseDocBo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.erp.warehouse.domain.entity.MovementDoc;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 移库单业务对象 wms_movement_order
 *
 * @author zcc
 * @date 2024-08-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MovementDoc.class, reverseConvertGenerate = false)
public class MovementDocBo extends BaseDocBo<MovementDocDetailBo> {

}

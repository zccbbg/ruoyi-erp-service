package com.ruoyi.erp.warehouse.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseDocBo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.erp.warehouse.domain.entity.OtherShipmentDoc;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出库单业务对象 wms_shipment_order
 *
 * @author zcc
 * @date 2024-08-01
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = OtherShipmentDoc.class, reverseConvertGenerate = false)
public class OtherShipmentDocBo extends BaseDocBo<OtherShipmentDocDetailBo> {

}

package com.ruoyi.erp.warehouse.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseDocDetailBo;
import com.ruoyi.erp.warehouse.domain.entity.OtherShipmentDocDetail;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出库单详情业务对象 wms_shipment_order_detail
 *
 * @author zcc
 * @date 2024-08-01
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMappers({
    @AutoMapper(target = OtherShipmentDocDetail.class, reverseConvertGenerate = false),
    @AutoMapper(target = InventoryBo.class, reverseConvertGenerate = false)
})
public class OtherShipmentDocDetailBo extends BaseDocDetailBo {

}

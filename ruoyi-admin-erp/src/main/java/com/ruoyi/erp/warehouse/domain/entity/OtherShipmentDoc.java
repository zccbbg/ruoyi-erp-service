package com.ruoyi.erp.warehouse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseDoc;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出库单对象 wms_shipment_order
 *
 * @author zcc
 * @date 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_shipment_doc")
public class OtherShipmentDoc extends BaseDoc {

}

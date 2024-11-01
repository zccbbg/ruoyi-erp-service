package com.ruoyi.erp.warehouse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseDocDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出库单详情对象 wms_shipment_order_detail
 *
 * @author zcc
 * @date 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_shipment_doc_detail")
public class OtherShipmentDocDetail extends BaseDocDetail {

    /**
     * 所属仓库
     */
    private Long warehouseId;
}

package com.ruoyi.warehouse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.base.domain.entity.BaseDocDetail;
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
@TableName("wms_shipment_order_detail")
public class OtherShipmentDocDetail extends BaseDocDetail {

    /**
     * 所属仓库
     */
    private Long warehouseId;
}

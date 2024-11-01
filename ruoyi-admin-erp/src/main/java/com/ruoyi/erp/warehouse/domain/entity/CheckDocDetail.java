package com.ruoyi.erp.warehouse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseDocDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 库存盘点单据详情对象 wms_check_order_detail
 *
 * @author zcc
 * @date 2024-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_check_doc_detail")
public class CheckDocDetail extends BaseDocDetail {

    /**
     * 盘点数量
     */
    private BigDecimal checkQuantity;
    /**
     * 所属仓库
     */
    private Long warehouseId;

    /**
     * 库存id
     */
    private Long inventoryId;
}

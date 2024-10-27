package com.ruoyi.warehouse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.base.domain.entity.BaseDoc;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 库存盘点单据对象 wms_check_order
 *
 * @author zcc
 * @date 2024-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_check_order")
public class CheckDoc extends BaseDoc {

    /**
     * 仓库id
     */
    private Long warehouseId;

}

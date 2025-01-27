package com.ruoyi.erp.warehouse.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseDoc;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 移库单对象 wms_movement_order
 *
 * @author zcc
 * @date 2024-08-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_movement_doc")
public class MovementDoc extends BaseDoc {

    @Serial
    private static final long serialVersionUID=1L;

    /**
     * 目标仓库
     */
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long targetWarehouseId;

}

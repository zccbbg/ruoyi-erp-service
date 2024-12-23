package com.ruoyi.erp.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("basic_warehouse")
public class Warehouse extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 编号
     */
    private String warehouseNo;

    /**
     * 名称
     */
    private String warehouseName;
    /**
     * 排序
     */
    private Long orderNum;

    /**
     * 备注
     */
    private String remark;


}

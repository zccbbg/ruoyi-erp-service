package com.ruoyi.erp.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("basic_goods")
public class Goods extends BaseEntity {

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
    private String goodsNo;

    /**
     * 名称
     */
    private String goodsName;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 单位类别
     */
    private String unit;

    /**
     * 品牌
     */
    private Long brandId;

    /**
     * 备注
     */
    private String remark;


}

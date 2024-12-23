package com.ruoyi.erp.basic.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.erp.basic.domain.entity.Goods;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Goods.class)
public class GoodsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 编号
     */
    @ExcelProperty(value = "编号")
    private String goodsNo;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String goodsName;

    /**
     * 单位类别
     */
    @ExcelProperty(value = "单位类别")
    private String unit;

    /**
     * 品牌
     */
    @ExcelProperty(value = "品牌")
    private Long brandId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    private Long categoryId;

    /**
     * 类别
     */
    private CategoryVo category;
}

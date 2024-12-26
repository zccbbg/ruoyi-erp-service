package com.ruoyi.erp.basic.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import com.ruoyi.erp.basic.domain.entity.Category;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Category.class)
public class CategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 物料类型id
     */
    @ExcelProperty(value = "物料类型id")
    private Long id;

    /**
     * 父物料类型id
     */
    @ExcelProperty(value = "父物料类型id")
    private Long parentId;

    /**
     * 祖级列表
     */
    @ExcelProperty(value = "祖级列表")
    private String ancestors;

    /**
     * 物料类型名称
     */
    @ExcelProperty(value = "物料类型名称")
    private String categoryName;

    /**
     * 显示顺序
     */
    @ExcelProperty(value = "显示顺序")
    private Long orderNum;

    /**
     * 子物料类型
     */
    @TableField(exist = false)
    private List<CategoryVo> children = new ArrayList<CategoryVo>();

}

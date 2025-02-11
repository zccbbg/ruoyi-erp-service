package com.ruoyi.erp.base.domain.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDoc extends BaseEntity {

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 编号
     */
    private String docNo;
    /**
     * 单据日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime docDate;

    /**
     * 商品总数
     */
    private BigDecimal goodsQty;
    /**
     * 单据总金额
     */
    private BigDecimal goodsAmount;
    /**
     * 审核状态
     */
    private Integer checkedStatus;
    /**
     * 审核人
     */
    private String checkedBy;

    /**
     * 备注
     */
    private String remark;
    /**
     * 仓库id
     */
    @ExcelProperty(value = "仓库id")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Long warehouseId;

}

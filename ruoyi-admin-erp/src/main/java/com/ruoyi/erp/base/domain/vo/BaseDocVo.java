package com.ruoyi.erp.base.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.mybatis.core.domain.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
public class BaseDocVo<T extends BaseDocDetailVo> extends BaseVo {
    /**
     * id
     */
    private Long id;

    /**
     * 单据编号
     */
    @ExcelProperty(value = "单据编号")
    private String docNo;

    /**
     * 单据日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate docDate;

    /**
     * 商品总数
     */
    @ExcelProperty(value = "商品总数")
    private BigDecimal goodsQty;

    /**
     * 订单金额
     */
    @ExcelProperty(value = "单据总金额")
    private BigDecimal goodsAmount;

    /**
     * 入库状态
     */
    @ExcelProperty(value = "审核状态")
    private Integer checkedStatus;

    /**
     * 仓库id
     */
    @ExcelProperty(value = "仓库id")
    private Long warehouseId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    private List<T> details;
}

package com.ruoyi.erp.purchase.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.erp.purchase.domain.entity.PurchaseOrder;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;

/**
 * 采购订单视图对象 purchase_order
 *
 * @author zcc
 * @date 2025-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PurchaseOrder.class)
public class PurchaseOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 单据编号
     */
    @ExcelProperty(value = "单据编号")
    private String billNo;

    /**
     * 单据日期
     */
    @ExcelProperty(value = "单据日期")
    private Date billDate;

    /**
     * 交货日期
     */
    @ExcelProperty(value = "交货日期")
    private Date deliveryDate;

    /**
     * 审核状态
     */
    @ExcelProperty(value = "审核状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "doc_checked_status")
    private Integer checkedStatus;

    /**
     * 审核人
     */
    @ExcelProperty(value = "审核人")
    private String checkedBy;

    /**
     * 库存状态
     */
    @ExcelProperty(value = "库存状态")
    private Integer stockStatus;

    /**
     * 供应商id
     */
    @ExcelProperty(value = "供应商id")
    private Long merchantId;

    /**
     * 商品数量
     */
    @ExcelProperty(value = "商品数量")
    private BigDecimal goodsQty;

    /**
     * 已处理数量
     */
    @ExcelProperty(value = "已处理数量")
    private BigDecimal processedQty;

    /**
     * 商品金额
     */
    @ExcelProperty(value = "商品金额")
    private BigDecimal goodsAmount;

    /**
     * 其他费用
     */
    @ExcelProperty(value = "其他费用")
    private BigDecimal otherExpensesAmount;

    /**
     * 优惠金额
     */
    @ExcelProperty(value = "优惠金额")
    private BigDecimal discountAmount;

    /**
     * 实际金额
     */
    @ExcelProperty(value = "实际金额")
    private BigDecimal actualAmount;

    /**
     * 预付金额
     */
    @ExcelProperty(value = "预付金额")
    private BigDecimal prepayAmount;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}

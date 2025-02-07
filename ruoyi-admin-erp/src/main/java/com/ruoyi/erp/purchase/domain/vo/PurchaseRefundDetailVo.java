package com.ruoyi.erp.purchase.domain.vo;

import java.math.BigDecimal;
import com.ruoyi.erp.purchase.domain.entity.PurchaseRefundDetail;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;

/**
 * 采购退货单明细视图对象 purchase_refund_detail
 *
 * @author zcc
 * @date 2025-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PurchaseRefundDetail.class)
public class PurchaseRefundDetailVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 父id
     */
    @ExcelProperty(value = "父id")
    private Long pid;

    /**
     * sku id
     */
    @ExcelProperty(value = "sku id")
    private Long skuId;

    /**
     * 商品数量
     */
    @ExcelProperty(value = "商品数量")
    private BigDecimal qty;

    /**
     * 不含税价
     */
    @ExcelProperty(value = "不含税价")
    private BigDecimal priceWithoutTax;

    /**
     * 税费
     */
    @ExcelProperty(value = "税费")
    private BigDecimal taxAmount;

    /**
     * 税率
     */
    @ExcelProperty(value = "税率")
    private BigDecimal taxRate;

    /**
     * 含税价
     */
    @ExcelProperty(value = "含税价")
    private BigDecimal priceWithTax;

    /**
     * 总金额
     */
    @ExcelProperty(value = "总金额")
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}

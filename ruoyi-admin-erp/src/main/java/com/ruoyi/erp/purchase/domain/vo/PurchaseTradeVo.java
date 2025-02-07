package com.ruoyi.erp.purchase.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTrade;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;

/**
 * 采购入库单视图对象 purchase_trade
 *
 * @author zcc
 * @date 2025-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PurchaseTrade.class)
public class PurchaseTradeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 订单id
     */
    @ExcelProperty(value = "订单id")
    private Long orderId;

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
     * 审核状态
     */
    @ExcelProperty(value = "审核状态")
    private Integer checkedStatus;

    /**
     * 支付状态
     */
    @ExcelProperty(value = "支付状态")
    private Integer paymentStatus;

    /**
     * 退货状态
     */
    @ExcelProperty(value = "退货状态")
    private Integer refundStatus;

    /**
     * 退货金额
     */
    @ExcelProperty(value = "退货金额")
    private BigDecimal refundAmount;

    /**
     * 退货抵扣
     */
    @ExcelProperty(value = "退货抵扣")
    private BigDecimal deductedRefundAmount;

    /**
     * 预付款抵扣
     */
    @ExcelProperty(value = "预付款抵扣")
    private BigDecimal deductedPrepayAmount;

    /**
     * 支付金额
     */
    @ExcelProperty(value = "支付金额")
    private BigDecimal paidAmount;

    /**
     * 剩余金额
     */
    @ExcelProperty(value = "剩余金额")
    private BigDecimal dueAmount;

    /**
     * 审核人
     */
    @ExcelProperty(value = "审核人")
    private String checkedBy;

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
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}

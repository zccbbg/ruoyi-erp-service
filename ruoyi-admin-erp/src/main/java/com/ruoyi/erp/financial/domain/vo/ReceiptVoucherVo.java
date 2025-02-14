package com.ruoyi.erp.financial.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.erp.financial.domain.entity.ReceiptVoucher;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;

/**
 * 收款单视图对象 financial_receipt_voucher
 *
 * @author zcc
 * @date 2025-02-14
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ReceiptVoucher.class)
public class ReceiptVoucherVo implements Serializable {

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
    private String voucherNo;

    /**
     * 收款日期
     */
    @ExcelProperty(value = "收款日期")
    private Date transDate;

    /**
     * 往来单位id
     */
    @ExcelProperty(value = "往来单位id")
    private Long merchantId;

    /**
     * 银行账户id
     */
    @ExcelProperty(value = "银行账户id")
    private Long bankAccountId;

    /**
     * 支付金额
     */
    @ExcelProperty(value = "支付金额")
    private BigDecimal paidAmount;

    /**
     * 优惠金额
     */
    @ExcelProperty(value = "优惠金额")
    private BigDecimal discountAmount;

    /**
     * 余额变动
     */
    @ExcelProperty(value = "余额变动")
    private BigDecimal balanceChange;

    /**
     * 交易前余额
     */
    @ExcelProperty(value = "交易前余额")
    private BigDecimal beforeBalance;

    /**
     * 交易后余额
     */
    @ExcelProperty(value = "交易后余额")
    private BigDecimal afterBalance;

    /**
     * 审核状态
     */
    @ExcelProperty(value = "审核状态")
    private Integer checkedStatus;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}

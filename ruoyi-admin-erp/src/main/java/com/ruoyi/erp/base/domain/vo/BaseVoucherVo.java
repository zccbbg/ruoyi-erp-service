package com.ruoyi.erp.base.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@ExcelIgnoreUnannotated
public class BaseVoucherVo implements Serializable {
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
    private LocalDate transDate;

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
    @ExcelProperty(value = "总金额")
    private BigDecimal totalAmount;


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

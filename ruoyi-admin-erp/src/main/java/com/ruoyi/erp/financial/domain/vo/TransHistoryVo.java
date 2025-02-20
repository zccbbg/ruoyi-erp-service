package com.ruoyi.erp.financial.domain.vo;

import java.math.BigDecimal;
import com.ruoyi.erp.financial.domain.entity.TransHistory;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 交易流水视图对象 financial_trans_history
 *
 * @author zcc
 * @date 2025-02-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = TransHistory.class)
public class TransHistoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 商家id
     */
    @ExcelProperty(value = "商家id")
    private Long merchantId;

    /**
     * 银行账户id
     */
    @ExcelProperty(value = "银行账户id")
    private Long bankAccountId;

    /**
     * 交易类型
     */
    @ExcelProperty(value = "交易类型")
    private String transType;

    /**
     * 关联业务id
     */
    @ExcelProperty(value = "关联业务id")
    private Long relatedId;

    /**
     * 关联业务编号
     */
    @ExcelProperty(value = "关联业务编号")
    private String relatedNo;

    /**
     * 总金额
     */
    @ExcelProperty(value = "总金额")
    private BigDecimal totalAmount;

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
     * 支付金额
     */
    @ExcelProperty(value = "支付金额")
    private BigDecimal paidAmount;

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
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private LocalDateTime createTime;


}

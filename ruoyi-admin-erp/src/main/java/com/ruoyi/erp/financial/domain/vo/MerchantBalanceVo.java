package com.ruoyi.erp.financial.domain.vo;

import java.math.BigDecimal;
import com.ruoyi.erp.financial.domain.entity.MerchantBalance;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;

/**
 * 商家余额视图对象 financial_merchant_balance
 *
 * @author zcc
 * @date 2025-02-19
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MerchantBalance.class)
public class MerchantBalanceVo implements Serializable {

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
     * 初始余额
     */
    @ExcelProperty(value = "初始余额")
    private BigDecimal initialBalance;

    /**
     * 当前余额
     */
    @ExcelProperty(value = "当前余额")
    private BigDecimal balance;


}

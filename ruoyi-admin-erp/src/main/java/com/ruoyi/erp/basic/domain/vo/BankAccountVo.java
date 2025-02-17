package com.ruoyi.erp.basic.domain.vo;

import com.ruoyi.erp.basic.domain.entity.BankAccount;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;

/**
 * 银行账户视图对象 basic_bank_account
 *
 * @author zcc
 * @date 2025-02-13
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = BankAccount.class)
public class BankAccountVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 账户编号
     */
    @ExcelProperty(value = "账户编号")
    private String accountNo;

    /**
     * 账户名称
     */
    @ExcelProperty(value = "账户名称")
    private String accountName;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}

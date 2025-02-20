package com.ruoyi.erp.financial.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.erp.base.domain.vo.BaseVoucherVo;
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
public class ReceiptVoucherVo extends BaseVoucherVo {

}

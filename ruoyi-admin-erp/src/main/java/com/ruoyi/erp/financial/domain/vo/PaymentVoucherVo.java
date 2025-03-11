package com.ruoyi.erp.financial.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.erp.base.domain.vo.BaseVoucherVo;

import com.ruoyi.erp.financial.domain.entity.PaymentVoucher;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PaymentVoucher.class)
public class PaymentVoucherVo extends BaseVoucherVo {
}

package com.ruoyi.erp.financial.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseVoucherBo;

import com.ruoyi.erp.financial.domain.entity.PaymentVoucher;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PaymentVoucher.class, reverseConvertGenerate = false)
public class PaymentVoucherBo extends BaseVoucherBo {

}

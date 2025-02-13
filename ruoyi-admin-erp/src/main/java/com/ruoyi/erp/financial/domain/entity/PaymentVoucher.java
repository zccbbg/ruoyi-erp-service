package com.ruoyi.erp.financial.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseVoucher;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("financial_payment_voucher")
public class PaymentVoucher extends BaseVoucher {
}

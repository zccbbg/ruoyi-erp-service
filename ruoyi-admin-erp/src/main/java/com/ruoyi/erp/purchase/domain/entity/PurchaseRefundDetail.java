package com.ruoyi.erp.purchase.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseRefund;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("purchase_refund_detail")
public class PurchaseRefundDetail extends BaseRefund {
}

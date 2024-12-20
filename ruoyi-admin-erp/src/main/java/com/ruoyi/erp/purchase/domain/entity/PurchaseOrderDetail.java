package com.ruoyi.erp.purchase.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseOrder;
import com.ruoyi.erp.base.domain.entity.BaseOrderDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("purchase_order_detail")
public class PurchaseOrderDetail extends BaseOrderDetail {
}

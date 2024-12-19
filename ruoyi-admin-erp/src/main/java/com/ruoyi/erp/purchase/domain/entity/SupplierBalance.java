package com.ruoyi.erp.purchase.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseBalance;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("purchase_supplier_balance")
public class SupplierBalance extends BaseBalance {
}

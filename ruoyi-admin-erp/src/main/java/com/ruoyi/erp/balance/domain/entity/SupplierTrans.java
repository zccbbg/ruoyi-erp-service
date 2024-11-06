package com.ruoyi.erp.balance.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseMerchantTrans;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bal_supplier_trans")
public class SupplierTrans extends BaseMerchantTrans {
}

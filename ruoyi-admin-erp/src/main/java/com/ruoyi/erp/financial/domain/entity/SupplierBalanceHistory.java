package com.ruoyi.erp.financial.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseBalanceHistory;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("purchase_supplier_balance_history")
public class SupplierBalanceHistory extends BaseBalanceHistory {
}

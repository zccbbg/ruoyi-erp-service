package com.ruoyi.erp.sales.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseMerchantBalance;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sales_customer_balance")
public class CustomerBalance extends BaseMerchantBalance {
}

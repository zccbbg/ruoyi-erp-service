package com.ruoyi.erp.balance.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseMerchantBalance;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bal_customer_balance")
public class CustomerBalance extends BaseMerchantBalance {
}

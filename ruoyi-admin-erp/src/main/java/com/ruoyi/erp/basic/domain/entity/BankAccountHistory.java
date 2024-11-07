package com.ruoyi.erp.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.mybatis.core.domain.BaseHistoryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("basic_bank_account_history")
public class BankAccountHistory extends BaseHistoryEntity {
}

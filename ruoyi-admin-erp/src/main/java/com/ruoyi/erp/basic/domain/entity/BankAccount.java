package com.ruoyi.erp.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("basic_bank_account")
public class BankAccount extends BaseEntity {
    private Long id;
    private String accountNo;
    private String accountName;
    private String remark;
}

package com.ruoyi.erp.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("basic_bank_account")
public class BankAccount extends BaseEntity {
    @Serial
    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 账户编号
     */
    private String accountNo;
    /**
     * 账户名称
     */
    private String accountName;
    /**
     * 备注
     */
    private String remark;
}

package com.ruoyi.erp.basic.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;


import java.io.Serial;

/**
 * 银行账户对象 basic_bank_account
 *
 * @author zcc
 * @date 2025-02-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("basic_bank_account")
public class BasicBankAccount extends BaseEntity {

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

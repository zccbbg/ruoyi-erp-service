package com.ruoyi.erp.basic.domain.bo;

import com.ruoyi.erp.basic.domain.entity.BankAccount;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import io.github.linpeilie.annotations.AutoMapper;


/**
 * 银行账户业务对象 basic_bank_account
 *
 * @author zcc
 * @date 2025-02-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = BankAccount.class, reverseConvertGenerate = false)
public class BankAccountBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 账户编号
     */
    @NotBlank(message = "账户编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String accountNo;

    /**
     * 账户名称
     */
    @NotBlank(message = "账户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String accountName;

    /**
     * 备注
     */
    private String remark;


}

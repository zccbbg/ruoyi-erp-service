package com.ruoyi.erp.financial.domain.bo;

import com.ruoyi.erp.financial.domain.entity.MerchantBalance;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import io.github.linpeilie.annotations.AutoMapper;

import java.math.BigDecimal;

/**
 * 商家余额业务对象 financial_merchant_balance
 *
 * @author zcc
 * @date 2025-02-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MerchantBalance.class, reverseConvertGenerate = false)
public class MerchantBalanceBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商家id
     */
    @NotNull(message = "商家id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long merchantId;

    /**
     * 初始余额
     */
    @NotNull(message = "初始余额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal initialBalance;

    /**
     * 当前余额
     */
    @NotNull(message = "当前余额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal balance;


}

package com.ruoyi.erp.financial.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class MerchantBalance extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 往来单位id
     */
    private Long merchantId;

    /**
     * 账户余额
     */
    private BigDecimal balance;
    /**
     * 期初余额
     */
    private BigDecimal initialBalance;
}

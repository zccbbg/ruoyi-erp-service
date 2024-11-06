package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseMerchantBalance extends BaseEntity {
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
     * 预付款
     */
    private BigDecimal prepayAmount;

    /**
     * 剩余金额
     */
    private BigDecimal dueAmount;
}

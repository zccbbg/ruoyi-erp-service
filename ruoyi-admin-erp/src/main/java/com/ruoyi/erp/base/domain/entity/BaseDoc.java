package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDoc extends BaseEntity {

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 编号
     */
    private String code;

    /**
     * 商品总数
     */
    private BigDecimal totalQuantity;
    /**
     * 单据总金额
     */
    private BigDecimal totalAmount;
    /**
     * 审核状态
     */
    private Integer checkedStatus;

    /**
     * 备注
     */
    private String remark;

}

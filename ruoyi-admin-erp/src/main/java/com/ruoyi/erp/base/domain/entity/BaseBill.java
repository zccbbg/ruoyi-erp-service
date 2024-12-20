package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseBill extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 单据编号
     */
    private String billNo;
    /**
     * 单据日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime billDate;
    /**
     * 审核状态
     */
    private Integer checkedStatus;
    /**
     * 审核人
     */
    private String checkedBy;
    /**
     * 往来单位id
     */
    private Long merchantId;
    /**
     * 商品数量
     */
    private BigDecimal goodsQuantity;

    /**
     * 商品金额（未优惠）
     */
    private BigDecimal goodsAmount;

    /**
     * 其他费用金额
     */
    private BigDecimal otherExpensesAmount;
    /**
     * 备注
     */
    private String remark;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 实际金额(货品金额+其他费用-优惠金额)
     */
    private BigDecimal actualAmount;
}

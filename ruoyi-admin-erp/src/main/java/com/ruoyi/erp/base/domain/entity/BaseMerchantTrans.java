package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseMerchantTrans extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 单据编号
     */
    private String transCode;
    /**
     * 审核状态
     */
    private Integer checkedStatus;
    /**
     * 审核人
     */
    private String checkName;

    /**
     * 单据日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime billDate;
    /**
     * 往来单位id
     */
    private Long merchantId;

    private Long bankAccountId;

    private String remark;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 退款抵扣
     */
    private BigDecimal deductedRefundAmount;

    /**
     * 预付款抵扣
     */
    private BigDecimal deductedPrepayAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 实际金额
     * 实际金额= 总金额-优惠金额-退款抵扣-预付款抵扣
     */
    private BigDecimal actualAmount;

    /**
     * 交易类型
     */
    private String transType;

    /**
     * 预付金额
     */
    private BigDecimal prepayAmount;

    /**
     * 支付金额=实际金额+预付金额
     */
    private BigDecimal paidAmount;

}

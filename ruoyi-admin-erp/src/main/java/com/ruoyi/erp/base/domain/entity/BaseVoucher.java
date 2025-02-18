package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseVoucher extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 编号
     */
    private String voucherNo;
    /**
     * 付款或收款日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transDate;
    /**
     * 往来单位id
     */
    private Long merchantId;

    private Long bankAccountId;
    /**
     * 支付金额
     */
    private BigDecimal paidAmount;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 总金额=支付金额+优惠金额
     */
    private BigDecimal totalAmount;
    /**
     * 审核状态
     */
    private Integer checkedStatus;
    /**
     * 审核人
     */
    private String checkedBy;

}

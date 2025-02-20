package com.ruoyi.erp.base.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseVoucherBo extends BaseEntity {
    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String voucherNo;

    /**
     * 收款日期
     */
    @NotNull(message = "单据日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private LocalDate transDate;

    /**
     * 往来单位id
     */
    @NotNull(message = "往来单位不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long merchantId;

    /**
     * 银行账户id
     */
    @NotNull(message = "银行账户不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bankAccountId;

    /**
     * 支付金额
     */
    @NotNull(message = "支付金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal paidAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 总金额
     */
    @NotNull(message = "总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal totalAmount;

    /**
     * 审核状态
     */
    private Integer checkedStatus;

    /**
     * 审核人
     */
    private String checkedBy;

    /**
     * 备注
     */
    private String remark;
}

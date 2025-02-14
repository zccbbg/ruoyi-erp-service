package com.ruoyi.erp.financial.domain.bo;

import com.ruoyi.erp.financial.domain.entity.ReceiptVoucher;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import io.github.linpeilie.annotations.AutoMapper;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 收款单业务对象 financial_receipt_voucher
 *
 * @author zcc
 * @date 2025-02-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ReceiptVoucher.class, reverseConvertGenerate = false)
public class ReceiptVoucherBo extends BaseEntity {

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
    @NotNull(message = "收款日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date transDate;

    /**
     * 往来单位id
     */
    @NotNull(message = "往来单位id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long merchantId;

    /**
     * 银行账户id
     */
    @NotNull(message = "银行账户id不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 余额变动
     */
    private BigDecimal balanceChange;

    /**
     * 交易前余额
     */
    private BigDecimal beforeBalance;

    /**
     * 交易后余额
     */
    private BigDecimal afterBalance;

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
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}

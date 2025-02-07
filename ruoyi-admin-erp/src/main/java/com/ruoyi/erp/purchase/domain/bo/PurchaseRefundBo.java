package com.ruoyi.erp.purchase.domain.bo;

import com.ruoyi.erp.purchase.domain.entity.PurchaseRefund;
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
 * 采购退货单业务对象 purchase_refund
 *
 * @author zcc
 * @date 2025-02-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PurchaseRefund.class, reverseConvertGenerate = false)
public class PurchaseRefundBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * trade id
     */
    @NotNull(message = "trade id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long tradeId;

    /**
     * 单据编号
     */
    @NotBlank(message = "单据编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String billNo;

    /**
     * 单据日期
     */
    @NotNull(message = "单据日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date billDate;

    /**
     * 审核状态
     */
    @NotNull(message = "审核状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer checkedStatus;

    /**
     * 审核人
     */
    @NotBlank(message = "审核人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String checkedBy;

    /**
     * 供应商id
     */
    @NotNull(message = "供应商id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long merchantId;

    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal goodsQty;

    /**
     * 商品金额
     */
    @NotNull(message = "商品金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal goodsAmount;

    /**
     * 其他费用
     */
    @NotNull(message = "其他费用不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal otherExpensesAmount;

    /**
     * 优惠金额
     */
    @NotNull(message = "优惠金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal discountAmount;

    /**
     * 实际金额
     */
    @NotNull(message = "实际金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal actualAmount;

    /**
     * 已支付退款金额
     */
    @NotNull(message = "已支付退款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal paidAmount;

    /**
     * 已抵扣退款金额
     */
    @NotNull(message = "已抵扣退款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal deductedAmount;

    /**
     * 未付金额
     */
    @NotNull(message = "未付金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal dueAmount;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}

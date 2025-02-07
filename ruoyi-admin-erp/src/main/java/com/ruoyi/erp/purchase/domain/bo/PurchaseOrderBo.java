package com.ruoyi.erp.purchase.domain.bo;

import com.ruoyi.erp.purchase.domain.entity.PurchaseOrder;
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
 * 采购订单业务对象 purchase_order
 *
 * @author zcc
 * @date 2025-02-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PurchaseOrder.class, reverseConvertGenerate = false)
public class PurchaseOrderBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

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
     * 交货日期
     */
    @NotNull(message = "交货日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date deliveryDate;

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
     * 库存状态
     */
    @NotNull(message = "库存状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer stockStatus;

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
     * 已处理数量
     */
    @NotNull(message = "已处理数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal processedQty;

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
     * 预付金额
     */
    @NotNull(message = "预付金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal prepayAmount;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}

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
    private Date billDate;

    /**
     * 交货日期
     */
    private Date deliveryDate;

    /**
     * 审核状态
     */
    private Integer checkedStatus;

    /**
     * 审核人
     */
    private String checkedBy;

    /**
     * 库存状态
     */
    private Integer stockStatus;

    /**
     * 供应商id
     */
    private Long merchantId;

    /**
     * 商品数量
     */
    private BigDecimal goodsQty;

    /**
     * 已处理数量
     */
    private BigDecimal processedQty;

    /**
     * 商品金额
     */
    private BigDecimal goodsAmount;

    /**
     * 其他费用
     */
    private BigDecimal otherExpensesAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 实际金额
     */
    private BigDecimal actualAmount;

    /**
     * 预付金额
     */
    private BigDecimal prepayAmount;

    /**
     * 备注
     */
    private String remark;


}

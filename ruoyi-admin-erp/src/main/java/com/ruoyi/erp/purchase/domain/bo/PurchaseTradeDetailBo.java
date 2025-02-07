package com.ruoyi.erp.purchase.domain.bo;

import com.ruoyi.erp.purchase.domain.entity.PurchaseTradeDetail;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import io.github.linpeilie.annotations.AutoMapper;

import java.math.BigDecimal;

/**
 * 采购入库单明细业务对象 purchase_trade_detail
 *
 * @author zcc
 * @date 2025-02-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PurchaseTradeDetail.class, reverseConvertGenerate = false)
public class PurchaseTradeDetailBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 父id
     */
    @NotNull(message = "父id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long pid;

    /**
     * sku id
     */
    @NotNull(message = "sku id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long skuId;

    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal qty;

    /**
     * 不含税价
     */
    @NotNull(message = "不含税价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal priceWithoutTax;

    /**
     * 税费
     */
    @NotNull(message = "税费不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal taxAmount;

    /**
     * 税率
     */
    @NotNull(message = "税率不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal taxRate;

    /**
     * 含税价
     */
    @NotNull(message = "含税价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal priceWithTax;

    /**
     * 总金额
     */
    @NotNull(message = "总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}

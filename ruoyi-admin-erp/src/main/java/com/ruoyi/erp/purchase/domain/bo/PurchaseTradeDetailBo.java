package com.ruoyi.erp.purchase.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseTradeDetailBo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTradeDetail;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.erp.warehouse.domain.entity.Inventory;
import com.ruoyi.erp.warehouse.domain.entity.OtherReceiptDocDetail;
import io.github.linpeilie.annotations.AutoMappers;
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
@AutoMappers({
    @AutoMapper(target = PurchaseTradeDetail.class, reverseConvertGenerate = false),
    @AutoMapper(target = Inventory.class, reverseConvertGenerate = false)
})
public class PurchaseTradeDetailBo extends BaseTradeDetailBo
{

}

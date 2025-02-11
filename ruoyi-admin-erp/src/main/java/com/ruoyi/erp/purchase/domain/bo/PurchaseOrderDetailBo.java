package com.ruoyi.erp.purchase.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseOrderDetailBo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseOrderDetail;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import io.github.linpeilie.annotations.AutoMapper;

import java.math.BigDecimal;

/**
 * 采购订单明细业务对象 purchase_order_detail
 *
 * @author zcc
 * @date 2025-02-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PurchaseOrderDetail.class, reverseConvertGenerate = false)
public class PurchaseOrderDetailBo extends BaseOrderDetailBo {




}

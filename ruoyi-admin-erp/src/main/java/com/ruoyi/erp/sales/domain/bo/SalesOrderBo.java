package com.ruoyi.erp.sales.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseOrderBo;
import com.ruoyi.erp.sales.domain.entity.SalesOrder;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SalesOrder.class, reverseConvertGenerate = false)
public class SalesOrderBo extends BaseOrderBo<SalesOrderDetailBo> {
}

package com.ruoyi.erp.sales.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseOrderDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesOrderDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SalesOrderDetail.class, reverseConvertGenerate = false)
public class SalesOrderDetailBo extends BaseOrderDetailBo {
}

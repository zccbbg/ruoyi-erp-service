package com.ruoyi.erp.sales.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.erp.base.domain.vo.BaseOrderVo;
import com.ruoyi.erp.sales.domain.entity.SalesOrder;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SalesOrder.class)
public class SalesOrderVo extends BaseOrderVo<SalesOrderDetailVo> {
}

package com.ruoyi.erp.sales.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.erp.base.domain.vo.BaseOrderDetailVo;
import com.ruoyi.erp.sales.domain.entity.SalesOrderDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SalesOrderDetail.class)
public class SalesOrderDetailVo extends BaseOrderDetailVo {
}

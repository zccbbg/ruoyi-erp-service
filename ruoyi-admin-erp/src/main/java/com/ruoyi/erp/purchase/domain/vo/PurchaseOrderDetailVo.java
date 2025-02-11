package com.ruoyi.erp.purchase.domain.vo;

import java.math.BigDecimal;

import com.ruoyi.erp.base.domain.vo.BaseOrderDetailVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseOrderDetail;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;

/**
 * 采购订单明细视图对象 purchase_order_detail
 *
 * @author zcc
 * @date 2025-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PurchaseOrderDetail.class)
public class PurchaseOrderDetailVo extends BaseOrderDetailVo {


}

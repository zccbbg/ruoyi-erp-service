package com.ruoyi.erp.purchase.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.erp.base.domain.vo.BaseOrderDetailVo;
import com.ruoyi.erp.base.domain.vo.BaseOrderVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseOrder;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;

/**
 * 采购订单视图对象 purchase_order
 *
 * @author zcc
 * @date 2025-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PurchaseOrder.class)
public class PurchaseOrderVo extends BaseOrderVo<PurchaseOrderDetailVo> {

}

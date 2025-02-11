package com.ruoyi.erp.purchase.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.erp.base.domain.vo.BaseRefundVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseRefund;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.io.Serial;

/**
 * 采购退货单视图对象 purchase_refund
 *
 * @author zcc
 * @date 2025-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PurchaseRefund.class)
public class PurchaseRefundVo<T extends PurchaseRefundDetailVo> extends BaseRefundVo<T> {
}

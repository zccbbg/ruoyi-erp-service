package com.ruoyi.erp.purchase.domain.vo;

import java.math.BigDecimal;

import com.ruoyi.erp.base.domain.vo.BaseTradeDetailVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTradeDetail;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import lombok.Data;
import io.github.linpeilie.annotations.AutoMapper;

import java.io.Serializable;
import java.io.Serial;

/**
 * 采购入库单明细视图对象 purchase_trade_detail
 *
 * @author zcc
 * @date 2025-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PurchaseTradeDetail.class)
public class PurchaseTradeDetailVo extends BaseTradeDetailVo {

}

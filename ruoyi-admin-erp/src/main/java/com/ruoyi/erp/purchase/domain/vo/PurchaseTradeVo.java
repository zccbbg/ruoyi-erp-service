package com.ruoyi.erp.purchase.domain.vo;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.erp.base.domain.vo.BaseTradeVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTrade;
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
 * 采购入库单视图对象 purchase_trade
 *
 * @author zcc
 * @date 2025-02-07
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = PurchaseTrade.class)
public class PurchaseTradeVo<T extends PurchaseTradeDetailVo> extends BaseTradeVo<T> {

}

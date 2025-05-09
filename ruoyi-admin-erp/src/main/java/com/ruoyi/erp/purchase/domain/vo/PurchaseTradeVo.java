package com.ruoyi.erp.purchase.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.erp.base.domain.vo.BaseTradeVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTrade;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.util.List;

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
    private List<String> refundNoList;
}

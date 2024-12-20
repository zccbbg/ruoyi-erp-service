package com.ruoyi.erp.base.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseOrderDetail extends BaseBillDetail{
    /**
     * 已处理数量（入库单就是已入库数量，出库单就是已出库数量）
     */
    private BigDecimal processedQty;
}

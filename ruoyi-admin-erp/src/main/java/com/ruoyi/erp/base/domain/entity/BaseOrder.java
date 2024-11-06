package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseOrder extends BaseBill{
    /**
     * 库存状态
     */
    private Integer stockStatus;
    /**
     * 交货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime deliveryDate;
    /**
     * 已处理数量（入库单就是已入库数量，出库单就是已出库数量）
     */
    private BigDecimal processedQuantity;

    /**
     * 预付款金额
     */
    private BigDecimal prepayAmount;
}

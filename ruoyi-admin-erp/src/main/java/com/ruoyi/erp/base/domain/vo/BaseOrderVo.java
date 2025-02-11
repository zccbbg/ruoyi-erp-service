package com.ruoyi.erp.base.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import com.ruoyi.common.mybatis.core.domain.BaseVo;
import com.ruoyi.erp.base.domain.bo.BaseOrderDetailBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
public class BaseOrderVo<T extends BaseOrderDetailVo> extends BaseBillVo {
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
    private BigDecimal processedQty;

    /**
     * 预付金额
     */
    private BigDecimal prepayAmount;


    private List<T> details;
}

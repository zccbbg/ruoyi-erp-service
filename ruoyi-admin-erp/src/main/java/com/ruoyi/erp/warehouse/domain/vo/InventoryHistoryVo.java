package com.ruoyi.erp.warehouse.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.erp.basic.domain.vo.SkuVo;
import com.ruoyi.erp.basic.domain.vo.GoodsVo;
import com.ruoyi.common.excel.annotation.ExcelDictFormat;
import com.ruoyi.common.excel.convert.ExcelDictConvert;
import com.ruoyi.erp.warehouse.domain.entity.InventoryHistory;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 库存记录视图对象 wms_inventory_history
 *
 * @author zcc
 * @date 2024-07-22
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = InventoryHistory.class)
public class InventoryHistoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 操作id（出库、入库、库存移动表单id）
     */
    @ExcelProperty(value = "操作id", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "出库、入库、库存移动表单id")
    private Long optId;

    /**
     * 操作单号（入库、出库、移库、盘库单号）
     */
    @ExcelProperty(value = "操作单号")
    private String optNo;

    /**
     * 单据类型
     */
    @ExcelProperty(value = "单据类型")
    private Integer optType;

    /**
     * 物料ID
     */
    @ExcelProperty(value = "物料ID")
    private Long skuId;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 库存变化
     */
    @ExcelProperty(value = "库存变化")
    private BigDecimal qty;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 所属仓库
     */
    @ExcelProperty(value = "所属仓库")
    private Long warehouseId;

    /**
     * 更新前数量
     */
    private BigDecimal beforeQty;
    /**
     * 更新后数量
     */
    private BigDecimal afterQty;

    private SkuVo sku;

    private GoodsVo goods;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}

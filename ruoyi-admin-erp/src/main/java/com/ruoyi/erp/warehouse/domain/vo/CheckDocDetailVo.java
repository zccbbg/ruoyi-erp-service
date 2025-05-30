package com.ruoyi.erp.warehouse.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.erp.base.domain.vo.BaseDocDetailVo;
import com.ruoyi.erp.warehouse.domain.entity.CheckDocDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 库存盘点单据详情视图对象 wms_check_order_detail
 *
 * @author zcc
 * @date 2024-08-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = CheckDocDetail.class)
public class CheckDocDetailVo extends BaseDocDetailVo {

    /**
     * 盘点数量
     */
    @ExcelProperty(value = "盘点数量")
    private BigDecimal checkQty;

    /**
     * 库存id
     */
    private Long inventoryId;

}

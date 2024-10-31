package com.ruoyi.erp.warehouse.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.erp.base.domain.vo.BaseDocVo;
import com.ruoyi.erp.warehouse.domain.entity.OtherReceiptDoc;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 入库单视图对象 wms_receipt_order
 *
 * @author zcc
 * @date 2024-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = OtherReceiptDoc.class)
public class OtherReceiptDocVo extends BaseDocVo<OtherReceiptDocDetailVo> {

    /**
     * 入库类型
     */
    @ExcelProperty(value = "操作类型")
    private Long optType;

    /**
     * 供应商
     */
    @ExcelProperty(value = "对接商家id")
    private Long merchantId;

    /**
     * 订单号
     */
    @ExcelProperty(value = "业务订单号")
    private String bizOrderNo;
}

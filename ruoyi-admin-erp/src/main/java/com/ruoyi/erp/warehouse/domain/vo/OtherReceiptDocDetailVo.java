package com.ruoyi.erp.warehouse.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.ruoyi.erp.base.domain.vo.BaseDocDetailVo;
import com.ruoyi.erp.warehouse.domain.entity.OtherReceiptDocDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 入库单详情视图对象 wms_receipt_order_detail
 *
 * @author zcc
 * @date 2024-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = OtherReceiptDocDetail.class)
public class OtherReceiptDocDetailVo extends BaseDocDetailVo {

}

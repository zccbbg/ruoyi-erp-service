package com.ruoyi.erp.warehouse.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.erp.base.domain.vo.BaseDocVo;
import com.ruoyi.erp.warehouse.domain.entity.OtherShipmentDoc;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 出库单视图对象 wms_shipment_order
 *
 * @author zcc
 * @date 2024-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ExcelIgnoreUnannotated
@AutoMapper(target = OtherShipmentDoc.class)
public class OtherShipmentDocVo extends BaseDocVo<OtherShipmentDocDetailVo> {

}

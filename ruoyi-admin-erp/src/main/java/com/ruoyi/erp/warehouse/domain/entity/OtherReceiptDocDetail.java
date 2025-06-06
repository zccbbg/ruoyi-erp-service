package com.ruoyi.erp.warehouse.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseDocDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 入库单详情对象 wms_receipt_order_detail
 *
 * @author zcc
 * @date 2024-07-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wms_receipt_doc_detail")
public class OtherReceiptDocDetail extends BaseDocDetail {

}

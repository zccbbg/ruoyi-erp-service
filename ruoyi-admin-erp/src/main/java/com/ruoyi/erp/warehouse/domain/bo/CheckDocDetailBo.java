package com.ruoyi.erp.warehouse.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseDocDetailBo;
import com.ruoyi.erp.warehouse.domain.entity.CheckDocDetail;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 库存盘点单据详情业务对象 wms_check_order_detail
 *
 * @author zcc
 * @date 2024-08-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = CheckDocDetail.class, reverseConvertGenerate = false)
public class CheckDocDetailBo extends BaseDocDetailBo {

    /**
     * 盘点数量
     */
    private BigDecimal checkQty;

    /**
     * 盈亏数
     */
    private BigDecimal profitAndLoss;

    /**
     * 库存id
     */
    private Long inventoryId;

    /**
     * 有盈亏
     */
    private Boolean haveProfitAndLoss;
}

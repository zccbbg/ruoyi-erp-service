package com.ruoyi.erp.domain.bo;

import com.ruoyi.erp.domain.entity.CheckDocDetail;
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
public class CheckOrderDetailBo extends BaseOrderDetailBo {

    /**
     * 盘点数量
     */
    private BigDecimal checkQuantity;

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

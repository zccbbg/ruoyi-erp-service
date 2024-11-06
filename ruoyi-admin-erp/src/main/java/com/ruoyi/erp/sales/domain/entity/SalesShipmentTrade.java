package com.ruoyi.erp.sales.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseTrade;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sales_shipment_trade")
public class SalesShipmentTrade extends BaseTrade {
}

package com.ruoyi.erp.purchase.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.erp.base.domain.entity.BaseTrade;
import com.ruoyi.erp.base.domain.entity.BaseTradeDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("purchase_trade_detail")
public class PurchaseTradeDetail extends BaseTradeDetail {
}

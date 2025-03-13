package com.ruoyi.erp.purchase.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseTradeBo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTrade;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import io.github.linpeilie.annotations.AutoMapper;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 采购入库单业务对象 purchase_trade
 *
 * @author zcc
 * @date 2025-02-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = PurchaseTrade.class, reverseConvertGenerate = false)
public class PurchaseTradeBo<T extends PurchaseTradeDetailBo> extends BaseTradeBo<T> {

}

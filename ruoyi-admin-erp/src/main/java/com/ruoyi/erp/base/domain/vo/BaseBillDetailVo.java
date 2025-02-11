package com.ruoyi.erp.base.domain.vo;

import com.ruoyi.erp.base.domain.entity.BaseDocDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseBillDetailVo extends BaseDocDetailVo {
    /**
     * 含税价
     */
    private BigDecimal priceWithTax;
    /**
     * 不含税价
     */
    private BigDecimal priceWithoutTax;
    /**
     * 税
     */
    private BigDecimal taxAmount;
    /**
     * 税率
     */
    private BigDecimal taxRate;
}

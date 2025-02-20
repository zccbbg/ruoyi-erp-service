package com.ruoyi.erp.financial.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.mybatis.core.domain.BaseHistoryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("financial_trans_history")
public class TransHistory extends BaseHistoryEntity {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 往来单位id
     */
    private Long merchantId;

    private Long bankAccountId;
    /**
     * 交易类型
     */
    private String transType;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 实际金额
     * 实际金额= 总金额-优惠金额
     */
    private BigDecimal actualAmount;
    /**
     * 支付金额
     */
    private BigDecimal paidAmount;

    /**
     * 余额变动=实际金额-支付金额
     */
    private BigDecimal balanceChange;
    /**
     * 交易前余额
     */
    private BigDecimal beforeBalance;
    /**
     * 交易后余额
     */
    private BigDecimal afterBalance;
    /**
     * 关联业务id
     */
    private Long relatedId;
    /**
     * 关联业务编号
     */
    private String relatedNo;

}

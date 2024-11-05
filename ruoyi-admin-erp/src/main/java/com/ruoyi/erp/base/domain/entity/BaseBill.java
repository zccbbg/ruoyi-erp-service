package com.ruoyi.erp.base.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseBill extends BaseEntity {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 单据编号
     */
    private String billCode;
    /**
     * 审核状态
     */
    private Integer checkedStatus;
    /**
     * 审核人
     */
    private String checkName;

    /**
     * 单据日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime billDate;
    /**
     * 往来单位id
     */
    private Long merchantId;
    /**
     * 商品数量
     */
    private BigDecimal goodsQuantity;

    /**
     * 已处理数量（入库单就是已入库数量，出库单就是已出库数量）
     */
    private BigDecimal processedQuantity;
    /**
     * 商品金额（未优惠）
     */
    private BigDecimal goodsAmount;

    /**
     * 其他费用金额
     */
    private BigDecimal otherExpensesAmount;
    /**
     * 备注
     */
    private String remark;

    /**
     * 交货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime deliveryDate;

    /**
     * 库存状态
     */
    private Integer stockStatus;


    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 实际金额(货品金额+其他费用-优惠金额)
     */
    private BigDecimal actualAmount;

    /**
     * 已支付定金
     */
    private BigDecimal payedDepositAmount;

    /**
     * 已抵扣定金
     */
    private BigDecimal deductedDepositAmount;
}

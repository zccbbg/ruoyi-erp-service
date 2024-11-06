package com.ruoyi.erp.balance.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bal_merchant_trans")
public class MerchantTrans extends BaseEntity {
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

    private Long bankAccountId;

    private String remark;

    private BigDecimal actualAmount;


}

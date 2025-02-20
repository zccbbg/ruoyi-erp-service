package com.ruoyi.erp.financial.domain.bo;

import com.ruoyi.common.mybatis.core.domain.BaseHistoryEntity;
import com.ruoyi.erp.financial.domain.entity.TransHistory;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import io.github.linpeilie.annotations.AutoMapper;

import java.math.BigDecimal;

/**
 * 交易流水业务对象 financial_trans_history
 *
 * @author zcc
 * @date 2025-02-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = TransHistory.class, reverseConvertGenerate = false)
public class TransHistoryBo extends BaseHistoryEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商家id
     */
    @NotNull(message = "商家id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long merchantId;

    /**
     * 银行账户id
     */
    @NotNull(message = "银行账户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bankAccountId;

    /**
     * 交易类型
     */
    @NotBlank(message = "交易类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String transType;

    /**
     * 关联业务id
     */
    @NotNull(message = "关联业务id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long relatedId;

    /**
     * 关联业务编号
     */
    @NotBlank(message = "关联业务编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String relatedNo;

    /**
     * 总金额
     */
    @NotNull(message = "总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal totalAmount;

    /**
     * 优惠金额
     */
    @NotNull(message = "优惠金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal discountAmount;

    /**
     * 实际金额
     */
    @NotNull(message = "实际金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal actualAmount;

    /**
     * 支付金额
     */
    @NotNull(message = "支付金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal paidAmount;

    /**
     * 余额变动
     */
    @NotNull(message = "余额变动不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal balanceChange;

    /**
     * 交易前余额
     */
    @NotNull(message = "交易前余额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal beforeBalance;

    /**
     * 交易后余额
     */
    @NotNull(message = "交易后余额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal afterBalance;


}

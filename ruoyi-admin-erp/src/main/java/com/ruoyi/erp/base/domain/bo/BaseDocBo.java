package com.ruoyi.erp.base.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDocBo<T extends BaseDocDetailBo> extends BaseEntity {
    /**
     *
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 编号
     */
    @NotBlank(message = "编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String docNo;

    /**
     * 单据日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate docDate;

    /**
     * 商品总数
     */
    private BigDecimal goodsQty;

    /**
     * 订单总金额
     */
    private BigDecimal goodsAmount;

    /**
     * 单据状态
     */
    private Integer checkedStatus;

    /**
     * 备注
     */
    private String remark;

    /**
     * 商品信息
     */
    private List<T> details;
}

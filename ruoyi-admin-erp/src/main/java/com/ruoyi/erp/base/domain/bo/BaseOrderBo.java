package com.ruoyi.erp.base.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.erp.base.domain.vo.BaseDocDetailVo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseOrderBo<T extends BaseOrderDetailBo>  extends BaseBillBo<T> {

    /**
     * 库存状态
     */
    private Integer stockStatus;
    /**
     * 交货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deliveryDate;
    /**
     * 已处理数量（入库单就是已入库数量，出库单就是已出库数量）
     */
    private BigDecimal processedQty;

    /**
     * 预付金额
     */
    private BigDecimal prepayAmount;
    private Long bankAccountId;
}

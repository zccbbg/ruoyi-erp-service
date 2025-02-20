package com.ruoyi.erp.financial.domain.bo;

import com.ruoyi.erp.base.domain.bo.BaseVoucherBo;
import com.ruoyi.erp.financial.domain.entity.ReceiptVoucher;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import io.github.linpeilie.annotations.AutoMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 收款单业务对象 financial_receipt_voucher
 *
 * @author zcc
 * @date 2025-02-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ReceiptVoucher.class, reverseConvertGenerate = false)
public class ReceiptVoucherBo extends BaseVoucherBo {

}

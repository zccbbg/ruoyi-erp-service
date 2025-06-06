package com.ruoyi.erp.base.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.erp.basic.domain.vo.SkuVo;
import com.ruoyi.erp.basic.domain.vo.GoodsVo;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ExcelIgnoreUnannotated
public class BaseDocDetailVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Long id;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 规格id
     */
    @ExcelProperty(value = "规格id")
    private Long skuId;

    /**
     * 入库数量
     */
    @ExcelProperty(value = "数量")
    private BigDecimal qty;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    private BigDecimal totalAmount;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 所属仓库
     */
    @ExcelProperty(value = "所属仓库")
    private Long warehouseId;

    private SkuVo sku;

    private GoodsVo goods;
}

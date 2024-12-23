package com.ruoyi.erp.basic.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SkuMapVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long skuId;
    private GoodsVo goods;
    private SkuVo sku;
}

package com.ruoyi.erp.basic.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.erp.basic.domain.entity.Goods;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Goods.class, reverseConvertGenerate = false)
public class GoodsBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 主键集合
     */
    private List<Long> ids;

    /**
     * 编号
     */
    private String itemCode;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String itemName;

    /**
     * 分类
     */
    @NotBlank(message = "分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private String itemCategory;

    /**
     * 单位类别
     */
    private String unit;

    /**
     * 品牌
     */
    private Long itemBrand;


    /**
     * 备注
     */
    private String remark;

    private List<SkuBo> sku;

}

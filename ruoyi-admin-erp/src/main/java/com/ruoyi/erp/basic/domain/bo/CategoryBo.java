package com.ruoyi.erp.basic.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ruoyi.erp.basic.domain.entity.Category;

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Category.class, reverseConvertGenerate = false)
public class CategoryBo extends BaseEntity {

    /**
     * 物料类型id
     */
    @NotNull(message = "物料类型id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 父物料类型id
     */
    private Long pid;

    /**
     * 物料类型名称
     */
    @NotBlank(message = "物料类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String categoryName;

    /**
     * 显示顺序
     */
    private Long orderNum;


}

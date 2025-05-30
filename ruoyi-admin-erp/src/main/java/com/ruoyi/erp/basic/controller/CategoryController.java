package com.ruoyi.erp.basic.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.idempotent.annotation.RepeatSubmit;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.web.core.BaseController;
import com.ruoyi.erp.basic.domain.bo.CategoryBo;
import com.ruoyi.erp.basic.domain.vo.CategoryVo;
import com.ruoyi.erp.basic.domain.vo.GoodsTypeTreeSelectVo;
import com.ruoyi.erp.basic.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basic/category")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;

    /**
     * 查询物料类型列表
     */
    @GetMapping("/list")
    @SaCheckPermission("basic:goods:list")
    public TableDataInfo<CategoryVo> list(CategoryBo bo, PageQuery pageQuery) {
        return categoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询物料类型列表
     */
    @GetMapping("/listNoPage")
    @SaCheckPermission("basic:goods:list")
    public R<List<CategoryVo>> listNoPage(CategoryBo bo) {
        return R.ok(categoryService.queryList(bo));
    }

    /**
     * 获取物料类型下拉树列表
     */
    @GetMapping("/treeselect")
    @SaCheckPermission("basic:goods:list")
    public R<List<GoodsTypeTreeSelectVo>> treeselect(CategoryBo query) {
        List<CategoryVo> itemTypes = categoryService.queryList(query);
        return R.ok(categoryService.buildGoodsTypeTreeSelect(itemTypes));
    }

    /**
     * 导出物料类型列表
     */
    @Log(title = "物料类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @SaCheckPermission("basic:goods:list")
    public void export(CategoryBo bo, HttpServletResponse response) {
        List<CategoryVo> list = categoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "物料类型", CategoryVo.class, response);
    }

    /**
     * 获取物料类型详细信息
     *
     * @param itemTypeId 主键
     */
    @GetMapping("/{itemTypeId}")
    @SaCheckPermission("basic:goods:list")
    public R<CategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long itemTypeId) {
        return R.ok(categoryService.queryById(itemTypeId));
    }

    /**
     * 新增物料类型
     */
    @Log(title = "物料类型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    @SaCheckPermission("basic:goods:edit")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CategoryBo bo) {
        categoryService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改物料类型
     */
    @Log(title = "物料类型", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    @SaCheckPermission("basic:goods:edit")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CategoryBo bo) {
        categoryService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除物料类型
     *
     * @param itemTypeIds 主键串
     */
    @Log(title = "物料类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{itemTypeIds}")
    @SaCheckPermission("basic:goods:edit")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] itemTypeIds) {
        List<Long> ids = new ArrayList<>(Arrays.asList(itemTypeIds));
        categoryService.deleteByIds(ids);
        return R.ok();
    }

    @PostMapping("/update/orderNum")
    @SaCheckPermission("basic:goods:edit")
    public R<Void> updateOrderNum(@RequestBody List<GoodsTypeTreeSelectVo> tree) {
        categoryService.updateOrderNum(tree);
        return R.ok();
    }
}

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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.erp.basic.domain.bo.GoodsBo;
import com.ruoyi.erp.basic.domain.vo.GoodsVo;
import com.ruoyi.erp.basic.service.GoodsService;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basic/item")
public class GoodsController extends BaseController {

    private final GoodsService itemService;

    /**
     * 查询物料列表
     */
    @GetMapping("/list")
    @SaCheckPermission("basic:goods:list")
    public TableDataInfo<GoodsVo> list(GoodsBo bo, PageQuery pageQuery) {
        return itemService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询物料列表
     */
    @GetMapping("/listNoPage")
    @SaCheckPermission("basic:goods:list")
    public R<List<GoodsVo>> list(GoodsBo bo) {
        return R.ok(itemService.queryList(bo));
    }

    /**
     * 导出物料列表
     */
    @Log(title = "物料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @SaCheckPermission("basic:goods:list")
    public void export(GoodsBo bo, HttpServletResponse response) {
        List<GoodsVo> list = itemService.queryList(bo);
        ExcelUtil.exportExcel(list, "物料", GoodsVo.class, response);
    }

    /**
     * 获取物料详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    @SaCheckPermission("basic:goods:list")
    public R<GoodsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(itemService.queryById(id));
    }

    /**
     * 新增物料
     */
    @Log(title = "物料", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    @SaCheckPermission("basic:goods:edit")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GoodsBo form) {
        itemService.insertByForm(form);
        return R.ok();
    }
    /**
     * 修改物料
     */
    @Log(title = "物料", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    @SaCheckPermission("basic:goods:edit")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GoodsBo form) {
        itemService.updateByForm(form);
        return R.ok();
    }

    /**
     * 删除物料
     *
     * @param id 主键
     */
    @Log(title = "物料", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    @SaCheckPermission("basic:goods:edit")
    public R<Void> remove(@NotNull(message = "主键不能为空")
                          @PathVariable Long id) {
        itemService.deleteById(id);
        return R.ok();
    }
}

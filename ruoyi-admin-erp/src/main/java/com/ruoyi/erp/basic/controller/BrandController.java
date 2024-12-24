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
import com.ruoyi.erp.basic.domain.bo.BrandBo;
import com.ruoyi.erp.basic.domain.vo.BrandVo;
import com.ruoyi.erp.basic.service.BrandService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌
 *
 * @author zcc
 * @date 2024-07-30
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basic/brand")
public class BrandController extends BaseController {

    private final BrandService brandService;

    /**
     * 查询商品品牌列表
     */
    @SaCheckPermission("wms:itemBrand:list")
    @GetMapping("/list")
    public TableDataInfo<BrandVo> list(BrandBo bo, PageQuery pageQuery) {
        return brandService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询商品品牌列表
     */
    @SaCheckPermission("wms:itemBrand:list")
    @GetMapping("/listNoPage")
    public R<List<BrandVo>> listNoPage(BrandBo bo) {
        return R.ok(brandService.queryList(bo));
    }

    /**
     * 导出商品品牌列表
     */
    @SaCheckPermission("wms:itemBrand:list")
    @Log(title = "商品品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BrandBo bo, HttpServletResponse response) {
        List<BrandVo> list = brandService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品品牌", BrandVo.class, response);
    }

    /**
     * 获取商品品牌详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:itemBrand:list")
    @GetMapping("/{id}")
    public R<BrandVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(brandService.queryById(id));
    }

    /**
     * 新增商品品牌
     */
    @SaCheckPermission("wms:itemBrand:edit")
    @Log(title = "商品品牌", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BrandBo bo) {
        brandService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改商品品牌
     */
    @SaCheckPermission("wms:itemBrand:edit")
    @Log(title = "商品品牌", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BrandBo bo) {
        brandService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除商品品牌
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:itemBrand:edit")
    @Log(title = "商品品牌", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public R<Void> remove(@NotNull(message = "主键不能为空")
                          @PathVariable Long id) {
        brandService.deleteById(id);
        return R.ok();
    }
}

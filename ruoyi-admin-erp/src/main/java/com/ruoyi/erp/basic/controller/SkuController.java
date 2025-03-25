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
import com.ruoyi.erp.basic.domain.bo.SkuBo;
import com.ruoyi.erp.basic.domain.vo.SkuMapVo;
import com.ruoyi.erp.basic.domain.vo.SkuVo;
import com.ruoyi.erp.basic.service.SkuService;
import com.ruoyi.erp.sales.mapper.SalesRefundDetailMapper;
import com.ruoyi.erp.sales.service.SalesRefundDetailService;
import com.ruoyi.erp.sales.service.SalesTradeDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basic/sku")
public class SkuController extends BaseController {

    private final SkuService skuService;
    private final SalesTradeDetailService salesTradeDetailService;

    /**
     * 查询sku信息列表
     */
    @GetMapping("/list")
    @SaCheckPermission("basic:goods:list")
    public TableDataInfo<SkuMapVo> list(SkuBo bo, PageQuery pageQuery) {
        return skuService.queryPageList(bo, pageQuery);
    }

    @GetMapping("/list/tradeId")
    @SaCheckPermission("basic:goods:list")
    public TableDataInfo<SkuMapVo> list(SkuBo bo, Long tradeId,PageQuery pageQuery) {
        if(tradeId==null){
            return skuService.queryPageList(bo, pageQuery);
        }
        Set<Long> skuIds = salesTradeDetailService.getSkuIds(tradeId);
        return skuService.queryPageList(bo, pageQuery,skuIds);
    }

    /**
     * 根据skuIds查询
     * @param bo
     * @param pageQuery
     * @return
     */

    @PostMapping("/list")
    @SaCheckPermission("basic:goods:list")
    public TableDataInfo<SkuMapVo> listBySkuIds(@RequestBody SkuBo bo,PageQuery pageQuery) {
        return skuService.queryPageList(bo, pageQuery);
    }
    /**
     * 查询sku信息列表
     */
    @GetMapping("/listNoPage")
    @SaCheckPermission("basic:goods:list")
    public R<List<SkuVo>> list(SkuBo bo) {
        return R.ok(skuService.queryList(bo));
    }

    /**
     * 导出sku信息列表
     */
    @Log(title = "sku信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @SaCheckPermission("basic:goods:list")
    public void export(SkuBo bo, HttpServletResponse response) {
        List<SkuVo> list = skuService.queryList(bo);
        ExcelUtil.exportExcel(list, "sku信息", SkuVo.class, response);
    }

    /**
     * 获取sku信息详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    @SaCheckPermission("basic:goods:list")
    public R<SkuVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(skuService.queryById(id));
    }

    /**
     * 新增sku信息
     */
    @Log(title = "sku信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    @SaCheckPermission("basic:goods:edit")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SkuBo bo) {
        return toAjax(skuService.insertByBo(bo));
    }

    /**
     * 修改sku信息
     */
    @Log(title = "sku信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    @SaCheckPermission("basic:goods:edit")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SkuBo bo) {
        return toAjax(skuService.updateByBo(bo));
    }

    /**
     * 删除sku信息
     *
     * @param id 主键
     */
    @Log(title = "sku信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    @SaCheckPermission("basic:goods:edit")
    public R<Void> remove(@NotNull(message = "主键不能为空")
                          @PathVariable Long id) {
        skuService.deleteById(id);
        return R.ok();
    }
}

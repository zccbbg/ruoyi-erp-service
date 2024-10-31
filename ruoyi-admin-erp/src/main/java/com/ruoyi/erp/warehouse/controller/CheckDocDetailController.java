package com.ruoyi.erp.warehouse.controller;

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
import com.ruoyi.erp.warehouse.domain.bo.CheckDocDetailBo;
import com.ruoyi.erp.warehouse.domain.vo.CheckDocDetailVo;
import com.ruoyi.erp.warehouse.service.CheckDocDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存盘点单据详情
 *
 * @author zcc
 * @date 2024-08-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/checkOrderDetail")
public class CheckDocDetailController extends BaseController {

    private final CheckDocDetailService checkDocDetailService;

    /**
     * 查询库存盘点单据详情列表
     */
    @SaCheckPermission("wms:check:all")
    @GetMapping("/list")
    public TableDataInfo<CheckDocDetailVo> list(CheckDocDetailBo bo, PageQuery pageQuery) {
        return checkDocDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库存盘点单据详情列表
     */
    @SaCheckPermission("wms:check:all")
    @Log(title = "库存盘点单据详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CheckDocDetailBo bo, HttpServletResponse response) {
        List<CheckDocDetailVo> list = checkDocDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "库存盘点单据详情", CheckDocDetailVo.class, response);
    }

    /**
     * 获取库存盘点单据详情详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:check:all")
    @GetMapping("/{id}")
    public R<CheckDocDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(checkDocDetailService.queryById(id));
    }

    /**
     * 新增库存盘点单据详情
     */
    @SaCheckPermission("wms:check:all")
    @Log(title = "库存盘点单据详情", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CheckDocDetailBo bo) {
        checkDocDetailService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改库存盘点单据详情
     */
    @SaCheckPermission("wms:check:all")
    @Log(title = "库存盘点单据详情", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CheckDocDetailBo bo) {
        checkDocDetailService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除库存盘点单据详情
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wms:check:all")
    @Log(title = "库存盘点单据详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        checkDocDetailService.deleteByIds(List.of(ids));
        return R.ok();
    }

    /**
     * 根据盘库单id查询盘库单详情列表
     */
    @SaCheckPermission("wms:check:all")
    @GetMapping("/list/{checkOrderId}")
    public R<List<CheckDocDetailVo>> listByCheckOrderId(@NotNull @PathVariable Long checkOrderId) {
        return R.ok(checkDocDetailService.queryByCheckOrderId(checkOrderId));
    }
}

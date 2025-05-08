package com.ruoyi.erp.warehouse.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.erp.base.constant.ServiceConstants;
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
import com.ruoyi.erp.warehouse.domain.bo.CheckDocBo;
import com.ruoyi.erp.warehouse.domain.vo.CheckDocVo;
import com.ruoyi.erp.warehouse.service.CheckDocService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 库存盘点单据
 *
 * @author zcc
 * @date 2024-08-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/check")
public class CheckDocController extends BaseController {

    private final CheckDocService checkDocService;

    /**
     * 查询库存盘点单据列表
     */
    @SaCheckPermission("wms:check:all")
    @GetMapping("/list")
    public TableDataInfo<CheckDocVo> list(CheckDocBo bo, PageQuery pageQuery) {
        return checkDocService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出库存盘点单据列表
     */
    @SaCheckPermission("wms:check:all")
    @Log(title = "库存盘点单据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CheckDocBo bo, HttpServletResponse response) {
        List<CheckDocVo> list = checkDocService.queryList(bo);
        ExcelUtil.exportExcel(list, "库存盘点单据", CheckDocVo.class, response);
    }

    /**
     * 获取库存盘点单据详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:check:all")
    @GetMapping("/{id}")
    public R<CheckDocVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(checkDocService.queryById(id));
    }

    /**
     * 新增库存盘点单据
     */
    @SaCheckPermission("wms:check:all")
    @Log(title = "库存盘点单据", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CheckDocBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        checkDocService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改库存盘点单据
     */
    @SaCheckPermission("wms:check:all")
    @Log(title = "库存盘点单据", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CheckDocBo bo) {
        checkDocService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 盘库结束
     */
    @SaCheckPermission("wms:check:all")
    @Log(title = "库存盘点单据", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/check")
    public R<Void> check(@Validated(AddGroup.class) @RequestBody CheckDocBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.FINISH);
        checkDocService.check(bo);
        return R.ok();
    }

    /**
     * 删除库存盘点单据
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:check:all")
    @Log(title = "库存盘点单据", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public R<Void> remove(@NotNull(message = "主键不能为空")
                          @PathVariable Long id) {
        checkDocService.deleteById(id);
        return R.ok();
    }
}

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
import com.ruoyi.erp.warehouse.domain.bo.MovementDocBo;
import com.ruoyi.erp.warehouse.service.MovementDocService;
import com.ruoyi.erp.warehouse.domain.vo.MovementDocVo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 移库单
 *
 * @author zcc
 * @date 2024-08-09
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/movement")
public class MovementDocController extends BaseController {

    private final MovementDocService movementDocService;

    /**
     * 查询移库单列表
     */
    @SaCheckPermission("wms:movement:all")
    @GetMapping("/list")
    public TableDataInfo<MovementDocVo> list(MovementDocBo bo, PageQuery pageQuery) {
        return movementDocService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出移库单列表
     */
    @SaCheckPermission("wms:movement:all")
    @Log(title = "移库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MovementDocBo bo, HttpServletResponse response) {
        List<MovementDocVo> list = movementDocService.queryList(bo);
        ExcelUtil.exportExcel(list, "移库单", MovementDocVo.class, response);
    }

    /**
     * 获取移库单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:movement:all")
    @GetMapping("/{id}")
    public R<MovementDocVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(movementDocService.queryById(id));
    }

    /**
     * 新增移库单
     */
    @SaCheckPermission("wms:movement:all")
    @Log(title = "移库单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MovementDocBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        movementDocService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改移库单
     */
    @SaCheckPermission("wms:movement:all")
    @Log(title = "移库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MovementDocBo bo) {
        movementDocService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 移库
     */
    @SaCheckPermission("wms:movement:all")
    @Log(title = "移库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/move")
    public R<Void> move(@Validated(AddGroup.class) @RequestBody MovementDocBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.FINISH);
        movementDocService.move(bo);
        return R.ok();
    }

    /**
     * 删除移库单
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:movement:all")
    @Log(title = "移库单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public R<Void> remove(@NotNull(message = "主键不能为空")
                          @PathVariable Long id) {
        movementDocService.deleteById(id);
        return R.ok();
    }
}

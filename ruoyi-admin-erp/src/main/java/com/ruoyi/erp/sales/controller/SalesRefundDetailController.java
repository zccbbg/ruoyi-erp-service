package com.ruoyi.erp.sales.controller;

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
import com.ruoyi.erp.sales.domain.bo.SalesRefundDetailBo;
import com.ruoyi.erp.sales.domain.vo.SalesRefundDetailVo;
import com.ruoyi.erp.sales.service.SalesRefundDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售退货单明细
 *
 * @date 2025-03-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sales/refundDetail")
public class SalesRefundDetailController extends BaseController {

    private final SalesRefundDetailService SalesRefundDetailService;
    private final SalesRefundDetailService salesRefundDetailService;

    /**
     * 查询销售退货单明细列表
     */
    @SaCheckPermission("sales:refundDetail:list")
    @GetMapping("/list")
    public TableDataInfo<SalesRefundDetailVo> list(SalesRefundDetailBo bo, PageQuery pageQuery) {
        return SalesRefundDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出销售退货单明细列表
     */
    @SaCheckPermission("sales:refundDetail:export")
    @Log(title = "销售退货单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SalesRefundDetailBo bo, HttpServletResponse response) {
        List<SalesRefundDetailVo> list = SalesRefundDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "销售退货单明细", SalesRefundDetailVo.class, response);
    }

    /**
     * 获取销售退货单明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("sales:refundDetail:query")
    @GetMapping("/{id}")
    public R<SalesRefundDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(SalesRefundDetailService.queryById(id));
    }

    /**
     * 新增销售退货单明细
     */
    @SaCheckPermission("sales:refundDetail:add")
    @Log(title = "销售退货单明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SalesRefundDetailBo bo) {
        SalesRefundDetailService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改销售退货单明细
     */
    @SaCheckPermission("sales:refundDetail:edit")
    @Log(title = "销售退货单明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SalesRefundDetailBo bo) {
        SalesRefundDetailService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除销售退货单明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("sales:refundDetail:remove")
    @Log(title = "销售退货单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        SalesRefundDetailService.deleteByIds(List.of(ids));
        return R.ok();
    }
    @SaCheckPermission("sales:refundDetail:all")
    @GetMapping("/listByRefundId/{refundId}")
    public R<List<SalesRefundDetailVo>> listByRefundId(@NotNull @PathVariable Long refundId) {
        return R.ok(salesRefundDetailService.queryByPid(refundId));
    }
}

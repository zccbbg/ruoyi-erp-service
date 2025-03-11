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
import com.ruoyi.erp.sales.domain.bo.SalesOrderDetailBo;
import com.ruoyi.erp.sales.domain.vo.SalesOrderDetailVo;
import com.ruoyi.erp.sales.service.SalesOrderDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sales/orderDetail")
public class SalesOrderDetailController {

    private final SalesOrderDetailService salesOrderDetailService;

    /**
     * 查询销售订单明细列表
     */
    @SaCheckPermission("sales:orderDetail:list")
    @GetMapping("/list")
    public TableDataInfo<SalesOrderDetailVo> list(SalesOrderDetailBo bo, PageQuery pageQuery) {
        return salesOrderDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出销售订单明细列表
     */
    @SaCheckPermission("sales:orderDetail:export")
    @Log(title = "销售订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SalesOrderDetailBo bo, HttpServletResponse response) {
        List<SalesOrderDetailVo> list = salesOrderDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "销售订单明细", SalesOrderDetailVo.class, response);
    }

    /**
     * 获取销售订单明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("sales:orderDetail:query")
    @GetMapping("/{id}")
    public R<SalesOrderDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                            @PathVariable Long id) {
        return R.ok(salesOrderDetailService.queryById(id));
    }

    /**
     * 新增销售订单明细
     */
    @SaCheckPermission("sales:orderDetail:add")
    @Log(title = "销售订单明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SalesOrderDetailBo bo) {
        salesOrderDetailService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改销售订单明细
     */
    @SaCheckPermission("sales:orderDetail:edit")
    @Log(title = "销售订单明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SalesOrderDetailBo bo) {
        salesOrderDetailService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除销售订单明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("sales:orderDetail:remove")
    @Log(title = "销售订单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        salesOrderDetailService.deleteByIds(List.of(ids));
        return R.ok();
    }

    @SaCheckPermission("sales:orderDetail:all")
    @GetMapping("/listByOrderId/{orderId}")
    public R<List<SalesOrderDetailVo>> listByOrderId(@NotNull @PathVariable Long orderId) {
        return R.ok(salesOrderDetailService.queryByPid(orderId));
    }
}

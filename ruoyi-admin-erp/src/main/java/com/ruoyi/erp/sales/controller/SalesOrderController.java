package com.ruoyi.erp.sales.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.core.constant.ServiceConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.idempotent.annotation.RepeatSubmit;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

import com.ruoyi.erp.sales.domain.bo.SalesOrderBo;
import com.ruoyi.erp.sales.domain.vo.SalesOrderVo;
import com.ruoyi.erp.sales.service.SalesOrderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售订单
 *
 * @date 2025-03-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sales/order")
public class SalesOrderController {
    private final SalesOrderService salesOrderService;

    /**
     * 分页查询销售订单列表
     */
    @SaCheckPermission("sales:order:list")
    @GetMapping("/list")
    public TableDataInfo<SalesOrderVo> list(SalesOrderBo bo, PageQuery pageQuery) {
        return salesOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出销售订单列表
     */
    @SaCheckPermission("sales:order:export")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SalesOrderBo bo, HttpServletResponse response) {
        List<SalesOrderVo> list = salesOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "销售订单", SalesOrderVo.class, response);
    }

    /**
     * 获取销售订单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("sales:order:query")
    @GetMapping("/{id}")
    public R<SalesOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                      @PathVariable Long id) {
        return R.ok(salesOrderService.queryById(id));
    }

    /**
     * 新增销售订单
     */
    @SaCheckPermission("sales:order:add")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SalesOrderBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        salesOrderService.insertByBo(bo);
        return R.ok();
    }

    @SaCheckPermission("sales:order:all")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/pass")
    public R<Void> pass(@Validated(AddGroup.class) @RequestBody SalesOrderBo bo) {
        if(bo.getMerchantId()==null){
            throw new ServiceException("请选择商家！");
        }
        bo.setCheckedStatus(ServiceConstants.Status.FINISH);
        salesOrderService.pass(bo);
        return R.ok();
    }

    /**
     * 修改销售订单
     */
    @SaCheckPermission("sales:order:edit")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SalesOrderBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        salesOrderService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除销售订单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("sales:order:remove")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        salesOrderService.deleteByIds(List.of(ids));
        return R.ok();
    }
}

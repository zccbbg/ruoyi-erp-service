package com.ruoyi.erp.sales.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.core.constant.ServiceConstants;
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
import com.ruoyi.erp.sales.domain.bo.SalesTradeBo;
import com.ruoyi.erp.sales.domain.vo.SalesTradeVo;
import com.ruoyi.erp.sales.service.SalesTradeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售入库单
 *
 * @date 2025-03-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sales/trade")
public class SalesTradeController extends BaseController {

    private final SalesTradeService SalesTradeService;

    /**
     * 查询销售入库单列表
     */
    @SaCheckPermission("sales:trade:list")
    @GetMapping("/list")
    public TableDataInfo<SalesTradeVo> list(SalesTradeBo bo, PageQuery pageQuery) {
        return SalesTradeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出销售入库单列表
     */
    @SaCheckPermission("sales:trade:export")
    @Log(title = "销售入库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SalesTradeBo bo, HttpServletResponse response) {
        List<SalesTradeVo> list = SalesTradeService.queryList(bo);
        ExcelUtil.exportExcel(list, "销售入库单", SalesTradeVo.class, response);
    }

    /**
     * 获取销售入库单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("sales:trade:query")
    @GetMapping("/{id}")
    public R<SalesTradeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(SalesTradeService.queryById(id));
    }

    /**
     * 新增销售入库单
     */
    @SaCheckPermission("sales:trade:add")
    @Log(title = "销售入库单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SalesTradeBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        SalesTradeService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改销售入库单
     */
    @SaCheckPermission("sales:trade:edit")
    @Log(title = "销售入库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SalesTradeBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        SalesTradeService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除销售入库单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("sales:trade:remove")
    @Log(title = "销售入库单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        SalesTradeService.deleteByIds(List.of(ids));
        return R.ok();
    }
}

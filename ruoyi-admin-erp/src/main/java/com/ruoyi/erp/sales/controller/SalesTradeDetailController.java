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
import com.ruoyi.erp.sales.domain.bo.SalesTradeDetailBo;
import com.ruoyi.erp.sales.domain.vo.SalesTradeDetailVo;
import com.ruoyi.erp.sales.service.SalesTradeDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售入库单明细
 *
 * @author zcc
 * @date 2025-02-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sales/tradeDetail")
public class SalesTradeDetailController extends BaseController {

    private final SalesTradeDetailService SalesTradeDetailService;

    /**
     * 查询销售入库单明细列表
     */
    @SaCheckPermission("sales:tradeDetail:list")
    @GetMapping("/list")
    public TableDataInfo<SalesTradeDetailVo> list(SalesTradeDetailBo bo, PageQuery pageQuery) {
        return SalesTradeDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出销售入库单明细列表
     */
    @SaCheckPermission("sales:tradeDetail:export")
    @Log(title = "销售入库单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SalesTradeDetailBo bo, HttpServletResponse response) {
        List<SalesTradeDetailVo> list = SalesTradeDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "销售入库单明细", SalesTradeDetailVo.class, response);
    }

    /**
     * 获取销售入库单明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("sales:tradeDetail:query")
    @GetMapping("/{id}")
    public R<SalesTradeDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(SalesTradeDetailService.queryById(id));
    }

    /**
     * 新增销售入库单明细
     */
    @SaCheckPermission("sales:tradeDetail:add")
    @Log(title = "销售入库单明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SalesTradeDetailBo bo) {
        SalesTradeDetailService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改销售入库单明细
     */
    @SaCheckPermission("sales:tradeDetail:edit")
    @Log(title = "销售入库单明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SalesTradeDetailBo bo) {
        SalesTradeDetailService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除销售入库单明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("sales:tradeDetail:remove")
    @Log(title = "销售入库单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        SalesTradeDetailService.deleteByIds(List.of(ids));
        return R.ok();
    }

    @SaCheckPermission("sales:tradeDetail:all")
    @GetMapping("/listByTradeId/{tradeId}")
    public R<List<SalesTradeDetailVo>> listByTradeId(@NotNull @PathVariable Long tradeId) {
        return R.ok(SalesTradeDetailService.queryByPid(tradeId));
    }
}

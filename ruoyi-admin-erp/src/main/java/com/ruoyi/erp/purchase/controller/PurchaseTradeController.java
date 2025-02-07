package com.ruoyi.erp.purchase.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.idempotent.annotation.RepeatSubmit;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.web.core.BaseController;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.erp.purchase.domain.vo.PurchaseTradeVo;
import com.ruoyi.erp.purchase.domain.bo.PurchaseTradeBo;
import com.ruoyi.erp.purchase.service.PurchaseTradeService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 采购入库单
 *
 * @author zcc
 * @date 2025-02-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase/trade")
public class PurchaseTradeController extends BaseController {

    private final PurchaseTradeService purchaseTradeService;

    /**
     * 查询采购入库单列表
     */
    @SaCheckPermission("purchase:trade:list")
    @GetMapping("/list")
    public TableDataInfo<PurchaseTradeVo> list(PurchaseTradeBo bo, PageQuery pageQuery) {
        return purchaseTradeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出采购入库单列表
     */
    @SaCheckPermission("purchase:trade:export")
    @Log(title = "采购入库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PurchaseTradeBo bo, HttpServletResponse response) {
        List<PurchaseTradeVo> list = purchaseTradeService.queryList(bo);
        ExcelUtil.exportExcel(list, "采购入库单", PurchaseTradeVo.class, response);
    }

    /**
     * 获取采购入库单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("purchase:trade:query")
    @GetMapping("/{id}")
    public R<PurchaseTradeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(purchaseTradeService.queryById(id));
    }

    /**
     * 新增采购入库单
     */
    @SaCheckPermission("purchase:trade:add")
    @Log(title = "采购入库单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PurchaseTradeBo bo) {
        purchaseTradeService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改采购入库单
     */
    @SaCheckPermission("purchase:trade:edit")
    @Log(title = "采购入库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PurchaseTradeBo bo) {
        purchaseTradeService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除采购入库单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("purchase:trade:remove")
    @Log(title = "采购入库单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        purchaseTradeService.deleteByIds(List.of(ids));
        return R.ok();
    }
}

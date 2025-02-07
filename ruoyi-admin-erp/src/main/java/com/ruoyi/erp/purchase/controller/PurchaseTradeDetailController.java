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
import com.ruoyi.erp.purchase.domain.vo.PurchaseTradeDetailVo;
import com.ruoyi.erp.purchase.domain.bo.PurchaseTradeDetailBo;
import com.ruoyi.erp.purchase.service.PurchaseTradeDetailService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 采购入库单明细
 *
 * @author zcc
 * @date 2025-02-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase/tradeDetail")
public class PurchaseTradeDetailController extends BaseController {

    private final PurchaseTradeDetailService purchaseTradeDetailService;

    /**
     * 查询采购入库单明细列表
     */
    @SaCheckPermission("purchase:tradeDetail:list")
    @GetMapping("/list")
    public TableDataInfo<PurchaseTradeDetailVo> list(PurchaseTradeDetailBo bo, PageQuery pageQuery) {
        return purchaseTradeDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出采购入库单明细列表
     */
    @SaCheckPermission("purchase:tradeDetail:export")
    @Log(title = "采购入库单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PurchaseTradeDetailBo bo, HttpServletResponse response) {
        List<PurchaseTradeDetailVo> list = purchaseTradeDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "采购入库单明细", PurchaseTradeDetailVo.class, response);
    }

    /**
     * 获取采购入库单明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("purchase:tradeDetail:query")
    @GetMapping("/{id}")
    public R<PurchaseTradeDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(purchaseTradeDetailService.queryById(id));
    }

    /**
     * 新增采购入库单明细
     */
    @SaCheckPermission("purchase:tradeDetail:add")
    @Log(title = "采购入库单明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PurchaseTradeDetailBo bo) {
        purchaseTradeDetailService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改采购入库单明细
     */
    @SaCheckPermission("purchase:tradeDetail:edit")
    @Log(title = "采购入库单明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PurchaseTradeDetailBo bo) {
        purchaseTradeDetailService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除采购入库单明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("purchase:tradeDetail:remove")
    @Log(title = "采购入库单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        purchaseTradeDetailService.deleteByIds(List.of(ids));
        return R.ok();
    }
}

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
import com.ruoyi.erp.purchase.domain.vo.PurchaseRefundDetailVo;
import com.ruoyi.erp.purchase.domain.bo.PurchaseRefundDetailBo;
import com.ruoyi.erp.purchase.service.PurchaseRefundDetailService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 采购退货单明细
 *
 * @author zcc
 * @date 2025-02-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase/refundDetail")
public class PurchaseRefundDetailController extends BaseController {

    private final PurchaseRefundDetailService purchaseRefundDetailService;

    /**
     * 查询采购退货单明细列表
     */
    @SaCheckPermission("purchase:refund:all")
    @GetMapping("/list")
    public TableDataInfo<PurchaseRefundDetailVo> list(PurchaseRefundDetailBo bo, PageQuery pageQuery) {
        return purchaseRefundDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出采购退货单明细列表
     */
    @SaCheckPermission("purchase:refund:all")
    @Log(title = "采购退货单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PurchaseRefundDetailBo bo, HttpServletResponse response) {
        List<PurchaseRefundDetailVo> list = purchaseRefundDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "采购退货单明细", PurchaseRefundDetailVo.class, response);
    }

    /**
     * 获取采购退货单明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("purchase:refund:all")
    @GetMapping("/{id}")
    public R<PurchaseRefundDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(purchaseRefundDetailService.queryById(id));
    }

    /**
     * 新增采购退货单明细
     */
    @SaCheckPermission("purchase:refund:all")
    @Log(title = "采购退货单明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PurchaseRefundDetailBo bo) {
        purchaseRefundDetailService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改采购退货单明细
     */
    @SaCheckPermission("purchase:refund:all")
    @Log(title = "采购退货单明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PurchaseRefundDetailBo bo) {
        purchaseRefundDetailService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除采购退货单明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("purchase:refund:all")
    @Log(title = "采购退货单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        purchaseRefundDetailService.deleteByIds(List.of(ids));
        return R.ok();
    }
    @SaCheckPermission("purchase:refund:all")
    @GetMapping("/listByRefundId/{refundId}")
    public R<List<PurchaseRefundDetailVo>> listByRefundId(@NotNull @PathVariable Long refundId) {
        return R.ok(purchaseRefundDetailService.queryByPid(refundId));
    }
}

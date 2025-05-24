package com.ruoyi.erp.purchase.controller;

import java.util.List;

import com.ruoyi.erp.warehouse.domain.vo.OtherReceiptDocDetailVo;
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
import com.ruoyi.erp.purchase.domain.vo.PurchaseOrderDetailVo;
import com.ruoyi.erp.purchase.domain.bo.PurchaseOrderDetailBo;
import com.ruoyi.erp.purchase.service.PurchaseOrderDetailService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 采购订单明细
 *
 * @author zcc
 * @date 2025-02-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase/orderDetail")
public class PurchaseOrderDetailController extends BaseController {

    private final PurchaseOrderDetailService purchaseOrderDetailService;

    /**
     * 查询采购订单明细列表
     */
    @SaCheckPermission("purchase:order:all")
    @GetMapping("/list")
    public TableDataInfo<PurchaseOrderDetailVo> list(PurchaseOrderDetailBo bo, PageQuery pageQuery) {
        return purchaseOrderDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出采购订单明细列表
     */
    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PurchaseOrderDetailBo bo, HttpServletResponse response) {
        List<PurchaseOrderDetailVo> list = purchaseOrderDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "采购订单明细", PurchaseOrderDetailVo.class, response);
    }

    /**
     * 获取采购订单明细详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("purchase:order:all")
    @GetMapping("/{id}")
    public R<PurchaseOrderDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(purchaseOrderDetailService.queryById(id));
    }

    /**
     * 新增采购订单明细
     */
    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单明细", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PurchaseOrderDetailBo bo) {
        purchaseOrderDetailService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改采购订单明细
     */
    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单明细", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PurchaseOrderDetailBo bo) {
        purchaseOrderDetailService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除采购订单明细
     *
     * @param ids 主键串
     */
    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        purchaseOrderDetailService.deleteByIds(List.of(ids));
        return R.ok();
    }

    @SaCheckPermission("purchase:order:all")
    @GetMapping("/listByOrderId/{orderId}")
    public R<List<PurchaseOrderDetailVo>> listByOrderId(@NotNull @PathVariable Long orderId) {
        return R.ok(purchaseOrderDetailService.queryByPid(orderId));
    }
}

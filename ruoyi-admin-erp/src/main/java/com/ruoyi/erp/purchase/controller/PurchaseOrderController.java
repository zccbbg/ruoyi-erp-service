package com.ruoyi.erp.purchase.controller;

import java.util.List;

import com.ruoyi.erp.base.constant.ServiceConstants;
import com.ruoyi.common.core.exception.ServiceException;
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
import com.ruoyi.erp.purchase.domain.vo.PurchaseOrderVo;
import com.ruoyi.erp.purchase.domain.bo.PurchaseOrderBo;
import com.ruoyi.erp.purchase.service.PurchaseOrderService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 采购订单
 *
 * @author zcc
 * @date 2025-02-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase/order")
public class PurchaseOrderController extends BaseController {

    private final PurchaseOrderService purchaseOrderService;

    /**
     * 查询采购订单列表
     */
    @SaCheckPermission("purchase:order:all")
    @GetMapping("/list")
    public TableDataInfo<PurchaseOrderVo> list(PurchaseOrderBo bo, PageQuery pageQuery) {
        return purchaseOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出采购订单列表
     */
    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PurchaseOrderBo bo, HttpServletResponse response) {
        List<PurchaseOrderVo> list = purchaseOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "采购订单", PurchaseOrderVo.class, response);
    }

    /**
     * 获取采购订单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("purchase:order:all")
    @GetMapping("/{id}")
    public R<PurchaseOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(purchaseOrderService.queryById(id));
    }

    /**
     * 新增采购订单
     */
    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PurchaseOrderBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        purchaseOrderService.insertByBo(bo);
        return R.ok();
    }

    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/pass")
    public R<Void> pass(@Validated(AddGroup.class) @RequestBody PurchaseOrderBo bo) {
        if(bo.getMerchantId()==null){
            throw new ServiceException("请选择商家！");
        }
        bo.setCheckedStatus(ServiceConstants.Status.FINISH);
        bo.setStockStatus(ServiceConstants.Status.PENDING);
        purchaseOrderService.pass(bo);
        return R.ok();
    }

    /**
     * 修改采购订单
     */
    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PurchaseOrderBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        purchaseOrderService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除采购订单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        purchaseOrderService.deleteByIds(List.of(ids));
        return R.ok();
    }
    /**
     * 根据id更新订单状态
     */
    @SaCheckPermission("purchase:order:all")
    @Log(title = "采购订单", businessType = BusinessType.UPDATE)
    @PostMapping("/finishStock")
    public R<Void> finishStockById(@RequestParam Long id) {
        purchaseOrderService.finishStockById(id);
        return R.ok();
    }

}

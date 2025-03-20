package com.ruoyi.erp.purchase.controller;

import java.util.List;

import com.ruoyi.common.core.constant.ServiceConstants;
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
import com.ruoyi.erp.purchase.domain.vo.PurchaseRefundVo;
import com.ruoyi.erp.purchase.domain.bo.PurchaseRefundBo;
import com.ruoyi.erp.purchase.service.PurchaseRefundService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 采购退货单
 *
 * @author zcc
 * @date 2025-02-07
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/purchase/refund")
public class PurchaseRefundController extends BaseController {

    private final PurchaseRefundService purchaseRefundService;

    /**
     * 查询采购退货单列表
     */
    @SaCheckPermission("purchase:refund:list")
    @GetMapping("/list")
    public TableDataInfo<PurchaseRefundVo> list(PurchaseRefundBo bo, PageQuery pageQuery) {
        return purchaseRefundService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出采购退货单列表
     */
    @SaCheckPermission("purchase:refund:export")
    @Log(title = "采购退货单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PurchaseRefundBo bo, HttpServletResponse response) {
        List<PurchaseRefundVo> list = purchaseRefundService.queryList(bo);
        ExcelUtil.exportExcel(list, "采购退货单", PurchaseRefundVo.class, response);
    }

    /**
     * 获取采购退货单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("purchase:refund:query")
    @GetMapping("/{id}")
    public R<PurchaseRefundVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(purchaseRefundService.queryById(id));
    }

    /**
     * 新增采购退货单
     */
    @SaCheckPermission("purchase:refund:add")
    @Log(title = "采购退货单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PurchaseRefundBo bo) {
        purchaseRefundService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改采购退货单
     */
    @SaCheckPermission("purchase:refund:edit")
    @Log(title = "采购退货单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PurchaseRefundBo bo) {
        purchaseRefundService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除采购退货单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("purchase:refund:remove")
    @Log(title = "采购退货单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        purchaseRefundService.deleteByIds(List.of(ids));
        return R.ok();
    }
    /**
     * 完成采购退货单
     */
    @SaCheckPermission("purchase:refund:all")
    @Log(title = "采购退货单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/pass")
    public R<Void> pass(@Validated(AddGroup.class) @RequestBody PurchaseRefundBo bo) {
        if(bo.getMerchantId()==null){
            throw new ServiceException("请选择商家！");
        }
        bo.setCheckedStatus(ServiceConstants.Status.FINISH);
        purchaseRefundService.pass(bo);
        return R.ok();
    }
}

package com.ruoyi.erp.financial.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.erp.base.constant.ServiceConstants;
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
import com.ruoyi.erp.financial.domain.bo.PaymentVoucherBo;

import com.ruoyi.erp.financial.domain.vo.PaymentVoucherVo;

import com.ruoyi.erp.financial.service.PaymentVoucherService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 付款单
 *
 * @author zcc
 * @date 2025-02-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/financial/paymentVoucher")
public class PaymentVoucherController extends BaseController {

    private final PaymentVoucherService paymentVoucherService;

    /**
     * 查询付款单列表
     */
    @SaCheckPermission("financial:paymentVoucher:list")
    @GetMapping("/list")
    public TableDataInfo<PaymentVoucherVo> list(PaymentVoucherBo bo, PageQuery pageQuery) {
        return paymentVoucherService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出付款单列表
     */
    @SaCheckPermission("financial:paymentVoucher:export")
    @Log(title = "付款单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(PaymentVoucherBo bo, HttpServletResponse response) {
        List<PaymentVoucherVo> list = paymentVoucherService.queryList(bo);
        ExcelUtil.exportExcel(list, "收款单", PaymentVoucherVo.class, response);
    }

    /**
     * 获取付款单详细信息
     *
     * @param id 主键
     */

    @SaCheckPermission("financial:paymentVoucher:query")
    @GetMapping("/{id}")
    public R<PaymentVoucherVo> getInfo(@NotNull(message = "主键不能为空")
                                       @PathVariable Long id) {
        return R.ok(paymentVoucherService.queryById(id));
    }

    /**
     * 新增付款单
     */
    @SaCheckPermission("financial:paymentVoucher:add")
    @Log(title = "付款单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody PaymentVoucherBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        paymentVoucherService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改付款单
     */
    @SaCheckPermission("financial:paymentVoucher:edit")
    @Log(title = "付款单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody PaymentVoucherBo bo) {
        paymentVoucherService.updateByBo(bo);
        return R.ok();
    }
    /**
     * 删除收款单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("financial:paymentVoucher:remove")
    @Log(title = "付款单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        paymentVoucherService.deleteByIds(List.of(ids));
        return R.ok();
    }


    /**
     * 付款完成
     */
    @SaCheckPermission("financial:paymentVoucher:all")
    @Log(title = "付款单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/finish")
    public R<Void> finish(@Validated(AddGroup.class) @RequestBody PaymentVoucherBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.FINISH);
        paymentVoucherService.finish(bo);
        return R.ok();
    }

}

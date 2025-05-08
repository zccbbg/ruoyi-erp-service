package com.ruoyi.erp.financial.controller;

import java.util.List;

import com.ruoyi.erp.base.constant.ServiceConstants;
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
import com.ruoyi.erp.financial.domain.vo.ReceiptVoucherVo;
import com.ruoyi.erp.financial.domain.bo.ReceiptVoucherBo;
import com.ruoyi.erp.financial.service.ReceiptVoucherService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 收款单
 *
 * @author zcc
 * @date 2025-02-14
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/financial/receiptVoucher")
public class ReceiptVoucherController extends BaseController {

    private final ReceiptVoucherService receiptVoucherService;

    /**
     * 查询收款单列表
     */
    @SaCheckPermission("financial:receiptVoucher:list")
    @GetMapping("/list")
    public TableDataInfo<ReceiptVoucherVo> list(ReceiptVoucherBo bo, PageQuery pageQuery) {
        return receiptVoucherService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出收款单列表
     */
    @SaCheckPermission("financial:receiptVoucher:export")
    @Log(title = "收款单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ReceiptVoucherBo bo, HttpServletResponse response) {
        List<ReceiptVoucherVo> list = receiptVoucherService.queryList(bo);
        ExcelUtil.exportExcel(list, "收款单", ReceiptVoucherVo.class, response);
    }

    /**
     * 获取收款单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("financial:receiptVoucher:query")
    @GetMapping("/{id}")
    public R<ReceiptVoucherVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(receiptVoucherService.queryById(id));
    }

    /**
     * 新增收款单
     */
    @SaCheckPermission("financial:receiptVoucher:add")
    @Log(title = "收款单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ReceiptVoucherBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        receiptVoucherService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 收款完成
     */
    @SaCheckPermission("financial:receiptVoucher:all")
    @Log(title = "收款单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/finish")
    public R<Void> finish(@Validated(AddGroup.class) @RequestBody ReceiptVoucherBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.FINISH);
        receiptVoucherService.finish(bo);
        return R.ok();
    }

    /**
     * 修改收款单
     */
    @SaCheckPermission("financial:receiptVoucher:edit")
    @Log(title = "收款单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ReceiptVoucherBo bo) {
        receiptVoucherService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除收款单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("financial:receiptVoucher:remove")
    @Log(title = "收款单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        receiptVoucherService.deleteByIds(List.of(ids));
        return R.ok();
    }
}

package com.ruoyi.erp.basic.controller;

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
import com.ruoyi.erp.basic.domain.vo.BasicBankAccountVo;
import com.ruoyi.erp.basic.domain.bo.BasicBankAccountBo;
import com.ruoyi.erp.basic.service.BasicBankAccountService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 银行账户
 *
 * @author zcc
 * @date 2025-02-13
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/basic/bankAccount")
public class BasicBankAccountController extends BaseController {

    private final BasicBankAccountService basicBankAccountService;

    /**
     * 查询银行账户列表
     */
    @SaCheckPermission("basic:bankAccount:list")
    @GetMapping("/list")
    public TableDataInfo<BasicBankAccountVo> list(BasicBankAccountBo bo, PageQuery pageQuery) {
        return basicBankAccountService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出银行账户列表
     */
    @SaCheckPermission("basic:bankAccount:export")
    @Log(title = "银行账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BasicBankAccountBo bo, HttpServletResponse response) {
        List<BasicBankAccountVo> list = basicBankAccountService.queryList(bo);
        ExcelUtil.exportExcel(list, "银行账户", BasicBankAccountVo.class, response);
    }

    /**
     * 获取银行账户详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("basic:bankAccount:query")
    @GetMapping("/{id}")
    public R<BasicBankAccountVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(basicBankAccountService.queryById(id));
    }

    /**
     * 新增银行账户
     */
    @SaCheckPermission("basic:bankAccount:add")
    @Log(title = "银行账户", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BasicBankAccountBo bo) {
        basicBankAccountService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改银行账户
     */
    @SaCheckPermission("basic:bankAccount:edit")
    @Log(title = "银行账户", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BasicBankAccountBo bo) {
        basicBankAccountService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除银行账户
     *
     * @param ids 主键串
     */
    @SaCheckPermission("basic:bankAccount:remove")
    @Log(title = "银行账户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        basicBankAccountService.deleteByIds(List.of(ids));
        return R.ok();
    }
}

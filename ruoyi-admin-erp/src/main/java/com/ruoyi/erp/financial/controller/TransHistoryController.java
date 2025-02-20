package com.ruoyi.erp.financial.controller;

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
import com.ruoyi.erp.financial.domain.vo.TransHistoryVo;
import com.ruoyi.erp.financial.domain.bo.TransHistoryBo;
import com.ruoyi.erp.financial.service.TransHistoryService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 交易流水
 *
 * @author zcc
 * @date 2025-02-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/financial/transHistory")
public class TransHistoryController extends BaseController {

    private final TransHistoryService transHistoryService;

    /**
     * 查询交易流水列表
     */
    @SaCheckPermission("financial:transHistory:list")
    @GetMapping("/list")
    public TableDataInfo<TransHistoryVo> list(TransHistoryBo bo, PageQuery pageQuery) {
        return transHistoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出交易流水列表
     */
    @SaCheckPermission("financial:transHistory:export")
    @Log(title = "交易流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TransHistoryBo bo, HttpServletResponse response) {
        List<TransHistoryVo> list = transHistoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "交易流水", TransHistoryVo.class, response);
    }

    /**
     * 获取交易流水详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("financial:transHistory:query")
    @GetMapping("/{id}")
    public R<TransHistoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(transHistoryService.queryById(id));
    }

    /**
     * 新增交易流水
     */
    @SaCheckPermission("financial:transHistory:add")
    @Log(title = "交易流水", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TransHistoryBo bo) {
        transHistoryService.insertByBo(bo);
        return R.ok();
    }
}

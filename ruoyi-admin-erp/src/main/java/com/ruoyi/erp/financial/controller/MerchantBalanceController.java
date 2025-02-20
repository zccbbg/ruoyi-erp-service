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
import com.ruoyi.erp.financial.domain.vo.MerchantBalanceVo;
import com.ruoyi.erp.financial.domain.bo.MerchantBalanceBo;
import com.ruoyi.erp.financial.service.MerchantBalanceService;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;

/**
 * 商家余额
 *
 * @author zcc
 * @date 2025-02-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/financial/merchantBalance")
public class MerchantBalanceController extends BaseController {

    private final MerchantBalanceService merchantBalanceService;

    /**
     * 查询商家余额列表
     */
    @SaCheckPermission("financial:merchantBalance:list")
    @GetMapping("/list")
    public TableDataInfo<MerchantBalanceVo> list(MerchantBalanceBo bo, PageQuery pageQuery) {
        return merchantBalanceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商家余额列表
     */
    @SaCheckPermission("financial:merchantBalance:export")
    @Log(title = "商家余额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MerchantBalanceBo bo, HttpServletResponse response) {
        List<MerchantBalanceVo> list = merchantBalanceService.queryList(bo);
        ExcelUtil.exportExcel(list, "商家余额", MerchantBalanceVo.class, response);
    }

    /**
     * 获取商家余额详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("financial:merchantBalance:query")
    @GetMapping("/{id}")
    public R<MerchantBalanceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(merchantBalanceService.queryById(id));
    }

    /**
     * 新增商家余额
     */
    @SaCheckPermission("financial:merchantBalance:add")
    @Log(title = "商家余额", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MerchantBalanceBo bo) {
        merchantBalanceService.insertByBo(bo);
        return R.ok();
    }
}

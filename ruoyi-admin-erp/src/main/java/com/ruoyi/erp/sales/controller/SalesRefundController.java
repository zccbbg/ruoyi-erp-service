package com.ruoyi.erp.sales.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.core.constant.ServiceConstants;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.idempotent.annotation.RepeatSubmit;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.web.core.BaseController;
import com.ruoyi.erp.sales.domain.bo.SalesRefundBo;
import com.ruoyi.erp.sales.domain.vo.SalesRefundVo;
import com.ruoyi.erp.sales.service.SalesRefundService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售退货单
 *
 * @date 2025-03-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sales/refund")
public class SalesRefundController extends BaseController {

    private final SalesRefundService salesRefundService;

    /**
     * 查询销售退货单列表
     */
    @SaCheckPermission("sales:refund:list")
    @GetMapping("/list")
    public TableDataInfo<SalesRefundVo> list(SalesRefundBo bo, PageQuery pageQuery) {
        return salesRefundService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出销售退货单列表
     */
    @SaCheckPermission("sales:refund:export")
    @Log(title = "销售退货单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SalesRefundBo bo, HttpServletResponse response) {
        List<SalesRefundVo> list = salesRefundService.queryList(bo);
        ExcelUtil.exportExcel(list, "销售退货单", SalesRefundVo.class, response);
    }

    /**
     * 获取销售退货单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("sales:refund:query")
    @GetMapping("/{id}")
    public R<SalesRefundVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(salesRefundService.queryById(id));
    }

    /**
     * 新增销售退货单
     */
    @SaCheckPermission("sales:refund:add")
    @Log(title = "销售退货单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SalesRefundBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        salesRefundService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改销售退货单
     */
    @SaCheckPermission("sales:refund:edit")
    @Log(title = "销售退货单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SalesRefundBo bo) {
        bo.setCheckedStatus(ServiceConstants.Status.PENDING);
        salesRefundService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除销售退货单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("sales:refund:remove")
    @Log(title = "销售退货单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        salesRefundService.deleteByIds(List.of(ids));
        return R.ok();
    }
    /**
     * 完成销售退货单
     */
    @SaCheckPermission("sales:refund:all")
    @Log(title = "销售退货单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/pass")
    public R<Void> pass(@Validated(AddGroup.class) @RequestBody SalesRefundBo bo) {
        if(bo.getMerchantId()==null){
            throw new ServiceException("请选择商家！");
        }
        bo.setCheckedStatus(ServiceConstants.Status.FINISH);
        salesRefundService.pass(bo);
        return R.ok();
    }
}

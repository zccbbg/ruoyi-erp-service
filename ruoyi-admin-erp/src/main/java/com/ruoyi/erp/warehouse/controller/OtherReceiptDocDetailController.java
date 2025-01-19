package com.ruoyi.erp.warehouse.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
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
import com.ruoyi.erp.warehouse.domain.bo.OtherReceiptDocDetailBo;
import com.ruoyi.erp.warehouse.domain.vo.OtherReceiptDocDetailVo;
import com.ruoyi.erp.warehouse.service.OtherReceiptDocDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入库单详情
 *
 * @author zcc
 * @date 2024-07-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/receiptDetail")
public class OtherReceiptDocDetailController extends BaseController {

    private final OtherReceiptDocDetailService otherReceiptDocDetailService;

    /**
     * 查询入库单详情列表
     */
    @SaCheckPermission("wms:receipt:all")
    @GetMapping("/list")
    public TableDataInfo<OtherReceiptDocDetailVo> list(OtherReceiptDocDetailBo bo, PageQuery pageQuery) {
        return otherReceiptDocDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出入库单详情列表
     */
    @SaCheckPermission("wms:receipt:all")
    @Log(title = "入库单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OtherReceiptDocDetailBo bo, HttpServletResponse response) {
        List<OtherReceiptDocDetailVo> list = otherReceiptDocDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "入库单详情", OtherReceiptDocDetailVo.class, response);
    }

    /**
     * 获取入库单详情详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:receipt:all")
    @GetMapping("/{id}")
    public R<OtherReceiptDocDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(otherReceiptDocDetailService.queryById(id));
    }

    /**
     * 新增入库单详情
     */
    @SaCheckPermission("wms:receipt:all")
    @Log(title = "入库单详情", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OtherReceiptDocDetailBo bo) {
        otherReceiptDocDetailService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改入库单详情
     */
    @SaCheckPermission("wms:receipt:all")
    @Log(title = "入库单详情", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OtherReceiptDocDetailBo bo) {
        otherReceiptDocDetailService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除入库单详情
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wms:receipt:all")
    @Log(title = "入库单详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        otherReceiptDocDetailService.deleteByIds(List.of(ids));
        return R.ok();
    }

    /**
     * 根据入库单id查询入库单详情列表
     */
    @SaCheckPermission("wms:receipt:all")
    @GetMapping("/list/{receiptDocId}")
    public R<List<OtherReceiptDocDetailVo>> listByReceiptDocId(@NotNull @PathVariable Long receiptDocId) {
        return R.ok(otherReceiptDocDetailService.queryByReceiptDocId(receiptDocId));
    }
}

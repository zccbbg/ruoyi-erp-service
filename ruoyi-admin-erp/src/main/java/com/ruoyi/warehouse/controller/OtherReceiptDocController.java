package com.ruoyi.warehouse.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.core.constant.ServiceConstants;
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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.warehouse.domain.bo.ReceiptDocBo;
import com.ruoyi.warehouse.domain.vo.ReceiptDocVo;
import com.ruoyi.warehouse.service.OtherReceiptDocService;

import java.util.List;

/**
 * 入库单
 *
 * @author zcc
 * @date 2024-07-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/receiptOrder")
public class OtherReceiptDocController extends BaseController {

    private final OtherReceiptDocService otherReceiptDocService;

    /**
     * 查询入库单列表
     */
    @SaCheckPermission("wms:receipt:all")
    @GetMapping("/list")
    public TableDataInfo<ReceiptDocVo> list(ReceiptDocBo bo, PageQuery pageQuery) {
        return otherReceiptDocService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出入库单列表
     */
    @SaCheckPermission("wms:receipt:all")
    @Log(title = "入库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ReceiptDocBo bo, HttpServletResponse response) {
        List<ReceiptDocVo> list = otherReceiptDocService.queryList(bo);
        ExcelUtil.exportExcel(list, "入库单", ReceiptDocVo.class, response);
    }

    /**
     * 获取入库单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:receipt:all")
    @GetMapping("/{id}")
    public R<ReceiptDocVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(otherReceiptDocService.queryById(id));
    }

    /**
     * 暂存入库单
     */
    @SaCheckPermission("wms:receipt:all")
    @Log(title = "入库单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ReceiptDocBo bo) {
        bo.setOrderStatus(ServiceConstants.ReceiptOrderStatus.PENDING);
        otherReceiptDocService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 入库
     */
    @SaCheckPermission("wms:receipt:all")
    @Log(title = "入库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("/warehousing")
    public R<Void> doWarehousing(@Validated(AddGroup.class) @RequestBody ReceiptDocBo bo) {
        bo.setOrderStatus(ServiceConstants.ReceiptOrderStatus.FINISH);
        otherReceiptDocService.receive(bo);
        return R.ok();
    }

    /**
     * 修改入库单
     */
    @SaCheckPermission("wms:receipt:all")
    @Log(title = "入库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ReceiptDocBo bo) {
        otherReceiptDocService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除入库单
     *
     * @param id 主键串
     */
    @SaCheckPermission("wms:receipt:all")
    @Log(title = "入库单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public R<Void> remove(@NotNull(message = "主键不能为空")
                          @PathVariable Long id) {
        otherReceiptDocService.deleteById(id);
        return R.ok();
    }
}

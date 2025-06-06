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
import com.ruoyi.erp.warehouse.domain.bo.OtherShipmentDocDetailBo;
import com.ruoyi.erp.warehouse.domain.vo.OtherShipmentDocDetailVo;
import com.ruoyi.erp.warehouse.service.OtherShipmentDocDetailService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库单详情
 *
 * @author zcc
 * @date 2024-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/shipmentDetail")
public class OtherShipmentDocDetailController extends BaseController {

    private final OtherShipmentDocDetailService otherShipmentDocDetailService;

    /**
     * 查询出库单详情列表
     */
    @SaCheckPermission("wms:shipment:all")
    @GetMapping("/list")
    public TableDataInfo<OtherShipmentDocDetailVo> list(OtherShipmentDocDetailBo bo, PageQuery pageQuery) {
        return otherShipmentDocDetailService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出出库单详情列表
     */
    @SaCheckPermission("wms:shipment:all")
    @Log(title = "出库单详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OtherShipmentDocDetailBo bo, HttpServletResponse response) {
        List<OtherShipmentDocDetailVo> list = otherShipmentDocDetailService.queryList(bo);
        ExcelUtil.exportExcel(list, "出库单详情", OtherShipmentDocDetailVo.class, response);
    }

    /**
     * 获取出库单详情详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:shipment:all")
    @GetMapping("/{id}")
    public R<OtherShipmentDocDetailVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(otherShipmentDocDetailService.queryById(id));
    }

    /**
     * 新增出库单详情
     */
    @SaCheckPermission("wms:shipment:all")
    @Log(title = "出库单详情", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OtherShipmentDocDetailBo bo) {
        otherShipmentDocDetailService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改出库单详情
     */
    @SaCheckPermission("wms:shipment:all")
    @Log(title = "出库单详情", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OtherShipmentDocDetailBo bo) {
        otherShipmentDocDetailService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除出库单详情
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wms:shipment:all")
    @Log(title = "出库单详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        otherShipmentDocDetailService.deleteByIds(List.of(ids));
        return R.ok();
    }

    /**
     * 获取出库单详情详细信息
     *
     */
    @SaCheckPermission("wms:shipment:all")
    @GetMapping("/list/{shipmentDocId}")
    public R<List<OtherShipmentDocDetailVo>> listByShipmentDocId(@NotNull @PathVariable Long shipmentDocId) {
        return R.ok(otherShipmentDocDetailService.queryByShipmentDocId(shipmentDocId));
    }
}

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
import com.ruoyi.warehouse.domain.bo.OtherShipmentDocBo;
import com.ruoyi.warehouse.domain.vo.OtherShipmentDocVo;
import com.ruoyi.warehouse.service.OtherShipmentDocService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库单
 *
 * @author zcc
 * @date 2024-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/shipmentOrder")
public class OtherShipmentDocController extends BaseController {

    private final OtherShipmentDocService otherShipmentDocService;

    /**
     * 查询出库单列表
     */
    @SaCheckPermission("wms:shipment:all")
    @GetMapping("/list")
    public TableDataInfo<OtherShipmentDocVo> list(OtherShipmentDocBo bo, PageQuery pageQuery) {
        return otherShipmentDocService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出出库单列表
     */
    @SaCheckPermission("wms:shipment:all")
    @Log(title = "出库单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(OtherShipmentDocBo bo, HttpServletResponse response) {
        List<OtherShipmentDocVo> list = otherShipmentDocService.queryList(bo);
        ExcelUtil.exportExcel(list, "出库单", OtherShipmentDocVo.class, response);
    }

    /**
     * 获取出库单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:shipment:all")
    @GetMapping("/{id}")
    public R<OtherShipmentDocVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(otherShipmentDocService.queryById(id));
    }

    /**
     * 新增出库单
     */
    @SaCheckPermission("wms:shipment:all")
    @Log(title = "出库单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OtherShipmentDocBo bo) {
        otherShipmentDocService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改出库单
     */
    @SaCheckPermission("wms:shipment:all")
    @Log(title = "出库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OtherShipmentDocBo bo) {
        otherShipmentDocService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 出库
     */
    @SaCheckPermission("wms:shipment:all")
    @Log(title = "出库单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping("/shipment")
    public R<Void> shipment(@Validated(AddGroup.class) @RequestBody OtherShipmentDocBo bo) {
        bo.setOrderStatus(ServiceConstants.ShipmentOrderStatus.FINISH);
        otherShipmentDocService.shipment(bo);
        return R.ok();
    }

    /**
     * 删除出库单
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:shipment:all")
    @Log(title = "出库单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public R<Void> remove(@NotNull(message = "主键不能为空")
                          @PathVariable Long id) {
        otherShipmentDocService.deleteById(id);
        return R.ok();
    }
}

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
import com.ruoyi.erp.basic.service.SkuService;
import com.ruoyi.erp.purchase.domain.bo.PurchaseTradeDetailBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseTradeDetailVo;
import com.ruoyi.erp.purchase.service.PurchaseTradeDetailService;
import com.ruoyi.erp.sales.service.SalesOrderDetailService;
import com.ruoyi.erp.warehouse.domain.bo.InventoryBo;
import com.ruoyi.erp.warehouse.service.InventoryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.erp.warehouse.domain.vo.InventoryVo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 库存
 *
 * @author zcc
 * @date 2024-07-19
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wms/inventory")
public class InventoryController extends BaseController {

    private final InventoryService inventoryService;
    private final PurchaseTradeDetailService purchaseTradeDetailService;
    private final SalesOrderDetailService salesOrderDetailService;

    /**
     * 查询库存列表商品维度
     */
    @SaCheckPermission("wms:inventory:all")
    @GetMapping(value = {"/boardList/item"})
    public TableDataInfo<InventoryVo> queryItemBoardList(InventoryBo bo, PageQuery pageQuery) {
        return inventoryService.queryItemBoardList(bo, pageQuery);
    }

    /**
     * 查询库存列表仓库维度
     */
    @SaCheckPermission("wms:inventory:all")
    @GetMapping("/boardList/warehouse")
    public TableDataInfo<InventoryVo> queryWarehouseBoardList(InventoryBo bo, PageQuery pageQuery) {
        return inventoryService.queryWarehouseBoardList(bo, pageQuery);
    }

    @SaCheckPermission("wms:inventory:all")
    @GetMapping("/boardList/warehouse/tradeId")
    public TableDataInfo<InventoryVo> queryWarehouseBoardList(InventoryBo bo, Long tradeId,PageQuery pageQuery) {
        if(tradeId == null){
            return inventoryService.queryWarehouseBoardList(bo, pageQuery);
        }else {
            Set<Long> skuIds = purchaseTradeDetailService.getSkuIds(tradeId);
            return inventoryService.queryWarehouseBoardList(bo, pageQuery,skuIds);
        }
    }
    @SaCheckPermission("wms:inventory:all")
    @GetMapping("/boardList/warehouse/orderId")
    public TableDataInfo<InventoryVo> queryWarehouseBoardListByOrderId(InventoryBo bo, Long orderId,PageQuery pageQuery) {
        if(orderId == null){
            return inventoryService.queryWarehouseBoardList(bo, pageQuery);
        }else {
            Set<Long> skuIds = salesOrderDetailService.getSkuIds(orderId);
            return inventoryService.queryWarehouseBoardList(bo, pageQuery,skuIds);
        }
    }

    /**
     * 查询库存列表商品维度
     */
    @SaCheckPermission("wms:inventory:all")
    @GetMapping(value = {"/listNoPage"})
    public R<List<InventoryVo>> listNoPage(InventoryBo bo) {
        return R.ok(inventoryService.queryList(bo));
    }

    /**
     * 导出库存列表
     */
    @SaCheckPermission("wms:inventory:all")
    @Log(title = "库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(InventoryBo bo, HttpServletResponse response) {
        List<InventoryVo> list = inventoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "库存", InventoryVo.class, response);
    }

    /**
     * 获取库存详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wms:inventory:all")
    @GetMapping("/{id}")
    public R<InventoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(inventoryService.queryById(id));
    }

    /**
     * 新增库存
     */
    @SaCheckPermission("wms:inventory:all")
    @Log(title = "库存", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody InventoryBo bo) {
        inventoryService.insertByBo(bo);
        return R.ok();
    }

    /**
     * 修改库存
     */
    @SaCheckPermission("wms:inventory:all")
    @Log(title = "库存", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody InventoryBo bo) {
        inventoryService.updateByBo(bo);
        return R.ok();
    }

    /**
     * 删除库存
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wms:inventory:all")
    @Log(title = "库存", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        inventoryService.deleteByIds(List.of(ids));
        return R.ok();
    }
}

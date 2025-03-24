package com.ruoyi.erp.warehouse.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.erp.basic.domain.vo.SkuMapVo;
import com.ruoyi.erp.basic.service.SkuService;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.base.domain.bo.BaseDocDetailBo;
import com.ruoyi.erp.warehouse.domain.bo.CheckDocDetailBo;
import com.ruoyi.erp.warehouse.domain.bo.InventoryBo;
import com.ruoyi.erp.warehouse.domain.entity.Inventory;
import com.ruoyi.erp.warehouse.domain.vo.InventoryVo;
import com.ruoyi.erp.warehouse.mapper.InventoryMapper;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 库存Service业务层处理
 *
 * @author zcc
 * @date 2024-07-19
 */
@RequiredArgsConstructor
@Service
public class InventoryService extends ServiceImpl<InventoryMapper, Inventory> {

    private final InventoryMapper inventoryMapper;
    private final SkuService skuService;

    /**
     * 查询库存
     */
    public InventoryVo queryById(Long id){
        return inventoryMapper.selectVoById(id);
    }

    /**
     * 查询库存列表
     */
    public List<InventoryVo> queryList(InventoryBo bo) {
        LambdaQueryWrapper<Inventory> lqw = buildQueryWrapper(bo);
        List<InventoryVo> vos = inventoryMapper.selectVoList(lqw);
        if(CollUtil.isNotEmpty(vos)){
            Set<Long> skuIds = vos.stream().map(InventoryVo::getSkuId).collect(Collectors.toSet());
            Map<Long, SkuMapVo> itemSkuMap = skuService.querySkuMapVosByIds(skuIds);
            vos.forEach(it -> {
                SkuMapVo skuMapVo = itemSkuMap.get(it.getSkuId());
                it.setSku(skuMapVo.getSku());
                it.setGoods(skuMapVo.getGoods());
            });
        }
        return vos;
    }

    private LambdaQueryWrapper<Inventory> buildQueryWrapper(InventoryBo bo) {
        LambdaQueryWrapper<Inventory> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(bo.getSkuId() != null, Inventory::getSkuId, bo.getSkuId());
        wrapper.eq(bo.getWarehouseId() != null, Inventory::getWarehouseId, bo.getWarehouseId());
        wrapper.eq(bo.getQty() != null, Inventory::getQty, bo.getQty());
        return wrapper;
    }

    /**
     * 新增库存
     */
    public void insertByBo(InventoryBo bo) {
        Inventory add = MapstructUtils.convert(bo, Inventory.class);
        inventoryMapper.insert(add);
    }

    /**
     * 修改库存
     */
    public void updateByBo(InventoryBo bo) {
        Inventory update = MapstructUtils.convert(bo, Inventory.class);
        inventoryMapper.updateById(update);
    }

    /**
     * 批量删除库存
     */
    public void deleteByIds(Collection<Long> ids) {
        inventoryMapper.deleteBatchIds(ids);
    }

    /**
     * 校验规格是否有库存
     * @param skuIds
     * @return
     */
    public boolean existsBySkuIds(@NotEmpty Collection<Long> skuIds) {
        LambdaQueryWrapper<Inventory> lqw = Wrappers.lambdaQuery();
        lqw.in(Inventory::getSkuId, skuIds);
        return inventoryMapper.exists(lqw);
    }

    public TableDataInfo<InventoryVo> queryWarehouseBoardList(InventoryBo bo, PageQuery pageQuery) {
            return TableDataInfo.build(inventoryMapper.queryWarehouseBoardList(pageQuery.build(), bo,null));
    }
    public TableDataInfo<InventoryVo> queryWarehouseBoardList(InventoryBo bo, PageQuery pageQuery,Set<Long> skuIds) {
        return TableDataInfo.build(inventoryMapper.queryWarehouseBoardList(pageQuery.build(), bo,skuIds));
    }

    public TableDataInfo<InventoryVo> queryItemBoardList(InventoryBo bo, PageQuery pageQuery) {
        Page<InventoryVo> result = inventoryMapper.queryGoodsBoardList(pageQuery.build(), bo);
        return TableDataInfo.build(result);
    }

    public void updateInventory(List<CheckDocDetailBo> details) {
        List<Inventory> updateInventoryList=new LinkedList<>();
        List<Inventory> insertInventoryList=new LinkedList<>();

        details.forEach(detail -> {
            LambdaQueryWrapper<Inventory> wrapper = Wrappers.lambdaQuery();
            if(detail.getInventoryId()!=null){
                wrapper.eq(Inventory::getId,detail.getInventoryId());
                Inventory inventory = inventoryMapper.selectOne(wrapper);
                if(inventory.getQty().compareTo(detail.getQty())!=0){
                    SkuMapVo skuMapVo = skuService.querySkuMapVo(detail.getSkuId());
                    throw new ServiceException(
                        "账面库存不匹配："+ skuMapVo.getGoods().getGoodsName()+"（"+ skuMapVo.getSku().getSkuName()+"）",
                        HttpStatus.CONFLICT,
                        "填写账面库存："+detail.getQty()+" 实际账面库存："+inventory.getQty());
                }else {
                    if(!inventory.getQty().equals(detail.getCheckQty())){
                        inventory.setQty(detail.getCheckQty());
                        updateInventoryList.add(inventory);
                    }
                }
            }else{
                wrapper.eq(Inventory::getSkuId,detail.getSkuId());
                wrapper.eq(Inventory::getWarehouseId,detail.getWarehouseId());
                Inventory inventory = inventoryMapper.selectOne(wrapper);
                if(inventory != null){
                    SkuMapVo skuMapVo = skuService.querySkuMapVo(detail.getSkuId());
                    throw new ServiceException(
                        "账面库存不匹配："+ skuMapVo.getGoods().getGoodsName()+"（"+ skuMapVo.getSku().getSkuName()+"）",
                        HttpStatus.CONFLICT,
                        "填写账面库存：0, 实际账面库存："+inventory.getQty());
                }else {
                    inventory = new Inventory();
                    inventory.setSkuId(detail.getSkuId());
                    inventory.setWarehouseId(detail.getWarehouseId());
                    inventory.setQty(detail.getCheckQty());
                    insertInventoryList.add(inventory);
                }
            }
        });
        if(CollUtil.isNotEmpty(updateInventoryList)){
            inventoryMapper.updateBatchById(updateInventoryList);
        }
        if(CollUtil.isNotEmpty(insertInventoryList)){
            inventoryMapper.insertBatch(insertInventoryList);
        }
    }

    @Transactional
    public void add(List<? extends BaseDocDetailBo> details) {
        List<Inventory> addList = new LinkedList<>();
        List<Inventory> updateList = new LinkedList<>();
        details.forEach(orderDetailsBo -> {
            LambdaQueryWrapper<Inventory> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(Inventory::getWarehouseId, orderDetailsBo.getWarehouseId());
            wrapper.eq(Inventory::getSkuId, orderDetailsBo.getSkuId());
            Inventory result = inventoryMapper.selectOne(wrapper);
            if(result!=null){
                BigDecimal before = result.getQty();
                BigDecimal after = before.add(orderDetailsBo.getQty());
                result.setQty(after);
                orderDetailsBo.setAfterQty(after);
                orderDetailsBo.setBeforeQty(before);
                updateList.add(result);
            }else {
                orderDetailsBo.setBeforeQty(BigDecimal.ZERO);
                orderDetailsBo.setAfterQty(orderDetailsBo.getQty());
                Inventory inventory = MapstructUtils.convert(orderDetailsBo, Inventory.class);
                addList.add(inventory);
            }
        });
        if (addList.size() > 0) {
            saveBatch(addList);
        }
        if (updateList.size() > 0) {
            updateBatchById(updateList);
        }
    }

    /**
     * 扣减库存
     * @param details
     */
    @Transactional
    public void subtract(List<? extends BaseDocDetailBo> details) {
        List<Inventory> updateList = new LinkedList<>();
        details.forEach(shipmentOrderDetailBo -> {
            LambdaQueryWrapper<Inventory> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(Inventory::getWarehouseId, shipmentOrderDetailBo.getWarehouseId());
            wrapper.eq(Inventory::getSkuId, shipmentOrderDetailBo.getSkuId());
            Inventory result = inventoryMapper.selectOne(wrapper);
            if(result==null){
                SkuMapVo skuMapVo = skuService.querySkuMapVo(shipmentOrderDetailBo.getSkuId());
                throw new ServiceException("库存不足", HttpStatus.CONFLICT, skuMapVo.getGoods().getGoodsName()+"（"+ skuMapVo.getSku().getSkuName()+"）库存不足，当前库存：0");
            }
            BigDecimal beforeQty = result.getQty();
            BigDecimal afterQty = beforeQty.subtract(shipmentOrderDetailBo.getQty());
            if(afterQty.signum() == -1){
                SkuMapVo skuMapVo = skuService.querySkuMapVo(shipmentOrderDetailBo.getSkuId());
                throw new ServiceException("库存不足", HttpStatus.CONFLICT, skuMapVo.getGoods().getGoodsName()+"（"+ skuMapVo.getSku().getSkuName()+"）库存不足，当前库存："+ beforeQty);
            }
            shipmentOrderDetailBo.setBeforeQty(beforeQty);
            shipmentOrderDetailBo.setAfterQty(afterQty);
            result.setQty(afterQty);
            updateList.add(result);
        });
        updateBatchById(updateList);
    }

    public boolean existsByWarehouseId(Long warehouseId) {
        LambdaQueryWrapper<Inventory> lqw = Wrappers.lambdaQuery();
        lqw.eq(Inventory::getWarehouseId, warehouseId);
        return inventoryMapper.exists(lqw);
    }
}

package com.ruoyi.erp.warehouse.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.base.domain.bo.BaseDocBo;
import com.ruoyi.erp.base.domain.bo.BaseDocDetailBo;
import com.ruoyi.erp.warehouse.domain.bo.InventoryHistoryBo;
import com.ruoyi.erp.warehouse.domain.query.InventoryHistoryQuery;
import com.ruoyi.erp.warehouse.domain.vo.InventoryHistoryVo;
import com.ruoyi.erp.warehouse.domain.entity.InventoryHistory;
import com.ruoyi.erp.warehouse.mapper.InventoryHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 库存记录Service业务层处理
 *
 * @author zcc
 * @date 2024-07-22
 */
@RequiredArgsConstructor
@Service
public class InventoryHistoryService extends ServiceImpl<InventoryHistoryMapper, InventoryHistory> {

    private final InventoryHistoryMapper inventoryHistoryMapper;

    public void saveInventoryHistory(BaseDocBo<? extends BaseDocDetailBo> bo, Integer orderType, Boolean isAdd){
        List<InventoryHistory> inventoryHistoryList = new LinkedList<>();
        bo.getDetails().forEach(detail -> {
            InventoryHistory inventoryHistory = new InventoryHistory();
            inventoryHistory.setOptId(bo.getId());
            inventoryHistory.setOptNo(bo.getDocNo());
            inventoryHistory.setOptType(orderType);
            inventoryHistory.setSkuId(detail.getSkuId());
            if(isAdd){
                inventoryHistory.setQty(detail.getQty());
            }else {
                inventoryHistory.setQty(detail.getQty().negate());
            }
            inventoryHistory.setWarehouseId(detail.getWarehouseId());
            inventoryHistory.setAmount(detail.getTotalAmount());
            inventoryHistory.setBeforeQty(detail.getBeforeQty());
            inventoryHistory.setAfterQty(detail.getAfterQty());
            inventoryHistoryList.add(inventoryHistory);
        });
        this.saveBatch(inventoryHistoryList);
    }

    /**
     * 查询库存记录
     */
    public InventoryHistoryVo queryById(Long id){
        return inventoryHistoryMapper.selectVoById(id);
    }

    /**
     * 查询库存记录列表
     */
    public TableDataInfo<InventoryHistoryVo> queryPageList(InventoryHistoryQuery query, PageQuery pageQuery) {
        Page<InventoryHistoryVo> result = inventoryHistoryMapper.selectVoPageByBo(pageQuery.build(), query);
        return TableDataInfo.build(result);
    }

    /**
     * 查询库存记录列表
     */
    public List<InventoryHistoryVo> queryList(InventoryHistoryBo bo) {
        LambdaQueryWrapper<InventoryHistory> lqw = buildQueryWrapper(bo);
        return inventoryHistoryMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<InventoryHistory> buildQueryWrapper(InventoryHistoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<InventoryHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOptId() != null, InventoryHistory::getOptId, bo.getOptId());
        lqw.eq(bo.getOptType() != null, InventoryHistory::getOptType, bo.getOptType());
        lqw.eq(bo.getSkuId() != null, InventoryHistory::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQty() != null, InventoryHistory::getQty, bo.getQty());
        lqw.eq(bo.getWarehouseId() != null, InventoryHistory::getWarehouseId, bo.getWarehouseId());
        return lqw;
    }

    /**
     * 新增库存记录
     */
    public void insertByBo(InventoryHistoryBo bo) {
        InventoryHistory add = MapstructUtils.convert(bo, InventoryHistory.class);
        inventoryHistoryMapper.insert(add);
    }

    /**
     * 修改库存记录
     */
    public void updateByBo(InventoryHistoryBo bo) {
        InventoryHistory update = MapstructUtils.convert(bo, InventoryHistory.class);
        inventoryHistoryMapper.updateById(update);
    }

    /**
     * 批量删除库存记录
     */
    public void deleteByIds(Collection<Long> ids) {
        inventoryHistoryMapper.deleteBatchIds(ids);
    }
}

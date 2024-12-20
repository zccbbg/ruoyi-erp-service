package com.ruoyi.erp.basic.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.basic.domain.bo.ItemSkuBo;
import com.ruoyi.erp.basic.domain.entity.Sku;
import com.ruoyi.erp.base.domain.vo.BaseDocDetailVo;
import com.ruoyi.erp.basic.domain.vo.ItemSkuMapVo;
import com.ruoyi.erp.basic.domain.vo.ItemSkuVo;
import com.ruoyi.erp.basic.mapper.ItemSkuMapper;
import com.ruoyi.erp.warehouse.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@Service
@Slf4j
public class ItemSkuService extends ServiceImpl<ItemSkuMapper, Sku> {


    private final ItemSkuMapper itemSkuMapper;
    private final InventoryService inventoryService;

    /**
     * 查询sku信息
     */

    public ItemSkuMapVo queryItemSkuMapVo(Long id) {
        return itemSkuMapper.queryItemSkuMapVo(id);
    }

    public ItemSkuVo queryById(Long id) {
        return itemSkuMapper.selectVoById(id);
    }


    /**
     * 查询sku信息列表，用于出入库的选择组件
     */

    public TableDataInfo<ItemSkuMapVo> queryPageList(ItemSkuBo bo, PageQuery pageQuery) {
        //开始查sku
        IPage<ItemSkuMapVo> result = itemSkuMapper.selectByBo(pageQuery.build(), bo);
        return TableDataInfo.build(result);
    }

    /**
     * 查询sku信息列表
     */

    public List<ItemSkuVo> queryList(ItemSkuBo bo) {
        LambdaQueryWrapper<Sku> lqw = buildQueryWrapper(bo);
        return itemSkuMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Sku> buildQueryWrapper(ItemSkuBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Sku> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getSkuName()), Sku::getSkuName, bo.getSkuName());
        lqw.eq(bo.getItemId() != null, Sku::getItemId, bo.getItemId());
        lqw.eq(StrUtil.isNotBlank(bo.getBarcode()), Sku::getBarcode, bo.getBarcode());
        lqw.orderByDesc(Sku::getItemId);
        return lqw;
    }

    /**
     * 新增sku信息
     */

    public Boolean insertByBo(ItemSkuBo bo) {
        Sku add = MapstructUtils.convert(bo, Sku.class);
        return itemSkuMapper.insert(add) > 0;
    }

    /**
     * 修改sku信息
     */

    public Boolean updateByBo(ItemSkuBo bo) {
        Sku update = MapstructUtils.convert(bo, Sku.class);
        return itemSkuMapper.updateById(update) > 0;
    }

    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        itemSkuMapper.deleteById(id);
    }

    private void validateIdBeforeDelete(Long id) {
        // 只有一个不能删除
        Sku sku = itemSkuMapper.selectById(id);

        if(queryByItemId(sku.getItemId()).size() <= 1){
            throw new ServiceException("删除失败", HttpStatus.CONFLICT,"至少包含一个商品规格！");
        }
        // 校验库存是否已关联
        if (inventoryService.existsBySkuIds(List.of(id))) {
            throw new ServiceException("删除失败", HttpStatus.CONFLICT,"该规格已有业务关联，无法删除！");
        }
    }

    private void validateSkuIdsBeforeDelete(Collection<Long> skuIds) {
        if (inventoryService.existsBySkuIds(skuIds)) {
            throw new ServiceException("删除失败", HttpStatus.CONFLICT,"该商品已有业务关联，无法删除！");
        }
    }
    /**
     * 批量删除sku信息
     */

    public void deleteByIds(Collection<Long> ids) {
        // 校验库存是否已关联
        validateSkuIdsBeforeDelete(ids);
        // 删除
        itemSkuMapper.deleteBatchIds(ids);
    }

    /**
     * 批量保存商品sku
     * @param sku    商品sku
     */
    @Transactional
    public void saveOrUpdateBatchByBo(List<ItemSkuBo> sku) {
        List<Sku> skuList = MapstructUtils.convert(sku, Sku.class);
        saveOrUpdateBatch(skuList);
    }

    public void setItemId(List<ItemSkuBo> itemSkuList,Long itemId) {
        for (ItemSkuBo itemSkuBo : itemSkuList) {
            if (StrUtil.isBlank(itemSkuBo.getBarcode())) {
                itemSkuBo.setItemId(itemId);
            }
        }
    }

    /**
     * 查询sku列表
     *
     * @param id 商品id
     */

    public List<ItemSkuVo> queryByItemId(Long id) {
        LambdaQueryWrapper<Sku> lqw = Wrappers.lambdaQuery();
        lqw.eq(Sku::getItemId, id);
        return itemSkuMapper.selectVoList(lqw);
    }

    public Map<Long, ItemSkuMapVo> queryItemSkuMapVosByIds(Set<Long> skuIds){
        return itemSkuMapper.queryItemSkuMapVos(skuIds).stream()
            .collect(Collectors.toMap(ItemSkuMapVo::getSkuId, Function.identity()));
    }

    public void setItemSkuMap(List<? extends BaseDocDetailVo> details){
        if (CollUtil.isNotEmpty(details)) {
            Set<Long> skuIds = details
                .stream()
                .map(BaseDocDetailVo::getSkuId)
                .collect(Collectors.toSet());

            Map<Long, ItemSkuMapVo> itemSkuMap = this.queryItemSkuMapVosByIds(skuIds);

            details.forEach(detail -> {
                    ItemSkuMapVo vo = itemSkuMap.get(detail.getSkuId());
                    detail.setItemSku(vo.getItemSku());
                    detail.setItem(vo.getItem());
            });
        }
    }
}

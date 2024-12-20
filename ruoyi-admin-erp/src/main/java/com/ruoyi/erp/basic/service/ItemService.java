package com.ruoyi.erp.basic.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.basic.domain.bo.ItemBo;
import com.ruoyi.erp.basic.domain.bo.ItemSkuBo;
import com.ruoyi.erp.basic.domain.entity.Goods;
import com.ruoyi.erp.basic.domain.entity.GoodsCategory;
import com.ruoyi.erp.basic.domain.vo.ItemCategoryVo;
import com.ruoyi.erp.basic.domain.vo.ItemSkuVo;
import com.ruoyi.erp.basic.domain.vo.ItemVo;
import com.ruoyi.erp.basic.mapper.ItemCategoryMapper;
import com.ruoyi.erp.basic.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemSkuService itemSkuService;
    private final ItemCategoryMapper itemCategoryMapper;

    /**
     * 查询物料
     */

    public ItemVo queryById(Long id) {
        return itemMapper.selectVoById(id);
    }

    /**
     * 查询物料
     *
     * @param itemIds ids
     */

    public List<ItemVo> queryById(List<Long> itemIds) {
        if (CollUtil.isEmpty(itemIds)) {
            return CollUtil.newArrayList();
        }
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.in(Goods::getId, itemIds);
        return itemMapper.selectVoList(lambdaQueryWrapper);
    }

    /**
     * 查询物料列表
     */

    public TableDataInfo<ItemVo> queryPageList(ItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Goods> lqw = buildQueryWrapper(bo);
        Page<ItemVo> result = itemMapper.selectVoPage(pageQuery.build(), lqw);
        List<ItemVo> itemVoList = result.getRecords();
        if (!CollUtil.isEmpty(itemVoList)) {
            LambdaQueryWrapper<GoodsCategory> itemTypeWrapper = new LambdaQueryWrapper<>();
            itemTypeWrapper.in(GoodsCategory::getId, itemVoList.stream().map(ItemVo::getItemCategory).collect(Collectors.toSet()));
            Map<Long, ItemCategoryVo> itemCategoryVoMap = itemCategoryMapper.selectVoList(itemTypeWrapper).stream().collect(Collectors.toMap(ItemCategoryVo::getId, Function.identity()));
            itemVoList.forEach(itemVo -> {
                itemVo.setItemCategoryInfo(itemCategoryVoMap.get(Long.valueOf(itemVo.getItemCategory())));
            });
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询物料列表
     */

    public List<ItemVo> queryList(ItemBo bo) {
        LambdaQueryWrapper<Goods> lqw = buildQueryWrapper(bo);
        return itemMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Goods> buildQueryWrapper(ItemBo bo) {
        LambdaQueryWrapper<Goods> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getItemCode()), Goods::getItemCode, bo.getItemCode());
        // 主键集合
        lqw.in(!CollUtil.isEmpty(bo.getIds()), Goods::getId, bo.getIds());
        lqw.like(StrUtil.isNotBlank(bo.getItemName()), Goods::getItemName, bo.getItemName());
        if (!StrUtil.isBlank(bo.getItemCategory())){
            Long parentId = Long.valueOf(bo.getItemCategory());
            List<Long> subIdList = this.buildSubItemCategoryIdList(parentId);
            subIdList.add(Long.valueOf(bo.getItemCategory()));
            lqw.in(Goods::getItemCategory, subIdList);
        }
        lqw.eq(StrUtil.isNotBlank(bo.getUnit()), Goods::getUnit, bo.getUnit());
        return lqw;
    }

    private List<Long> buildSubItemCategoryIdList(Long parentId) {
        LambdaQueryWrapper<GoodsCategory> itemTypeWrapper = new LambdaQueryWrapper<>();
        itemTypeWrapper.eq(GoodsCategory::getParentId, parentId);
        return itemCategoryMapper.selectList(itemTypeWrapper).stream().map(GoodsCategory::getId).collect(Collectors.toList());
    }

    /**
     * 新增物料
     *
     * @param bo
     */
    @Transactional
    public void insertByForm(ItemBo bo) {
        validateBoBeforeSave(bo);
        Goods goods = MapstructUtils.convert(bo, Goods.class);
        itemMapper.insert(goods);
        itemSkuService.setItemId(bo.getSku(), goods.getId());
        itemSkuService.saveOrUpdateBatchByBo(bo.getSku());
    }

    /**
     * 修改物料
     *
     * @param bo
     */
    @Transactional
    public void updateByForm(ItemBo bo) {
        validateBoBeforeSave(bo);
        itemMapper.updateById(MapstructUtils.convert(bo, Goods.class));
        itemSkuService.setItemId(bo.getSku(),bo.getId());
        itemSkuService.saveOrUpdateBatchByBo(bo.getSku());
    }

    /**
     * 保存前的数据校验
     */
    private void validateBoBeforeSave(ItemBo itemBo) {
        validateItemName(itemBo);
        validateItemSkuName(itemBo.getSku());
    }

    private void validateItemName(ItemBo item) {
        LambdaQueryWrapper<Goods> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Goods::getItemName, item.getItemName());
        queryWrapper.ne(item.getId() != null, Goods::getId, item.getId());
        Assert.isTrue(itemMapper.selectCount(queryWrapper) == 0, "商品名称重复");
    }

    private void validateItemSkuName(List<ItemSkuBo> skuVoList) {
         Assert.isTrue(
             skuVoList.stream().map(ItemSkuBo::getSkuName).distinct().count() == skuVoList.size(),
             "商品规格重复"
         );
    }



    /**
     * 批量删除物料
     */
    @Transactional
    public void deleteById(Long id) {
        List<Long> skuIds = itemSkuService.queryByItemId(id).stream().map(ItemSkuVo::getId).toList();
        itemMapper.deleteById(id);
        itemSkuService.deleteByIds(skuIds);
    }

}

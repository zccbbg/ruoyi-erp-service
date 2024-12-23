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
import com.ruoyi.erp.basic.domain.bo.GoodsBo;
import com.ruoyi.erp.basic.domain.bo.SkuBo;
import com.ruoyi.erp.basic.domain.entity.Goods;
import com.ruoyi.erp.basic.domain.entity.GoodsCategory;
import com.ruoyi.erp.basic.domain.vo.CategoryVo;
import com.ruoyi.erp.basic.domain.vo.SkuVo;
import com.ruoyi.erp.basic.domain.vo.ItemVo;
import com.ruoyi.erp.basic.mapper.CategoryMapper;
import com.ruoyi.erp.basic.mapper.GoodsMapper;
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

    private final GoodsMapper goodsMapper;
    private final ItemSkuService itemSkuService;
    private final CategoryMapper categoryMapper;

    /**
     * 查询物料
     */

    public ItemVo queryById(Long id) {
        return goodsMapper.selectVoById(id);
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
        return goodsMapper.selectVoList(lambdaQueryWrapper);
    }

    /**
     * 查询物料列表
     */

    public TableDataInfo<ItemVo> queryPageList(GoodsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Goods> lqw = buildQueryWrapper(bo);
        Page<ItemVo> result = goodsMapper.selectVoPage(pageQuery.build(), lqw);
        List<ItemVo> itemVoList = result.getRecords();
        if (!CollUtil.isEmpty(itemVoList)) {
            LambdaQueryWrapper<GoodsCategory> itemTypeWrapper = new LambdaQueryWrapper<>();
            itemTypeWrapper.in(GoodsCategory::getId, itemVoList.stream().map(ItemVo::getItemCategory).collect(Collectors.toSet()));
            Map<Long, CategoryVo> itemCategoryVoMap = categoryMapper.selectVoList(itemTypeWrapper).stream().collect(Collectors.toMap(CategoryVo::getId, Function.identity()));
            itemVoList.forEach(itemVo -> {
                itemVo.setItemCategoryInfo(itemCategoryVoMap.get(Long.valueOf(itemVo.getItemCategory())));
            });
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询物料列表
     */

    public List<ItemVo> queryList(GoodsBo bo) {
        LambdaQueryWrapper<Goods> lqw = buildQueryWrapper(bo);
        return goodsMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Goods> buildQueryWrapper(GoodsBo bo) {
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
        return categoryMapper.selectList(itemTypeWrapper).stream().map(GoodsCategory::getId).collect(Collectors.toList());
    }

    /**
     * 新增物料
     *
     * @param bo
     */
    @Transactional
    public void insertByForm(GoodsBo bo) {
        validateBoBeforeSave(bo);
        Goods goods = MapstructUtils.convert(bo, Goods.class);
        goodsMapper.insert(goods);
        itemSkuService.setItemId(bo.getSku(), goods.getId());
        itemSkuService.saveOrUpdateBatchByBo(bo.getSku());
    }

    /**
     * 修改物料
     *
     * @param bo
     */
    @Transactional
    public void updateByForm(GoodsBo bo) {
        validateBoBeforeSave(bo);
        goodsMapper.updateById(MapstructUtils.convert(bo, Goods.class));
        itemSkuService.setItemId(bo.getSku(),bo.getId());
        itemSkuService.saveOrUpdateBatchByBo(bo.getSku());
    }

    /**
     * 保存前的数据校验
     */
    private void validateBoBeforeSave(GoodsBo goodsBo) {
        validateItemName(goodsBo);
        validateItemSkuName(goodsBo.getSku());
    }

    private void validateItemName(GoodsBo item) {
        LambdaQueryWrapper<Goods> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Goods::getItemName, item.getItemName());
        queryWrapper.ne(item.getId() != null, Goods::getId, item.getId());
        Assert.isTrue(goodsMapper.selectCount(queryWrapper) == 0, "商品名称重复");
    }

    private void validateItemSkuName(List<SkuBo> skuVoList) {
         Assert.isTrue(
             skuVoList.stream().map(SkuBo::getSkuName).distinct().count() == skuVoList.size(),
             "商品规格重复"
         );
    }



    /**
     * 批量删除物料
     */
    @Transactional
    public void deleteById(Long id) {
        List<Long> skuIds = itemSkuService.queryByItemId(id).stream().map(SkuVo::getId).toList();
        goodsMapper.deleteById(id);
        itemSkuService.deleteByIds(skuIds);
    }

}

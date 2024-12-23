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
import com.ruoyi.erp.basic.domain.entity.Category;
import com.ruoyi.erp.basic.domain.vo.CategoryVo;
import com.ruoyi.erp.basic.domain.vo.SkuVo;
import com.ruoyi.erp.basic.domain.vo.GoodsVo;
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
public class GoodsService {

    private final GoodsMapper goodsMapper;
    private final SkuService skuService;
    private final CategoryMapper categoryMapper;

    /**
     * 查询物料
     */

    public GoodsVo queryById(Long id) {
        return goodsMapper.selectVoById(id);
    }

    /**
     * 查询物料
     *
     * @param itemIds ids
     */

    public List<GoodsVo> queryById(List<Long> itemIds) {
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

    public TableDataInfo<GoodsVo> queryPageList(GoodsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Goods> lqw = buildQueryWrapper(bo);
        Page<GoodsVo> result = goodsMapper.selectVoPage(pageQuery.build(), lqw);
        List<GoodsVo> goodsVoList = result.getRecords();
        if (!CollUtil.isEmpty(goodsVoList)) {
            LambdaQueryWrapper<Category> itemTypeWrapper = new LambdaQueryWrapper<>();
            itemTypeWrapper.in(Category::getId, goodsVoList.stream().map(GoodsVo::getCategory).collect(Collectors.toSet()));
            Map<Long, CategoryVo> categoryVoMap = categoryMapper.selectVoList(itemTypeWrapper).stream().collect(Collectors.toMap(CategoryVo::getId, Function.identity()));
            goodsVoList.forEach(itemVo -> {
                itemVo.setCategory(categoryVoMap.get(Long.valueOf(itemVo.getCategoryId())));
            });
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询物料列表
     */

    public List<GoodsVo> queryList(GoodsBo bo) {
        LambdaQueryWrapper<Goods> lqw = buildQueryWrapper(bo);
        return goodsMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Goods> buildQueryWrapper(GoodsBo bo) {
        LambdaQueryWrapper<Goods> lqw = Wrappers.lambdaQuery();
        lqw.eq(StrUtil.isNotBlank(bo.getGoodsNo()), Goods::getGoodsNo, bo.getGoodsNo());
        // 主键集合
        lqw.in(!CollUtil.isEmpty(bo.getIds()), Goods::getId, bo.getIds());
        lqw.like(StrUtil.isNotBlank(bo.getGoodsName()), Goods::getGoodsName, bo.getGoodsName());
        if (bo.getCategoryId()!=null){
            Long parentId = bo.getCategoryId();
            List<Long> subIdList = this.buildSubGoodsCategoryIdList(parentId);
            subIdList.add(bo.getCategoryId());
            lqw.in(Goods::getCategoryId, subIdList);
        }
        lqw.eq(StrUtil.isNotBlank(bo.getUnit()), Goods::getUnit, bo.getUnit());
        return lqw;
    }

    private List<Long> buildSubGoodsCategoryIdList(Long parentId) {
        LambdaQueryWrapper<Category> itemTypeWrapper = new LambdaQueryWrapper<>();
        itemTypeWrapper.eq(Category::getPid, parentId);
        return categoryMapper.selectList(itemTypeWrapper).stream().map(Category::getId).collect(Collectors.toList());
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
        skuService.setGoodsId(bo.getSku(), goods.getId());
        skuService.saveOrUpdateBatchByBo(bo.getSku());
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
        skuService.setGoodsId(bo.getSku(),bo.getId());
        skuService.saveOrUpdateBatchByBo(bo.getSku());
    }

    /**
     * 保存前的数据校验
     */
    private void validateBoBeforeSave(GoodsBo goodsBo) {
        validateGoodsName(goodsBo);
        validateGoodsSkuName(goodsBo.getSku());
    }

    private void validateGoodsName(GoodsBo item) {
        LambdaQueryWrapper<Goods> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Goods::getGoodsName, item.getGoodsName());
        queryWrapper.ne(item.getId() != null, Goods::getId, item.getId());
        Assert.isTrue(goodsMapper.selectCount(queryWrapper) == 0, "商品名称重复");
    }

    private void validateGoodsSkuName(List<SkuBo> skuVoList) {
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
        List<Long> skuIds = skuService.queryByGoodsId(id).stream().map(SkuVo::getId).toList();
        goodsMapper.deleteById(id);
        skuService.deleteByIds(skuIds);
    }

}

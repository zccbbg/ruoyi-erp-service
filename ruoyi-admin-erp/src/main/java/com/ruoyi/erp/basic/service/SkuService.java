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
import com.ruoyi.erp.base.domain.vo.BaseOrderDetailVo;
import com.ruoyi.erp.basic.domain.bo.SkuBo;
import com.ruoyi.erp.basic.domain.entity.Sku;
import com.ruoyi.erp.base.domain.vo.BaseDocDetailVo;
import com.ruoyi.erp.basic.domain.vo.SkuMapVo;
import com.ruoyi.erp.basic.domain.vo.SkuVo;
import com.ruoyi.erp.basic.mapper.SkuMapper;
import com.ruoyi.erp.warehouse.domain.bo.InventoryBo;
import com.ruoyi.erp.warehouse.domain.vo.InventoryVo;
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
public class SkuService extends ServiceImpl<SkuMapper, Sku> {


    private final SkuMapper skuMapper;
    private final InventoryService inventoryService;

    /**
     * 查询sku信息
     */

    public SkuMapVo querySkuMapVo(Long id) {
        return skuMapper.querySkuMapVo(id);
    }

    public SkuVo queryById(Long id) {
        return skuMapper.selectVoById(id);
    }


    /**
     * 查询sku信息列表，用于出入库的选择组件
     */

    public TableDataInfo<SkuMapVo> queryPageList(SkuBo bo, PageQuery pageQuery) {
        //开始查sku
        IPage<SkuMapVo> result = skuMapper.selectByBo(pageQuery.build(), bo,null);
        return TableDataInfo.build(result);
    }
    public TableDataInfo<SkuMapVo> queryPageList(SkuBo bo, PageQuery pageQuery, Set<Long> skuIds) {
        return TableDataInfo.build(skuMapper.selectByBo(pageQuery.build(), bo,skuIds));
    }

    /**
     * 查询sku信息列表
     */

    public List<SkuVo> queryList(SkuBo bo) {
        LambdaQueryWrapper<Sku> lqw = buildQueryWrapper(bo);
        return skuMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Sku> buildQueryWrapper(SkuBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Sku> lqw = Wrappers.lambdaQuery();
        lqw.like(StrUtil.isNotBlank(bo.getSkuName()), Sku::getSkuName, bo.getSkuName());
        lqw.eq(bo.getGoodsId() != null, Sku::getGoodsId, bo.getGoodsId());
        lqw.eq(StrUtil.isNotBlank(bo.getBarcode()), Sku::getBarcode, bo.getBarcode());
        lqw.orderByDesc(Sku::getGoodsId);
        return lqw;
    }

    /**
     * 新增sku信息
     */

    public Boolean insertByBo(SkuBo bo) {
        Sku add = MapstructUtils.convert(bo, Sku.class);
        return skuMapper.insert(add) > 0;
    }

    /**
     * 修改sku信息
     */

    public Boolean updateByBo(SkuBo bo) {
        Sku update = MapstructUtils.convert(bo, Sku.class);
        return skuMapper.updateById(update) > 0;
    }

    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        skuMapper.deleteById(id);
    }

    private void validateIdBeforeDelete(Long id) {
        // 只有一个不能删除
        Sku sku = skuMapper.selectById(id);

        if(queryByGoodsId(sku.getGoodsId()).size() <= 1){
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
        skuMapper.deleteBatchIds(ids);
    }

    /**
     * 批量保存商品sku
     * @param sku    商品sku
     */
    @Transactional
    public void saveOrUpdateBatchByBo(List<SkuBo> sku) {
        List<Sku> skuList = MapstructUtils.convert(sku, Sku.class);
        saveOrUpdateBatch(skuList);
    }

    public void setGoodsId(List<SkuBo> itemSkuList, Long itemId) {
        for (SkuBo skuBo : itemSkuList) {
            if (StrUtil.isBlank(skuBo.getBarcode())) {
                skuBo.setGoodsId(itemId);
            }
        }
    }

    /**
     * 查询sku列表
     *
     * @param id 商品id
     */

    public List<SkuVo> queryByGoodsId(Long id) {
        LambdaQueryWrapper<Sku> lqw = Wrappers.lambdaQuery();
        lqw.eq(Sku::getGoodsId, id);
        return skuMapper.selectVoList(lqw);
    }

    public Map<Long, SkuMapVo> querySkuMapVosByIds(Set<Long> skuIds){
        return skuMapper.querySkuMapVos(skuIds).stream()
            .collect(Collectors.toMap(SkuMapVo::getSkuId, Function.identity()));
    }

    public void setSkuMap(List<? extends BaseDocDetailVo> details){
        if (CollUtil.isNotEmpty(details)) {
            Set<Long> skuIds = details
                .stream()
                .map(BaseDocDetailVo::getSkuId)
                .collect(Collectors.toSet());

            Map<Long, SkuMapVo> itemSkuMap = this.querySkuMapVosByIds(skuIds);

            details.forEach(detail -> {
                    SkuMapVo vo = itemSkuMap.get(detail.getSkuId());
                    detail.setSku(vo.getSku());
                    detail.setGoods(vo.getGoods());
            });
        }
    }
}

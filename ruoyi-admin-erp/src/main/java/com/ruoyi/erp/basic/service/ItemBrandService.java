package com.ruoyi.erp.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.basic.domain.bo.ItemBrandBo;
import com.ruoyi.erp.basic.domain.entity.Goods;
import com.ruoyi.erp.basic.domain.entity.Brand;
import com.ruoyi.erp.basic.domain.vo.ItemBrandVo;
import com.ruoyi.erp.basic.mapper.ItemBrandMapper;
import com.ruoyi.erp.basic.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 商品品牌Service业务层处理
 *
 * @author zcc
 * @date 2024-07-30
 */
@RequiredArgsConstructor
@Service
public class ItemBrandService {

    private final ItemBrandMapper itemBrandMapper;
    private final ItemMapper itemMapper;

    /**
     * 查询商品品牌
     */
    public ItemBrandVo queryById(Long id){
        return itemBrandMapper.selectVoById(id);
    }

    /**
     * 查询商品品牌列表
     */
    public TableDataInfo<ItemBrandVo> queryPageList(ItemBrandBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Brand> lqw = buildQueryWrapper(bo);
        Page<ItemBrandVo> result = itemBrandMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品品牌列表
     */
    public List<ItemBrandVo> queryList(ItemBrandBo bo) {
        LambdaQueryWrapper<Brand> lqw = buildQueryWrapper(bo);
        return itemBrandMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Brand> buildQueryWrapper(ItemBrandBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Brand> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getBrandName()), Brand::getBrandName, bo.getBrandName());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增商品品牌
     */
    public void insertByBo(ItemBrandBo bo) {
        Brand add = MapstructUtils.convert(bo, Brand.class);
        itemBrandMapper.insert(add);
    }

    /**
     * 修改商品品牌
     */
    public void updateByBo(ItemBrandBo bo) {
        Brand update = MapstructUtils.convert(bo, Brand.class);
        itemBrandMapper.updateById(update);
    }

    /**
     * 批量删除商品品牌
     */
    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        itemBrandMapper.deleteById(id);
    }

    private void validateIdBeforeDelete(Long id) {
        LambdaQueryWrapper<Goods> itemLambdaQueryWrapper = Wrappers.lambdaQuery();
        itemLambdaQueryWrapper.eq(Goods::getItemBrand, id);
        if (itemMapper.exists(itemLambdaQueryWrapper)) {
            throw new ServiceException("删除失败", HttpStatus.CONFLICT,"该品牌已有业务关联，无法删除！");
        }
    }
}

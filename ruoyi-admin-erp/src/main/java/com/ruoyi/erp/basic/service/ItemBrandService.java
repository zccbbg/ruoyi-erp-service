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
import com.ruoyi.erp.basic.domain.bo.BrandBo;
import com.ruoyi.erp.basic.domain.entity.Goods;
import com.ruoyi.erp.basic.domain.entity.Brand;
import com.ruoyi.erp.basic.domain.vo.BrandVo;
import com.ruoyi.erp.basic.mapper.BrandMapper;
import com.ruoyi.erp.basic.mapper.GoodsMapper;
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

    private final BrandMapper brandMapper;
    private final GoodsMapper goodsMapper;

    /**
     * 查询商品品牌
     */
    public BrandVo queryById(Long id){
        return brandMapper.selectVoById(id);
    }

    /**
     * 查询商品品牌列表
     */
    public TableDataInfo<BrandVo> queryPageList(BrandBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Brand> lqw = buildQueryWrapper(bo);
        Page<BrandVo> result = brandMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品品牌列表
     */
    public List<BrandVo> queryList(BrandBo bo) {
        LambdaQueryWrapper<Brand> lqw = buildQueryWrapper(bo);
        return brandMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Brand> buildQueryWrapper(BrandBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Brand> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getBrandName()), Brand::getBrandName, bo.getBrandName());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增商品品牌
     */
    public void insertByBo(BrandBo bo) {
        Brand add = MapstructUtils.convert(bo, Brand.class);
        brandMapper.insert(add);
    }

    /**
     * 修改商品品牌
     */
    public void updateByBo(BrandBo bo) {
        Brand update = MapstructUtils.convert(bo, Brand.class);
        brandMapper.updateById(update);
    }

    /**
     * 批量删除商品品牌
     */
    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        brandMapper.deleteById(id);
    }

    private void validateIdBeforeDelete(Long id) {
        LambdaQueryWrapper<Goods> itemLambdaQueryWrapper = Wrappers.lambdaQuery();
        itemLambdaQueryWrapper.eq(Goods::getItemBrand, id);
        if (goodsMapper.exists(itemLambdaQueryWrapper)) {
            throw new ServiceException("删除失败", HttpStatus.CONFLICT,"该品牌已有业务关联，无法删除！");
        }
    }
}

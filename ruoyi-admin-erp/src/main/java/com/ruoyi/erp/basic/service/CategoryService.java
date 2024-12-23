package com.ruoyi.erp.basic.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.basic.domain.bo.CategoryBo;
import com.ruoyi.erp.basic.domain.entity.Goods;
import com.ruoyi.erp.basic.domain.vo.CategoryVo;
import com.ruoyi.erp.basic.domain.vo.GoodsTypeTreeSelectVo;
import com.ruoyi.erp.basic.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.basic.domain.entity.Category;
import com.ruoyi.erp.basic.mapper.GoodsMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.hutool.core.lang.Validator.isNotNull;

@RequiredArgsConstructor
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> {

    private final CategoryMapper categoryMapper;
    private final GoodsMapper goodsMapper;

    /**
     * 查询物料类型
     */

    public CategoryVo queryById(Long id) {
        return categoryMapper.selectVoById(id);
    }

    /**
     * 查询物料类型列表
     */

    public TableDataInfo<CategoryVo> queryPageList(CategoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Category> lqw = buildQueryWrapper(bo);
        Page<CategoryVo> result = categoryMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询物料类型列表
     */

    public List<CategoryVo> queryList(CategoryBo bo) {
        LambdaQueryWrapper<Category> lqw = buildQueryWrapper(bo);
        lqw.orderByAsc(Category::getOrderNum);
        return categoryMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Category> buildQueryWrapper(CategoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Category> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, Category::getPid, bo.getPid());
        lqw.like(StrUtil.isNotBlank(bo.getCategoryName()), Category::getCategoryName, bo.getCategoryName());
        lqw.eq(bo.getOrderNum() != null, Category::getOrderNum, bo.getOrderNum());
        lqw.eq(StrUtil.isNotBlank(bo.getStatus()), Category::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增物料类型
     */

    public void insertByBo(CategoryBo bo) {
        validateGoodsTypeName(bo);
        Category add = MapstructUtils.convert(bo, Category.class);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        if (bo.getPid() != null){
            wrapper.eq(Category::getPid, bo.getPid());
        }else {
            wrapper.eq(Category::getPid, 0L);
        }
        //查当前级别排序最大值
        wrapper.orderByDesc(Category::getOrderNum);
        wrapper.last("limit 1");
        Category itemType = categoryMapper.selectOne(wrapper);
        add.setOrderNum(itemType == null ? 0L : itemType.getOrderNum() + 1);
        categoryMapper.insert(add);
    }

    /**
     * 修改物料类型
     */

    public void updateByBo(CategoryBo bo) {
        validateGoodsTypeName(bo);
        Category update = MapstructUtils.convert(bo, Category.class);
        categoryMapper.updateById(update);
    }

    private void validateGoodsTypeName(CategoryBo bo) {
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Category::getCategoryName, bo.getCategoryName());
        queryWrapper.ne(bo.getId() != null, Category::getId, bo.getId());
        Assert.isTrue(categoryMapper.selectCount(queryWrapper) == 0, "分类名重复");
    }

    /**
     * 批量删除物料类型
     */

    public void deleteByIds(List<Long> ids) {
        // 有子分类不能删
        LambdaQueryWrapper<Category> itemCategoryLqw = new LambdaQueryWrapper<>();
        itemCategoryLqw.in(Category::getPid, ids);
        Assert.state(categoryMapper.selectCount(itemCategoryLqw) == 0, "删除失败！请先删除该分类下的子分类！");
        // 被商品应用了不能删
        LambdaQueryWrapper<Goods> itemLqw = Wrappers.lambdaQuery();
        itemLqw.in(Goods::getCategoryId, ids);
        Assert.state(goodsMapper.selectCount(itemLqw) == 0, "删除失败！分类已被商品使用！");
        // 删除
        LambdaQueryWrapper<Category> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.in(Category::getId, ids);
        categoryMapper.delete(deleteWrapper);
    }

    /**
     * @param itemTypes
     * @return
     */

    public List<GoodsTypeTreeSelectVo> buildGoodsTypeTreeSelect(List<CategoryVo> itemTypes) {
        List<CategoryVo> itemTypeTrees = buildDeptTree(itemTypes);
        return itemTypeTrees.stream().map(GoodsTypeTreeSelectVo::new).collect(Collectors.toList());
    }

    /**
     * 构建前端所需要树结构
     *
     * @param itemTypes 部门列表
     * @return 树结构列表
     */
    private List<CategoryVo> buildDeptTree(List<CategoryVo> itemTypes) {
        List<CategoryVo> returnList = new ArrayList<>();
        List<Long> tempList = new ArrayList<Long>();
        for (CategoryVo dept : itemTypes) {
            tempList.add(dept.getId());
        }
        for (CategoryVo dept : itemTypes) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId())) {
                recursionFn(itemTypes, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty()) {
            returnList = itemTypes;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<CategoryVo> list, CategoryVo t) {
        // 得到子节点列表
        List<CategoryVo> childList = getChildList(list, t);
        t.setChildren(childList);
        for (CategoryVo tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<CategoryVo> getChildList(List<CategoryVo> list, CategoryVo t) {
        List<CategoryVo> tlist = new ArrayList<>();
        for (CategoryVo n : list) {
            if (isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<CategoryVo> list, CategoryVo t) {
        return getChildList(list, t).size() > 0;
    }

    public void updateOrderNum(List<GoodsTypeTreeSelectVo> tree) {
        List<Category> updateList = new ArrayList<>();
        for (int i = 0; i < tree.size(); i++) {
            Category itemType = new Category();
            itemType.setId(tree.get(i).getId());
            itemType.setOrderNum((long) i);
            updateList.add(itemType);
        }
        saveOrUpdateBatch(updateList);
    }
}

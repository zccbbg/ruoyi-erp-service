package com.ruoyi.warehouse.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.erp.basic.service.ItemSkuService;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.warehouse.domain.bo.MovementDocDetailBo;
import com.ruoyi.warehouse.domain.entity.MovementDocDetail;
import com.ruoyi.warehouse.domain.vo.MovementDocDetailVo;
import com.ruoyi.warehouse.mapper.MovementOrderDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 库存移动详情Service业务层处理
 *
 * @author zcc
 * @date 2024-08-09
 */
@RequiredArgsConstructor
@Service
public class MovementDocDetailService extends ServiceImpl<MovementOrderDetailMapper, MovementDocDetail> {

    private final MovementOrderDetailMapper movementOrderDetailMapper;
    private final ItemSkuService itemSkuService;

    /**
     * 查询库存移动详情
     */
    public MovementDocDetailVo queryById(Long id){
        return movementOrderDetailMapper.selectVoById(id);
    }

    /**
     * 查询库存移动详情列表
     */
    public TableDataInfo<MovementDocDetailVo> queryPageList(MovementDocDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MovementDocDetail> lqw = buildQueryWrapper(bo);
        Page<MovementDocDetailVo> result = movementOrderDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询库存移动详情列表
     */
    public List<MovementDocDetailVo> queryList(MovementDocDetailBo bo) {
        LambdaQueryWrapper<MovementDocDetail> lqw = buildQueryWrapper(bo);
        return movementOrderDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MovementDocDetail> buildQueryWrapper(MovementDocDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MovementDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, MovementDocDetail::getOrderId, bo.getOrderId());
        lqw.eq(bo.getSkuId() != null, MovementDocDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQuantity() != null, MovementDocDetail::getQuantity, bo.getQuantity());
        lqw.eq(bo.getSourceWarehouseId() != null, MovementDocDetail::getSourceWarehouseId, bo.getSourceWarehouseId());
        lqw.eq(bo.getTargetWarehouseId() != null, MovementDocDetail::getTargetWarehouseId, bo.getTargetWarehouseId());
        return lqw;
    }

    /**
     * 新增库存移动详情
     */
    public void insertByBo(MovementDocDetailBo bo) {
        MovementDocDetail add = MapstructUtils.convert(bo, MovementDocDetail.class);
        movementOrderDetailMapper.insert(add);
    }

    /**
     * 修改库存移动详情
     */
    public void updateByBo(MovementDocDetailBo bo) {
        MovementDocDetail update = MapstructUtils.convert(bo, MovementDocDetail.class);
        movementOrderDetailMapper.updateById(update);
    }

    /**
     * 批量删除库存移动详情
     */
    public void deleteByIds(Collection<Long> ids) {
        movementOrderDetailMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void saveDetails(List<MovementDocDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    /**
     * 根据移库单id查询移库单详情
     * @param movementOrderId
     * @return
     */
    public List<MovementDocDetailVo> queryByMovementOrderId(Long movementOrderId) {
        MovementDocDetailBo bo = new MovementDocDetailBo();
        bo.setOrderId(movementOrderId);
        List<MovementDocDetailVo> details = queryList(bo);
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        itemSkuService.setItemSkuMap(details);
        return details;
    }
}

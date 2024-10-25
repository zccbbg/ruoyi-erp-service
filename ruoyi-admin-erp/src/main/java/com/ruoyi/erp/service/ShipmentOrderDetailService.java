package com.ruoyi.erp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.domain.bo.ShipmentOrderDetailBo;
import com.ruoyi.erp.domain.entity.ShipmentDocDetail;
import com.ruoyi.erp.domain.vo.ShipmentOrderDetailVo;
import com.ruoyi.erp.mapper.ShipmentOrderDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 出库单详情Service业务层处理
 *
 * @author zcc
 * @date 2024-08-01
 */
@RequiredArgsConstructor
@Service
public class ShipmentOrderDetailService extends ServiceImpl<ShipmentOrderDetailMapper, ShipmentDocDetail> {

    private final ShipmentOrderDetailMapper shipmentOrderDetailMapper;
    private final ItemSkuService itemSkuService;

    /**
     * 查询出库单详情
     */
    public ShipmentOrderDetailVo queryById(Long id){
        return shipmentOrderDetailMapper.selectVoById(id);
    }

    /**
     * 查询出库单详情列表
     */
    public TableDataInfo<ShipmentOrderDetailVo> queryPageList(ShipmentOrderDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ShipmentDocDetail> lqw = buildQueryWrapper(bo);
        Page<ShipmentOrderDetailVo> result = shipmentOrderDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询出库单详情列表
     */
    public List<ShipmentOrderDetailVo> queryList(ShipmentOrderDetailBo bo) {
        LambdaQueryWrapper<ShipmentDocDetail> lqw = buildQueryWrapper(bo);
        return shipmentOrderDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ShipmentDocDetail> buildQueryWrapper(ShipmentOrderDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ShipmentDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, ShipmentDocDetail::getOrderId, bo.getOrderId());
        lqw.eq(bo.getSkuId() != null, ShipmentDocDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQuantity() != null, ShipmentDocDetail::getQuantity, bo.getQuantity());
        lqw.eq(bo.getAmount() != null, ShipmentDocDetail::getAmount, bo.getAmount());
        lqw.eq(bo.getWarehouseId() != null, ShipmentDocDetail::getWarehouseId, bo.getWarehouseId());
        return lqw;
    }

    /**
     * 新增出库单详情
     */
    public void insertByBo(ShipmentOrderDetailBo bo) {
        ShipmentDocDetail add = MapstructUtils.convert(bo, ShipmentDocDetail.class);
        shipmentOrderDetailMapper.insert(add);
    }

    /**
     * 修改出库单详情
     */
    public void updateByBo(ShipmentOrderDetailBo bo) {
        ShipmentDocDetail update = MapstructUtils.convert(bo, ShipmentDocDetail.class);
        shipmentOrderDetailMapper.updateById(update);
    }

    /**
     * 批量删除出库单详情
     */
    public void deleteByIds(Collection<Long> ids) {
        shipmentOrderDetailMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void saveDetails(List<ShipmentDocDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    public List<ShipmentOrderDetailVo> queryByShipmentOrderId(Long shipmentOrderId) {
        ShipmentOrderDetailBo bo = new ShipmentOrderDetailBo();
        bo.setOrderId(shipmentOrderId);
        List<ShipmentOrderDetailVo> details = queryList(bo);
        itemSkuService.setItemSkuMap(details);
        return details;
    }
}

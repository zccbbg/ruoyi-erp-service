package com.ruoyi.warehouse.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.warehouse.domain.bo.ShipmentOrderDetailBo;
import com.ruoyi.warehouse.domain.entity.OtherShipmentDocDetail;
import com.ruoyi.warehouse.domain.vo.ShipmentOrderDetailVo;
import com.ruoyi.warehouse.mapper.ShipmentOrderDetailMapper;
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
public class ShipmentOrderDetailService extends ServiceImpl<ShipmentOrderDetailMapper, OtherShipmentDocDetail> {

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
        LambdaQueryWrapper<OtherShipmentDocDetail> lqw = buildQueryWrapper(bo);
        Page<ShipmentOrderDetailVo> result = shipmentOrderDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询出库单详情列表
     */
    public List<ShipmentOrderDetailVo> queryList(ShipmentOrderDetailBo bo) {
        LambdaQueryWrapper<OtherShipmentDocDetail> lqw = buildQueryWrapper(bo);
        return shipmentOrderDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OtherShipmentDocDetail> buildQueryWrapper(ShipmentOrderDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OtherShipmentDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, OtherShipmentDocDetail::getOrderId, bo.getOrderId());
        lqw.eq(bo.getSkuId() != null, OtherShipmentDocDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQuantity() != null, OtherShipmentDocDetail::getQuantity, bo.getQuantity());
        lqw.eq(bo.getAmount() != null, OtherShipmentDocDetail::getAmount, bo.getAmount());
        lqw.eq(bo.getWarehouseId() != null, OtherShipmentDocDetail::getWarehouseId, bo.getWarehouseId());
        return lqw;
    }

    /**
     * 新增出库单详情
     */
    public void insertByBo(ShipmentOrderDetailBo bo) {
        OtherShipmentDocDetail add = MapstructUtils.convert(bo, OtherShipmentDocDetail.class);
        shipmentOrderDetailMapper.insert(add);
    }

    /**
     * 修改出库单详情
     */
    public void updateByBo(ShipmentOrderDetailBo bo) {
        OtherShipmentDocDetail update = MapstructUtils.convert(bo, OtherShipmentDocDetail.class);
        shipmentOrderDetailMapper.updateById(update);
    }

    /**
     * 批量删除出库单详情
     */
    public void deleteByIds(Collection<Long> ids) {
        shipmentOrderDetailMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void saveDetails(List<OtherShipmentDocDetail> list) {
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

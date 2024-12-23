package com.ruoyi.erp.warehouse.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.erp.basic.service.SkuService;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.warehouse.domain.bo.OtherShipmentDocDetailBo;
import com.ruoyi.erp.warehouse.domain.entity.OtherShipmentDocDetail;
import com.ruoyi.erp.warehouse.domain.vo.OtherShipmentDocDetailVo;
import com.ruoyi.erp.warehouse.mapper.OtherShipmentDocDetailMapper;
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
public class OtherShipmentDocDetailService extends ServiceImpl<OtherShipmentDocDetailMapper, OtherShipmentDocDetail> {

    private final OtherShipmentDocDetailMapper otherShipmentDocDetailMapper;
    private final SkuService skuService;

    /**
     * 查询出库单详情
     */
    public OtherShipmentDocDetailVo queryById(Long id){
        return otherShipmentDocDetailMapper.selectVoById(id);
    }

    /**
     * 查询出库单详情列表
     */
    public TableDataInfo<OtherShipmentDocDetailVo> queryPageList(OtherShipmentDocDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OtherShipmentDocDetail> lqw = buildQueryWrapper(bo);
        Page<OtherShipmentDocDetailVo> result = otherShipmentDocDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询出库单详情列表
     */
    public List<OtherShipmentDocDetailVo> queryList(OtherShipmentDocDetailBo bo) {
        LambdaQueryWrapper<OtherShipmentDocDetail> lqw = buildQueryWrapper(bo);
        return otherShipmentDocDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OtherShipmentDocDetail> buildQueryWrapper(OtherShipmentDocDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OtherShipmentDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, OtherShipmentDocDetail::getPid, bo.getPid());
        lqw.eq(bo.getSkuId() != null, OtherShipmentDocDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQty() != null, OtherShipmentDocDetail::getQty, bo.getQty());
        lqw.eq(bo.getAmount() != null, OtherShipmentDocDetail::getAmount, bo.getAmount());
        lqw.eq(bo.getWarehouseId() != null, OtherShipmentDocDetail::getWarehouseId, bo.getWarehouseId());
        return lqw;
    }

    /**
     * 新增出库单详情
     */
    public void insertByBo(OtherShipmentDocDetailBo bo) {
        OtherShipmentDocDetail add = MapstructUtils.convert(bo, OtherShipmentDocDetail.class);
        otherShipmentDocDetailMapper.insert(add);
    }

    /**
     * 修改出库单详情
     */
    public void updateByBo(OtherShipmentDocDetailBo bo) {
        OtherShipmentDocDetail update = MapstructUtils.convert(bo, OtherShipmentDocDetail.class);
        otherShipmentDocDetailMapper.updateById(update);
    }

    /**
     * 批量删除出库单详情
     */
    public void deleteByIds(Collection<Long> ids) {
        otherShipmentDocDetailMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void saveDetails(List<OtherShipmentDocDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    public List<OtherShipmentDocDetailVo> queryByShipmentDocId(Long shipmentDocId) {
        OtherShipmentDocDetailBo bo = new OtherShipmentDocDetailBo();
        bo.setPid(shipmentDocId);
        List<OtherShipmentDocDetailVo> details = queryList(bo);
        skuService.setSkuMap(details);
        return details;
    }
}

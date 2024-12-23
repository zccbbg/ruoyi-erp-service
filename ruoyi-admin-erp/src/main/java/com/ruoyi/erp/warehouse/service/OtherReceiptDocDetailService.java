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
import com.ruoyi.erp.warehouse.domain.bo.OtherReceiptDocDetailBo;
import com.ruoyi.erp.warehouse.domain.entity.OtherReceiptDocDetail;
import com.ruoyi.erp.warehouse.domain.vo.OtherReceiptDocDetailVo;
import com.ruoyi.erp.warehouse.mapper.OtherReceiptDocDetailMapper;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 入库单详情Service业务层处理
 *
 * @author zcc
 * @date 2024-07-19
 */
@RequiredArgsConstructor
@Service
public class OtherReceiptDocDetailService extends ServiceImpl<OtherReceiptDocDetailMapper, OtherReceiptDocDetail> {

    private final OtherReceiptDocDetailMapper otherReceiptDocDetailMapper;
    private final SkuService skuService;

    /**
     * 查询入库单详情
     */
    public OtherReceiptDocDetailVo queryById(Long id){
        return otherReceiptDocDetailMapper.selectVoById(id);
    }

    /**
     * 查询入库单详情列表
     */
    public TableDataInfo<OtherReceiptDocDetailVo> queryPageList(OtherReceiptDocDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OtherReceiptDocDetail> lqw = buildQueryWrapper(bo);
        Page<OtherReceiptDocDetailVo> result = otherReceiptDocDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询入库单详情列表
     */
    public List<OtherReceiptDocDetailVo> queryList(OtherReceiptDocDetailBo bo) {
        LambdaQueryWrapper<OtherReceiptDocDetail> lqw = buildQueryWrapper(bo);
        return otherReceiptDocDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OtherReceiptDocDetail> buildQueryWrapper(OtherReceiptDocDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OtherReceiptDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, OtherReceiptDocDetail::getPid, bo.getPid());
        lqw.eq(bo.getSkuId() != null, OtherReceiptDocDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQuantity() != null, OtherReceiptDocDetail::getQuantity, bo.getQuantity());
        lqw.eq(bo.getAmount() != null, OtherReceiptDocDetail::getAmount, bo.getAmount());
        lqw.eq(bo.getWarehouseId() != null, OtherReceiptDocDetail::getWarehouseId, bo.getWarehouseId());
        return lqw;
    }

    /**
     * 新增入库单详情
     */
    public void insertByBo(OtherReceiptDocDetailBo bo) {
        OtherReceiptDocDetail add = MapstructUtils.convert(bo, OtherReceiptDocDetail.class);
        otherReceiptDocDetailMapper.insert(add);
    }

    /**
     * 修改入库单详情
     */
    public void updateByBo(OtherReceiptDocDetailBo bo) {
        OtherReceiptDocDetail update = MapstructUtils.convert(bo, OtherReceiptDocDetail.class);
        otherReceiptDocDetailMapper.updateById(update);
    }

    /**
     * 批量删除入库单详情
     */
    public void deleteByIds(Collection<Long> ids) {
        otherReceiptDocDetailMapper.deleteBatchIds(ids);
    }

    /**
     * 根据入库单id删除入库单详情
     */
    public void deleteByReceiptDocId(@NotNull Long receiptDocId) {
        LambdaQueryWrapper<OtherReceiptDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(OtherReceiptDocDetail::getPid, receiptDocId);
        otherReceiptDocDetailMapper.delete(lqw);
    }

    @Transactional
    public void saveDetails(List<OtherReceiptDocDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    public List<OtherReceiptDocDetailVo> queryByReceiptDocId(Long receiptDocId) {
        OtherReceiptDocDetailBo bo = new OtherReceiptDocDetailBo();
        bo.setPid(receiptDocId);
        List<OtherReceiptDocDetailVo> details = queryList(bo);
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        skuService.setItemSkuMap(details);
        return details;
    }
}

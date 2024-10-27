package com.ruoyi.warehouse.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.warehouse.domain.bo.ReceiptDocDetailBo;
import com.ruoyi.warehouse.domain.entity.OtherReceiptDocDetail;
import com.ruoyi.warehouse.domain.vo.ReceiptOrderDetailVo;
import com.ruoyi.warehouse.mapper.ReceiptOrderDetailMapper;
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
public class ReceiptOrderDetailService extends ServiceImpl<ReceiptOrderDetailMapper, OtherReceiptDocDetail> {

    private final ReceiptOrderDetailMapper receiptOrderDetailMapper;
    private final ItemSkuService itemSkuService;

    /**
     * 查询入库单详情
     */
    public ReceiptOrderDetailVo queryById(Long id){
        return receiptOrderDetailMapper.selectVoById(id);
    }

    /**
     * 查询入库单详情列表
     */
    public TableDataInfo<ReceiptOrderDetailVo> queryPageList(ReceiptDocDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OtherReceiptDocDetail> lqw = buildQueryWrapper(bo);
        Page<ReceiptOrderDetailVo> result = receiptOrderDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询入库单详情列表
     */
    public List<ReceiptOrderDetailVo> queryList(ReceiptDocDetailBo bo) {
        LambdaQueryWrapper<OtherReceiptDocDetail> lqw = buildQueryWrapper(bo);
        return receiptOrderDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OtherReceiptDocDetail> buildQueryWrapper(ReceiptDocDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OtherReceiptDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, OtherReceiptDocDetail::getOrderId, bo.getOrderId());
        lqw.eq(bo.getSkuId() != null, OtherReceiptDocDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQuantity() != null, OtherReceiptDocDetail::getQuantity, bo.getQuantity());
        lqw.eq(bo.getAmount() != null, OtherReceiptDocDetail::getAmount, bo.getAmount());
        lqw.eq(bo.getWarehouseId() != null, OtherReceiptDocDetail::getWarehouseId, bo.getWarehouseId());
        return lqw;
    }

    /**
     * 新增入库单详情
     */
    public void insertByBo(ReceiptDocDetailBo bo) {
        OtherReceiptDocDetail add = MapstructUtils.convert(bo, OtherReceiptDocDetail.class);
        receiptOrderDetailMapper.insert(add);
    }

    /**
     * 修改入库单详情
     */
    public void updateByBo(ReceiptDocDetailBo bo) {
        OtherReceiptDocDetail update = MapstructUtils.convert(bo, OtherReceiptDocDetail.class);
        receiptOrderDetailMapper.updateById(update);
    }

    /**
     * 批量删除入库单详情
     */
    public void deleteByIds(Collection<Long> ids) {
        receiptOrderDetailMapper.deleteBatchIds(ids);
    }

    /**
     * 根据入库单id删除入库单详情
     */
    public void deleteByReceiptOrderId(@NotNull Long receiptOrderId) {
        LambdaQueryWrapper<OtherReceiptDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(OtherReceiptDocDetail::getOrderId, receiptOrderId);
        receiptOrderDetailMapper.delete(lqw);
    }

    @Transactional
    public void saveDetails(List<OtherReceiptDocDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    public List<ReceiptOrderDetailVo> queryByReceiptOrderId(Long receiptOrderId) {
        ReceiptDocDetailBo bo = new ReceiptDocDetailBo();
        bo.setOrderId(receiptOrderId);
        List<ReceiptOrderDetailVo> details = queryList(bo);
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        itemSkuService.setItemSkuMap(details);
        return details;
    }
}

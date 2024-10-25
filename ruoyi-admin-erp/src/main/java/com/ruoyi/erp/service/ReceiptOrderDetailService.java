package com.ruoyi.erp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.domain.bo.ReceiptOrderDetailBo;
import com.ruoyi.erp.domain.entity.OtherReceiptDetail;
import com.ruoyi.erp.domain.vo.ReceiptOrderDetailVo;
import com.ruoyi.erp.mapper.ReceiptOrderDetailMapper;
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
public class ReceiptOrderDetailService extends ServiceImpl<ReceiptOrderDetailMapper, OtherReceiptDetail> {

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
    public TableDataInfo<ReceiptOrderDetailVo> queryPageList(ReceiptOrderDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OtherReceiptDetail> lqw = buildQueryWrapper(bo);
        Page<ReceiptOrderDetailVo> result = receiptOrderDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询入库单详情列表
     */
    public List<ReceiptOrderDetailVo> queryList(ReceiptOrderDetailBo bo) {
        LambdaQueryWrapper<OtherReceiptDetail> lqw = buildQueryWrapper(bo);
        return receiptOrderDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OtherReceiptDetail> buildQueryWrapper(ReceiptOrderDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OtherReceiptDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, OtherReceiptDetail::getOrderId, bo.getOrderId());
        lqw.eq(bo.getSkuId() != null, OtherReceiptDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQuantity() != null, OtherReceiptDetail::getQuantity, bo.getQuantity());
        lqw.eq(bo.getAmount() != null, OtherReceiptDetail::getAmount, bo.getAmount());
        lqw.eq(bo.getWarehouseId() != null, OtherReceiptDetail::getWarehouseId, bo.getWarehouseId());
        return lqw;
    }

    /**
     * 新增入库单详情
     */
    public void insertByBo(ReceiptOrderDetailBo bo) {
        OtherReceiptDetail add = MapstructUtils.convert(bo, OtherReceiptDetail.class);
        receiptOrderDetailMapper.insert(add);
    }

    /**
     * 修改入库单详情
     */
    public void updateByBo(ReceiptOrderDetailBo bo) {
        OtherReceiptDetail update = MapstructUtils.convert(bo, OtherReceiptDetail.class);
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
        LambdaQueryWrapper<OtherReceiptDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(OtherReceiptDetail::getOrderId, receiptOrderId);
        receiptOrderDetailMapper.delete(lqw);
    }

    @Transactional
    public void saveDetails(List<OtherReceiptDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    public List<ReceiptOrderDetailVo> queryByReceiptOrderId(Long receiptOrderId) {
        ReceiptOrderDetailBo bo = new ReceiptOrderDetailBo();
        bo.setOrderId(receiptOrderId);
        List<ReceiptOrderDetailVo> details = queryList(bo);
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        itemSkuService.setItemSkuMap(details);
        return details;
    }
}

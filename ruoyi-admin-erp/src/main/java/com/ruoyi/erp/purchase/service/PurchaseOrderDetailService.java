package com.ruoyi.erp.purchase.service;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.erp.basic.service.SkuService;
import com.ruoyi.erp.warehouse.domain.bo.OtherReceiptDocDetailBo;
import com.ruoyi.erp.warehouse.domain.entity.OtherReceiptDocDetail;
import com.ruoyi.erp.warehouse.domain.vo.OtherReceiptDocDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.purchase.domain.bo.PurchaseOrderDetailBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseOrderDetailVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseOrderDetail;
import com.ruoyi.erp.purchase.mapper.PurchaseOrderDetailMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.baomidou.mybatisplus.extension.toolkit.Db.saveOrUpdateBatch;

/**
 * 采购订单明细Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class PurchaseOrderDetailService {

    private final PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    private final SkuService skuService;

    /**
     * 查询采购订单明细
     */
    public PurchaseOrderDetailVo queryById(Long id){
        return purchaseOrderDetailMapper.selectVoById(id);
    }

    /**
     * 查询采购订单明细列表
     */
    public TableDataInfo<PurchaseOrderDetailVo> queryPageList(PurchaseOrderDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PurchaseOrderDetail> lqw = buildQueryWrapper(bo);
        Page<PurchaseOrderDetailVo> result = purchaseOrderDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询采购订单明细列表
     */
    public List<PurchaseOrderDetailVo> queryList(PurchaseOrderDetailBo bo) {
        LambdaQueryWrapper<PurchaseOrderDetail> lqw = buildQueryWrapper(bo);
        return purchaseOrderDetailMapper.selectVoList(lqw);
    }

    public List<PurchaseOrderDetailVo> queryByPid(Long pid) {
        PurchaseOrderDetailBo bo = new PurchaseOrderDetailBo();
        bo.setPid(pid);
        List<PurchaseOrderDetailVo> details = queryList(bo);
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        skuService.setSkuMap(details);
        return details;
    }

    private LambdaQueryWrapper<PurchaseOrderDetail> buildQueryWrapper(PurchaseOrderDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PurchaseOrderDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, PurchaseOrderDetail::getPid, bo.getPid());
        lqw.eq(bo.getSkuId() != null, PurchaseOrderDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQty() != null, PurchaseOrderDetail::getQty, bo.getQty());
        lqw.eq(bo.getProcessedQty() != null, PurchaseOrderDetail::getProcessedQty, bo.getProcessedQty());
        lqw.eq(bo.getPriceWithoutTax() != null, PurchaseOrderDetail::getPriceWithoutTax, bo.getPriceWithoutTax());
        lqw.eq(bo.getTaxAmount() != null, PurchaseOrderDetail::getTaxAmount, bo.getTaxAmount());
        lqw.eq(bo.getTaxRate() != null, PurchaseOrderDetail::getTaxRate, bo.getTaxRate());
        lqw.eq(bo.getPriceWithTax() != null, PurchaseOrderDetail::getPriceWithTax, bo.getPriceWithTax());
        lqw.eq(bo.getTotalAmount() != null, PurchaseOrderDetail::getTotalAmount, bo.getTotalAmount());
        return lqw;
    }

    /**
     * 新增采购订单明细
     */
    public void insertByBo(PurchaseOrderDetailBo bo) {
        PurchaseOrderDetail add = MapstructUtils.convert(bo, PurchaseOrderDetail.class);
        purchaseOrderDetailMapper.insert(add);
    }

    /**
     * 修改采购订单明细
     */
    public void updateByBo(PurchaseOrderDetailBo bo) {
        PurchaseOrderDetail update = MapstructUtils.convert(bo, PurchaseOrderDetail.class);
        purchaseOrderDetailMapper.updateById(update);
    }

    /**
     * 批量删除采购订单明细
     */
    public void deleteByIds(Collection<Long> ids) {
        purchaseOrderDetailMapper.deleteBatchIds(ids);
    }

    public void saveDetails(List<PurchaseOrderDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    public Set<Long> getSkuIds(Long orderId) {
        PurchaseOrderDetailBo bo = new PurchaseOrderDetailBo();
        bo.setPid(orderId);
        List<PurchaseOrderDetailVo> details = queryList(bo);
        if (CollUtil.isEmpty(details)) {
            return Collections.emptySet();
        }
        return details.stream().map(PurchaseOrderDetailVo::getSkuId).collect(Collectors.toSet());
    }
}

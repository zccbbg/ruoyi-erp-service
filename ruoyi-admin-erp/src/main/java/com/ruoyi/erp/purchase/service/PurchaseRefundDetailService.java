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
import com.ruoyi.erp.purchase.domain.entity.PurchaseTradeDetail;
import com.ruoyi.erp.purchase.domain.vo.PurchaseTradeDetailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.purchase.domain.bo.PurchaseRefundDetailBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseRefundDetailVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseRefundDetail;
import com.ruoyi.erp.purchase.mapper.PurchaseRefundDetailMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Collection;

import static com.baomidou.mybatisplus.extension.toolkit.Db.saveOrUpdateBatch;

/**
 * 采购退货单明细Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class PurchaseRefundDetailService {

    private final PurchaseRefundDetailMapper purchaseRefundDetailMapper;
    private final SkuService skuService;

    /**
     * 查询采购退货单明细
     */
    public PurchaseRefundDetailVo queryById(Long id){
        return purchaseRefundDetailMapper.selectVoById(id);
    }

    /**
     * 查询采购退货单明细列表
     */
    public TableDataInfo<PurchaseRefundDetailVo> queryPageList(PurchaseRefundDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PurchaseRefundDetail> lqw = buildQueryWrapper(bo);
        Page<PurchaseRefundDetailVo> result = purchaseRefundDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询采购退货单明细列表
     */
    public List<PurchaseRefundDetailVo> queryList(PurchaseRefundDetailBo bo) {
        LambdaQueryWrapper<PurchaseRefundDetail> lqw = buildQueryWrapper(bo);
        return purchaseRefundDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PurchaseRefundDetail> buildQueryWrapper(PurchaseRefundDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PurchaseRefundDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, PurchaseRefundDetail::getPid, bo.getPid());
        lqw.eq(bo.getSkuId() != null, PurchaseRefundDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQty() != null, PurchaseRefundDetail::getQty, bo.getQty());
        lqw.eq(bo.getPriceWithoutTax() != null, PurchaseRefundDetail::getPriceWithoutTax, bo.getPriceWithoutTax());
        lqw.eq(bo.getTaxAmount() != null, PurchaseRefundDetail::getTaxAmount, bo.getTaxAmount());
        lqw.eq(bo.getTaxRate() != null, PurchaseRefundDetail::getTaxRate, bo.getTaxRate());
        lqw.eq(bo.getPriceWithTax() != null, PurchaseRefundDetail::getPriceWithTax, bo.getPriceWithTax());
        lqw.eq(bo.getTotalAmount() != null, PurchaseRefundDetail::getTotalAmount, bo.getTotalAmount());
        return lqw;
    }

    /**
     * 新增采购退货单明细
     */
    public void insertByBo(PurchaseRefundDetailBo bo) {
        PurchaseRefundDetail add = MapstructUtils.convert(bo, PurchaseRefundDetail.class);
        purchaseRefundDetailMapper.insert(add);
    }

    /**
     * 修改采购退货单明细
     */
    public void updateByBo(PurchaseRefundDetailBo bo) {
        PurchaseRefundDetail update = MapstructUtils.convert(bo, PurchaseRefundDetail.class);
        purchaseRefundDetailMapper.updateById(update);
    }

    /**
     * 批量删除采购退货单明细
     */
    public void deleteByIds(Collection<Long> ids) {
        purchaseRefundDetailMapper.deleteBatchIds(ids);
    }

    /**
     * 添加采购退货单明细
     * @param addDetailList
     */
    public void saveDetails(List<PurchaseRefundDetail> addDetailList) {
        if (CollUtil.isEmpty(addDetailList)) {
            return;
        }
        saveOrUpdateBatch(addDetailList);
    }
    public List<PurchaseRefundDetailVo> queryByPid(Long pid) {
        List<PurchaseRefundDetailVo> details = purchaseRefundDetailMapper.selectVoList(Wrappers.lambdaQuery(PurchaseRefundDetail.class).eq(PurchaseRefundDetail::getPid, pid));
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        skuService.setSkuMap(details);
        return details;
    }
}

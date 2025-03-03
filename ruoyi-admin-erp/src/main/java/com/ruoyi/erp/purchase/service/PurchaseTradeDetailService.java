package com.ruoyi.erp.purchase.service;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.purchase.domain.bo.PurchaseTradeDetailBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseTradeDetailVo;
import com.ruoyi.erp.purchase.domain.entity.PurchaseTradeDetail;
import com.ruoyi.erp.purchase.mapper.PurchaseTradeDetailMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

import static com.baomidou.mybatisplus.extension.toolkit.Db.saveOrUpdateBatch;

/**
 * 采购入库单明细Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class PurchaseTradeDetailService {

    private final PurchaseTradeDetailMapper purchaseTradeDetailMapper;

    /**
     * 查询采购入库单明细
     */
    public PurchaseTradeDetailVo queryById(Long id){
        return purchaseTradeDetailMapper.selectVoById(id);
    }

    /**
     * 查询采购入库单明细列表
     */
    public TableDataInfo<PurchaseTradeDetailVo> queryPageList(PurchaseTradeDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PurchaseTradeDetail> lqw = buildQueryWrapper(bo);
        Page<PurchaseTradeDetailVo> result = purchaseTradeDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询采购入库单明细列表
     */
    public List<PurchaseTradeDetailVo> queryList(PurchaseTradeDetailBo bo) {
        LambdaQueryWrapper<PurchaseTradeDetail> lqw = buildQueryWrapper(bo);
        return purchaseTradeDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PurchaseTradeDetail> buildQueryWrapper(PurchaseTradeDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PurchaseTradeDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, PurchaseTradeDetail::getPid, bo.getPid());
        lqw.eq(bo.getSkuId() != null, PurchaseTradeDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQty() != null, PurchaseTradeDetail::getQty, bo.getQty());
        lqw.eq(bo.getPriceWithoutTax() != null, PurchaseTradeDetail::getPriceWithoutTax, bo.getPriceWithoutTax());
        lqw.eq(bo.getTaxAmount() != null, PurchaseTradeDetail::getTaxAmount, bo.getTaxAmount());
        lqw.eq(bo.getTaxRate() != null, PurchaseTradeDetail::getTaxRate, bo.getTaxRate());
        lqw.eq(bo.getPriceWithTax() != null, PurchaseTradeDetail::getPriceWithTax, bo.getPriceWithTax());
        lqw.eq(bo.getTotalAmount() != null, PurchaseTradeDetail::getTotalAmount, bo.getTotalAmount());
        return lqw;
    }

    /**
     * 新增采购入库单明细
     */
    public void insertByBo(PurchaseTradeDetailBo bo) {
        PurchaseTradeDetail add = MapstructUtils.convert(bo, PurchaseTradeDetail.class);
        purchaseTradeDetailMapper.insert(add);
    }

    /**
     * 修改采购入库单明细
     */
    public void updateByBo(PurchaseTradeDetailBo bo) {
        PurchaseTradeDetail update = MapstructUtils.convert(bo, PurchaseTradeDetail.class);
        purchaseTradeDetailMapper.updateById(update);
    }

    /**
     * 批量删除采购入库单明细
     */
    public void deleteByIds(Collection<Long> ids) {
        purchaseTradeDetailMapper.deleteBatchIds(ids);
    }

    public void saveDetails(List<PurchaseTradeDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }
}

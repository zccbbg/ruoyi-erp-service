package com.ruoyi.erp.sales.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.basic.service.SkuService;
import com.ruoyi.erp.sales.domain.bo.SalesRefundDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesRefundDetail;
import com.ruoyi.erp.sales.domain.vo.SalesRefundDetailVo;
import com.ruoyi.erp.sales.mapper.SalesRefundDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.baomidou.mybatisplus.extension.toolkit.Db.saveOrUpdateBatch;

/**
 * 销售退货单明细Service业务层处理
 *
 * @date 2025-03-11
 */
@RequiredArgsConstructor
@Service
public class SalesRefundDetailService {

    private final SalesRefundDetailMapper salesRefundDetailMapper;
    private final SkuService skuService;

    /**
     * 查询销售退货单明细
     */
    public SalesRefundDetailVo queryById(Long id){
        return salesRefundDetailMapper.selectVoById(id);
    }

    /**
     * 查询销售退货单明细列表
     */
    public TableDataInfo<SalesRefundDetailVo> queryPageList(SalesRefundDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SalesRefundDetail> lqw = buildQueryWrapper(bo);
        Page<SalesRefundDetailVo> result = salesRefundDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询销售退货单明细列表
     */
    public List<SalesRefundDetailVo> queryList(SalesRefundDetailBo bo) {
        LambdaQueryWrapper<SalesRefundDetail> lqw = buildQueryWrapper(bo);
        return salesRefundDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SalesRefundDetail> buildQueryWrapper(SalesRefundDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SalesRefundDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, SalesRefundDetail::getPid, bo.getPid());
        lqw.eq(bo.getSkuId() != null, SalesRefundDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQty() != null, SalesRefundDetail::getQty, bo.getQty());
        lqw.eq(bo.getPriceWithoutTax() != null, SalesRefundDetail::getPriceWithoutTax, bo.getPriceWithoutTax());
        lqw.eq(bo.getTaxAmount() != null, SalesRefundDetail::getTaxAmount, bo.getTaxAmount());
        lqw.eq(bo.getTaxRate() != null, SalesRefundDetail::getTaxRate, bo.getTaxRate());
        lqw.eq(bo.getPriceWithTax() != null, SalesRefundDetail::getPriceWithTax, bo.getPriceWithTax());
        lqw.eq(bo.getTotalAmount() != null, SalesRefundDetail::getTotalAmount, bo.getTotalAmount());
        return lqw;
    }

    /**
     * 新增销售退货单明细
     */
    public void insertByBo(SalesRefundDetailBo bo) {
        SalesRefundDetail add = MapstructUtils.convert(bo, SalesRefundDetail.class);
        salesRefundDetailMapper.insert(add);
    }

    /**
     * 修改销售退货单明细
     */
    public void updateByBo(SalesRefundDetailBo bo) {
        SalesRefundDetail update = MapstructUtils.convert(bo, SalesRefundDetail.class);
        salesRefundDetailMapper.updateById(update);
    }

    /**
     * 批量删除销售退货单明细
     */
    public void deleteByIds(Collection<Long> ids) {
        salesRefundDetailMapper.deleteBatchIds(ids);
    }

    public void saveDetails(List<SalesRefundDetail> addDetailList) {
        if (CollUtil.isEmpty(addDetailList)) {
            return;
        }
        saveOrUpdateBatch(addDetailList);
    }

    public List<SalesRefundDetailVo> queryByPid(Long pid) {
        List<SalesRefundDetailVo> details = salesRefundDetailMapper.selectVoList(Wrappers.lambdaQuery(SalesRefundDetail.class).eq(SalesRefundDetail::getPid, pid));
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        skuService.setSkuMap(details);
        return details;
    }
}

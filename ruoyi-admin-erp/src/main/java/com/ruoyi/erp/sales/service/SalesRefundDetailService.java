package com.ruoyi.erp.sales.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.sales.domain.bo.SalesRefundDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesRefundDetail;
import com.ruoyi.erp.sales.domain.vo.SalesRefundDetailVo;
import com.ruoyi.erp.sales.mapper.SalesRefundDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 销售退货单明细Service业务层处理
 *
 * @date 2025-03-11
 */
@RequiredArgsConstructor
@Service
public class SalesRefundDetailService {

    private final SalesRefundDetailMapper SalesRefundDetailMapper;

    /**
     * 查询销售退货单明细
     */
    public SalesRefundDetailVo queryById(Long id){
        return SalesRefundDetailMapper.selectVoById(id);
    }

    /**
     * 查询销售退货单明细列表
     */
    public TableDataInfo<SalesRefundDetailVo> queryPageList(SalesRefundDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SalesRefundDetail> lqw = buildQueryWrapper(bo);
        Page<SalesRefundDetailVo> result = SalesRefundDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询销售退货单明细列表
     */
    public List<SalesRefundDetailVo> queryList(SalesRefundDetailBo bo) {
        LambdaQueryWrapper<SalesRefundDetail> lqw = buildQueryWrapper(bo);
        return SalesRefundDetailMapper.selectVoList(lqw);
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
        SalesRefundDetailMapper.insert(add);
    }

    /**
     * 修改销售退货单明细
     */
    public void updateByBo(SalesRefundDetailBo bo) {
        SalesRefundDetail update = MapstructUtils.convert(bo, SalesRefundDetail.class);
        SalesRefundDetailMapper.updateById(update);
    }

    /**
     * 批量删除销售退货单明细
     */
    public void deleteByIds(Collection<Long> ids) {
        SalesRefundDetailMapper.deleteBatchIds(ids);
    }
}

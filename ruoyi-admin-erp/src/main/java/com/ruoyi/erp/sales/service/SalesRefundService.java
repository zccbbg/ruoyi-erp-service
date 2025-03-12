package com.ruoyi.erp.sales.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.sales.domain.bo.SalesRefundBo;
import com.ruoyi.erp.sales.domain.entity.SalesRefund;
import com.ruoyi.erp.sales.domain.vo.SalesRefundVo;
import com.ruoyi.erp.sales.mapper.SalesRefundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 销售退货单Service业务层处理
 *
 * @date 2025-03-12
 */
@RequiredArgsConstructor
@Service
public class SalesRefundService {

    private final SalesRefundMapper salesRefundMapper;

    /**
     * 查询销售退货单
     */
    public SalesRefundVo queryById(Long id){
        return salesRefundMapper.selectVoById(id);
    }

    /**
     * 查询销售退货单列表
     */
    public TableDataInfo<SalesRefundVo> queryPageList(SalesRefundBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SalesRefund> lqw = buildQueryWrapper(bo);
        Page<SalesRefundVo> result = salesRefundMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询销售退货单列表
     */
    public List<SalesRefundVo> queryList(SalesRefundBo bo) {
        LambdaQueryWrapper<SalesRefund> lqw = buildQueryWrapper(bo);
        return salesRefundMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SalesRefund> buildQueryWrapper(SalesRefundBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SalesRefund> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getTradeId() != null, SalesRefund::getTradeId, bo.getTradeId());
        lqw.eq(StringUtils.isNotBlank(bo.getDocNo()), SalesRefund::getDocNo, bo.getDocNo());
        lqw.eq(bo.getDocDate() != null, SalesRefund::getDocDate, bo.getDocDate());
        lqw.eq(bo.getCheckedStatus() != null, SalesRefund::getCheckedStatus, bo.getCheckedStatus());
        lqw.eq(bo.getMerchantId() != null, SalesRefund::getMerchantId, bo.getMerchantId());
        lqw.eq(bo.getGoodsQty() != null, SalesRefund::getGoodsQty, bo.getGoodsQty());
        lqw.eq(bo.getGoodsAmount() != null, SalesRefund::getGoodsAmount, bo.getGoodsAmount());
        lqw.eq(bo.getOtherExpensesAmount() != null, SalesRefund::getOtherExpensesAmount, bo.getOtherExpensesAmount());
        lqw.eq(bo.getDiscountAmount() != null, SalesRefund::getDiscountAmount, bo.getDiscountAmount());
        lqw.eq(bo.getActualAmount() != null, SalesRefund::getActualAmount, bo.getActualAmount());
        lqw.eq(bo.getPaidAmount() != null, SalesRefund::getPaidAmount, bo.getPaidAmount());
        lqw.eq(bo.getDeductedAmount() != null, SalesRefund::getDeductedAmount, bo.getDeductedAmount());
        lqw.eq(bo.getDueAmount() != null, SalesRefund::getDueAmount, bo.getDueAmount());
        return lqw;
    }

    /**
     * 新增销售退货单
     */
    public void insertByBo(SalesRefundBo bo) {
        SalesRefund add = MapstructUtils.convert(bo, SalesRefund.class);
        salesRefundMapper.insert(add);
    }

    /**
     * 修改销售退货单
     */
    public void updateByBo(SalesRefundBo bo) {
        SalesRefund update = MapstructUtils.convert(bo, SalesRefund.class);
        salesRefundMapper.updateById(update);
    }

    /**
     * 批量删除销售退货单
     */
    public void deleteByIds(Collection<Long> ids) {
        salesRefundMapper.deleteBatchIds(ids);
    }
}

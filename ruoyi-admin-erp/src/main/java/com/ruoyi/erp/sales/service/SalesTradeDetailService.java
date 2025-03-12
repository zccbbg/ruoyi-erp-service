package com.ruoyi.erp.sales.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.basic.service.SkuService;
import com.ruoyi.erp.sales.domain.bo.SalesTradeDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesTradeDetail;
import com.ruoyi.erp.sales.domain.vo.SalesTradeDetailVo;
import com.ruoyi.erp.sales.mapper.SalesTradeDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.baomidou.mybatisplus.extension.toolkit.Db.saveOrUpdateBatch;

/**
 * 销售入库单明细Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class SalesTradeDetailService {

    private final SalesTradeDetailMapper SalesTradeDetailMapper;
    private final SkuService skuService;

    /**
     * 查询销售入库单明细
     */
    public SalesTradeDetailVo queryById(Long id){
        return SalesTradeDetailMapper.selectVoById(id);
    }

    /**
     * 查询销售入库单明细列表
     */
    public TableDataInfo<SalesTradeDetailVo> queryPageList(SalesTradeDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SalesTradeDetail> lqw = buildQueryWrapper(bo);
        Page<SalesTradeDetailVo> result = SalesTradeDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询销售入库单明细列表
     */
    public List<SalesTradeDetailVo> queryList(SalesTradeDetailBo bo) {
        LambdaQueryWrapper<SalesTradeDetail> lqw = buildQueryWrapper(bo);
        return SalesTradeDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SalesTradeDetail> buildQueryWrapper(SalesTradeDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SalesTradeDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, SalesTradeDetail::getPid, bo.getPid());
        lqw.eq(bo.getSkuId() != null, SalesTradeDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQty() != null, SalesTradeDetail::getQty, bo.getQty());
        lqw.eq(bo.getPriceWithoutTax() != null, SalesTradeDetail::getPriceWithoutTax, bo.getPriceWithoutTax());
        lqw.eq(bo.getTaxAmount() != null, SalesTradeDetail::getTaxAmount, bo.getTaxAmount());
        lqw.eq(bo.getTaxRate() != null, SalesTradeDetail::getTaxRate, bo.getTaxRate());
        lqw.eq(bo.getPriceWithTax() != null, SalesTradeDetail::getPriceWithTax, bo.getPriceWithTax());
        lqw.eq(bo.getTotalAmount() != null, SalesTradeDetail::getTotalAmount, bo.getTotalAmount());
        return lqw;
    }

    /**
     * 新增销售入库单明细
     */
    public void insertByBo(SalesTradeDetailBo bo) {
        SalesTradeDetail add = MapstructUtils.convert(bo, SalesTradeDetail.class);
        SalesTradeDetailMapper.insert(add);
    }

    /**
     * 修改销售入库单明细
     */
    public void updateByBo(SalesTradeDetailBo bo) {
        SalesTradeDetail update = MapstructUtils.convert(bo, SalesTradeDetail.class);
        SalesTradeDetailMapper.updateById(update);
    }

    /**
     * 批量删除销售入库单明细
     */
    public void deleteByIds(Collection<Long> ids) {
        SalesTradeDetailMapper.deleteBatchIds(ids);
    }

    public void saveDetails(List<SalesTradeDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    public List<SalesTradeDetailVo> queryByPid(Long pid) {
        List<SalesTradeDetailVo> details = SalesTradeDetailMapper.selectVoList(Wrappers.lambdaQuery(SalesTradeDetail.class).eq(SalesTradeDetail::getPid, pid));
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        skuService.setSkuMap(details);
        return details;
    }
}

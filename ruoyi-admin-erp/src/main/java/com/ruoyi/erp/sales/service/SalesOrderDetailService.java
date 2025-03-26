package com.ruoyi.erp.sales.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.base.service.BaseDocService;
import com.ruoyi.erp.basic.service.SkuService;
import com.ruoyi.erp.purchase.domain.bo.PurchaseOrderDetailBo;
import com.ruoyi.erp.purchase.domain.vo.PurchaseOrderDetailVo;
import com.ruoyi.erp.sales.domain.bo.SalesOrderDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesOrderDetail;
import com.ruoyi.erp.sales.domain.vo.SalesOrderDetailVo;
import com.ruoyi.erp.sales.mapper.SalesOrderDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.baomidou.mybatisplus.extension.toolkit.Db.saveOrUpdateBatch;

@RequiredArgsConstructor
@Service
public class SalesOrderDetailService extends BaseDocService<SalesOrderDetail> {


    private final SalesOrderDetailMapper salesOrderDetailMapper;
    private final SkuService skuService;

    public List<SalesOrderDetailVo> queryList(SalesOrderDetailBo bo) {
        LambdaQueryWrapper<SalesOrderDetail> lqw = buildQueryWrapper(bo);
        return salesOrderDetailMapper.selectVoList(lqw);
    }
    public List<SalesOrderDetailVo> queryBySid(Long pid) {
        SalesOrderDetailBo bo = new SalesOrderDetailBo();
        bo.setPid(pid);
        List<SalesOrderDetailVo> details = queryList(bo);
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        skuService.setSkuMap(details);
        return details;
    }

    public void saveDetails(List<SalesOrderDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }


    private LambdaQueryWrapper<SalesOrderDetail> buildQueryWrapper(SalesOrderDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SalesOrderDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, SalesOrderDetail::getPid, bo.getPid());
        lqw.eq(bo.getSkuId() != null, SalesOrderDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQty() != null, SalesOrderDetail::getQty, bo.getQty());
        lqw.eq(bo.getProcessedQty() != null, SalesOrderDetail::getProcessedQty, bo.getProcessedQty());
        lqw.eq(bo.getPriceWithoutTax() != null, SalesOrderDetail::getPriceWithoutTax, bo.getPriceWithoutTax());
        lqw.eq(bo.getTaxAmount() != null, SalesOrderDetail::getTaxAmount, bo.getTaxAmount());
        lqw.eq(bo.getTaxRate() != null,SalesOrderDetail::getTaxRate, bo.getTaxRate());
        lqw.eq(bo.getPriceWithTax() != null, SalesOrderDetail::getPriceWithTax, bo.getPriceWithTax());
        lqw.eq(bo.getTotalAmount() != null, SalesOrderDetail::getTotalAmount, bo.getTotalAmount());
        return lqw;
    }

    public TableDataInfo<SalesOrderDetailVo> queryPageList(SalesOrderDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SalesOrderDetail> lqw = buildQueryWrapper(bo);
        IPage<SalesOrderDetailVo> result = salesOrderDetailMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    public SalesOrderDetailVo queryById(Long id) {
        return salesOrderDetailMapper.selectVoById(id);
    }

    public void insertByBo(SalesOrderDetailBo bo) {
        SalesOrderDetail add = MapstructUtils.convert(bo, SalesOrderDetail.class);
        salesOrderDetailMapper.insert(add);
    }

    public void updateByBo(SalesOrderDetailBo bo) {
        SalesOrderDetail update = MapstructUtils.convert(bo, SalesOrderDetail.class);
        salesOrderDetailMapper.updateById(update);
    }

    public void deleteByIds(List<Long> ids) {
        salesOrderDetailMapper.deleteBatchIds(ids);
    }


    public List<SalesOrderDetailVo> queryByPid(Long pid) {
        SalesOrderDetailBo bo = new SalesOrderDetailBo();
        bo.setPid(pid);
        List<SalesOrderDetailVo> details = queryList(bo);
        if (CollUtil.isEmpty(details)) {
            return Collections.emptyList();
        }
        skuService.setSkuMap(details);
        return details;
    }

    public Set<Long> getSkuIds(Long orderId) {
        SalesOrderDetailBo bo = new SalesOrderDetailBo();
        bo.setPid(orderId);
        List<SalesOrderDetailVo> details = queryList(bo);
        if (CollUtil.isEmpty(details)) {
            return Collections.emptySet();
        }
        return details.stream().map(SalesOrderDetailVo::getSkuId).collect(Collectors.toSet());
    }
}

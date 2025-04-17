package com.ruoyi.erp.sales.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.base.service.BaseDocService;
import com.ruoyi.erp.basic.types.TransType;
import com.ruoyi.erp.financial.service.MerchantBalanceService;

import com.ruoyi.erp.sales.domain.bo.SalesOrderBo;
import com.ruoyi.erp.sales.domain.bo.SalesOrderDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesOrder;
import com.ruoyi.erp.sales.domain.entity.SalesOrderDetail;
import com.ruoyi.erp.sales.domain.vo.SalesOrderVo;
import com.ruoyi.erp.sales.mapper.SalesOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@RequiredArgsConstructor
@Service
public class SalesOrderService extends BaseDocService<SalesOrderDetail> {
    private final SalesOrderMapper salesOrderMapper;
    private final SalesOrderDetailService salesOrderDetailService;
    private final MerchantBalanceService merchantBalanceService;

    /**
     * 查询销售订单
     */
    public SalesOrderVo queryById(Long id){
        SalesOrderVo salesOrderVo = salesOrderMapper.selectVoById(id);
        salesOrderVo.setDetails(salesOrderDetailService.queryBySid(id));
        return salesOrderVo;
    }

    /**
     * 查询销售订单列表
     */
    public TableDataInfo<SalesOrderVo> queryPageList(SalesOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SalesOrder> lqw = buildQueryWrapper(bo);
        Page<SalesOrderVo> result = salesOrderMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询销售订单列表
     */
    public List<SalesOrderVo> queryList(SalesOrderBo bo) {
        LambdaQueryWrapper<SalesOrder> lqw = buildQueryWrapper(bo);
        return salesOrderMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SalesOrder> buildQueryWrapper(SalesOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SalesOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getDocNo()), SalesOrder::getDocNo, bo.getDocNo());
        lqw.between(params.get("beginDocDate") != null && params.get("endDocDate") != null,
            SalesOrder::getDocDate ,params.get("beginDocDate"), params.get("endDocDate"));
        lqw.between(params.get("beginDeliveryDate") != null && params.get("endDeliveryDate") != null,
            SalesOrder::getDeliveryDate ,params.get("beginDeliveryDate"), params.get("endDeliveryDate"));
        lqw.between(params.get("beginBillDate")!= null && params.get("endBillDate") != null,
            SalesOrder::getDocNo, params.get("beginBillDate"), params.get("endBillDate"));
        lqw.eq(bo.getCheckedStatus() != null, SalesOrder::getCheckedStatus, bo.getCheckedStatus());
        lqw.eq(bo.getStockStatus() != null, SalesOrder::getStockStatus, bo.getStockStatus());
        lqw.orderByDesc(BaseEntity::getUpdateTime);
        return lqw;
    }

    /**
     * 新增销售订单
     */
    public void insertByBo(SalesOrderBo bo) {
        // 创建入库单
        SalesOrder add = MapstructUtils.convert(bo, SalesOrder.class);
        List<SalesOrderDetailBo> detailBoList = bo.getDetails();
        List<SalesOrderDetail> addDetailList = MapstructUtils.convert(detailBoList, SalesOrderDetail.class);
        Long sameWarehouseId = getSameWarehouseId(addDetailList);
        add.setWarehouseId(sameWarehouseId);
        salesOrderMapper.insert(add);
        bo.setId(add.getId());
        addDetailList.forEach(it -> {
            it.setPid(add.getId());
        });
        // 创建入库单明细
        salesOrderDetailService.saveDetails(addDetailList);
    }

    /**
     * 修改销售订单
     */
    public void updateByBo(SalesOrderBo bo) {
        SalesOrder update = MapstructUtils.convert(bo, SalesOrder.class);
        List<SalesOrderDetail> detailList = MapstructUtils.convert(bo.getDetails(), SalesOrderDetail.class);
        Long sameWarehouseId = getSameWarehouseId(detailList);
        update.setWarehouseId(sameWarehouseId);
        salesOrderMapper.updateById(update);
        // 保存入库单明细
        detailList.forEach(it -> it.setPid(bo.getId()));
        salesOrderDetailService.saveDetails(detailList);
    }

    /**
     * 批量删除销售订单
     */
    public void deleteByIds(Collection<Long> ids) {
        salesOrderMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void pass(SalesOrderBo bo) {
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        if(bo.getPrepayAmount()!=null && bo.getBankAccountId()!=null){
            merchantBalanceService.doOrder(bo, TransType.SALES_ORDER);
        }
    }
}

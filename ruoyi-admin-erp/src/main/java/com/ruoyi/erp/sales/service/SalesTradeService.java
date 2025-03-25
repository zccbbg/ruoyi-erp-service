package com.ruoyi.erp.sales.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.ServiceConstants;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.base.service.BaseDocService;
import com.ruoyi.erp.basic.types.TransType;
import com.ruoyi.erp.financial.service.MerchantBalanceService;
import com.ruoyi.erp.sales.domain.bo.SalesRefundBo;
import com.ruoyi.erp.sales.domain.bo.SalesTradeBo;
import com.ruoyi.erp.sales.domain.bo.SalesTradeDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesTrade;
import com.ruoyi.erp.sales.domain.entity.SalesTradeDetail;
import com.ruoyi.erp.sales.domain.vo.SalesTradeVo;
import com.ruoyi.erp.sales.mapper.SalesTradeMapper;
import com.ruoyi.erp.sales.service.SalesTradeDetailService;
import com.ruoyi.erp.warehouse.service.InventoryHistoryService;
import com.ruoyi.erp.warehouse.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 销售出库单Service业务层处理
 *
 * @author zcc
 * @date 2025-02-07
 */
@RequiredArgsConstructor
@Service
public class SalesTradeService extends BaseDocService<SalesTradeDetail> {

    private final SalesTradeMapper salesTradeMapper;
    private final SalesTradeDetailService salesTradeDetailService;
    private final MerchantBalanceService merchantBalanceService;
    private final InventoryService inventoryService;
    private final InventoryHistoryService inventoryHistoryService;

    /**
     * 查询销售出库单
     */
    public SalesTradeVo queryById(Long id){
        SalesTradeVo salesTradeVo = salesTradeMapper.selectVoById(id);
        salesTradeVo.setDetails(salesTradeDetailService.queryByPid(id));
        return salesTradeVo;
    }

    /**
     * 查询销售出库单列表
     */
    public TableDataInfo<SalesTradeVo> queryPageList(SalesTradeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SalesTrade> lqw = buildQueryWrapper(bo);
        Page<SalesTradeVo> result = salesTradeMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询销售出库单列表
     */
    public List<SalesTradeVo> queryList(SalesTradeBo bo) {
        LambdaQueryWrapper<SalesTrade> lqw = buildQueryWrapper(bo);
        return salesTradeMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SalesTrade> buildQueryWrapper(SalesTradeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SalesTrade> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, SalesTrade::getOrderId, bo.getOrderId());
        lqw.between(params.get("beginDocDate") != null && params.get("endDocDate") != null,
            SalesTrade::getDocDate ,params.get("beginDocDate"), params.get("endDocDate"));
        lqw.eq(StringUtils.isNotBlank(bo.getDocNo()), SalesTrade::getDocNo, bo.getDocNo());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), SalesTrade::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getCheckedStatus() != null, SalesTrade::getCheckedStatus, bo.getCheckedStatus());
        lqw.eq(bo.getRefundStatus() != null, SalesTrade::getRefundStatus, bo.getRefundStatus());
        lqw.eq(bo.getBankAccountId() != null, SalesTrade::getBankAccountId, bo.getBankAccountId());
        lqw.eq(bo.getMerchantId() != null, SalesTrade::getMerchantId, bo.getMerchantId());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增销售出库单
     */
    public void insertByBo(SalesTradeBo bo) {
        SalesTrade add = MapstructUtils.convert(bo, SalesTrade.class);
        List<SalesTradeDetailBo> detailBoList = bo.getDetails();
        List<SalesTradeDetail> addDetailList = MapstructUtils.convert(detailBoList, SalesTradeDetail.class);
        Long sameWarehouseId = getSameWarehouseId(addDetailList);
        add.setWarehouseId(sameWarehouseId);
        salesTradeMapper.insert(add);
        bo.setId(add.getId());
        addDetailList.forEach(it -> {
            it.setPid(add.getId());
        });
        // 创建入库单明细
        salesTradeDetailService.saveDetails(addDetailList);
    }

    /**
     * 修改销售出库单
     */
    public void updateByBo(SalesTradeBo bo) {
        SalesTrade update = MapstructUtils.convert(bo, SalesTrade.class);
        List<SalesTradeDetail> detailList = MapstructUtils.convert(bo.getDetails(), SalesTradeDetail.class);
        Long sameWarehouseId = getSameWarehouseId(detailList);
        update.setWarehouseId(sameWarehouseId);
        salesTradeMapper.updateById(update);
        // 保存入库单明细
        detailList.forEach(it -> it.setPid(bo.getId()));
        salesTradeDetailService.saveDetails(detailList);
    }

    /**
     * 批量删除销售出库单
     */
    public void deleteByIds(Collection<Long> ids) {
        salesTradeMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void pass(SalesTradeBo bo) {
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        merchantBalanceService.doTrade(bo, TransType.SALES_TRADE);
        inventoryService.subtract(bo.getDetails());
        inventoryHistoryService.saveInventoryHistory(bo, ServiceConstants.InventoryHistoryBizType.SALES,true);
    }

    public void refund(SalesRefundBo bo) {
        QueryWrapper<SalesTrade> qw = new QueryWrapper<>();
        qw.eq("doc_no", bo.getTradeNo());
        qw.eq("checked_status",1);
        SalesTrade salesTrade = salesTradeMapper.selectOne(qw);
        if(salesTrade !=null){
            salesTrade.setRefundStatus(1);
            BigDecimal refundAmount = salesTrade.getRefundAmount();
            if(refundAmount==null){
                refundAmount = bo.getActualAmount();
            }else {
                refundAmount = refundAmount.add(bo.getActualAmount());
            }
            salesTrade.setRefundAmount(refundAmount);
            salesTradeMapper.updateById(salesTrade);
        }
    }
}

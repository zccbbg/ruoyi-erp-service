package com.ruoyi.erp.sales.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.erp.base.constant.ServiceConstants;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.base.service.BaseDocService;
import com.ruoyi.erp.basic.types.TransType;
import com.ruoyi.erp.financial.service.MerchantBalanceService;
import com.ruoyi.erp.sales.domain.bo.SalesRefundBo;
import com.ruoyi.erp.sales.domain.bo.SalesRefundDetailBo;
import com.ruoyi.erp.sales.domain.entity.SalesRefund;
import com.ruoyi.erp.sales.domain.entity.SalesRefundDetail;
import com.ruoyi.erp.sales.domain.vo.SalesRefundVo;
import com.ruoyi.erp.sales.mapper.SalesRefundMapper;
import com.ruoyi.erp.warehouse.service.InventoryHistoryService;
import com.ruoyi.erp.warehouse.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 销售退货单Service业务层处理
 *
 * @date 2025-03-12
 */
@RequiredArgsConstructor
@Service
public class SalesRefundService extends BaseDocService<SalesRefundDetail> {

    private final SalesRefundMapper salesRefundMapper;
    private final MerchantBalanceService merchantBalanceService;
    private final InventoryService inventoryService;
    private final InventoryHistoryService inventoryHistoryService;
    private final SalesTradeService salesTradeService;
    private final SalesRefundDetailService salesRefundDetailService;

    /**
     * 查询销售退货单
     */
    public SalesRefundVo queryById(Long id){
        SalesRefundVo salesTradeVo = salesRefundMapper.selectVoById(id);
        salesTradeVo.setDetails(salesRefundDetailService.queryByPid(id));
        return salesTradeVo;
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
        lqw.between(params.get("beginBillDate")!= null && params.get("endBillDate") != null,
            SalesRefund::getDocNo, params.get("beginBillDate"), params.get("endBillDate"));
        lqw.orderByDesc(SalesRefund::getCreateTime);
        return lqw;
    }

    /**
     * 新增销售退货单
     */
    public void insertByBo(SalesRefundBo bo) {
        SalesRefund add = MapstructUtils.convert(bo, SalesRefund.class);
        List<SalesRefundDetailBo> detailBoList = bo.getDetails();
        List<SalesRefundDetail> addDetailList = MapstructUtils.convert(detailBoList, SalesRefundDetail.class);
        Long sameWarehouseId = getSameWarehouseId(addDetailList);
        add.setWarehouseId(sameWarehouseId);
        add.setTradeNo(bo.getTradeNo());
        salesRefundMapper.insert(add);
        bo.setId(add.getId());
        addDetailList.forEach(it -> {
            it.setPid(add.getId());
        });
        // 创建入库单明细
        salesRefundDetailService.saveDetails(addDetailList);
    }

    /**
     * 修改销售退货单
     */
    public void updateByBo(SalesRefundBo bo) {
        SalesRefund update = MapstructUtils.convert(bo, SalesRefund.class);
        List<SalesRefundDetail> detailList = MapstructUtils.convert(bo.getDetails(), SalesRefundDetail.class);
        Long sameWarehouseId = getSameWarehouseId(detailList);
        update.setWarehouseId(sameWarehouseId);
        salesRefundMapper.updateById(update);
        // 保存入库单明细
        detailList.forEach(it -> it.setPid(bo.getId()));
        salesRefundDetailService.saveDetails(detailList);
    }

    /**
     * 批量删除销售退货单
     */
    public void deleteByIds(Collection<Long> ids) {
        salesRefundMapper.deleteBatchIds(ids);
    }

    /**
     * 完成销售退货单
     * @param bo
     */
    public void pass(SalesRefundBo bo) {
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        //修改账户余额
        merchantBalanceService.doRefund(bo, TransType.SALES_RETURN);
        //添加库存
        inventoryService.add(bo.getDetails());
        //添加库存记录
        inventoryHistoryService.saveInventoryHistory(bo, ServiceConstants.InventoryHistoryBizType.SALES_REFUND, true);
        //添加销售单
        salesTradeService.refund(bo);


    }
}

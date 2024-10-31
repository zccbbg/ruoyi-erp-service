package com.ruoyi.erp.warehouse.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.ServiceConstants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.exception.base.BaseException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.warehouse.domain.bo.CheckDocBo;
import com.ruoyi.erp.warehouse.domain.bo.CheckDocDetailBo;
import com.ruoyi.erp.warehouse.domain.vo.CheckDocVo;
import com.ruoyi.erp.warehouse.domain.entity.CheckDoc;
import com.ruoyi.erp.warehouse.domain.entity.CheckDocDetail;
import com.ruoyi.erp.warehouse.mapper.CheckOrderMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 库存盘点单据Service业务层处理
 *
 * @author zcc
 * @date 2024-08-13
 */
@RequiredArgsConstructor
@Service
public class CheckOrderService {

    private final CheckOrderMapper checkOrderMapper;
    private final CheckDocDetailService checkDocDetailService;
    private final InventoryService inventoryService;
    private final InventoryHistoryService inventoryHistoryService;

    /**
     * 查询库存盘点单据
     */
    public CheckDocVo queryById(Long id){
        CheckDocVo checkOrderVo = checkOrderMapper.selectVoById(id);
        if (checkOrderVo == null) {
            throw new BaseException("盘库单不存在");
        }
        checkOrderVo.setDetails(checkDocDetailService.queryByCheckOrderId(id));
        return checkOrderVo;
    }

    /**
     * 查询库存盘点单据列表
     */
    public TableDataInfo<CheckDocVo> queryPageList(CheckDocBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CheckDoc> lqw = buildQueryWrapper(bo);
        Page<CheckDocVo> result = checkOrderMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询库存盘点单据列表
     */
    public List<CheckDocVo> queryList(CheckDocBo bo) {
        LambdaQueryWrapper<CheckDoc> lqw = buildQueryWrapper(bo);
        return checkOrderMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CheckDoc> buildQueryWrapper(CheckDocBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CheckDoc> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), CheckDoc::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getOrderStatus() != null, CheckDoc::getOrderStatus, bo.getOrderStatus());
        lqw.eq(bo.getTotalQuantity() != null, CheckDoc::getTotalQuantity, bo.getTotalQuantity());
        lqw.eq(bo.getWarehouseId() != null, CheckDoc::getWarehouseId, bo.getWarehouseId());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增库存盘点单据
     */
    @Transactional
    public void insertByBo(CheckDocBo bo) {
        // 校验盘库单号唯一性
        validateCheckOrderNo(bo.getOrderNo());
        // 创建盘库单
        CheckDoc add = MapstructUtils.convert(bo, CheckDoc.class);
        checkOrderMapper.insert(add);
        // 创建盘库单明细
        List<CheckDocDetail> addDetailList = MapstructUtils.convert(bo.getDetails(), CheckDocDetail.class);
        addDetailList.forEach(it -> it.setOrderId(add.getId()));
        checkDocDetailService.saveDetails(addDetailList);
    }

    private void validateCheckOrderNo(String checkOrderNo) {
        LambdaQueryWrapper<CheckDoc> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(CheckDoc::getOrderNo, checkOrderNo);
        if (checkOrderMapper.exists(lambdaQueryWrapper)) {
            throw new BaseException("盘库单号重复，请手动修改");
        }
    }

    /**
     * 修改库存盘点单据
     */
    @Transactional
    public void updateByBo(CheckDocBo bo) {
        // 更新盘库单
        CheckDoc update = MapstructUtils.convert(bo, CheckDoc.class);
        checkOrderMapper.updateById(update);
        // 保存盘库单明细
        List<CheckDocDetail> detailList = MapstructUtils.convert(bo.getDetails(), CheckDocDetail.class);
        detailList.forEach(it -> it.setOrderId(bo.getId()));
        checkDocDetailService.saveDetails(detailList);
    }

    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        checkOrderMapper.deleteById(id);
    }

    private void validateIdBeforeDelete(Long id) {
        CheckDocVo checkOrderVo = queryById(id);
        if (checkOrderVo == null) {
            throw new BaseException("盘库单不存在");
        }
        if (ServiceConstants.CheckOrderStatus.FINISH.equals(checkOrderVo.getOrderStatus())) {
            throw new ServiceException("盘库单【" + checkOrderVo.getOrderNo() + "】已盘库完成，无法删除！");
        }
    }

    /**
     * 批量删除库存盘点单据
     */
    public void deleteByIds(Collection<Long> ids) {
        checkOrderMapper.deleteBatchIds(ids);
    }

    /**
     * @param bo
     */
    @Transactional
    public void check(CheckDocBo bo) {
        List<CheckDocDetailBo> details = bo.getDetails();
        // 保存盘库单 check order
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        // 保存库存 inventory
        inventoryService.updateInventory(details);
        // 新增库存记录 inventory history
        CheckDocBo filterBo = this.filterCheckOrderDetail(bo);
        inventoryHistoryService.saveInventoryHistory(filterBo, ServiceConstants.InventoryHistoryOrderType.CHECK, true);
    }

    private CheckDocBo filterCheckOrderDetail(CheckDocBo bo) {
        CheckDocBo filterBo = SerializationUtils.clone(bo);
        List<CheckDocDetailBo> details = filterBo.getDetails().stream().filter(detail -> {
            BigDecimal result = detail.getCheckQuantity().subtract(detail.getQuantity());
            return result.signum() != 0;
        }).map(detail->{
            BigDecimal result = detail.getCheckQuantity().subtract(detail.getQuantity());
            detail.setBeforeQuantity(detail.getQuantity());
            detail.setAfterQuantity(detail.getCheckQuantity());
            detail.setQuantity(result);
            return detail;
        }).toList();
        filterBo.setDetails(details);
        return filterBo;
    }
}

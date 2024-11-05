package com.ruoyi.erp.warehouse.service;

import cn.hutool.core.collection.CollUtil;
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
import com.ruoyi.erp.warehouse.domain.bo.MovementDocBo;
import com.ruoyi.erp.warehouse.domain.entity.MovementDoc;
import com.ruoyi.erp.warehouse.domain.entity.MovementDocDetail;
import com.ruoyi.erp.warehouse.domain.vo.MovementDocVo;
import com.ruoyi.erp.warehouse.mapper.MovementDocMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 移库单Service业务层处理
 *
 * @author zcc
 * @date 2024-08-09
 */
@RequiredArgsConstructor
@Service
public class MovementDocService {

    private final MovementDocMapper movementDocMapper;
    private final MovementDocDetailService movementDocDetailService;
    private final InventoryService inventoryService;
    private final InventoryHistoryService inventoryHistoryService;


    /**
     * 查询移库单
     */
    public MovementDocVo queryById(Long id) {
        MovementDocVo movementOrderVo = movementDocMapper.selectVoById(id);
        if (movementOrderVo == null) {
            throw new BaseException("移库单不存在");
        }
        movementOrderVo.setDetails(movementDocDetailService.queryByMovementDocId(id));
        return movementOrderVo;
    }

    /**
     * 查询移库单列表
     */
    public TableDataInfo<MovementDocVo> queryPageList(MovementDocBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MovementDoc> lqw = buildQueryWrapper(bo);
        Page<MovementDocVo> result = movementDocMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询移库单列表
     */
    public List<MovementDocVo> queryList(MovementDocBo bo) {
        LambdaQueryWrapper<MovementDoc> lqw = buildQueryWrapper(bo);
        return movementDocMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MovementDoc> buildQueryWrapper(MovementDocBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MovementDoc> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getDocCode()), MovementDoc::getDocCode, bo.getDocCode());
        lqw.eq(bo.getSourceWarehouseId() != null, MovementDoc::getSourceWarehouseId, bo.getSourceWarehouseId());
        lqw.eq(bo.getTargetWarehouseId() != null, MovementDoc::getTargetWarehouseId, bo.getTargetWarehouseId());
        lqw.eq(bo.getCheckedStatus() != null, MovementDoc::getCheckedStatus, bo.getCheckedStatus());
        lqw.eq(bo.getTotalQuantity() != null, MovementDoc::getTotalQuantity, bo.getTotalQuantity());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增移库单
     */
    @Transactional
    public void insertByBo(MovementDocBo bo) {
        // 1.校验移库单号唯一性
        validateMovementBizNo(bo.getDocCode());
        // 2.创建移库单
        MovementDoc add = MapstructUtils.convert(bo, MovementDoc.class);
        movementDocMapper.insert(add);
        bo.setId(add.getId());
        // 3.创建移库单明细
        List<MovementDocDetail> addDetailList = MapstructUtils.convert(bo.getDetails(), MovementDocDetail.class);
        addDetailList.forEach(it -> {
            it.setPid(add.getId());
        });
        movementDocDetailService.saveDetails(addDetailList);
    }

    private void validateMovementBizNo(String movementBizNo) {
        LambdaQueryWrapper<MovementDoc> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(MovementDoc::getDocCode, movementBizNo);
        if (movementDocMapper.exists(lambdaQueryWrapper)) {
            throw new BaseException("移库单号重复，请手动修改");
        }
    }

    /**
     * 修改移库单
     */
    @Transactional
    public void updateByBo(MovementDocBo bo) {
        // 1.更新移库单
        MovementDoc update = MapstructUtils.convert(bo, MovementDoc.class);
        movementDocMapper.updateById(update);
        // 2.保存移库单明细
        List<MovementDocDetail> detailList = MapstructUtils.convert(bo.getDetails(), MovementDocDetail.class);
        detailList.forEach(it -> it.setPid(bo.getId()));
        movementDocDetailService.saveDetails(detailList);
    }

    /**
     * 删除移库单
     * @param id
     */
    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        movementDocMapper.deleteById(id);
    }

    private void validateIdBeforeDelete(Long id) {
        MovementDocVo movementBizVo = queryById(id);
        if (movementBizVo == null) {
            throw new BaseException("移库单不存在");
        }
        if (ServiceConstants.Status.FINISH.equals(movementBizVo.getCheckedStatus())) {
            throw new ServiceException("移库单【" + movementBizVo.getDocCode() + "】已移库，无法删除！");
        }
    }

    /**
     * 批量删除移库单
     */
    public void deleteByIds(Collection<Long> ids) {
        movementDocMapper.deleteBatchIds(ids);
    }

    /**
     * 移库
     * @param bo
     */
    @Transactional
    public void move(MovementDocBo bo) {


        // 1.校验商品明细不能为空！
        validateBeforeMove(bo);

        // 3.保存移库单核移库单明细
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        // 4.更新库存Inventory
        MovementDocBo shipmentBo = getShipmentBo(bo);
        inventoryService.subtract(shipmentBo.getDetails());

        MovementDocBo receiptBo = getReceiptBo(bo);
        inventoryService.add(receiptBo.getDetails());


        // 6.创建库存记录流水
        inventoryHistoryService.saveInventoryHistory(shipmentBo, ServiceConstants.InventoryHistoryBizType.MOVEMENT,false);
        inventoryHistoryService.saveInventoryHistory(receiptBo, ServiceConstants.InventoryHistoryBizType.MOVEMENT,true);
    }

    private MovementDocBo getReceiptBo(MovementDocBo bo) {

        MovementDocBo receiptBo = SerializationUtils.clone(bo);
        receiptBo.getDetails().forEach(detail -> detail.setWarehouseId(detail.getTargetWarehouseId()));
        return receiptBo;
    }

    private MovementDocBo getShipmentBo(MovementDocBo bo) {
        MovementDocBo shipmentBo = SerializationUtils.clone(bo);
        shipmentBo.getDetails().forEach(detail -> detail.setWarehouseId(detail.getSourceWarehouseId()));
        return shipmentBo;
    }

    private void validateBeforeMove(MovementDocBo bo) {
        if (CollUtil.isEmpty(bo.getDetails())) {
            throw new BaseException("商品明细不能为空！");
        }
    }
}

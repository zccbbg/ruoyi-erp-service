package com.ruoyi.erp.warehouse.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.constant.ServiceConstants;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.exception.base.BaseException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.warehouse.domain.bo.OtherShipmentDocBo;
import com.ruoyi.erp.warehouse.domain.bo.OtherShipmentDocDetailBo;
import com.ruoyi.erp.warehouse.domain.entity.OtherShipmentDoc;
import com.ruoyi.erp.warehouse.domain.entity.OtherShipmentDocDetail;
import com.ruoyi.erp.warehouse.domain.vo.OtherShipmentDocVo;
import com.ruoyi.erp.warehouse.mapper.OtherShipmentDocMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 出库单Service业务层处理
 *
 * @author zcc
 * @date 2024-08-01
 */
@RequiredArgsConstructor
@Service
public class OtherShipmentDocService {

    private final OtherShipmentDocMapper otherShipmentDocMapper;
    private final OtherShipmentDocDetailService otherShipmentDocDetailService;
    private final InventoryService inventoryService;
    private final InventoryHistoryService inventoryHistoryService;

    /**
     * 查询出库单
     */
    public OtherShipmentDocVo queryById(Long id){
        OtherShipmentDocVo shipmentOrderVo = otherShipmentDocMapper.selectVoById(id);
        if (shipmentOrderVo == null) {
            throw new BaseException("出库单不存在");
        }
        shipmentOrderVo.setDetails(otherShipmentDocDetailService.queryByShipmentDocId(shipmentOrderVo.getId()));
        return shipmentOrderVo;
    }

    /**
     * 查询出库单列表
     */
    public TableDataInfo<OtherShipmentDocVo> queryPageList(OtherShipmentDocBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OtherShipmentDoc> lqw = buildQueryWrapper(bo);
        Page<OtherShipmentDocVo> result = otherShipmentDocMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询出库单列表
     */
    public List<OtherShipmentDocVo> queryList(OtherShipmentDocBo bo) {
        LambdaQueryWrapper<OtherShipmentDoc> lqw = buildQueryWrapper(bo);
        return otherShipmentDocMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OtherShipmentDoc> buildQueryWrapper(OtherShipmentDocBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OtherShipmentDoc> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getDocNo()), OtherShipmentDoc::getDocNo, bo.getDocNo());
        lqw.eq(bo.getOptType() != null, OtherShipmentDoc::getOptType, bo.getOptType());
        lqw.eq(bo.getMerchantId() != null, OtherShipmentDoc::getMerchantId, bo.getMerchantId());
        lqw.eq(bo.getGoodsAmount() != null, OtherShipmentDoc::getGoodsAmount, bo.getGoodsAmount());
        lqw.eq(bo.getGoodsQty() != null, OtherShipmentDoc::getGoodsQty, bo.getGoodsQty());
        lqw.eq(bo.getCheckedStatus() != null, OtherShipmentDoc::getCheckedStatus, bo.getCheckedStatus());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 暂存出库单
     */
    @Transactional
    public void insertByBo(OtherShipmentDocBo bo) {
        // 校验出库单号唯一性
        validateShipmentOrderNo(bo.getDocNo());
        // 创建出库单
        OtherShipmentDoc add = MapstructUtils.convert(bo, OtherShipmentDoc.class);
        otherShipmentDocMapper.insert(add);
        bo.setId(add.getId());
        List<OtherShipmentDocDetailBo> detailBoList = bo.getDetails();
        List<OtherShipmentDocDetail> addDetailList = MapstructUtils.convert(detailBoList, OtherShipmentDocDetail.class);
        addDetailList.forEach(it -> it.setPid(add.getId()));
        otherShipmentDocDetailService.saveDetails(addDetailList);
    }

    public void validateShipmentOrderNo(String shipmentOrderNo) {
        LambdaQueryWrapper<OtherShipmentDoc> receiptOrderLqw = Wrappers.lambdaQuery();
        receiptOrderLqw.eq(OtherShipmentDoc::getDocNo, shipmentOrderNo);
        OtherShipmentDoc shipmentOrder = otherShipmentDocMapper.selectOne(receiptOrderLqw);
        Assert.isNull(shipmentOrder, "出库单号重复，请手动修改");
    }


    /**
     * 修改出库单
     */
    @Transactional
    public void updateByBo(OtherShipmentDocBo bo) {
        // 更新出库单
        OtherShipmentDoc update = MapstructUtils.convert(bo, OtherShipmentDoc.class);
        otherShipmentDocMapper.updateById(update);
        // 保存出库单明细
        List<OtherShipmentDocDetail> detailList = MapstructUtils.convert(bo.getDetails(), OtherShipmentDocDetail.class);
        detailList.forEach(it -> it.setPid(bo.getId()));
        otherShipmentDocDetailService.saveDetails(detailList);
    }

    /**
     * 批量删除出库单
     */
    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        otherShipmentDocMapper.deleteById(id);
    }

    public void validateIdBeforeDelete(Long id) {
        OtherShipmentDocVo shipmentOrderVo = queryById(id);
        if (shipmentOrderVo == null) {
            throw new BaseException("出库单不存在");
        }
        if (ServiceConstants.Status.FINISH.equals(shipmentOrderVo.getDocNo())) {
            throw new ServiceException("删除失败", HttpStatus.CONFLICT,"出库单【" + shipmentOrderVo.getDocNo() + "】已出库，无法删除！");
        }
    }

    /**
     * 出库
     * @param bo
     */
    @Transactional
    public void shipment(OtherShipmentDocBo bo) {
        // 1.校验商品明细不能为空！
        validateBeforeShipment(bo);
        // 2. 保存入库单和入库单明细
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        // 3.更新库存：Inventory表
        inventoryService.subtract(bo.getDetails());

        // 4.创建库存记录
        inventoryHistoryService.saveInventoryHistory(bo,ServiceConstants.InventoryHistoryBizType.SHIPMENT,false);
    }


    private void validateBeforeShipment(OtherShipmentDocBo bo) {
        if (CollUtil.isEmpty(bo.getDetails())) {
            throw new BaseException("商品明细不能为空！");
        }
    }
}

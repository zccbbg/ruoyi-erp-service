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
import com.ruoyi.erp.warehouse.domain.bo.OtherReceiptDocBo;
import com.ruoyi.erp.warehouse.domain.bo.OtherReceiptDocDetailBo;
import com.ruoyi.erp.warehouse.domain.entity.OtherReceiptDoc;
import com.ruoyi.erp.warehouse.domain.entity.OtherReceiptDocDetail;
import com.ruoyi.erp.warehouse.domain.vo.OtherReceiptDocVo;
import com.ruoyi.erp.warehouse.mapper.OtherReceiptDocMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 入库单Service业务层处理
 *
 * @author zcc
 * @date 2024-07-19
 */
@RequiredArgsConstructor
@Service
public class OtherReceiptDocService {

    private final OtherReceiptDocMapper otherReceiptDocMapper;
    private final OtherReceiptDocDetailService otherReceiptDocDetailService;
    private final InventoryService inventoryService;
    private final InventoryHistoryService inventoryHistoryService;

    /**
     * 查询入库单
     */
    public OtherReceiptDocVo queryById(Long id){
        OtherReceiptDocVo receiptDocVo = otherReceiptDocMapper.selectVoById(id);
        Assert.notNull(receiptDocVo, "入库单不存在");
        receiptDocVo.setDetails(otherReceiptDocDetailService.queryByReceiptDocId(id));
        return receiptDocVo;
    }

    /**
     * 查询入库单列表
     */
    public TableDataInfo<OtherReceiptDocVo> queryPageList(OtherReceiptDocBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OtherReceiptDoc> lqw = buildQueryWrapper(bo);
        Page<OtherReceiptDocVo> result = otherReceiptDocMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询入库单列表
     */
    public List<OtherReceiptDocVo> queryList(OtherReceiptDocBo bo) {
        LambdaQueryWrapper<OtherReceiptDoc> lqw = buildQueryWrapper(bo);
        return otherReceiptDocMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OtherReceiptDoc> buildQueryWrapper(OtherReceiptDocBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OtherReceiptDoc> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getDocNo()), OtherReceiptDoc::getDocNo, bo.getDocNo());
        lqw.eq(bo.getGoodsAmount() != null, OtherReceiptDoc::getGoodsAmount, bo.getGoodsAmount());
        lqw.eq(bo.getCheckedStatus() != null, OtherReceiptDoc::getCheckedStatus, bo.getCheckedStatus());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 暂存入库单
     */
    @Transactional
    public void insertByBo(OtherReceiptDocBo bo) {
        // 校验入库单号唯一性
        validateReceiptBizNo(bo.getDocNo());
        // 创建入库单
        OtherReceiptDoc add = MapstructUtils.convert(bo, OtherReceiptDoc.class);
        List<OtherReceiptDocDetailBo> detailBoList = bo.getDetails();
        List<OtherReceiptDocDetail> addDetailList = MapstructUtils.convert(detailBoList, OtherReceiptDocDetail.class);
        Long sameWarehouseId = getSameWarehouseId(addDetailList);
        add.setWarehouseId(sameWarehouseId);
        otherReceiptDocMapper.insert(add);
        bo.setId(add.getId());

        addDetailList.forEach(it -> {
            it.setPid(add.getId());
        });
        // 创建入库单明细
        otherReceiptDocDetailService.saveDetails(addDetailList);
    }

    private Long getSameWarehouseId(List<OtherReceiptDocDetail> detailBoList){
        if (detailBoList == null || detailBoList.isEmpty()) {
            return null; // 空列表返回null
        }

        Long firstWarehouseId = detailBoList.get(0).getWarehouseId(); // 获取第一个元素的warehouseId
        if(firstWarehouseId == null){
            return null;
        }
        for (OtherReceiptDocDetail detail : detailBoList) {
            if (!firstWarehouseId.equals(detail.getWarehouseId())) {
                return null; // 如果发现不一致的warehouseId，返回null
            }
        }

        return firstWarehouseId; // 所有warehouseId都相同，返回第一个warehouseId
    }

    /**
     * 入库：
     * 1.校验
     * 2.保存入库单和入库单明细
     * 3.保存库存明细
     * 4.增加库存
     * 5.保存库存记录
     */
    @Transactional
    public void inbound(OtherReceiptDocBo bo) {
        // 1. 校验
        validateBeforeReceive(bo);

        // 2. 保存入库单和入库单明细
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }

        // 3.增加库存
        inventoryService.add(bo.getDetails());

        // 4.保存库存记录
        inventoryHistoryService.saveInventoryHistory(bo,ServiceConstants.InventoryHistoryBizType.RECEIPT,true);
    }

    private void validateBeforeReceive(OtherReceiptDocBo bo) {
        if (CollUtil.isEmpty(bo.getDetails())) {
            throw new BaseException("商品明细不能为空");
        }
    }

    /**
     * 修改入库单
     */
    @Transactional
    public void updateByBo(OtherReceiptDocBo bo) {
        // 更新入库单
        OtherReceiptDoc update = MapstructUtils.convert(bo, OtherReceiptDoc.class);
        List<OtherReceiptDocDetail> detailList = MapstructUtils.convert(bo.getDetails(), OtherReceiptDocDetail.class);
        Long sameWarehouseId = getSameWarehouseId(detailList);
        update.setWarehouseId(sameWarehouseId);
        otherReceiptDocMapper.updateById(update);
        // 保存入库单明细
        detailList.forEach(it -> it.setPid(bo.getId()));
        otherReceiptDocDetailService.saveDetails(detailList);
    }


    /**
     * 删除入库单
     */
    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        otherReceiptDocMapper.deleteById(id);
    }

    private void validateIdBeforeDelete(Long id) {
        OtherReceiptDocVo receiptBizVo = queryById(id);
        Assert.notNull(receiptBizVo, "入库单不存在");
        if (ServiceConstants.Status.FINISH.equals(receiptBizVo.getCheckedStatus())) {
            throw new ServiceException("删除失败", HttpStatus.CONFLICT,"入库单【" + receiptBizVo.getDocNo() + "】已入库，无法删除！");
        }
    }

    /**
     * 批量删除入库单
     */
    public void deleteByIds(Collection<Long> ids) {
        otherReceiptDocMapper.deleteBatchIds(ids);
    }

    public void validateReceiptBizNo(String receiptBizNo) {
        LambdaQueryWrapper<OtherReceiptDoc> receiptDocLqw = Wrappers.lambdaQuery();
        receiptDocLqw.eq(OtherReceiptDoc::getDocNo, receiptBizNo);
        OtherReceiptDoc receiptDoc = otherReceiptDocMapper.selectOne(receiptDocLqw);
        Assert.isNull(receiptDoc, "入库单号重复，请手动修改");
    }
}

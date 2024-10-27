package com.ruoyi.warehouse.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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
import com.ruoyi.warehouse.domain.bo.ReceiptDocBo;
import com.ruoyi.warehouse.domain.bo.ReceiptDocDetailBo;
import com.ruoyi.warehouse.domain.entity.OtherReceiptDoc;
import com.ruoyi.warehouse.domain.entity.OtherReceiptDocDetail;
import com.ruoyi.warehouse.domain.vo.ReceiptOrderVo;
import com.ruoyi.warehouse.mapper.ReceiptOrderMapper;
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
public class ReceiptOrderService {

    private final ReceiptOrderMapper receiptOrderMapper;
    private final ReceiptOrderDetailService receiptOrderDetailService;
    private final InventoryService inventoryService;
    private final InventoryHistoryService inventoryHistoryService;

    /**
     * 查询入库单
     */
    public ReceiptOrderVo queryById(Long id){
        ReceiptOrderVo receiptOrderVo = receiptOrderMapper.selectVoById(id);
        Assert.notNull(receiptOrderVo, "入库单不存在");
        receiptOrderVo.setDetails(receiptOrderDetailService.queryByReceiptOrderId(id));
        return receiptOrderVo;
    }

    /**
     * 查询入库单列表
     */
    public TableDataInfo<ReceiptOrderVo> queryPageList(ReceiptDocBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OtherReceiptDoc> lqw = buildQueryWrapper(bo);
        Page<ReceiptOrderVo> result = receiptOrderMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询入库单列表
     */
    public List<ReceiptOrderVo> queryList(ReceiptDocBo bo) {
        LambdaQueryWrapper<OtherReceiptDoc> lqw = buildQueryWrapper(bo);
        return receiptOrderMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OtherReceiptDoc> buildQueryWrapper(ReceiptDocBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OtherReceiptDoc> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), OtherReceiptDoc::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getOptType() != null, OtherReceiptDoc::getOptType, bo.getOptType());
        lqw.eq(bo.getMerchantId() != null, OtherReceiptDoc::getMerchantId, bo.getMerchantId());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), OtherReceiptDoc::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getTotalAmount() != null, OtherReceiptDoc::getTotalAmount, bo.getTotalAmount());
        lqw.eq(bo.getOrderStatus() != null, OtherReceiptDoc::getOrderStatus, bo.getOrderStatus());
        lqw.orderByDesc(BaseEntity::getCreateTime);
        return lqw;
    }

    /**
     * 暂存入库单
     */
    @Transactional
    public void insertByBo(ReceiptDocBo bo) {
        // 校验入库单号唯一性
        validateReceiptOrderNo(bo.getOrderNo());
        // 创建入库单
        OtherReceiptDoc add = MapstructUtils.convert(bo, OtherReceiptDoc.class);
        receiptOrderMapper.insert(add);
        bo.setId(add.getId());
        List<ReceiptDocDetailBo> detailBoList = bo.getDetails();
        List<OtherReceiptDocDetail> addDetailList = MapstructUtils.convert(detailBoList, OtherReceiptDocDetail.class);
        addDetailList.forEach(it -> {
            it.setOrderId(add.getId());
        });
        // 创建入库单明细
        receiptOrderDetailService.saveDetails(addDetailList);
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
    public void receive(ReceiptDocBo bo) {
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
        inventoryHistoryService.saveInventoryHistory(bo,ServiceConstants.InventoryHistoryOrderType.RECEIPT,true);
    }

    private void validateBeforeReceive(ReceiptDocBo bo) {
        if (CollUtil.isEmpty(bo.getDetails())) {
            throw new BaseException("商品明细不能为空");
        }
    }

    /**
     * 修改入库单
     */
    @Transactional
    public void updateByBo(ReceiptDocBo bo) {
        // 更新入库单
        OtherReceiptDoc update = MapstructUtils.convert(bo, OtherReceiptDoc.class);
        receiptOrderMapper.updateById(update);
        // 保存入库单明细
        List<OtherReceiptDocDetail> detailList = MapstructUtils.convert(bo.getDetails(), OtherReceiptDocDetail.class);
        detailList.forEach(it -> it.setOrderId(bo.getId()));
        receiptOrderDetailService.saveDetails(detailList);
    }

    /**
     * 入库单作废
     * @param id
     */
    public void editToInvalid(Long id) {
        LambdaUpdateWrapper<OtherReceiptDoc> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(OtherReceiptDoc::getId, id);
        wrapper.set(OtherReceiptDoc::getOrderStatus, ServiceConstants.ReceiptOrderStatus.INVALID);
        receiptOrderMapper.update(null, wrapper);
    }

    /**
     * 删除入库单
     */
    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        receiptOrderMapper.deleteById(id);
    }

    private void validateIdBeforeDelete(Long id) {
        ReceiptOrderVo receiptOrderVo = queryById(id);
        Assert.notNull(receiptOrderVo, "入库单不存在");
        if (ServiceConstants.ReceiptOrderStatus.FINISH.equals(receiptOrderVo.getOrderStatus())) {
            throw new ServiceException("删除失败", HttpStatus.CONFLICT,"入库单【" + receiptOrderVo.getOrderNo() + "】已入库，无法删除！");
        }
    }

    /**
     * 批量删除入库单
     */
    public void deleteByIds(Collection<Long> ids) {
        receiptOrderMapper.deleteBatchIds(ids);
    }

    public void validateReceiptOrderNo(String receiptOrderNo) {
        LambdaQueryWrapper<OtherReceiptDoc> receiptOrderLqw = Wrappers.lambdaQuery();
        receiptOrderLqw.eq(OtherReceiptDoc::getOrderNo, receiptOrderNo);
        OtherReceiptDoc receiptOrder = receiptOrderMapper.selectOne(receiptOrderLqw);
        Assert.isNull(receiptOrder, "入库单号重复，请手动修改");
    }
}

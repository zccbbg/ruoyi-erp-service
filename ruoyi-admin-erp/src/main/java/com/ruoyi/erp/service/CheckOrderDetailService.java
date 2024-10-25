package com.ruoyi.erp.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.domain.bo.CheckOrderDetailBo;
import com.ruoyi.erp.domain.entity.CheckDocDetail;
import com.ruoyi.erp.domain.vo.CheckOrderDetailVo;
import com.ruoyi.erp.mapper.CheckOrderDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 库存盘点单据详情Service业务层处理
 *
 * @author zcc
 * @date 2024-08-13
 */
@RequiredArgsConstructor
@Service
public class CheckOrderDetailService extends ServiceImpl<CheckOrderDetailMapper, CheckDocDetail> {

    private final CheckOrderDetailMapper checkOrderDetailMapper;
    private final ItemSkuService itemSkuService;

    /**
     * 查询库存盘点单据详情
     */
    public CheckOrderDetailVo queryById(Long id){
        return checkOrderDetailMapper.selectVoById(id);
    }

    /**
     * 查询库存盘点单据详情列表
     */
    public TableDataInfo<CheckOrderDetailVo> queryPageList(CheckOrderDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CheckDocDetail> lqw = buildQueryWrapper(bo);
        Page<CheckOrderDetailVo> result = checkOrderDetailMapper.selectVoPage(pageQuery.build(), lqw);
        if (CollUtil.isEmpty(result.getRecords())) {
            return TableDataInfo.build(result);
        }
        itemSkuService.setItemSkuMap(result.getRecords());
        return TableDataInfo.build(result);
    }

    /**
     * 查询库存盘点单据详情列表
     */
    public List<CheckOrderDetailVo> queryList(CheckOrderDetailBo bo) {
        LambdaQueryWrapper<CheckDocDetail> lqw = buildQueryWrapper(bo);
        return checkOrderDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CheckDocDetail> buildQueryWrapper(CheckOrderDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CheckDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, CheckDocDetail::getOrderId, bo.getOrderId());
        lqw.eq(bo.getSkuId() != null, CheckDocDetail::getSkuId, bo.getSkuId());
        lqw.eq(bo.getQuantity() != null, CheckDocDetail::getQuantity, bo.getQuantity());
        lqw.eq(bo.getCheckQuantity() != null, CheckDocDetail::getCheckQuantity, bo.getCheckQuantity());
        lqw.eq(bo.getWarehouseId() != null, CheckDocDetail::getWarehouseId, bo.getWarehouseId());
        lqw.apply(bo.getHaveProfitAndLoss() != null && bo.getHaveProfitAndLoss(), "quantity != check_quantity");
        return lqw;
    }

    /**
     * 新增库存盘点单据详情
     */
    public void insertByBo(CheckOrderDetailBo bo) {
        CheckDocDetail add = MapstructUtils.convert(bo, CheckDocDetail.class);
        checkOrderDetailMapper.insert(add);
    }

    /**
     * 修改库存盘点单据详情
     */
    public void updateByBo(CheckOrderDetailBo bo) {
        CheckDocDetail update = MapstructUtils.convert(bo, CheckDocDetail.class);
        checkOrderDetailMapper.updateById(update);
    }

    /**
     * 批量删除库存盘点单据详情
     */
    public void deleteByIds(Collection<Long> ids) {
        checkOrderDetailMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void saveDetails(List<CheckDocDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    public List<CheckOrderDetailVo> queryByCheckOrderId(Long checkOrderId) {
        CheckOrderDetailBo bo = new CheckOrderDetailBo();
        bo.setOrderId(checkOrderId);
        List<CheckOrderDetailVo> details = queryList(bo);
        itemSkuService.setItemSkuMap(details);
        return details;
    }
}

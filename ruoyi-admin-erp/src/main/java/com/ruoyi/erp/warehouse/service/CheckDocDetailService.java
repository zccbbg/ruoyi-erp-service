package com.ruoyi.erp.warehouse.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.erp.basic.service.SkuService;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.warehouse.domain.bo.CheckDocDetailBo;
import com.ruoyi.erp.warehouse.domain.vo.CheckDocDetailVo;
import com.ruoyi.erp.warehouse.mapper.CheckDocDetailMapper;
import com.ruoyi.erp.warehouse.domain.entity.CheckDocDetail;
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
public class CheckDocDetailService extends ServiceImpl<CheckDocDetailMapper, CheckDocDetail> {

    private final CheckDocDetailMapper checkDocDetailMapper;
    private final SkuService skuService;

    /**
     * 查询库存盘点单据详情
     */
    public CheckDocDetailVo queryById(Long id){
        return checkDocDetailMapper.selectVoById(id);
    }

    /**
     * 查询库存盘点单据详情列表
     */
    public TableDataInfo<CheckDocDetailVo> queryPageList(CheckDocDetailBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CheckDocDetail> lqw = buildQueryWrapper(bo);
        Page<CheckDocDetailVo> result = checkDocDetailMapper.selectVoPage(pageQuery.build(), lqw);
        if (CollUtil.isEmpty(result.getRecords())) {
            return TableDataInfo.build(result);
        }
        skuService.setSkuMap(result.getRecords());
        return TableDataInfo.build(result);
    }

    /**
     * 查询库存盘点单据详情列表
     */
    public List<CheckDocDetailVo> queryList(CheckDocDetailBo bo) {
        LambdaQueryWrapper<CheckDocDetail> lqw = buildQueryWrapper(bo);
        return checkDocDetailMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CheckDocDetail> buildQueryWrapper(CheckDocDetailBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CheckDocDetail> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPid() != null, CheckDocDetail::getPid, bo.getPid());
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
    public void insertByBo(CheckDocDetailBo bo) {
        CheckDocDetail add = MapstructUtils.convert(bo, CheckDocDetail.class);
        checkDocDetailMapper.insert(add);
    }

    /**
     * 修改库存盘点单据详情
     */
    public void updateByBo(CheckDocDetailBo bo) {
        CheckDocDetail update = MapstructUtils.convert(bo, CheckDocDetail.class);
        checkDocDetailMapper.updateById(update);
    }

    /**
     * 批量删除库存盘点单据详情
     */
    public void deleteByIds(Collection<Long> ids) {
        checkDocDetailMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void saveDetails(List<CheckDocDetail> list) {
        if (CollUtil.isEmpty(list)) {
            return;
        }
        saveOrUpdateBatch(list);
    }

    public List<CheckDocDetailVo> queryByCheckDocId(Long checkDocId) {
        CheckDocDetailBo bo = new CheckDocDetailBo();
        bo.setPid(checkDocId);
        List<CheckDocDetailVo> details = queryList(bo);
        skuService.setSkuMap(details);
        return details;
    }
}

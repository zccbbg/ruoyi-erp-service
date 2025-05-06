package com.ruoyi.erp.financial.service;

import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.domain.BaseHistoryEntity;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.erp.financial.domain.entity.PaymentVoucher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.financial.domain.bo.TransHistoryBo;
import com.ruoyi.erp.financial.domain.vo.TransHistoryVo;
import com.ruoyi.erp.financial.domain.entity.TransHistory;
import com.ruoyi.erp.financial.mapper.TransHistoryMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 交易流水Service业务层处理
 *
 * @author zcc
 * @date 2025-02-19
 */
@RequiredArgsConstructor
@Service
public class TransHistoryService {

    private final TransHistoryMapper transHistoryMapper;

    /**
     * 查询交易流水
     */
    public TransHistoryVo queryById(Long id){
        return transHistoryMapper.selectVoById(id);
    }

    /**
     * 查询交易流水列表
     */
    public TableDataInfo<TransHistoryVo> queryPageList(TransHistoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TransHistory> lqw = buildQueryWrapper(bo);
        Page<TransHistoryVo> result = transHistoryMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询交易流水列表
     */
    public List<TransHistoryVo> queryList(TransHistoryBo bo) {
        LambdaQueryWrapper<TransHistory> lqw = buildQueryWrapper(bo);
        return transHistoryMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TransHistory> buildQueryWrapper(TransHistoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TransHistory> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMerchantId() != null, TransHistory::getMerchantId, bo.getMerchantId());
        lqw.eq(bo.getBankAccountId() != null, TransHistory::getBankAccountId, bo.getBankAccountId());
        lqw.eq(StringUtils.isNotBlank(bo.getTransType()), TransHistory::getTransType, bo.getTransType());
        lqw.eq(StringUtils.isNotBlank(bo.getRelatedNo()), TransHistory::getRelatedNo, bo.getRelatedNo());
        lqw.between(params.get("beginTransDate") != null && params.get("endTransDate") != null,
            TransHistory::getCreateTime ,params.get("beginTransDate"), params.get("endTransDate"));
        lqw.orderByDesc(BaseHistoryEntity::getCreateTime);
        return lqw;
    }

    /**
     * 新增交易流水
     */
    public void insertByBo(TransHistoryBo bo) {
        TransHistory add = MapstructUtils.convert(bo, TransHistory.class);
        transHistoryMapper.insert(add);
    }

    /**
     * 修改交易流水
     */
    public void updateByBo(TransHistoryBo bo) {
        TransHistory update = MapstructUtils.convert(bo, TransHistory.class);
        transHistoryMapper.updateById(update);
    }

    /**
     * 批量删除交易流水
     */
    public void deleteByIds(Collection<Long> ids) {
        transHistoryMapper.deleteBatchIds(ids);
    }
}

package com.ruoyi.erp.financial.service;

import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.erp.basic.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.financial.domain.bo.ReceiptVoucherBo;
import com.ruoyi.erp.financial.domain.vo.ReceiptVoucherVo;
import com.ruoyi.erp.financial.domain.entity.ReceiptVoucher;
import com.ruoyi.erp.financial.mapper.ReceiptVoucherMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

/**
 * 收款单Service业务层处理
 *
 * @author zcc
 * @date 2025-02-14
 */
@RequiredArgsConstructor
@Service
public class ReceiptVoucherService {

    private final ReceiptVoucherMapper receiptVoucherMapper;
    private final MerchantBalanceService merchantBalanceService;

    /**
     * 查询收款单
     */
    public ReceiptVoucherVo queryById(Long id){
        return receiptVoucherMapper.selectVoById(id);
    }

    /**
     * 查询收款单列表
     */
    public TableDataInfo<ReceiptVoucherVo> queryPageList(ReceiptVoucherBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ReceiptVoucher> lqw = buildQueryWrapper(bo);
        Page<ReceiptVoucherVo> result = receiptVoucherMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询收款单列表
     */
    public List<ReceiptVoucherVo> queryList(ReceiptVoucherBo bo) {
        LambdaQueryWrapper<ReceiptVoucher> lqw = buildQueryWrapper(bo);
        return receiptVoucherMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ReceiptVoucher> buildQueryWrapper(ReceiptVoucherBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ReceiptVoucher> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getVoucherNo()), ReceiptVoucher::getVoucherNo, bo.getVoucherNo());
        lqw.between(params.get("beginTransDate") != null && params.get("endTransDate") != null,
            ReceiptVoucher::getTransDate ,params.get("beginTransDate"), params.get("endTransDate"));
        lqw.eq(bo.getMerchantId() != null, ReceiptVoucher::getMerchantId, bo.getMerchantId());
        lqw.eq(bo.getBankAccountId() != null, ReceiptVoucher::getBankAccountId, bo.getBankAccountId());
        lqw.orderByDesc(BaseEntity::getUpdateTime);
        return lqw;
    }

    /**
     * 新增收款单
     */
    public void insertByBo(ReceiptVoucherBo bo) {
        ReceiptVoucher add = MapstructUtils.convert(bo, ReceiptVoucher.class);
        receiptVoucherMapper.insert(add);
        bo.setId(add.getId());
    }

    /**
     * 修改收款单
     */
    public void updateByBo(ReceiptVoucherBo bo) {
        ReceiptVoucher update = MapstructUtils.convert(bo, ReceiptVoucher.class);
        receiptVoucherMapper.updateById(update);
    }

    /**
     * 批量删除收款单
     */
    public void deleteByIds(Collection<Long> ids) {
        receiptVoucherMapper.deleteBatchIds(ids);
    }

    @Transactional
    public void finish(ReceiptVoucherBo bo) {
        if (Objects.isNull(bo.getId())) {
            insertByBo(bo);
        } else {
            updateByBo(bo);
        }
        merchantBalanceService.add(bo);
    }
}

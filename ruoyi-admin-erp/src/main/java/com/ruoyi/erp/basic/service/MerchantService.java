package com.ruoyi.erp.basic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.constant.HttpStatus;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.erp.basic.domain.bo.MerchantBo;
import com.ruoyi.erp.basic.domain.entity.Merchant;
import com.ruoyi.erp.warehouse.domain.entity.OtherReceiptDoc;
import com.ruoyi.erp.basic.domain.vo.MerchantVo;
import com.ruoyi.erp.basic.mapper.MerchantMapper;
import com.ruoyi.erp.warehouse.mapper.OtherReceiptDocMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 往来单位Service业务层处理
 *
 * @author zcc
 * @date 2024-07-16
 */
@RequiredArgsConstructor
@Service
public class MerchantService {

    private final MerchantMapper merchantMapper;
    private final OtherReceiptDocMapper otherReceiptDocMapper;

    /**
     * 查询往来单位
     */
    public MerchantVo queryById(Long id){
        return merchantMapper.selectVoById(id);
    }

    /**
     * 查询往来单位列表
     */
    public TableDataInfo<MerchantVo> queryPageList(MerchantBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Merchant> lqw = buildQueryWrapper(bo);
        Page<MerchantVo> result = merchantMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询往来单位列表
     */
    public List<MerchantVo> queryList(MerchantBo bo) {
        LambdaQueryWrapper<Merchant> lqw = buildQueryWrapper(bo);
        return merchantMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Merchant> buildQueryWrapper(MerchantBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Merchant> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantNo()), Merchant::getMerchantNo, bo.getMerchantNo());
        lqw.like(StringUtils.isNotBlank(bo.getMerchantName()), Merchant::getMerchantName, bo.getMerchantName());
        lqw.eq(bo.getMerchantTypeCustomer() != null, Merchant::getMerchantTypeCustomer, bo.getMerchantTypeCustomer());
        lqw.eq(bo.getMerchantTypeSupplier() != null, Merchant::getMerchantTypeSupplier, bo.getMerchantTypeSupplier());
        return lqw;
    }

    /**
     * 新增往来单位
     */
    public void insertByBo(MerchantBo bo) {
        Merchant add = MapstructUtils.convert(bo, Merchant.class);
        merchantMapper.insert(add);
    }

    /**
     * 修改往来单位
     */
    public void updateByBo(MerchantBo bo) {
        Merchant update = MapstructUtils.convert(bo, Merchant.class);
        merchantMapper.updateById(update);
    }

    /**
     * 删除往来单位
     */
    public void deleteById(Long id) {
        validateIdBeforeDelete(id);
        merchantMapper.deleteById(id);
    }

    private void validateIdBeforeDelete(Long id) {
        LambdaQueryWrapper<OtherReceiptDoc> receiptOrderLqw = Wrappers.lambdaQuery();
        Long receiptOrderCount = otherReceiptDocMapper.selectCount(receiptOrderLqw);
        if (receiptOrderCount != null && receiptOrderCount > 0) {
            throw new ServiceException("删除失败", HttpStatus.CONFLICT,"该企业已有业务关联，无法删除！");
        }
    }

    /**
     * 批量删除往来单位
     */
    public void deleteByIds(Collection<Long> ids) {
        merchantMapper.deleteBatchIds(ids);
    }
}

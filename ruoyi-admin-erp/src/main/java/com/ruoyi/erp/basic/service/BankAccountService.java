package com.ruoyi.erp.basic.service;

import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.erp.basic.domain.bo.BankAccountBo;
import com.ruoyi.erp.basic.domain.entity.BankAccount;
import com.ruoyi.erp.basic.domain.vo.BankAccountVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.basic.mapper.BankAccountMapper;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 银行账户Service业务层处理
 *
 * @author zcc
 * @date 2025-02-13
 */
@RequiredArgsConstructor
@Service
public class BankAccountService {

    private final BankAccountMapper bankAccountMapper;

    /**
     * 查询银行账户
     */
    public BankAccountVo queryById(Long id){
        return bankAccountMapper.selectVoById(id);
    }

    /**
     * 查询银行账户列表
     */
    public TableDataInfo<BankAccountVo> queryPageList(BankAccountBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BankAccount> lqw = buildQueryWrapper(bo);
        Page<BankAccountVo> result = bankAccountMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询银行账户列表
     */
    public List<BankAccountVo> queryList(BankAccountBo bo) {
        LambdaQueryWrapper<BankAccount> lqw = buildQueryWrapper(bo);
        return bankAccountMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BankAccount> buildQueryWrapper(BankAccountBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BankAccount> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增银行账户
     */
    public void insertByBo(BankAccountBo bo) {
        BankAccount add = MapstructUtils.convert(bo, BankAccount.class);
        bankAccountMapper.insert(add);
    }

    /**
     * 修改银行账户
     */
    public void updateByBo(BankAccountBo bo) {
        BankAccount update = MapstructUtils.convert(bo, BankAccount.class);
        bankAccountMapper.updateById(update);
    }

    /**
     * 批量删除银行账户
     */
    public void deleteByIds(Collection<Long> ids) {
        bankAccountMapper.deleteBatchIds(ids);
    }
}

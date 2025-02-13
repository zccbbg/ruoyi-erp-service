package com.ruoyi.erp.basic.service;

import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.erp.basic.domain.bo.BasicBankAccountBo;
import com.ruoyi.erp.basic.domain.vo.BasicBankAccountVo;
import com.ruoyi.erp.basic.domain.entity.BasicBankAccount;
import com.ruoyi.erp.basic.mapper.BasicBankAccountMapper;

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
public class BasicBankAccountService {

    private final BasicBankAccountMapper basicBankAccountMapper;

    /**
     * 查询银行账户
     */
    public BasicBankAccountVo queryById(Long id){
        return basicBankAccountMapper.selectVoById(id);
    }

    /**
     * 查询银行账户列表
     */
    public TableDataInfo<BasicBankAccountVo> queryPageList(BasicBankAccountBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<BasicBankAccount> lqw = buildQueryWrapper(bo);
        Page<BasicBankAccountVo> result = basicBankAccountMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询银行账户列表
     */
    public List<BasicBankAccountVo> queryList(BasicBankAccountBo bo) {
        LambdaQueryWrapper<BasicBankAccount> lqw = buildQueryWrapper(bo);
        return basicBankAccountMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<BasicBankAccount> buildQueryWrapper(BasicBankAccountBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<BasicBankAccount> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增银行账户
     */
    public void insertByBo(BasicBankAccountBo bo) {
        BasicBankAccount add = MapstructUtils.convert(bo, BasicBankAccount.class);
        basicBankAccountMapper.insert(add);
    }

    /**
     * 修改银行账户
     */
    public void updateByBo(BasicBankAccountBo bo) {
        BasicBankAccount update = MapstructUtils.convert(bo, BasicBankAccount.class);
        basicBankAccountMapper.updateById(update);
    }

    /**
     * 批量删除银行账户
     */
    public void deleteByIds(Collection<Long> ids) {
        basicBankAccountMapper.deleteBatchIds(ids);
    }
}

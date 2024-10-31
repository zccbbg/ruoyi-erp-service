package com.ruoyi.erp.basic.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.mybatis.core.mapper.BaseMapperPlus;
import com.ruoyi.erp.basic.domain.bo.ItemSkuBo;
import com.ruoyi.erp.basic.domain.entity.ItemSku;
import com.ruoyi.erp.basic.domain.vo.ItemSkuMapVo;
import com.ruoyi.erp.basic.domain.vo.ItemSkuVo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface ItemSkuMapper extends BaseMapperPlus<ItemSku, ItemSkuVo> {

    IPage<ItemSkuMapVo> selectByBo(IPage<ItemSkuVo> page, @Param("bo") ItemSkuBo bo);

    List<ItemSkuMapVo> queryItemSkuMapVos(Collection<Long> ids);

    ItemSkuMapVo queryItemSkuMapVo(Long id);
}

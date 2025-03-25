package com.ruoyi.erp.basic.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.mybatis.core.mapper.BaseMapperPlus;
import com.ruoyi.erp.basic.domain.bo.SkuBo;
import com.ruoyi.erp.basic.domain.entity.Sku;
import com.ruoyi.erp.basic.domain.vo.SkuMapVo;
import com.ruoyi.erp.basic.domain.vo.SkuVo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface SkuMapper extends BaseMapperPlus<Sku, SkuVo> {

    IPage<SkuMapVo> selectByBo(IPage<SkuVo> page, @Param("bo") SkuBo bo ,@Param("skuIds") Set<Long> skuIds);

    List<SkuMapVo> querySkuMapVos(Collection<Long> ids);

    SkuMapVo querySkuMapVo(Long id);
}

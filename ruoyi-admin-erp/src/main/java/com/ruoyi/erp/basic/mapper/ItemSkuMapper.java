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

public interface GoodsSkuMapper extends BaseMapperPlus<Sku, SkuVo> {

    IPage<SkuMapVo> selectByBo(IPage<SkuVo> page, @Param("bo") SkuBo bo);

    List<SkuMapVo> queryGoodsSkuMapVos(Collection<Long> ids);

    SkuMapVo queryGoodsSkuMapVo(Long id);
}

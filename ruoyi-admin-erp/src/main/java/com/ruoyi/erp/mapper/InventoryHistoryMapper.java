package com.ruoyi.erp.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.mybatis.core.mapper.BaseMapperPlus;
import com.ruoyi.erp.domain.bo.InventoryHistoryBo;
import com.ruoyi.erp.domain.entity.InventoryHistory;
import com.ruoyi.erp.domain.vo.InventoryHistoryVo;
import org.apache.ibatis.annotations.Param;

/**
 * 库存记录Mapper接口
 *
 * @author zcc
 * @date 2024-07-22
 */
public interface InventoryHistoryMapper extends BaseMapperPlus<InventoryHistory, InventoryHistoryVo> {

    Page<InventoryHistoryVo> selectVoPageByBo(Page<Object> page, @Param("bo") InventoryHistoryBo bo);
}

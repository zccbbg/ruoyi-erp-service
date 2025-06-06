package com.ruoyi.erp.warehouse.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.mybatis.core.mapper.BaseMapperPlus;
import com.ruoyi.erp.warehouse.domain.query.InventoryHistoryQuery;
import com.ruoyi.erp.warehouse.domain.vo.InventoryHistoryVo;
import com.ruoyi.erp.warehouse.domain.entity.InventoryHistory;
import org.apache.ibatis.annotations.Param;

/**
 * 库存记录Mapper接口
 *
 * @author zcc
 * @date 2024-07-22
 */
public interface InventoryHistoryMapper extends BaseMapperPlus<InventoryHistory, InventoryHistoryVo> {

    Page<InventoryHistoryVo> selectVoPageByBo(Page<Object> page, @Param("query") InventoryHistoryQuery query);
}

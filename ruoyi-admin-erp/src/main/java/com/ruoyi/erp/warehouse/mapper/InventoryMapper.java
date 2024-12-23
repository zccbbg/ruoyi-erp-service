package com.ruoyi.erp.warehouse.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.mybatis.core.mapper.BaseMapperPlus;
import com.ruoyi.erp.warehouse.domain.bo.InventoryBo;
import com.ruoyi.erp.warehouse.domain.entity.Inventory;
import com.ruoyi.erp.warehouse.domain.vo.InventoryVo;
import org.apache.ibatis.annotations.Param;

/**
 * 库存Mapper接口
 *
 * @author zcc
 * @date 2024-07-19
 */
public interface InventoryMapper extends BaseMapperPlus<Inventory, InventoryVo> {

    Page<InventoryVo> queryGoodsBoardList(Page<InventoryVo> page, @Param("bo") InventoryBo bo);
    Page<InventoryVo> queryWarehouseBoardList(Page<InventoryVo> page, @Param("bo") InventoryBo bo);

}

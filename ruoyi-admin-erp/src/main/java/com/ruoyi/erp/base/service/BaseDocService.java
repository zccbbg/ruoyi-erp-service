package com.ruoyi.erp.base.service;

import com.ruoyi.erp.base.domain.entity.BaseDocDetail;
import com.ruoyi.erp.warehouse.domain.entity.MovementDocDetail;

import java.util.List;

public class BaseDocService<T extends BaseDocDetail> {
    protected Long getSameWarehouseId(List<T> detailBoList){
        if (detailBoList == null || detailBoList.isEmpty()) {
            return null; // 空列表返回null
        }

        Long firstWarehouseId = detailBoList.get(0).getWarehouseId(); // 获取第一个元素的warehouseId
        if(firstWarehouseId == null){
            return null;
        }
        for (T detail : detailBoList) {
            if (!firstWarehouseId.equals(detail.getWarehouseId())) {
                return null; // 如果发现不一致的warehouseId，返回null
            }
        }

        return firstWarehouseId; // 所有warehouseId都相同，返回第一个warehouseId
    }
}

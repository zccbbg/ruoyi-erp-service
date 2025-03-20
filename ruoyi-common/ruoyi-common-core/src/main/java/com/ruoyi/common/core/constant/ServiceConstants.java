package com.ruoyi.common.core.constant;

public class ServiceConstants {

    /**
     * 库存记录操作类型
     */
    public class InventoryHistoryBizType {
        public static final Integer RECEIPT = 1;
        public static final Integer SHIPMENT = 2;
        public static final Integer MOVEMENT = 3;
        public static final Integer CHECK = 4;
        public static final Integer PURCHASE = 5;
        public static final Integer SALES = 6;
        public static final Integer REFUND = 7;
    }

    /**
     * 盘库单状态
     */
    public class Status {
        public static final Integer INVALID = -1;
        public static final Integer PENDING = 0;
        public static final Integer FINISH = 1;
    }
}

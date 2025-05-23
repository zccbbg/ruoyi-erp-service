SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for basic_bank_account
-- ----------------------------
DROP TABLE IF EXISTS `basic_bank_account`;
CREATE TABLE `basic_bank_account`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账户编号',
  `account_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账户名称',
  `remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1917854397259173891 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '银行账户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of basic_bank_account
-- ----------------------------
INSERT INTO `basic_bank_account` VALUES (1, '111', '支付宝', '111', NULL, NULL, 'admin', '2025-02-13 17:50:20.064');
INSERT INTO `basic_bank_account` VALUES (2, '222', '微信', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `basic_bank_account` VALUES (3, '333', '建设银行', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `basic_bank_account` VALUES (1917854397259173890, 'gsyh', '工商银行', NULL, 'admin', '2025-05-01 16:11:34.989', 'admin', '2025-05-01 16:11:34.989');

-- ----------------------------
-- Table structure for basic_brand
-- ----------------------------
DROP TABLE IF EXISTS `basic_brand`;
CREATE TABLE `basic_brand`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '品牌名称',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1912430868845932547 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品品牌表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of basic_brand
-- ----------------------------
INSERT INTO `basic_brand` VALUES (1828364846953566209, '小米', 'admin', '2024-08-27 17:32:03.551', 'admin', '2024-08-27 17:32:03.551');
INSERT INTO `basic_brand` VALUES (1828364873889386498, '苹果', 'admin', '2024-08-27 17:32:09.971', 'admin', '2024-08-27 17:32:09.971');
INSERT INTO `basic_brand` VALUES (1828364927610032129, '华为', 'admin', '2024-08-27 17:32:22.786', 'admin', '2024-08-27 17:32:22.786');
INSERT INTO `basic_brand` VALUES (1828407151135723522, '爱普生', 'admin', '2024-08-27 20:20:09.656', 'admin', '2024-08-27 20:20:09.656');
INSERT INTO `basic_brand` VALUES (1828407291103842306, '惠普', 'admin', '2024-08-27 20:20:43.031', 'admin', '2024-08-27 20:20:43.031');
INSERT INTO `basic_brand` VALUES (1880949350445445121, '理想', 'admin', '2025-01-19 20:04:06.000', 'admin', '2025-04-16 17:00:19.310');

-- ----------------------------
-- Table structure for basic_category
-- ----------------------------
DROP TABLE IF EXISTS `basic_category`;
CREATE TABLE `basic_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '物料类型id',
  `pid` bigint(20) NULL DEFAULT 0 COMMENT '父物料类型id',
  `category_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '物料类型名称',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '显示顺序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1911320690431959042 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '物料类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of basic_category
-- ----------------------------
INSERT INTO `basic_category` VALUES (1828364988754595841, 0, '手机', 0, 'admin', '2024-08-27 17:32:37.357', 'admin', '2024-08-27 20:14:12.100');
INSERT INTO `basic_category` VALUES (1828365014901886978, 0, '打印机', 1, 'admin', '2024-08-27 17:32:43.598', 'admin', '2024-08-27 20:14:12.447');
INSERT INTO `basic_category` VALUES (1828365043024695297, 0, '电脑', 3, 'admin', '2024-08-27 17:32:50.301', 'admin', '2024-08-27 20:14:12.704');
INSERT INTO `basic_category` VALUES (1828405743737016322, 0, '家电', 4, 'admin', '2024-08-27 20:14:34.104', 'admin', '2024-08-27 20:14:34.104');
INSERT INTO `basic_category` VALUES (1828405773474631681, 1828405743737016322, '冰箱', 0, 'admin', '2024-08-27 20:14:41.195', 'admin', '2024-08-27 20:14:41.195');
INSERT INTO `basic_category` VALUES (1828405825714688001, 1828405743737016322, '电视', 1, 'admin', '2024-08-27 20:14:53.651', 'admin', '2024-08-27 20:14:53.651');
INSERT INTO `basic_category` VALUES (1828408600515219457, 0, '健生器材', 5, 'admin', '2024-08-27 20:25:55.213', 'admin', '2024-08-27 20:25:55.213');
INSERT INTO `basic_category` VALUES (1829397860466749441, 0, '生鲜', 6, 'admin', '2024-08-30 13:56:53.174', 'admin', '2024-08-30 13:56:53.174');
INSERT INTO `basic_category` VALUES (1829397958923841538, 1829397860466749441, '水果', 0, 'admin', '2024-08-30 13:57:16.644', 'admin', '2024-08-30 13:57:16.644');
INSERT INTO `basic_category` VALUES (1829398007993004034, 1829397860466749441, '海鲜', 1, 'admin', '2024-08-30 13:57:28.347', 'admin', '2024-08-30 13:57:28.347');
INSERT INTO `basic_category` VALUES (1840282771834667010, 1828405743737016322, '空调', 2, 'wms2_admin', '2024-09-29 14:49:38.274', 'wms2_admin', '2024-09-29 14:49:38.274');
INSERT INTO `basic_category` VALUES (1911320405697437698, 0, '家具', 7, 'admin', '2025-04-13 15:27:49.998', 'admin', '2025-04-13 15:27:49.998');
INSERT INTO `basic_category` VALUES (1911320690431959041, 0, '文具', 8, 'admin', '2025-04-13 15:28:57.885', 'admin', '2025-04-13 15:28:57.885');

-- ----------------------------
-- Table structure for basic_goods
-- ----------------------------
DROP TABLE IF EXISTS `basic_goods`;
CREATE TABLE `basic_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `goods_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '分类id',
  `unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单位类别',
  `brand_id` bigint(20) NULL DEFAULT NULL COMMENT '品牌',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1917839034320527362 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '物料' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of basic_goods
-- ----------------------------
INSERT INTO `basic_goods` VALUES (1828402622516334594, NULL, '华为 nova flip', 1828364988754595841, NULL, 1828364927610032129, NULL, 'admin', '2024-08-27 20:02:09.948', 'admin', '2024-09-02 21:45:27.127');
INSERT INTO `basic_goods` VALUES (1828406450112335874, NULL, 'macbook', 1828365043024695297, NULL, 1828364873889386498, NULL, 'admin', '2024-08-27 20:17:22.521', 'admin', '2024-08-27 20:17:22.521');
INSERT INTO `basic_goods` VALUES (1828407870173646849, NULL, '爱普生打印机', 1828365014901886978, NULL, NULL, NULL, 'admin', '2024-08-27 20:23:01.096', 'admin', '2024-08-27 20:23:01.096');
INSERT INTO `basic_goods` VALUES (1828408320146968578, NULL, '小米米家436L十字四门风冷无霜嵌入式家用冰箱', 1828405773474631681, NULL, 1828364846953566209, NULL, 'admin', '2024-08-27 20:24:48.375', 'admin', '2024-08-27 20:24:48.375');
INSERT INTO `basic_goods` VALUES (1828408795734904833, NULL, '杠铃', 1828408600515219457, NULL, NULL, NULL, 'admin', '2024-08-27 20:26:41.757', 'admin', '2024-08-27 20:33:27.034');
INSERT INTO `basic_goods` VALUES (1829398192563351554, NULL, '舟山带鱼', 1829398007993004034, NULL, NULL, NULL, 'admin', '2024-08-30 13:58:12.354', 'admin', '2024-08-30 14:01:08.050');
INSERT INTO `basic_goods` VALUES (1829398333580046338, NULL, '青岛大虾', 1829398007993004034, NULL, NULL, NULL, 'admin', '2024-08-30 13:58:45.971', 'admin', '2024-08-30 14:00:49.686');
INSERT INTO `basic_goods` VALUES (1829398492388978689, NULL, '启东黄鱼', 1829398007993004034, NULL, NULL, NULL, 'admin', '2024-08-30 13:59:23.834', 'admin', '2024-08-30 14:00:32.373');
INSERT INTO `basic_goods` VALUES (1829398701680553985, NULL, '红富士苹果', 1829397958923841538, NULL, NULL, NULL, 'admin', '2024-08-30 14:00:13.735', 'admin', '2024-08-30 14:00:13.735');
INSERT INTO `basic_goods` VALUES (1829399118040723457, NULL, '树山梨', 1829397958923841538, NULL, NULL, NULL, 'admin', '2024-08-30 14:01:52.989', 'admin', '2025-05-09 09:16:02.916');
INSERT INTO `basic_goods` VALUES (1840282974297915394, NULL, '小米空调', 1840282771834667010, NULL, 1828364846953566209, NULL, 'wms2_admin', '2024-09-29 14:50:26.535', 'wms2_admin', '2024-09-29 14:50:26.535');
INSERT INTO `basic_goods` VALUES (1872979180842192897, NULL, '油汀', 1828405743737016322, NULL, NULL, NULL, 'admin', '2024-12-28 20:13:29.641', 'admin', '2024-12-28 20:18:27.106');
INSERT INTO `basic_goods` VALUES (1911320529479737346, NULL, '站立式书桌', 1911320405697437698, NULL, 1880949350445445121, NULL, 'admin', '2025-04-13 15:28:19.516', 'admin', '2025-05-09 09:15:39.380');
INSERT INTO `basic_goods` VALUES (1911320755523362817, NULL, '派克钢笔', 1911320690431959041, NULL, NULL, NULL, 'admin', '2025-04-13 15:29:13.400', 'admin', '2025-05-09 09:15:18.806');
INSERT INTO `basic_goods` VALUES (1917833930485903362, NULL, '小米电视机', 1828405825714688001, NULL, NULL, NULL, 'admin', '2025-05-01 14:50:15.317', 'admin', '2025-05-01 14:50:15.317');
INSERT INTO `basic_goods` VALUES (1917839034320527361, NULL, '华为电视机', 1828405825714688001, NULL, 1828364927610032129, NULL, 'admin', '2025-05-01 15:10:32.167', 'admin', '2025-05-01 15:19:01.095');

-- ----------------------------
-- Table structure for basic_merchant
-- ----------------------------
DROP TABLE IF EXISTS `basic_merchant`;
CREATE TABLE `basic_merchant`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号',
  `merchant_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `merchant_type_customer` tinyint(4) NULL DEFAULT NULL COMMENT '企业类型：是否是客户',
  `merchant_type_supplier` tinyint(4) NULL DEFAULT NULL COMMENT '企业类型：是否是供应商',
  `merchant_level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '级别',
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开户行',
  `bank_account` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '银行账户',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `mobile` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `tel` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座机号',
  `contact_person` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Email',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1919589262811754498 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '往来单位' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of basic_merchant
-- ----------------------------
INSERT INTO `basic_merchant` VALUES (1911320917931008001, 'tb', '淘宝', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2025-04-13 15:29:52.123', 'admin', '2025-04-13 15:29:52.123');
INSERT INTO `basic_merchant` VALUES (1911320973711056897, 'pdd', '拼多多', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2025-04-13 15:30:05.419', 'admin', '2025-04-13 15:32:22.622');
INSERT INTO `basic_merchant` VALUES (1917854292355436546, 'jd', '京东', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2025-05-01 16:11:09.971', 'admin', '2025-05-01 16:11:09.971');
INSERT INTO `basic_merchant` VALUES (1919571614044577794, 'douyin', '抖音', 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2025-05-06 09:55:11.372', 'admin', '2025-05-06 09:55:11.372');

-- ----------------------------
-- Table structure for basic_sku
-- ----------------------------
DROP TABLE IF EXISTS `basic_sku`;
CREATE TABLE `basic_sku`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格名称',
  `goods_id` bigint(20) NULL DEFAULT NULL,
  `barcode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '条码',
  `sku_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `length` decimal(10, 1) NULL DEFAULT NULL COMMENT '长，单位cm',
  `width` decimal(10, 1) NULL DEFAULT NULL COMMENT '宽，单位cm',
  `height` decimal(10, 1) NULL DEFAULT NULL COMMENT '高，单位cm',
  `gross_weight` decimal(10, 3) NULL DEFAULT NULL COMMENT '毛重，单位kg',
  `net_weight` decimal(10, 3) NULL DEFAULT NULL COMMENT '净重，单位kg',
  `cost_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '成本价（元）',
  `selling_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '销售价（元）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1917840284202160130 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'sku信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of basic_sku
-- ----------------------------
INSERT INTO `basic_sku` VALUES (1828402624005312514, '黑', 1828402622516334594, 'x00003', '00001', NULL, NULL, NULL, NULL, NULL, 5000.00, 5288.00, 'admin', '2024-08-27 20:02:10.302', 'admin', '2024-09-02 21:45:27.177');
INSERT INTO `basic_sku` VALUES (1828402624005312515, '白', 1828402622516334594, '', '000002', NULL, NULL, NULL, NULL, NULL, 5000.00, 5288.00, 'admin', '2024-08-27 20:02:10.304', 'admin', '2024-09-02 21:45:27.184');
INSERT INTO `basic_sku` VALUES (1828402624005312516, '粉', 1828402622516334594, '', '00003', NULL, NULL, NULL, NULL, NULL, 5000.00, 5288.00, 'admin', '2024-08-27 20:02:10.305', 'admin', '2024-09-02 21:45:27.190');
INSERT INTO `basic_sku` VALUES (1828406451399987201, 'pro', 1828406450112335874, '', 'mac0001', NULL, NULL, NULL, NULL, NULL, NULL, 24999.00, 'admin', '2024-08-27 20:17:22.821', 'admin', '2024-08-27 20:17:22.821');
INSERT INTO `basic_sku` VALUES (1828407871469686786, 'l6468', 1828407870173646849, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 3188.00, 'admin', '2024-08-27 20:23:01.393', 'admin', '2024-08-27 20:23:01.393');
INSERT INTO `basic_sku` VALUES (1828408321522700289, '白色', 1828408320146968578, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2699.00, 'admin', '2024-08-27 20:24:48.697', 'admin', '2024-08-27 20:24:48.697');
INSERT INTO `basic_sku` VALUES (1828408796968030210, '10kg', 1828408795734904833, '102025115', NULL, NULL, NULL, NULL, NULL, 10.000, NULL, NULL, 'admin', '2024-08-27 20:26:42.049', 'admin', '2024-08-27 20:33:27.395');
INSERT INTO `basic_sku` VALUES (1828408796968030211, '20kg', 1828408795734904833, '254523055', NULL, NULL, NULL, NULL, NULL, 20.000, NULL, NULL, 'admin', '2024-08-27 20:26:42.052', 'admin', '2024-08-27 20:33:27.515');
INSERT INTO `basic_sku` VALUES (1828408796968030212, '50kg', 1828408795734904833, '5204862525', NULL, NULL, NULL, NULL, NULL, 50.000, NULL, NULL, 'admin', '2024-08-27 20:26:42.052', 'admin', '2024-08-27 20:33:27.634');
INSERT INTO `basic_sku` VALUES (1829398193024724993, '大', 1829398192563351554, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-08-30 13:58:12.457', 'admin', '2024-08-30 14:01:08.172');
INSERT INTO `basic_sku` VALUES (1829398193024724994, '中', 1829398192563351554, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-08-30 13:58:12.458', 'admin', '2024-08-30 14:01:08.328');
INSERT INTO `basic_sku` VALUES (1829398333903007745, '大', 1829398333580046338, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-08-30 13:58:46.047', 'admin', '2024-08-30 14:00:49.854');
INSERT INTO `basic_sku` VALUES (1829398333903007746, '中', 1829398333580046338, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-08-30 13:58:46.048', 'admin', '2024-08-30 14:00:50.001');
INSERT INTO `basic_sku` VALUES (1829398492779048962, '大', 1829398492388978689, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-08-30 13:59:23.925', 'admin', '2024-08-30 14:00:32.544');
INSERT INTO `basic_sku` VALUES (1829398492779048963, '中', 1829398492388978689, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-08-30 13:59:23.925', 'admin', '2024-08-30 14:00:32.683');
INSERT INTO `basic_sku` VALUES (1829398702011904001, '大', 1829398701680553985, '', NULL, 10.0, 10.0, 10.0, NULL, NULL, NULL, NULL, 'admin', '2024-08-30 14:00:13.810', 'admin', '2024-08-30 14:00:13.810');
INSERT INTO `basic_sku` VALUES (1829398702011904002, '中', 1829398701680553985, '', NULL, 5.0, 5.0, 5.0, NULL, NULL, NULL, NULL, 'admin', '2024-08-30 14:00:13.812', 'admin', '2024-08-30 14:00:13.812');
INSERT INTO `basic_sku` VALUES (1829399118304964609, '大', 1829399118040723457, '', NULL, NULL, NULL, NULL, NULL, NULL, 10.00, 30.00, 'admin', '2024-08-30 14:01:53.064', 'admin', '2025-05-09 09:16:03.022');
INSERT INTO `basic_sku` VALUES (1829399118304964610, '中', 1829399118040723457, '', NULL, NULL, NULL, NULL, NULL, NULL, 20.00, 50.00, 'admin', '2024-08-30 14:01:53.064', 'admin', '2025-05-09 09:16:03.210');
INSERT INTO `basic_sku` VALUES (1840282974629265410, '1P', 1840282974297915394, '', NULL, NULL, NULL, NULL, NULL, NULL, 2000.00, NULL, 'wms2_admin', '2024-09-29 14:50:26.627', 'wms2_admin', '2024-09-29 14:50:26.627');
INSERT INTO `basic_sku` VALUES (1840282974696374273, '2P', 1840282974297915394, '', NULL, NULL, NULL, NULL, NULL, NULL, 3000.00, NULL, 'wms2_admin', '2024-09-29 14:50:26.628', 'wms2_admin', '2024-09-29 14:50:26.628');
INSERT INTO `basic_sku` VALUES (1872979181249040385, '1w', 1872979180842192897, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-12-28 20:13:29.745', 'admin', '2024-12-28 20:18:27.269');
INSERT INTO `basic_sku` VALUES (1872980429209710593, '2w', 1872979180842192897, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', '2024-12-28 20:18:27.275', 'admin', '2024-12-28 20:18:27.275');
INSERT INTO `basic_sku` VALUES (1911320529936916482, '1.2m', 1911320529479737346, '', NULL, NULL, NULL, NULL, NULL, NULL, 20.00, 120.00, 'admin', '2025-04-13 15:28:19.621', 'admin', '2025-05-09 09:15:39.500');
INSERT INTO `basic_sku` VALUES (1911320529936916483, '1.5m', 1911320529479737346, '', NULL, NULL, NULL, NULL, NULL, NULL, 40.00, 200.00, 'admin', '2025-04-13 15:28:19.622', 'admin', '2025-05-09 09:15:39.610');
INSERT INTO `basic_sku` VALUES (1911320755917627394, '302c', 1911320755523362817, '', NULL, NULL, NULL, NULL, NULL, NULL, 1.00, 3.00, 'admin', '2025-04-13 15:29:13.493', 'admin', '2025-05-09 09:15:18.973');
INSERT INTO `basic_sku` VALUES (1917833930871779330, '50寸', 1917833930485903362, '11', NULL, 1.0, 1.0, 1.0, 2.000, 1.000, 2000.00, 3000.00, 'admin', '2025-05-01 14:50:15.420', 'admin', '2025-05-01 14:50:15.420');
INSERT INTO `basic_sku` VALUES (1917833930871779331, '70寸', 1917833930485903362, '22', NULL, 1.0, 2.0, 2.0, 4.000, 2.000, 3000.00, 4000.00, 'admin', '2025-05-01 14:50:15.423', 'admin', '2025-05-01 14:50:15.423');
INSERT INTO `basic_sku` VALUES (1917839034718986241, '50寸', 1917839034320527361, '2', '1', NULL, NULL, NULL, NULL, NULL, 2500.00, 3500.00, 'admin', '2025-05-01 15:10:32.265', 'admin', '2025-05-01 15:19:01.234');
INSERT INTO `basic_sku` VALUES (1917839205863366658, '60寸', 1917839034320527361, '4', '3', NULL, NULL, NULL, NULL, NULL, 3500.00, 4500.00, 'admin', '2025-05-01 15:11:13.075', 'admin', '2025-05-01 15:19:01.368');
INSERT INTO `basic_sku` VALUES (1917840284202160129, '70寸', 1917839034320527361, '6', '5', NULL, NULL, NULL, NULL, NULL, 4500.00, 5500.00, 'admin', '2025-05-01 15:15:30.161', 'admin', '2025-05-01 15:19:01.504');

-- ----------------------------
-- Table structure for basic_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `basic_warehouse`;
CREATE TABLE `basic_warehouse`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `warehouse_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `warehouse_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `order_num` bigint(20) NULL DEFAULT 0 COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1917833304259538946 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '仓库' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of basic_warehouse
-- ----------------------------
INSERT INTO `basic_warehouse` VALUES (1828364609002311682, '1112', '苏州园区', NULL, 1, 'admin', '2024-08-27 17:31:06.000', 'admin', '2024-12-26 18:08:09.436');
INSERT INTO `basic_warehouse` VALUES (1828364740028174337, NULL, '常熟冷链仓', NULL, 2, 'admin', '2024-08-27 17:31:38.066', 'admin', '2024-08-30 13:55:34.766');
INSERT INTO `basic_warehouse` VALUES (1882763227608973314, NULL, '盛泽仓', NULL, 4, 'admin', '2025-01-24 20:11:48.176', 'admin', '2025-01-24 20:11:48.176');
INSERT INTO `basic_warehouse` VALUES (1911320153045147649, NULL, '车坊仓', NULL, 5, 'admin', '2025-04-13 15:26:49.755', 'admin', '2025-04-13 15:26:49.755');
INSERT INTO `basic_warehouse` VALUES (1911320211283058689, NULL, '唯亭仓', NULL, 6, 'admin', '2025-04-13 15:27:03.641', 'admin', '2025-04-13 15:27:03.641');

-- ----------------------------
-- Table structure for financial_merchant_balance
-- ----------------------------
DROP TABLE IF EXISTS `financial_merchant_balance`;
CREATE TABLE `financial_merchant_balance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL COMMENT '商家id',
  `initial_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '当前余额',
  `balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '当前余额',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_merchant_id`(`merchant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商家余额' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of financial_merchant_balance
-- ----------------------------

-- ----------------------------
-- Table structure for financial_payment_voucher
-- ----------------------------
DROP TABLE IF EXISTS `financial_payment_voucher`;
CREATE TABLE `financial_payment_voucher`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `voucher_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `trans_date` datetime(3) NULL DEFAULT NULL COMMENT '付款日期',
  `merchant_id` bigint(20) NULL DEFAULT NULL COMMENT '往来单位id',
  `bank_account_id` bigint(20) NULL DEFAULT NULL COMMENT '银行账户id',
  `paid_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `discount_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额=支付金额+优惠金额',
  `checked_status` tinyint(4) NULL DEFAULT NULL COMMENT '审核状态',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '付款单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of financial_payment_voucher
-- ----------------------------

-- ----------------------------
-- Table structure for financial_receipt_voucher
-- ----------------------------
DROP TABLE IF EXISTS `financial_receipt_voucher`;
CREATE TABLE `financial_receipt_voucher`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `voucher_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `trans_date` datetime(3) NULL DEFAULT NULL COMMENT '收款日期',
  `merchant_id` bigint(20) NULL DEFAULT NULL COMMENT '往来单位id',
  `bank_account_id` bigint(20) NULL DEFAULT NULL COMMENT '银行账户id',
  `paid_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `discount_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `checked_status` tinyint(4) NULL DEFAULT NULL COMMENT '审核状态',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收款单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of financial_receipt_voucher
-- ----------------------------

-- ----------------------------
-- Table structure for financial_trans_history
-- ----------------------------
DROP TABLE IF EXISTS `financial_trans_history`;
CREATE TABLE `financial_trans_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL COMMENT '商家id',
  `bank_account_id` bigint(20) NULL DEFAULT NULL COMMENT '银行账户id',
  `trans_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交易类型',
  `related_id` bigint(20) NOT NULL COMMENT '关联业务id',
  `related_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联业务编号',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `discount_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `actual_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `paid_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `balance_change` decimal(10, 2) NULL DEFAULT NULL COMMENT '余额变动',
  `before_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '交易前余额',
  `after_balance` decimal(10, 2) NULL DEFAULT NULL COMMENT '交易后余额',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '交易流水' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of financial_trans_history
-- ----------------------------

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1887722008033972226, 'purchase_refund', '采购退货单', NULL, NULL, 'PurchaseRefund', 'crud', 'com.ruoyi.erp.purchase', 'purchase', 'refund', '采购退货单', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838016122515458\"}', 'admin', '2025-02-07 12:22:32', 'admin', '2025-02-07 14:50:31', NULL);
INSERT INTO `gen_table` VALUES (1887722009917214721, 'purchase_refund_detail', '采购退货单明细', NULL, NULL, 'PurchaseRefundDetail', 'crud', 'com.ruoyi.erp.purchase', 'purchase', 'refundDetail', '采购退货单明细', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838016122515458\"}', 'admin', '2025-02-07 12:22:50', 'admin', '2025-02-07 14:51:00', NULL);
INSERT INTO `gen_table` VALUES (1887722011741736962, 'purchase_trade', '采购入库单', NULL, NULL, 'PurchaseTrade', 'crud', 'com.ruoyi.erp.purchase', 'purchase', 'trade', '采购入库单', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838016122515458\"}', 'admin', '2025-02-07 12:23:10', 'admin', '2025-02-07 14:51:25', NULL);
INSERT INTO `gen_table` VALUES (1887722013624979457, 'purchase_trade_detail', '采购入库单明细', NULL, NULL, 'PurchaseTradeDetail', 'crud', 'com.ruoyi.erp.purchase', 'purchase', 'tradeDetail', '采购入库单明细', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838016122515458\"}', 'admin', '2025-02-07 12:23:34', 'admin', '2025-02-07 14:51:44', NULL);
INSERT INTO `gen_table` VALUES (1887722123079536641, 'purchase_order', '采购订单', NULL, NULL, 'PurchaseOrder', 'crud', 'com.ruoyi.erp.purchase', 'purchase', 'order', '采购订单', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838016122515458\"}', 'admin', '2024-12-20 14:20:57', 'admin', '2025-02-07 16:58:49', NULL);
INSERT INTO `gen_table` VALUES (1887722125172494338, 'purchase_order_detail', '采购订单明细', NULL, NULL, 'PurchaseOrderDetail', 'crud', 'com.ruoyi.erp.purchase', 'purchase', 'orderDetail', '采购订单明细', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838016122515458\"}', 'admin', '2024-12-20 14:20:57', 'admin', '2025-02-07 14:52:32', NULL);
INSERT INTO `gen_table` VALUES (1889971286500442114, 'basic_bank_account', '银行账户', NULL, NULL, 'BasicBankAccount', 'crud', 'com.ruoyi.erp.basic', 'basic', 'bankAccount', '银行账户', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1808758090157985794\"}', 'admin', '2025-02-13 17:31:00', 'admin', '2025-02-13 17:43:28', NULL);
INSERT INTO `gen_table` VALUES (1890209021781880834, 'financial_payment_voucher', '付款单', NULL, NULL, 'FinancialPaymentVoucher', 'crud', 'com.ruoyi.erp', 'erp', 'paymentVoucher', '付款单', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838986088869890\"}', 'admin', '2025-02-13 21:14:21', 'admin', '2025-02-19 17:44:41', NULL);
INSERT INTO `gen_table` VALUES (1890209023669317634, 'financial_receipt_voucher', '收款单', NULL, NULL, 'ReceiptVoucher', 'crud', 'com.ruoyi.erp', 'financial', 'receiptVoucher', '收款单', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838986088869890\"}', 'admin', '2025-02-13 21:15:20', 'admin', '2025-02-19 17:54:57', NULL);
INSERT INTO `gen_table` VALUES (1890209025430925313, 'financial_trans_history', '交易流水', NULL, NULL, 'TransHistory', 'crud', 'com.ruoyi.erp.financial', 'financial', 'transHistory', '交易流水', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838986088869890\"}', 'admin', '2025-02-13 20:17:36', 'admin', '2025-02-19 18:04:18', NULL);
INSERT INTO `gen_table` VALUES (1892145801439555586, 'financial_merchant_balance', '商家余额', NULL, NULL, 'MerchantBalance', 'crud', 'com.ruoyi.erp.financial', 'financial', 'merchantBalance', '商家余额', 'zcc', '0', '/', '{\"treeCode\":null,\"treeName\":null,\"treeParentCode\":null,\"parentMenuId\":\"1871838986088869890\"}', 'admin', '2025-02-13 18:02:24', 'admin', '2025-02-19 18:04:36', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL COMMENT '编号',
  `table_id` bigint(20) NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1887722008608591874, 1887722008033972226, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:31');
INSERT INTO `gen_table_column` VALUES (1887722008608591876, 1887722008033972226, 'bill_no', '单据编号', 'varchar(32)', 'String', 'billNo', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:31');
INSERT INTO `gen_table_column` VALUES (1887722008608591877, 1887722008033972226, 'bill_date', '单据日期', 'datetime(3)', 'Date', 'billDate', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:31');
INSERT INTO `gen_table_column` VALUES (1887722008608591878, 1887722008033972226, 'checked_status', '审核状态', 'tinyint(4)', 'Integer', 'checkedStatus', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 5, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:31');
INSERT INTO `gen_table_column` VALUES (1887722008608591879, 1887722008033972226, 'checked_by', '审核人', 'varchar(64)', 'String', 'checkedBy', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:31');
INSERT INTO `gen_table_column` VALUES (1887722008608591880, 1887722008033972226, 'merchant_id', '供应商id', 'bigint(20)', 'Long', 'merchantId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:31');
INSERT INTO `gen_table_column` VALUES (1887722008608591881, 1887722008033972226, 'goods_qty', '商品数量', 'decimal(10,2)', 'BigDecimal', 'goodsQty', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:31');
INSERT INTO `gen_table_column` VALUES (1887722008608591882, 1887722008033972226, 'goods_amount', '商品金额', 'decimal(10,2)', 'BigDecimal', 'goodsAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:31');
INSERT INTO `gen_table_column` VALUES (1887722008608591883, 1887722008033972226, 'other_expenses_amount', '其他费用', 'decimal(10,2)', 'BigDecimal', 'otherExpensesAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008608591884, 1887722008033972226, 'discount_amount', '优惠金额', 'decimal(10,2)', 'BigDecimal', 'discountAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008608591885, 1887722008033972226, 'actual_amount', '实际金额', 'decimal(10,2)', 'BigDecimal', 'actualAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008608591886, 1887722008033972226, 'paid_amount', '已支付退款金额', 'decimal(10,2)', 'BigDecimal', 'paidAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008608591887, 1887722008033972226, 'deducted_amount', '已抵扣退款金额', 'decimal(10,2)', 'BigDecimal', 'deductedAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008608591888, 1887722008033972226, 'due_amount', '未付金额', 'decimal(10,2)', 'BigDecimal', 'dueAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008608591889, 1887722008033972226, 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 16, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008675700737, 1887722008033972226, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008675700738, 1887722008033972226, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 18, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008675700739, 1887722008033972226, 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 19, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722008675700740, 1887722008033972226, 'update_time', '修改时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 20, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:50:32');
INSERT INTO `gen_table_column` VALUES (1887722010441502721, 1887722009917214721, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:00');
INSERT INTO `gen_table_column` VALUES (1887722010441502722, 1887722009917214721, 'pid', '父id', 'bigint(20)', 'Long', 'pid', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:00');
INSERT INTO `gen_table_column` VALUES (1887722010441502723, 1887722009917214721, 'sku_id', 'sku id', 'bigint(20)', 'Long', 'skuId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:00');
INSERT INTO `gen_table_column` VALUES (1887722010441502724, 1887722009917214721, 'qty', '商品数量', 'decimal(10,2)', 'BigDecimal', 'qty', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:00');
INSERT INTO `gen_table_column` VALUES (1887722010441502725, 1887722009917214721, 'price_without_tax', '不含税价', 'decimal(10,2)', 'BigDecimal', 'priceWithoutTax', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722010441502726, 1887722009917214721, 'tax_amount', '税费', 'decimal(10,2)', 'BigDecimal', 'taxAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722010441502727, 1887722009917214721, 'tax_rate', '税率', 'decimal(10,2)', 'BigDecimal', 'taxRate', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722010441502728, 1887722009917214721, 'price_with_tax', '含税价', 'decimal(10,2)', 'BigDecimal', 'priceWithTax', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722010441502729, 1887722009917214721, 'total_amount', '总金额', 'decimal(10,2)', 'BigDecimal', 'totalAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722010441502730, 1887722009917214721, 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 10, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722010441502731, 1887722009917214721, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722010441502732, 1887722009917214721, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722010441502733, 1887722009917214721, 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722010441502734, 1887722009917214721, 'update_time', '修改时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2025-02-07 12:36:14', 'admin', '2025-02-07 14:51:01');
INSERT INTO `gen_table_column` VALUES (1887722012194721794, 1887722011741736962, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:25');
INSERT INTO `gen_table_column` VALUES (1887722012194721795, 1887722011741736962, 'order_id', '订单id', 'bigint(20)', 'Long', 'orderId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:25');
INSERT INTO `gen_table_column` VALUES (1887722012194721796, 1887722011741736962, 'bill_no', '单据编号', 'varchar(32)', 'String', 'billNo', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:25');
INSERT INTO `gen_table_column` VALUES (1887722012194721797, 1887722011741736962, 'bill_date', '单据日期', 'datetime(3)', 'Date', 'billDate', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721798, 1887722011741736962, 'checked_status', '审核状态', 'tinyint(4)', 'Integer', 'checkedStatus', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 5, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721799, 1887722011741736962, 'payment_status', '支付状态', 'tinyint(4)', 'Integer', 'paymentStatus', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 6, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721800, 1887722011741736962, 'refund_status', '退货状态', 'tinyint(4)', 'Integer', 'refundStatus', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 7, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721801, 1887722011741736962, 'refund_amount', '退货金额', 'decimal(10,2)', 'BigDecimal', 'refundAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721802, 1887722011741736962, 'deducted_refund_amount', '退货抵扣', 'decimal(10,2)', 'BigDecimal', 'deductedRefundAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721803, 1887722011741736962, 'deducted_prepay_amount', '预付款抵扣', 'decimal(10,2)', 'BigDecimal', 'deductedPrepayAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721804, 1887722011741736962, 'paid_amount', '支付金额', 'decimal(10,2)', 'BigDecimal', 'paidAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721805, 1887722011741736962, 'due_amount', '剩余金额', 'decimal(10,2)', 'BigDecimal', 'dueAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721806, 1887722011741736962, 'checked_by', '审核人', 'varchar(64)', 'String', 'checkedBy', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721807, 1887722011741736962, 'merchant_id', '供应商id', 'bigint(20)', 'Long', 'merchantId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721808, 1887722011741736962, 'goods_qty', '商品数量', 'decimal(10,2)', 'BigDecimal', 'goodsQty', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721809, 1887722011741736962, 'goods_amount', '商品金额', 'decimal(10,2)', 'BigDecimal', 'goodsAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721810, 1887722011741736962, 'other_expenses_amount', '其他费用', 'decimal(10,2)', 'BigDecimal', 'otherExpensesAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:26');
INSERT INTO `gen_table_column` VALUES (1887722012194721811, 1887722011741736962, 'discount_amount', '优惠金额', 'decimal(10,2)', 'BigDecimal', 'discountAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:27');
INSERT INTO `gen_table_column` VALUES (1887722012194721812, 1887722011741736962, 'actual_amount', '实际金额', 'decimal(10,2)', 'BigDecimal', 'actualAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:27');
INSERT INTO `gen_table_column` VALUES (1887722012194721813, 1887722011741736962, 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 20, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:27');
INSERT INTO `gen_table_column` VALUES (1887722012194721814, 1887722011741736962, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 21, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:27');
INSERT INTO `gen_table_column` VALUES (1887722012194721815, 1887722011741736962, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 22, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:27');
INSERT INTO `gen_table_column` VALUES (1887722012194721816, 1887722011741736962, 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 23, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:27');
INSERT INTO `gen_table_column` VALUES (1887722012257636354, 1887722011741736962, 'update_time', '修改时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 24, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:27');
INSERT INTO `gen_table_column` VALUES (1887722014023438337, 1887722013624979457, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438338, 1887722013624979457, 'pid', '父id', 'bigint(20)', 'Long', 'pid', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438339, 1887722013624979457, 'sku_id', 'sku id', 'bigint(20)', 'Long', 'skuId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438340, 1887722013624979457, 'qty', '商品数量', 'decimal(10,2)', 'BigDecimal', 'qty', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438341, 1887722013624979457, 'price_without_tax', '不含税价', 'decimal(10,2)', 'BigDecimal', 'priceWithoutTax', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438342, 1887722013624979457, 'tax_amount', '税费', 'decimal(10,2)', 'BigDecimal', 'taxAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438343, 1887722013624979457, 'tax_rate', '税率', 'decimal(10,2)', 'BigDecimal', 'taxRate', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438344, 1887722013624979457, 'price_with_tax', '含税价', 'decimal(10,2)', 'BigDecimal', 'priceWithTax', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438345, 1887722013624979457, 'total_amount', '总金额', 'decimal(10,2)', 'BigDecimal', 'totalAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438346, 1887722013624979457, 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 10, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438347, 1887722013624979457, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:44');
INSERT INTO `gen_table_column` VALUES (1887722014023438348, 1887722013624979457, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:45');
INSERT INTO `gen_table_column` VALUES (1887722014023438349, 1887722013624979457, 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:45');
INSERT INTO `gen_table_column` VALUES (1887722014023438350, 1887722013624979457, 'update_time', '修改时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2025-02-07 12:36:15', 'admin', '2025-02-07 14:51:45');
INSERT INTO `gen_table_column` VALUES (1887722123540910081, 1887722123079536641, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '0', NULL, '0', '0', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910082, 1887722123079536641, 'bill_no', '单据编号', 'varchar(32)', 'String', 'billNo', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910083, 1887722123079536641, 'bill_date', '单据日期', 'datetime(3)', 'Date', 'billDate', '0', '0', '0', '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 3, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910084, 1887722123079536641, 'delivery_date', '交货日期', 'datetime(3)', 'Date', 'deliveryDate', '0', '0', '0', '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 4, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910085, 1887722123079536641, 'checked_status', '审核状态', 'tinyint(4)', 'Integer', 'checkedStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'radio', 'doc_checked_status', 5, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910086, 1887722123079536641, 'checked_by', '审核人', 'varchar(64)', 'String', 'checkedBy', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 6, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910087, 1887722123079536641, 'stock_status', '库存状态', 'tinyint(4)', 'Integer', 'stockStatus', '0', '0', '0', '1', '1', '1', '1', 'EQ', 'checkbox', '', 7, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910088, 1887722123079536641, 'merchant_id', '供应商id', 'bigint(20)', 'Long', 'merchantId', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 8, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910089, 1887722123079536641, 'goods_qty', '商品数量', 'decimal(10,2)', 'BigDecimal', 'goodsQty', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 9, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910090, 1887722123079536641, 'processed_qty', '已处理数量', 'decimal(10,2)', 'BigDecimal', 'processedQty', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 10, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910091, 1887722123079536641, 'goods_amount', '商品金额', 'decimal(10,2)', 'BigDecimal', 'goodsAmount', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 11, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910092, 1887722123079536641, 'other_expenses_amount', '其他费用', 'decimal(10,2)', 'BigDecimal', 'otherExpensesAmount', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 12, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:49');
INSERT INTO `gen_table_column` VALUES (1887722123540910093, 1887722123079536641, 'discount_amount', '优惠金额', 'decimal(10,2)', 'BigDecimal', 'discountAmount', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 13, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:50');
INSERT INTO `gen_table_column` VALUES (1887722123540910094, 1887722123079536641, 'actual_amount', '实际金额', 'decimal(10,2)', 'BigDecimal', 'actualAmount', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 14, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:50');
INSERT INTO `gen_table_column` VALUES (1887722123540910095, 1887722123079536641, 'prepay_amount', '预付金额', 'decimal(10,2)', 'BigDecimal', 'prepayAmount', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 15, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:50');
INSERT INTO `gen_table_column` VALUES (1887722123540910096, 1887722123079536641, 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '0', '1', '1', '1', NULL, 'EQ', 'input', '', 16, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:50');
INSERT INTO `gen_table_column` VALUES (1887722123540910097, 1887722123079536641, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:50');
INSERT INTO `gen_table_column` VALUES (1887722123540910098, 1887722123079536641, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 18, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:50');
INSERT INTO `gen_table_column` VALUES (1887722123540910099, 1887722123079536641, 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 19, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:50');
INSERT INTO `gen_table_column` VALUES (1887722123540910100, 1887722123079536641, 'update_time', '修改时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 20, 'admin', '2025-02-07 12:36:41', 'admin', '2025-02-07 16:58:50');
INSERT INTO `gen_table_column` VALUES (1887722125625479169, 1887722125172494338, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479170, 1887722125172494338, 'pid', '父id', 'bigint(20)', 'Long', 'pid', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479171, 1887722125172494338, 'sku_id', 'sku id', 'bigint(20)', 'Long', 'skuId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479172, 1887722125172494338, 'qty', '商品数量', 'decimal(10,2)', 'BigDecimal', 'qty', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479173, 1887722125172494338, 'processed_qty', '已处理数量', 'decimal(10,2)', 'BigDecimal', 'processedQty', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479174, 1887722125172494338, 'price_without_tax', '不含税价', 'decimal(10,2)', 'BigDecimal', 'priceWithoutTax', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479175, 1887722125172494338, 'tax_amount', '税费', 'decimal(10,2)', 'BigDecimal', 'taxAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479176, 1887722125172494338, 'tax_rate', '税率', 'decimal(10,2)', 'BigDecimal', 'taxRate', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479177, 1887722125172494338, 'price_with_tax', '含税价', 'decimal(10,2)', 'BigDecimal', 'priceWithTax', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479178, 1887722125172494338, 'total_amount', '总金额', 'decimal(10,2)', 'BigDecimal', 'totalAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479179, 1887722125172494338, 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 11, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:32');
INSERT INTO `gen_table_column` VALUES (1887722125625479180, 1887722125172494338, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:33');
INSERT INTO `gen_table_column` VALUES (1887722125625479181, 1887722125172494338, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:33');
INSERT INTO `gen_table_column` VALUES (1887722125625479182, 1887722125172494338, 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:33');
INSERT INTO `gen_table_column` VALUES (1887722125625479183, 1887722125172494338, 'update_time', '修改时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 15, 'admin', '2025-02-07 12:36:42', 'admin', '2025-02-07 14:52:33');
INSERT INTO `gen_table_column` VALUES (1887755092666175490, 1887722008033972226, 'trade_id', 'trade id', 'bigint(20)', 'Long', 'tradeId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-07 14:47:42', 'admin', '2025-02-07 14:50:31');
INSERT INTO `gen_table_column` VALUES (1889971286957621249, 1889971286500442114, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-13 17:34:03', 'admin', '2025-02-13 17:43:28');
INSERT INTO `gen_table_column` VALUES (1889971286957621250, 1889971286500442114, 'account_no', '账户编号', 'varchar(30)', 'String', 'accountNo', '0', '0', '1', '1', '1', '1', '0', 'EQ', 'input', '', 2, 'admin', '2025-02-13 17:34:03', 'admin', '2025-02-13 17:43:28');
INSERT INTO `gen_table_column` VALUES (1889971286957621251, 1889971286500442114, 'account_name', '账户名称', 'varchar(64)', 'String', 'accountName', '0', '0', '1', '1', '1', '1', '0', 'LIKE', 'input', '', 3, 'admin', '2025-02-13 17:34:03', 'admin', '2025-02-13 17:43:28');
INSERT INTO `gen_table_column` VALUES (1889971286957621252, 1889971286500442114, 'remark', '备注', 'varchar(64)', 'String', 'remark', '0', '0', '0', '1', '1', '1', NULL, 'EQ', 'input', '', 4, 'admin', '2025-02-13 17:34:03', 'admin', '2025-02-13 17:43:28');
INSERT INTO `gen_table_column` VALUES (1889971286957621253, 1889971286500442114, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2025-02-13 17:34:03', 'admin', '2025-02-13 17:43:28');
INSERT INTO `gen_table_column` VALUES (1889971286957621254, 1889971286500442114, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2025-02-13 17:34:03', 'admin', '2025-02-13 17:43:28');
INSERT INTO `gen_table_column` VALUES (1889971286957621255, 1889971286500442114, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2025-02-13 17:34:03', 'admin', '2025-02-13 17:43:28');
INSERT INTO `gen_table_column` VALUES (1889971286957621256, 1889971286500442114, 'update_time', '更新时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2025-02-13 17:34:03', 'admin', '2025-02-13 17:43:28');
INSERT INTO `gen_table_column` VALUES (1890209022360694786, 1890209021781880834, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:41');
INSERT INTO `gen_table_column` VALUES (1890209022360694787, 1890209021781880834, 'voucher_no', '编号', 'varchar(32)', 'String', 'voucherNo', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:41');
INSERT INTO `gen_table_column` VALUES (1890209022360694788, 1890209021781880834, 'trans_date', '付款日期', 'datetime(3)', 'Date', 'transDate', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 3, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:41');
INSERT INTO `gen_table_column` VALUES (1890209022360694789, 1890209021781880834, 'merchant_id', '往来单位id', 'bigint(20)', 'Long', 'merchantId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022360694790, 1890209021781880834, 'bank_account_id', '银行账户id', 'bigint(20)', 'Long', 'bankAccountId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803649, 1890209021781880834, 'paid_amount', '支付金额', 'decimal(10,2)', 'BigDecimal', 'paidAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803650, 1890209021781880834, 'discount_amount', '优惠金额', 'decimal(10,2)', 'BigDecimal', 'discountAmount', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803651, 1890209021781880834, 'balance_change', '余额变动=支付金额+优惠金额', 'decimal(10,2)', 'BigDecimal', 'balanceChange', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803652, 1890209021781880834, 'before_balance', '交易前余额', 'decimal(15,2)', 'BigDecimal', 'beforeBalance', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803653, 1890209021781880834, 'after_balance', '交易后余额', 'decimal(10,2)', 'BigDecimal', 'afterBalance', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803654, 1890209021781880834, 'checked_status', '审核状态', 'tinyint(4)', 'Integer', 'checkedStatus', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 11, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803655, 1890209021781880834, 'checked_by', '审核人', 'varchar(64)', 'String', 'checkedBy', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803656, 1890209021781880834, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803657, 1890209021781880834, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803658, 1890209021781880834, 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803659, 1890209021781880834, 'update_time', '修改时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:42');
INSERT INTO `gen_table_column` VALUES (1890209022427803660, 1890209021781880834, 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 17, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:44:43');
INSERT INTO `gen_table_column` VALUES (1890209024126496770, 1890209023669317634, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:57');
INSERT INTO `gen_table_column` VALUES (1890209024126496771, 1890209023669317634, 'voucher_no', '编号', 'varchar(32)', 'String', 'voucherNo', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496772, 1890209023669317634, 'trans_date', '收款日期', 'datetime(3)', 'Date', 'transDate', '0', '0', '1', '1', '1', '1', '1', 'BETWEEN', 'datetime', '', 3, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496773, 1890209023669317634, 'merchant_id', '往来单位id', 'bigint(20)', 'Long', 'merchantId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496774, 1890209023669317634, 'bank_account_id', '银行账户id', 'bigint(20)', 'Long', 'bankAccountId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496775, 1890209023669317634, 'paid_amount', '支付金额', 'decimal(10,2)', 'BigDecimal', 'paidAmount', '0', '0', '1', '1', '1', '1', '0', 'EQ', 'input', '', 6, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496776, 1890209023669317634, 'discount_amount', '优惠金额', 'decimal(10,2)', 'BigDecimal', 'discountAmount', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 7, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496777, 1890209023669317634, 'balance_change', '余额变动', 'decimal(10,2)', 'BigDecimal', 'balanceChange', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 8, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496778, 1890209023669317634, 'before_balance', '交易前余额', 'decimal(15,2)', 'BigDecimal', 'beforeBalance', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 9, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496779, 1890209023669317634, 'after_balance', '交易后余额', 'decimal(10,2)', 'BigDecimal', 'afterBalance', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'input', '', 10, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496780, 1890209023669317634, 'checked_status', '审核状态', 'tinyint(4)', 'Integer', 'checkedStatus', '0', '0', '0', '1', '1', '1', '0', 'EQ', 'radio', '', 11, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496781, 1890209023669317634, 'checked_by', '审核人', 'varchar(64)', 'String', 'checkedBy', '0', '0', '0', '1', '1', '0', '0', 'EQ', 'input', '', 12, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496782, 1890209023669317634, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024126496783, 1890209023669317634, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:58');
INSERT INTO `gen_table_column` VALUES (1890209024193605634, 1890209023669317634, 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:59');
INSERT INTO `gen_table_column` VALUES (1890209024193605635, 1890209023669317634, 'update_time', '修改时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:59');
INSERT INTO `gen_table_column` VALUES (1890209024193605636, 1890209023669317634, 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', '1', '1', '1', '1', NULL, 'EQ', 'input', '', 17, 'admin', '2025-02-14 09:18:44', 'admin', '2025-02-19 17:54:59');
INSERT INTO `gen_table_column` VALUES (1890209025892298753, 1890209025430925313, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025892298754, 1890209025430925313, 'merchant_id', '商家id', 'bigint(20)', 'Long', 'merchantId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025892298755, 1890209025430925313, 'bank_account_id', '银行账户id', 'bigint(20)', 'Long', 'bankAccountId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025892298756, 1890209025430925313, 'trans_type', '交易类型', 'varchar(64)', 'String', 'transType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025892298757, 1890209025430925313, 'related_id', '关联业务id', 'bigint(20)', 'Long', 'relatedId', '0', '0', '1', '1', '1', '1', '0', 'EQ', 'input', '', 5, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025892298758, 1890209025430925313, 'related_no', '关联业务编号', 'varchar(64)', 'String', 'relatedNo', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025955213314, 1890209025430925313, 'total_amount', '总金额', 'decimal(10,2)', 'BigDecimal', 'totalAmount', '0', '0', '1', '1', '1', '1', '0', 'BETWEEN', 'input', '', 7, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025955213315, 1890209025430925313, 'discount_amount', '优惠金额', 'decimal(10,2)', 'BigDecimal', 'discountAmount', '0', '0', '1', '1', '1', '1', '0', 'BETWEEN', 'input', '', 8, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025955213316, 1890209025430925313, 'actual_amount', '实际金额', 'decimal(10,2)', 'BigDecimal', 'actualAmount', '0', '0', '1', '1', '1', '1', '0', 'BETWEEN', 'input', '', 9, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025955213317, 1890209025430925313, 'paid_amount', '支付金额', 'decimal(10,2)', 'BigDecimal', 'paidAmount', '0', '0', '1', '1', '1', '1', '0', 'BETWEEN', 'input', '', 10, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025955213318, 1890209025430925313, 'balance_change', '余额变动', 'decimal(10,2)', 'BigDecimal', 'balanceChange', '0', '0', '1', '1', '1', '1', '0', 'BETWEEN', 'input', '', 11, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025955213319, 1890209025430925313, 'before_balance', '交易前余额', 'decimal(10,2)', 'BigDecimal', 'beforeBalance', '0', '0', '1', '1', '1', '1', '0', 'BETWEEN', 'input', '', 12, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025955213320, 1890209025430925313, 'after_balance', '交易后余额', 'decimal(10,2)', 'BigDecimal', 'afterBalance', '0', '0', '1', '1', '1', '1', '0', 'BETWEEN', 'input', '', 13, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:19');
INSERT INTO `gen_table_column` VALUES (1890209025955213321, 1890209025430925313, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, '1', NULL, 'EQ', 'input', '', 14, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:20');
INSERT INTO `gen_table_column` VALUES (1890209025955213322, 1890209025430925313, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, '1', NULL, 'EQ', 'datetime', '', 15, 'admin', '2025-02-14 09:18:45', 'admin', '2025-02-19 18:04:20');
INSERT INTO `gen_table_column` VALUES (1892145801921900545, 1892145801439555586, 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', '1', NULL, '1', '1', NULL, 'EQ', 'input', '', 1, 'admin', '2025-02-19 17:34:48', 'admin', '2025-02-19 18:04:36');
INSERT INTO `gen_table_column` VALUES (1892145801921900546, 1892145801439555586, 'merchant_id', '商家id', 'bigint(20)', 'Long', 'merchantId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2025-02-19 17:34:48', 'admin', '2025-02-19 18:04:36');
INSERT INTO `gen_table_column` VALUES (1892145801921900547, 1892145801439555586, 'initial_balance', '初始余额', 'decimal(10,2)', 'BigDecimal', 'initialBalance', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2025-02-19 17:34:48', 'admin', '2025-02-19 18:04:37');
INSERT INTO `gen_table_column` VALUES (1892145801921900548, 1892145801439555586, 'balance', '当前余额', 'decimal(10,2)', 'BigDecimal', 'balance', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2025-02-19 17:34:48', 'admin', '2025-02-19 18:04:37');
INSERT INTO `gen_table_column` VALUES (1892145801921900549, 1892145801439555586, 'create_by', '创建人', 'varchar(64)', 'String', 'createBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2025-02-19 17:34:48', 'admin', '2025-02-19 18:04:37');
INSERT INTO `gen_table_column` VALUES (1892145801921900550, 1892145801439555586, 'create_time', '创建时间', 'datetime(3)', 'Date', 'createTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2025-02-19 17:34:48', 'admin', '2025-02-19 18:04:37');
INSERT INTO `gen_table_column` VALUES (1892145801984815105, 1892145801439555586, 'update_by', '修改人', 'varchar(64)', 'String', 'updateBy', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2025-02-19 17:34:48', 'admin', '2025-02-19 18:04:37');
INSERT INTO `gen_table_column` VALUES (1892145801984815106, 1892145801439555586, 'update_time', '修改时间', 'datetime(3)', 'Date', 'updateTime', '0', '0', '0', NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2025-02-19 17:34:48', 'admin', '2025-02-19 18:04:37');

-- ----------------------------
-- Table structure for purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE `purchase_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据编号',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `delivery_date` datetime(3) NULL DEFAULT NULL COMMENT '交货日期',
  `checked_status` smallint(4) NULL DEFAULT NULL COMMENT '审核状态',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `stock_status` tinyint(4) NULL DEFAULT NULL COMMENT '库存状态',
  `merchant_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商id',
  `goods_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `processed_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '已处理数量',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品金额',
  `other_expenses_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '其他费用',
  `discount_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `actual_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `prepay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '预付金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `bank_account_id` bigint(20) NULL DEFAULT NULL COMMENT '交易账户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_bill_no`(`doc_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of purchase_order
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order_detail`;
CREATE TABLE `purchase_order_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL COMMENT '父id',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku id',
  `qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `processed_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '已处理数量',
  `price_without_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '不含税价',
  `tax_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '税费',
  `tax_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '税率',
  `price_with_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '含税价',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购订单明细' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of purchase_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_refund
-- ----------------------------
DROP TABLE IF EXISTS `purchase_refund`;
CREATE TABLE `purchase_refund`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trade_id` bigint(20) NULL DEFAULT NULL COMMENT 'trade id',
  `doc_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据编号',
  `trade_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '入库单编号',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `checked_status` tinyint(4) NULL DEFAULT NULL COMMENT '审核状态',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `merchant_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商id',
  `goods_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品金额',
  `other_expenses_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '其他费用',
  `discount_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `actual_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `paid_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '已支付退款金额',
  `bank_account_id` bigint(20) NULL DEFAULT NULL COMMENT '交易账户id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `prepay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '预付金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购退货单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of purchase_refund
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_refund_detail
-- ----------------------------
DROP TABLE IF EXISTS `purchase_refund_detail`;
CREATE TABLE `purchase_refund_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL COMMENT '父id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku id',
  `qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `price_without_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '不含税价',
  `tax_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '税费',
  `tax_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '税率',
  `price_with_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '含税价',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id\r\n仓库id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购退货单明细' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of purchase_refund_detail
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_trade
-- ----------------------------
DROP TABLE IF EXISTS `purchase_trade`;
CREATE TABLE `purchase_trade`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `doc_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据编号',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `checked_status` tinyint(4) NULL DEFAULT NULL COMMENT '审核状态',
  `refund_status` tinyint(4) NULL DEFAULT NULL COMMENT '退货状态',
  `refund_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '退货金额',
  `paid_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `bank_account_id` bigint(20) NULL DEFAULT NULL COMMENT '交易账户id',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `merchant_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商id',
  `goods_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品金额',
  `other_expenses_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '其他费用',
  `discount_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `actual_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `prepay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '预付金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购入库单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of purchase_trade
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_trade_detail
-- ----------------------------
DROP TABLE IF EXISTS `purchase_trade_detail`;
CREATE TABLE `purchase_trade_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL COMMENT '父id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku id',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `price_without_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '不含税价',
  `tax_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '税费',
  `tax_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '税率',
  `price_with_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '含税价',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购入库单明细' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of purchase_trade_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sales_order
-- ----------------------------
DROP TABLE IF EXISTS `sales_order`;
CREATE TABLE `sales_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据编号',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `delivery_date` datetime(3) NULL DEFAULT NULL COMMENT '交货日期',
  `checked_status` smallint(4) NULL DEFAULT NULL COMMENT '审核状态',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `stock_status` tinyint(4) NULL DEFAULT NULL COMMENT '库存状态',
  `merchant_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商id',
  `goods_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `processed_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '已处理数量',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品金额',
  `other_expenses_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '其他费用',
  `discount_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `actual_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `prepay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '预付金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `bank_account_id` bigint(20) NULL DEFAULT NULL COMMENT '交易账户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_bill_no`(`doc_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售订单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sales_order
-- ----------------------------

-- ----------------------------
-- Table structure for sales_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `sales_order_detail`;
CREATE TABLE `sales_order_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL COMMENT '父id',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku id',
  `qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `processed_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '已处理数量',
  `price_without_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '不含税价',
  `tax_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '税费',
  `tax_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '税率',
  `price_with_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '含税价',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售订单明细' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sales_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sales_refund
-- ----------------------------
DROP TABLE IF EXISTS `sales_refund`;
CREATE TABLE `sales_refund`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trade_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '销售出库单号',
  `trade_id` bigint(20) NOT NULL COMMENT 'trade id',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `doc_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据编号',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `checked_status` tinyint(4) NULL DEFAULT NULL COMMENT '审核状态',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `merchant_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商id',
  `goods_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品金额',
  `other_expenses_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '其他费用',
  `discount_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `actual_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `paid_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '已支付退款金额',
  `bank_account_id` bigint(20) NULL DEFAULT NULL COMMENT '交易账户id',
  `deducted_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '已抵扣退款金额',
  `due_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '未付金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `prepay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '预付金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售退货单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sales_refund
-- ----------------------------

-- ----------------------------
-- Table structure for sales_refund_detail
-- ----------------------------
DROP TABLE IF EXISTS `sales_refund_detail`;
CREATE TABLE `sales_refund_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL COMMENT '父id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku id',
  `qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `price_without_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '不含税价',
  `tax_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '税费',
  `tax_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '税率',
  `price_with_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '含税价',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `trade_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '销售出库单编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售退货单明细' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sales_refund_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sales_trade
-- ----------------------------
DROP TABLE IF EXISTS `sales_trade`;
CREATE TABLE `sales_trade`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NULL DEFAULT NULL COMMENT '订单id',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `doc_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '单据编号',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `checked_status` tinyint(4) NULL DEFAULT NULL COMMENT '审核状态',
  `refund_status` tinyint(4) NULL DEFAULT NULL COMMENT '退货状态',
  `refund_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '退货金额',
  `paid_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `bank_account_id` bigint(20) NULL DEFAULT NULL COMMENT '交易账户id',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `merchant_id` bigint(20) NULL DEFAULT NULL COMMENT '供应商id',
  `goods_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品金额',
  `other_expenses_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '其他费用',
  `discount_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '优惠金额',
  `actual_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  `prepay_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '预付金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售入库单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sales_trade
-- ----------------------------

-- ----------------------------
-- Table structure for sales_trade_detail
-- ----------------------------
DROP TABLE IF EXISTS `sales_trade_detail`;
CREATE TABLE `sales_trade_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL COMMENT '父id',
  `sku_id` bigint(20) NOT NULL COMMENT 'sku id',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品数量',
  `price_without_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '不含税价',
  `tax_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '税费',
  `tax_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '税率',
  `price_with_tax` decimal(10, 2) NULL DEFAULT NULL COMMENT '含税价',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '销售入库单明细' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sales_trade_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` bigint(20) NOT NULL COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2024-06-13 16:06:37', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2024-06-13 16:06:37', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-light', 'Y', 'admin', '2024-06-13 16:06:37', 'admin', '2024-07-16 11:25:33', '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', '2024-06-13 16:06:37', '', NULL, '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2024-06-13 16:06:37', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (11, 'OSS预览列表资源开关', 'sys.oss.previewListResource', 'true', 'Y', 'admin', '2024-06-13 16:06:37', '', NULL, 'true:开启, false:关闭');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '1', '0', 'admin', '2024-06-13 16:06:25', '', NULL);
INSERT INTO `sys_dept` VALUES (1811589666899832833, 102, '0,100,102', '测试部门2', 0, '负责人', '', '', '1', '0', 'admin', '2024-07-12 10:33:29', 'admin', '2024-07-12 10:33:29');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0停用 1正常）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '1', 'sys_show_hide', '', 'primary', 'Y', '1', 'admin', '2024-06-13 16:06:36', 'admin', '2024-07-10 16:34:54', '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '0', 'sys_show_hide', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:36', 'admin', '2024-07-10 16:35:07', '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '1', 'sys_normal_disable', '', 'primary', 'Y', '1', 'admin', '2024-06-13 16:06:36', 'admin', '2024-07-10 14:30:58', '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '0', 'sys_normal_disable', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:36', 'admin', '2024-07-10 14:31:06', '停用状态');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '1', 'sys_notice_status', '', 'primary', 'Y', '1', 'admin', '2024-06-13 16:06:36', 'admin', '2024-07-10 17:24:35', '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '0', 'sys_notice_status', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:36', 'admin', '2024-07-10 17:24:44', '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:37', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '1', 'admin', '2024-06-13 16:06:37', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '1', 'admin', '2024-06-13 16:06:37', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '1', 'admin', '2024-06-13 16:06:37', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:37', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '1', 'admin', '2024-06-13 16:06:37', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:37', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '失败', '0', 'sys_common_status', '', 'danger', 'N', '1', 'admin', '2024-06-13 16:06:37', 'admin', '2024-07-15 10:50:52', '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '成功', '1', 'sys_common_status', '', 'success', 'N', '1', 'admin', '2024-06-13 16:06:37', 'admin', '2024-07-15 10:51:05', '停用状态');
INSERT INTO `sys_dict_data` VALUES (29, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (1812692503272718338, 0, '客户', '1', 'merchant_type', NULL, 'default', 'N', '1', 'admin', '2024-07-15 11:35:46', 'admin', '2024-07-16 11:21:11', NULL);
INSERT INTO `sys_dict_data` VALUES (1812694839395135489, 1, '供应商', '2', 'merchant_type', NULL, 'default', 'N', '1', 'admin', '2024-07-15 11:45:03', 'admin', '2024-07-16 11:21:29', '');
INSERT INTO `sys_dict_data` VALUES (1813051377282904066, 3, '客户/供应商', '3', 'merchant_type', NULL, 'default', 'N', '1', 'admin', '2024-07-16 11:21:48', 'admin', '2024-07-16 11:21:48', NULL);
INSERT INTO `sys_dict_data` VALUES (1813153852862160897, 0, '未入库', '0', 'wms_receipt_status', NULL, 'info', 'N', '1', 'admin', '2024-07-16 18:09:00', 'admin', '2024-07-22 09:38:14', NULL);
INSERT INTO `sys_dict_data` VALUES (1813153899775451137, 1, '部分入库', '1', 'wms_receipt_status', NULL, 'primary', 'N', '1', 'admin', '2024-07-16 18:09:11', 'admin', '2025-05-09 10:34:08', NULL);
INSERT INTO `sys_dict_data` VALUES (1814219171351085057, 0, '生产入库', '1', 'wms_receipt_type', NULL, 'primary', 'N', '1', 'admin', '2024-07-19 16:42:12', 'admin', '2024-07-22 09:38:50', NULL);
INSERT INTO `sys_dict_data` VALUES (1814219220520910849, 1, '采购入库', '2', 'wms_receipt_type', NULL, 'primary', 'N', '1', 'admin', '2024-07-19 16:42:23', 'admin', '2024-07-22 09:38:56', NULL);
INSERT INTO `sys_dict_data` VALUES (1814219269975949313, 2, '退货入库', '3', 'wms_receipt_type', NULL, 'primary', 'N', '1', 'admin', '2024-07-19 16:42:35', 'admin', '2024-07-22 09:39:01', NULL);
INSERT INTO `sys_dict_data` VALUES (1814219304272773121, 3, '归还入库', '4', 'wms_receipt_type', NULL, 'primary', 'N', '1', 'admin', '2024-07-19 16:42:43', 'admin', '2024-07-22 09:39:06', NULL);
INSERT INTO `sys_dict_data` VALUES (1818850512650706945, 0, '未出库', '0', 'wms_shipment_status', NULL, 'info', 'N', '1', 'admin', '2024-08-01 11:25:29', 'admin', '2024-08-01 14:25:37', NULL);
INSERT INTO `sys_dict_data` VALUES (1818850565389885441, 1, '部分出库', '1', 'wms_shipment_status', NULL, 'primary', 'N', '1', 'admin', '2024-08-01 11:25:42', 'admin', '2025-05-09 14:52:06', NULL);
INSERT INTO `sys_dict_data` VALUES (1818850814351187969, 0, '退货出库', '1', 'wms_shipment_type', NULL, 'primary', 'N', '1', 'admin', '2024-08-01 11:26:41', 'wms2_admin', '2024-09-25 18:45:02', NULL);
INSERT INTO `sys_dict_data` VALUES (1818850852594851841, 1, '销售出库', '2', 'wms_shipment_type', NULL, 'primary', 'N', '1', 'admin', '2024-08-01 11:26:51', 'wms2_admin', '2024-09-25 18:45:13', NULL);
INSERT INTO `sys_dict_data` VALUES (1818850884714831874, 2, '生产出库', '3', 'wms_shipment_type', NULL, 'primary', 'N', '1', 'admin', '2024-08-01 11:26:58', 'wms2_admin', '2024-09-25 18:45:23', NULL);
INSERT INTO `sys_dict_data` VALUES (1821067084643434498, 1, '其他入库', '1', 'wms_inventory_history_type', NULL, 'success', 'N', '1', 'admin', '2024-08-07 14:13:21', 'admin', '2025-01-23 16:09:48', NULL);
INSERT INTO `sys_dict_data` VALUES (1821067144441626625, 3, '其他出库', '2', 'wms_inventory_history_type', NULL, 'danger', 'N', '1', 'admin', '2024-08-07 14:13:36', 'admin', '2025-01-23 16:10:13', NULL);
INSERT INTO `sys_dict_data` VALUES (1821067181917732866, 4, '移库', '3', 'wms_inventory_history_type', NULL, 'warning', 'N', '1', 'admin', '2024-08-07 14:13:45', 'admin', '2025-01-23 16:10:23', NULL);
INSERT INTO `sys_dict_data` VALUES (1821067222455681026, 5, '盘库', '4', 'wms_inventory_history_type', NULL, 'primary', 'N', '1', 'admin', '2024-08-07 14:13:54', 'admin', '2025-01-23 16:10:40', NULL);
INSERT INTO `sys_dict_data` VALUES (1822820748966006786, 0, '未移库', '0', 'wms_movement_status', NULL, 'info', 'N', '1', 'admin', '2024-08-12 10:21:48', 'admin', '2025-05-20 15:05:27', NULL);
INSERT INTO `sys_dict_data` VALUES (1822820794864275457, 1, '已移库', '1', 'wms_movement_status', NULL, 'primary', 'N', '1', 'admin', '2024-08-12 10:21:59', 'admin', '2025-05-20 15:05:35', NULL);
INSERT INTO `sys_dict_data` VALUES (1823182345731391489, 0, '未盘库', '0', 'wms_check_status', NULL, 'info', 'N', '1', 'admin', '2024-08-13 10:18:39', 'admin', '2025-05-20 15:04:44', NULL);
INSERT INTO `sys_dict_data` VALUES (1823182400756465666, 1, '已盘库', '1', 'wms_check_status', NULL, 'primary', 'N', '1', 'admin', '2024-08-13 10:18:52', 'admin', '2025-05-20 15:04:57', NULL);
INSERT INTO `sys_dict_data` VALUES (1882339863081852929, 0, '采购入库', '5', 'wms_inventory_history_type', NULL, 'success', 'N', '1', 'admin', '2025-01-23 16:09:30', 'admin', '2025-01-23 16:09:30', NULL);
INSERT INTO `sys_dict_data` VALUES (1882340243366813697, 2, '销售出库', '6', 'wms_inventory_history_type', NULL, 'danger', 'N', '1', 'admin', '2025-01-23 16:11:01', 'admin', '2025-01-23 16:11:01', NULL);
INSERT INTO `sys_dict_data` VALUES (1887784663876640769, 0, '未审核', '0', 'doc_checked_status', NULL, 'default', 'N', '1', 'admin', '2025-02-07 16:45:12', 'admin', '2025-02-07 16:45:23', NULL);
INSERT INTO `sys_dict_data` VALUES (1887784806642360321, 1, '审核不通过', '500', 'doc_checked_status', NULL, 'danger', 'N', '1', 'admin', '2025-02-07 16:45:46', 'admin', '2025-02-07 16:45:46', NULL);
INSERT INTO `sys_dict_data` VALUES (1887784886057312258, 2, '审核通过', '200', 'doc_checked_status', NULL, 'success', 'N', '1', 'admin', '2025-02-07 16:46:05', 'admin', '2025-02-07 16:46:15', NULL);
INSERT INTO `sys_dict_data` VALUES (1892437377550241794, 0, '收款单', 'ReceiptVoucher', 'trans_type', NULL, 'default', 'N', '1', 'admin', '2025-02-20 12:53:25', 'admin', '2025-02-20 12:53:25', NULL);
INSERT INTO `sys_dict_data` VALUES (1892457266574835714, 0, '未审核', '0', 'finish_status', NULL, 'warning', 'N', '1', 'admin', '2025-02-20 14:12:27', 'admin', '2025-05-20 13:37:50', NULL);
INSERT INTO `sys_dict_data` VALUES (1892457327853617154, 1, '已审核', '1', 'finish_status', NULL, 'success', 'N', '1', 'admin', '2025-02-20 14:12:42', 'admin', '2025-05-20 13:37:58', NULL);
INSERT INTO `sys_dict_data` VALUES (1902619096513376257, 9, '采购退货', '7', 'wms_inventory_history_type', NULL, 'danger', 'N', '1', 'admin', '2025-03-20 15:11:56', 'admin', '2025-03-26 09:33:56', NULL);
INSERT INTO `sys_dict_data` VALUES (1902897057707679745, 1, '有退货', '1', 'refund_status', NULL, 'warning', 'N', '1', 'admin', '2025-03-21 09:36:27', 'admin', '2025-03-21 09:36:27', NULL);
INSERT INTO `sys_dict_data` VALUES (1902897136296353794, 0, '无退货', '0', 'refund_status', NULL, 'success', 'N', '1', 'admin', '2025-03-21 09:36:46', 'admin', '2025-03-21 09:36:46', NULL);
INSERT INTO `sys_dict_data` VALUES (1904075748731105281, 16, '销售退货', '8', 'wms_inventory_history_type', NULL, 'success', 'N', '1', 'admin', '2025-03-24 15:40:09', 'admin', '2025-03-26 09:34:10', NULL);
INSERT INTO `sys_dict_data` VALUES (1920661667344908289, 3, '入库完成', '2', 'wms_receipt_status', NULL, 'success', 'N', '1', 'admin', '2025-05-09 10:06:40', 'admin', '2025-05-21 10:29:21', NULL);
INSERT INTO `sys_dict_data` VALUES (1920733559518945281, 2, '出库完成', '2', 'wms_shipment_status', NULL, 'success', 'N', '1', 'admin', '2025-05-09 14:52:21', 'admin', '2025-05-21 10:30:32', NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '1', 'admin', '2024-06-13 16:06:35', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '1', 'admin', '2024-06-13 16:06:35', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '1', 'admin', '2024-06-13 16:06:35', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '1', 'admin', '2024-06-13 16:06:35', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '1', 'admin', '2024-06-13 16:06:35', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '1', 'admin', '2024-06-13 16:06:35', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '1', 'admin', '2024-06-13 16:06:35', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '1', 'admin', '2024-06-13 16:06:36', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (1812692454547488770, '企业类型', 'merchant_type', '1', 'admin', '2024-07-15 11:35:34', 'admin', '2024-07-16 17:41:32', '企业类型');
INSERT INTO `sys_dict_type` VALUES (1813152108564373505, '入库状态', 'wms_receipt_status', '1', 'admin', '2024-07-16 18:02:04', 'admin', '2024-07-16 18:02:17', '入库状态');
INSERT INTO `sys_dict_type` VALUES (1814219082624778242, '入库类型', 'wms_receipt_type', '1', 'admin', '2024-07-19 16:41:51', 'admin', '2024-07-19 16:41:51', NULL);
INSERT INTO `sys_dict_type` VALUES (1818848671749709825, '出库状态', 'wms_shipment_status', '1', 'admin', '2024-08-01 11:18:11', 'admin', '2024-08-01 11:18:11', NULL);
INSERT INTO `sys_dict_type` VALUES (1818848738502057985, '出库类型', 'wms_shipment_type', '1', 'admin', '2024-08-01 11:18:26', 'admin', '2024-08-01 11:18:26', NULL);
INSERT INTO `sys_dict_type` VALUES (1821066855638630402, '库存记录操作类型', 'wms_inventory_history_type', '1', 'admin', '2024-08-07 14:12:27', 'admin', '2024-08-07 14:12:27', NULL);
INSERT INTO `sys_dict_type` VALUES (1822820566366982146, '移库状态', 'wms_movement_status', '1', 'admin', '2024-08-12 10:21:04', 'admin', '2024-08-12 10:21:04', NULL);
INSERT INTO `sys_dict_type` VALUES (1823182238898274306, '盘库状态', 'wms_check_status', '1', 'admin', '2024-08-13 10:18:14', 'admin', '2024-08-13 10:18:14', NULL);
INSERT INTO `sys_dict_type` VALUES (1887784602035822593, '审核状态', 'doc_checked_status', '1', 'admin', '2025-02-07 16:44:57', 'admin', '2025-02-07 16:44:57', NULL);
INSERT INTO `sys_dict_type` VALUES (1892437042857365505, '交易类型', 'trans_type', '1', 'admin', '2025-02-20 12:52:05', 'admin', '2025-02-20 12:52:05', NULL);
INSERT INTO `sys_dict_type` VALUES (1892457155329310721, '完成状态', 'finish_status', '1', 'admin', '2025-02-20 14:12:01', 'admin', '2025-02-20 14:12:01', NULL);
INSERT INTO `sys_dict_type` VALUES (1902896755097034753, '退货状态', 'refund_status', '1', 'admin', '2025-03-21 09:35:15', 'admin', '2025-03-21 09:35:15', NULL);

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_logininfor_s`(`status`) USING BTREE,
  INDEX `idx_sys_logininfor_lt`(`login_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query_param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '显示状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 110, 'system', NULL, '', 0, 0, 'M', '1', '1', '', 'system', 'admin', '2024-06-13 16:06:26', 'admin', '2024-08-20 13:45:48', '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 120, 'monitor', NULL, '', 0, 0, 'M', '1', '1', '', 'monitor', 'admin', '2024-06-13 16:06:26', 'admin', '2024-08-20 13:45:57', '系统监控目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 0, 0, 'C', '1', '1', 'system:user:list', 'user', 'admin', '2024-06-13 16:06:26', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 0, 0, 'C', '1', '1', 'system:role:list', 'peoples', 'admin', '2024-06-13 16:06:26', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 0, 0, 'C', '1', '1', 'system:menu:list', 'tree-table', 'admin', '2024-06-13 16:06:26', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 0, 0, 'C', '1', '1', 'system:dept:list', 'tree', 'admin', '2024-06-13 16:06:26', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 0, 0, 'C', '1', '1', 'system:post:list', 'post', 'admin', '2024-06-13 16:06:26', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 0, 0, 'C', '1', '1', 'system:dict:list', 'dict', 'admin', '2024-06-13 16:06:26', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 0, 0, 'C', '1', '1', 'system:config:list', 'edit', 'admin', '2024-06-13 16:06:26', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 0, 0, 'C', '1', '1', 'system:notice:list', 'message', 'admin', '2024-06-13 16:06:26', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 0, 140, 'log', '', '', 0, 0, 'M', '1', '1', '', 'log', 'admin', '2024-06-13 16:06:27', 'admin', '2024-08-20 13:46:16', '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 0, 0, 'C', '1', '1', 'monitor:online:list', 'online', 'admin', '2024-06-13 16:06:27', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (112, '缓存列表', 2, 6, 'cacheList', 'monitor/cache/list', '', 0, 0, 'C', '1', '1', 'monitor:cache:list', 'redis-list', 'admin', '2024-06-13 16:06:27', '', NULL, '缓存列表菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 0, 0, 'C', '1', '1', 'monitor:cache:list', 'redis', 'admin', '2024-06-13 16:06:27', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 0, 130, 'gen', 'tool/gen/index', '', 0, 0, 'C', '1', '1', 'tool:gen:list', 'code', 'admin', '2024-06-13 16:06:27', 'admin', '2024-08-20 13:46:06', '代码生成菜单');
INSERT INTO `sys_menu` VALUES (118, '文件管理', 1, 10, 'oss', 'system/oss/index', '', 0, 0, 'C', '1', '1', 'system:oss:list', 'upload', 'admin', '2024-06-13 16:06:27', '', NULL, '文件管理菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 0, 0, 'C', '1', '1', 'monitor:operlog:list', 'form', 'admin', '2024-06-13 16:06:27', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 0, 0, 'C', '1', '1', 'monitor:logininfor:list', 'logininfor', 'admin', '2024-06-13 16:06:27', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', '', 0, 0, 'F', '1', '1', 'system:user:query', '#', 'admin', '2024-06-13 16:06:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', '', 0, 0, 'F', '1', '1', 'system:user:add', '#', 'admin', '2024-06-13 16:06:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', '', 0, 0, 'F', '1', '1', 'system:user:edit', '#', 'admin', '2024-06-13 16:06:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', '', 0, 0, 'F', '1', '1', 'system:user:remove', '#', 'admin', '2024-06-13 16:06:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', '', 0, 0, 'F', '1', '1', 'system:user:export', '#', 'admin', '2024-06-13 16:06:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', '', 0, 0, 'F', '1', '1', 'system:user:import', '#', 'admin', '2024-06-13 16:06:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', '', 0, 0, 'F', '1', '1', 'system:user:resetPwd', '#', 'admin', '2024-06-13 16:06:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', '', 0, 0, 'F', '1', '1', 'system:role:query', '#', 'admin', '2024-06-13 16:06:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', '', 0, 0, 'F', '1', '1', 'system:role:add', '#', 'admin', '2024-06-13 16:06:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', '', 0, 0, 'F', '1', '1', 'system:role:edit', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', '', 0, 0, 'F', '1', '1', 'system:role:remove', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', '', 0, 0, 'F', '1', '1', 'system:role:export', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', '', 0, 0, 'F', '1', '1', 'system:menu:query', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', '', 0, 0, 'F', '1', '1', 'system:menu:add', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', '', 0, 0, 'F', '1', '1', 'system:menu:edit', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', '', 0, 0, 'F', '1', '1', 'system:menu:remove', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', '', 0, 0, 'F', '1', '1', 'system:dept:query', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', '', 0, 0, 'F', '1', '1', 'system:dept:add', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', '', 0, 0, 'F', '1', '1', 'system:dept:edit', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', '', 0, 0, 'F', '1', '1', 'system:dept:remove', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', '', 0, 0, 'F', '1', '1', 'system:post:query', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', '', 0, 0, 'F', '1', '1', 'system:post:add', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', '', 0, 0, 'F', '1', '1', 'system:post:edit', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', '', 0, 0, 'F', '1', '1', 'system:post:remove', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', '', 0, 0, 'F', '1', '1', 'system:post:export', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', '', 0, 0, 'F', '1', '1', 'system:dict:query', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', '', 0, 0, 'F', '1', '1', 'system:dict:add', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', '', 0, 0, 'F', '1', '1', 'system:dict:edit', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', '', 0, 0, 'F', '1', '1', 'system:dict:remove', '#', 'admin', '2024-06-13 16:06:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', '', 0, 0, 'F', '1', '1', 'system:dict:export', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', '', 0, 0, 'F', '1', '1', 'system:config:query', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', '', 0, 0, 'F', '1', '1', 'system:config:add', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', '', 0, 0, 'F', '1', '1', 'system:config:edit', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', '', 0, 0, 'F', '1', '1', 'system:config:remove', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', '', 0, 0, 'F', '1', '1', 'system:config:export', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', '', 0, 0, 'F', '1', '1', 'system:notice:query', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', '', 0, 0, 'F', '1', '1', 'system:notice:add', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', '', 0, 0, 'F', '1', '1', 'system:notice:edit', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', '', 0, 0, 'F', '1', '1', 'system:notice:remove', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:operlog:query', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:operlog:remove', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:operlog:export', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:logininfor:query', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:logininfor:remove', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:logininfor:export', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:online:query', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:online:batchLogout', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:online:forceLogout', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '账户解锁', 501, 4, '#', '', '', 0, 0, 'F', '1', '1', 'monitor:logininfor:unlock', '#', 'admin', '2024-06-13 16:06:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', '', 0, 0, 'F', '1', '1', 'tool:gen:query', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', '', 0, 0, 'F', '1', '1', 'tool:gen:edit', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', '', 0, 0, 'F', '1', '1', 'tool:gen:remove', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', '', 0, 0, 'F', '1', '1', 'tool:gen:import', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', '', 0, 0, 'F', '1', '1', 'tool:gen:preview', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', '', 0, 0, 'F', '1', '1', 'tool:gen:code', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1600, '文件查询', 118, 1, '#', '', '', 0, 0, 'F', '1', '1', 'system:oss:query', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1601, '文件上传', 118, 2, '#', '', '', 0, 0, 'F', '1', '1', 'system:oss:upload', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1602, '文件下载', 118, 3, '#', '', '', 0, 0, 'F', '1', '1', 'system:oss:download', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1603, '文件删除', 118, 4, '#', '', '', 0, 0, 'F', '1', '1', 'system:oss:remove', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1604, '配置添加', 118, 5, '#', '', '', 0, 0, 'F', '1', '1', 'system:oss:add', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1605, '配置编辑', 118, 6, '#', '', '', 0, 0, 'F', '1', '1', 'system:oss:edit', '#', 'admin', '2024-06-13 16:06:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1808758090157985794, '基础信息', 0, 50, 'basic', NULL, NULL, 0, 0, 'M', '1', '1', NULL, 'excel', 'admin', '2024-07-04 15:01:48', 'admin', '2024-12-25 17:48:11', '');
INSERT INTO `sys_menu` VALUES (1813458070128599041, '仓库列表', 1808758090157985794, 2, 'warehouse', 'basic/warehouse/index', NULL, 0, 0, 'C', '1', '1', 'basic:warehouse:list', 'documentation', 'admin', '2024-07-17 14:17:51', 'admin', '2025-01-19 20:00:33', '');
INSERT INTO `sys_menu` VALUES (1813820131794837506, '商品列表', 1808758090157985794, 4, 'goods', 'basic/goods/index', NULL, 0, 0, 'C', '1', '1', 'basic:goods:list', 'documentation', 'admin', '2024-07-18 14:16:33', 'admin', '2025-01-19 20:00:54', '');
INSERT INTO `sys_menu` VALUES (1818123963605549057, '品牌列表', 1808758090157985794, 3, 'brand', 'basic/brand/index', NULL, 0, 0, 'C', '1', '1', 'basic:brand:list', 'documentation', 'admin', '2024-07-30 11:18:27', 'admin', '2025-01-19 20:00:44', '');
INSERT INTO `sys_menu` VALUES (1820729144067321858, '库存统计', 1909062390478917634, 100, 'inventory', 'wms/inventory/statistic', NULL, 0, 0, 'C', '1', '1', 'wms:inventory:all', 'chart', 'admin', '2024-08-06 15:50:30', 'admin', '2025-04-07 09:56:15', '');
INSERT INTO `sys_menu` VALUES (1821075355068559361, '库存记录', 1909062856000524290, 200, 'inventoryHistory', 'wms/inventory/history', NULL, 0, 0, 'C', '1', '1', 'wms:inventoryHistory:all', 'list', 'admin', '2024-08-07 14:46:13', 'admin', '2025-04-07 09:57:43', '');
INSERT INTO `sys_menu` VALUES (1829349433573822466, '仓库查询', 1813458070128599041, 1, '', NULL, NULL, 0, 0, 'F', '1', '1', 'basic:warehouse:list', '#', 'admin', '2024-08-30 10:44:27', 'admin', '2024-12-25 17:32:08', '');
INSERT INTO `sys_menu` VALUES (1829350022131142658, '仓库编辑', 1813458070128599041, 2, '', NULL, NULL, 0, 0, 'F', '1', '1', 'basic:warehouse:edit', '#', 'admin', '2024-08-30 10:46:48', 'admin', '2024-12-25 17:31:55', '');
INSERT INTO `sys_menu` VALUES (1829350164603260929, '品牌查询', 1818123963605549057, 1, '', NULL, NULL, 0, 0, 'F', '1', '1', 'basic:brand:list', '#', 'admin', '2024-08-30 10:47:22', 'admin', '2024-12-25 17:33:24', '');
INSERT INTO `sys_menu` VALUES (1829350944311791617, '品牌编辑', 1818123963605549057, 2, '', NULL, NULL, 0, 0, 'F', '1', '1', 'basic:brand:edit', '#', 'admin', '2024-08-30 10:50:27', 'admin', '2024-12-25 17:34:04', '');
INSERT INTO `sys_menu` VALUES (1829351081448755202, '商品查询', 1813820131794837506, 1, '', NULL, NULL, 0, 0, 'F', '1', '1', 'basic:goods:list', '#', 'admin', '2024-08-30 10:51:00', 'admin', '2024-12-25 17:35:00', '');
INSERT INTO `sys_menu` VALUES (1829351166857367553, '商品编辑', 1813820131794837506, 2, '', NULL, NULL, 0, 0, 'F', '1', '1', 'basic:goods:edit', '#', 'admin', '2024-08-30 10:51:21', 'admin', '2024-12-25 17:35:13', '');
INSERT INTO `sys_menu` VALUES (1871838016122515458, '采购管理', 0, 10, 'purchase', NULL, NULL, 0, 0, 'M', '1', '1', NULL, 'exit-fullscreen', 'admin', '2024-12-25 16:38:55', 'admin', '2025-01-25 18:42:25', '');
INSERT INTO `sys_menu` VALUES (1871838227569963010, '销售管理', 0, 20, 'sales', NULL, NULL, 0, 0, 'M', '1', '1', NULL, 'fullscreen', 'admin', '2024-12-25 16:39:45', 'admin', '2025-01-25 18:42:39', '');
INSERT INTO `sys_menu` VALUES (1871838760355622913, '仓库管理', 0, 30, 'wms', NULL, NULL, 0, 0, 'M', '1', '1', NULL, 'redis', 'admin', '2024-12-25 16:41:52', 'admin', '2025-01-27 13:59:50', '');
INSERT INTO `sys_menu` VALUES (1871838986088869890, '资金管理', 0, 40, 'finalcial', NULL, NULL, 0, 0, 'M', '1', '1', NULL, 'money', 'admin', '2024-12-25 16:42:46', 'admin', '2024-12-26 13:55:31', '');
INSERT INTO `sys_menu` VALUES (1872981868162166786, '往来单位', 1808758090157985794, 5, 'merchant', 'basic/merchant/index', NULL, 0, 0, 'C', '1', '1', 'basic:merchant:list', 'documentation', 'admin', '2024-12-28 20:24:10', 'admin', '2024-12-28 20:26:30', '');
INSERT INTO `sys_menu` VALUES (1872982566027243521, '往来单位查询', 1872981868162166786, 1, '', NULL, NULL, 0, 0, 'F', '1', '1', 'basic:merchant:list', '#', 'admin', '2024-12-28 20:26:57', 'admin', '2024-12-28 20:26:57', '');
INSERT INTO `sys_menu` VALUES (1872982689608216577, '往来单位编辑', 1872981868162166786, 2, '', NULL, NULL, 0, 0, 'F', '1', '1', 'basic:merchant:edit', '#', 'admin', '2024-12-28 20:27:26', 'admin', '2024-12-28 20:27:26', '');
INSERT INTO `sys_menu` VALUES (1880968475272114177, '其他入库', 1871838760355622913, 1, 'receipt', 'wms/doc/receipt/index', NULL, 0, 0, 'C', '1', '1', 'wms:receipt:all', 'exit-fullscreen', 'admin', '2025-01-19 21:20:06', 'admin', '2025-01-19 21:26:06', '');
INSERT INTO `sys_menu` VALUES (1881066113506050050, '编辑其他入库单', 1871838760355622913, 11, 'receiptEdit', 'wms/doc/receipt/edit', NULL, 0, 0, 'C', '0', '1', 'wms:receipt:all', '#', 'admin', '2025-01-20 03:48:05', 'admin', '2025-01-20 03:49:45', '');
INSERT INTO `sys_menu` VALUES (1883103519470116865, '其他出库', 1871838760355622913, 2, 'shipment', 'wms/doc/shipment/index', NULL, 0, 0, 'C', '1', '1', 'wms:shipment:all', 'fullscreen', 'admin', '2025-01-25 18:44:00', 'admin', '2025-01-25 18:44:00', '');
INSERT INTO `sys_menu` VALUES (1883126645054566401, '编辑其他出库', 1871838760355622913, 12, 'shipmentEdit', 'wms/doc/shipment/edit', NULL, 0, 0, 'C', '0', '1', 'wms:shipment:edit', '#', 'admin', '2025-01-25 20:15:54', 'admin', '2025-01-25 20:15:54', '');
INSERT INTO `sys_menu` VALUES (1883412523677184001, '调拨单', 1871838760355622913, 3, 'movement', 'wms/doc/movement/index', NULL, 0, 0, 'C', '1', '1', 'wms:movement:all', 'drag', 'admin', '2025-01-26 15:11:52', 'admin', '2025-01-26 15:42:15', '');
INSERT INTO `sys_menu` VALUES (1883479508515627009, '编辑调拨单', 1871838760355622913, 13, 'movementEdit', 'wms/doc/movement/edit', NULL, 0, 0, 'C', '0', '1', 'wms:movement:all', '#', 'admin', '2025-01-26 19:38:03', 'admin', '2025-01-26 19:50:18', '');
INSERT INTO `sys_menu` VALUES (1883751242967826433, '盘库单', 1871838760355622913, 4, 'check', 'wms/doc/check/index', NULL, 0, 0, 'C', '1', '1', 'wms:check:all', 'example', 'admin', '2025-01-27 13:37:49', 'admin', '2025-01-27 13:37:49', '');
INSERT INTO `sys_menu` VALUES (1883764644054380545, '编辑盘库单', 1871838760355622913, 14, 'checkEdit', 'wms/doc/check/edit', NULL, 0, 0, 'C', '0', '1', 'wms:check:all', '#', 'admin', '2025-01-27 14:31:04', 'admin', '2025-01-27 14:31:04', '');
INSERT INTO `sys_menu` VALUES (1887717125616652290, '采购订单', 1871838016122515458, 1, 'order', 'purchase/order/index', NULL, 0, 0, 'C', '1', '1', 'purchase:order:all', 'documentation', 'admin', '2025-02-07 12:16:49', 'admin', '2025-03-12 08:48:15', '');
INSERT INTO `sys_menu` VALUES (1887717372736655361, '采购入库单', 1871838016122515458, 2, 'trade', 'purchase/trade/index', NULL, 0, 0, 'C', '1', '1', 'purchase:trade:all', 'documentation', 'admin', '2025-02-07 12:17:48', 'admin', '2025-03-12 10:14:41', '');
INSERT INTO `sys_menu` VALUES (1887717757459189762, '采购退货单', 1871838016122515458, 3, 'refund', 'purchase/refund/index', NULL, 0, 0, 'C', '1', '1', 'purchase:refund:all', 'documentation', 'admin', '2025-02-07 12:19:20', 'admin', '2025-02-07 12:19:20', '');
INSERT INTO `sys_menu` VALUES (1887798227798597634, '编辑采购订单', 1871838016122515458, 11, 'orderEdit', 'purchase/order/edit', NULL, 0, 0, 'C', '0', '1', 'purchase:order:all', '#', 'admin', '2025-02-07 17:39:06', 'admin', '2025-03-25 21:30:56', '');
INSERT INTO `sys_menu` VALUES (1889973674825220098, '银行账户', 1808758090157985794, 6, 'bankAccount', 'basic/bankAccount/index', NULL, 0, 0, 'C', '1', '1', 'basic:bankAccount:list', 'documentation', 'admin', '2025-02-13 17:45:16', 'admin', '2025-02-13 17:47:42', '银行账户菜单');
INSERT INTO `sys_menu` VALUES (1890210720529526786, '收款单', 1871838986088869890, 1, 'receiptVoucher', 'financial/receiptVoucher/index', NULL, 0, 0, 'C', '1', '1', 'financial:receiptVoucher:list', 'documentation', 'admin', '2025-02-14 09:32:31', 'admin', '2025-02-14 09:35:31', '收款单菜单');
INSERT INTO `sys_menu` VALUES (1892153338725466113, '交易流水', 1909062856000524290, 5, 'transHistory', 'financial/transHistory/index', NULL, 0, 0, 'C', '1', '1', 'financial:transHistory:list', 'documentation', 'admin', '2025-02-20 08:59:46', 'admin', '2025-04-07 09:57:24', '交易流水菜单');
INSERT INTO `sys_menu` VALUES (1892153338985512962, '商户余额', 1909062390478917634, 4, 'merchantBalance', 'financial/merchantBalance/index', NULL, 0, 0, 'C', '1', '1', 'financial:merchantBalance:list', 'documentation', 'admin', '2025-02-20 08:44:47', 'admin', '2025-04-07 09:55:47', '商家余额菜单');
INSERT INTO `sys_menu` VALUES (1895048931932205058, '编辑采购入库单', 1871838016122515458, 12, 'tradeEdit', 'purchase/trade/edit', NULL, 0, 0, 'C', '0', '1', 'purchase:trade:all', '#', 'admin', '2025-02-27 17:50:48', 'admin', '2025-03-12 10:16:09', '');
INSERT INTO `sys_menu` VALUES (1899016688277135362, '付款单', 1871838986088869890, 2, 'paymentVoucher', 'financial/paymentVoucher/index', NULL, 0, 0, 'C', '1', '1', 'financial:paymentVoucher:list', 'documentation', 'admin', '2025-03-10 16:37:15', 'admin', '2025-03-11 08:56:42', '');
INSERT INTO `sys_menu` VALUES (1899291413045841922, '销售订单', 1871838227569963010, 1, 'order', 'sales/order/index', NULL, 0, 0, 'C', '1', '1', 'sales:order:all', 'documentation', 'admin', '2025-03-11 10:48:55', 'admin', '2025-03-11 15:08:09', '');
INSERT INTO `sys_menu` VALUES (1899362260070707201, '编辑销售订单', 1871838227569963010, 11, 'orderEdit', 'sales/order/edit', NULL, 0, 0, 'C', '0', '1', 'sales:order:all', 'documentation', 'admin', '2025-03-11 15:30:26', 'admin', '2025-03-12 10:46:44', '');
INSERT INTO `sys_menu` VALUES (1899635468703449090, '销售出库单', 1871838227569963010, 2, 'trade', 'sales/trade/index', NULL, 0, 0, 'C', '1', '1', 'sales:trade:all', 'documentation', 'admin', '2025-03-12 09:36:04', 'admin', '2025-03-12 16:15:25', '');
INSERT INTO `sys_menu` VALUES (1899638949199822849, '编辑销售出库', 1871838227569963010, 12, 'tradeEdit', 'sales/trade/edit', NULL, 0, 0, 'C', '0', '1', 'sales:trade:all', 'documentation', 'admin', '2025-03-12 09:49:54', 'admin', '2025-03-13 21:32:03', '');
INSERT INTO `sys_menu` VALUES (1899653114815668225, '销售退货单', 1871838227569963010, 3, 'refund', 'sales/refund/index', NULL, 0, 0, 'C', '1', '1', 'sales:refund:all', 'documentation', 'admin', '2025-03-12 10:46:11', 'admin', '2025-03-12 10:47:07', '');
INSERT INTO `sys_menu` VALUES (1902554435680022529, '编辑采购退货单', 1871838016122515458, 10, 'refundEdit', 'purchase/refund/edit', NULL, 0, 0, 'C', '0', '1', 'purchase:refund:all', 'documentation', 'admin', '2025-03-20 10:55:00', 'admin', '2025-03-21 15:10:58', '');
INSERT INTO `sys_menu` VALUES (1903972572161900545, '编辑销售退货单', 1871838227569963010, 8, 'refundEdit', 'sales/refund/edit', NULL, 0, 0, 'C', '0', '1', 'sales:refund:all', '#', 'admin', '2025-03-24 08:50:10', 'admin', '2025-03-24 13:11:45', '');
INSERT INTO `sys_menu` VALUES (1909062390478917634, '统计看板', 0, 2, 'kanban', NULL, NULL, 0, 0, 'M', '1', '1', NULL, 'chart', 'admin', '2025-04-07 09:55:17', 'admin', '2025-04-07 09:55:17', '');
INSERT INTO `sys_menu` VALUES (1909062856000524290, '操作流水', 0, 3, 'history', NULL, NULL, 0, 0, 'M', '1', '1', NULL, 'log', 'admin', '2025-04-07 09:57:08', 'admin', '2025-04-07 09:57:08', '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` bigint(20) NOT NULL COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '1', 'admin', '2024-06-13 16:06:38', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '1', 'admin', '2024-06-13 16:06:38', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0异常 1正常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type`) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status`) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (1925937767962304514, '操作日志', 9, 'com.ruoyi.system.controller.monitor.SysOperlogController.clean()', 'DELETE', 1, 'admin', '研发部门', '/monitor/operlog/clean', '223.64.124.132', '中国|江苏省|苏州市|移动', '{}', '{\"code\":200,\"msg\":\"操作成功\",\"detailMessage\":null,\"data\":null}', 1, '', '2025-05-23 23:32:01');
INSERT INTO `sys_oper_log` VALUES (1925937804293365761, '登录日志', 9, 'com.ruoyi.system.controller.monitor.SysLogininforController.clean()', 'DELETE', 1, 'admin', '研发部门', '/monitor/logininfor/clean', '223.64.124.132', '中国|江苏省|苏州市|移动', '{}', '{\"code\":200,\"msg\":\"操作成功\",\"detailMessage\":null,\"data\":null}', 1, '', '2025-05-23 23:32:09');

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`  (
  `oss_id` bigint(20) NOT NULL COMMENT '对象存储主键',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件名',
  `original_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '原名',
  `file_suffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '文件后缀名',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'URL地址',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '上传人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新人',
  `service` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'minio' COMMENT '服务商',
  PRIMARY KEY (`oss_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'OSS对象存储表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oss_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss_config`;
CREATE TABLE `sys_oss_config`  (
  `oss_config_id` bigint(20) NOT NULL COMMENT '主建',
  `config_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '配置key',
  `access_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'accessKey',
  `secret_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '秘钥',
  `bucket_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '桶名称',
  `prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '前缀',
  `endpoint` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '访问站点',
  `domain` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '自定义域名',
  `is_https` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '域',
  `access_policy` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否默认（0=是,1=否）',
  `ext1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '扩展字段',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`oss_config_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '对象存储配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_oss_config
-- ----------------------------
INSERT INTO `sys_oss_config` VALUES (1, 'minio', 'ruoyi', 'ruoyi123', 'ruoyi', '', '127.0.0.1:9000', '', 'N', '', '1', '0', '', 'admin', '2024-06-13 16:06:38', 'admin', '2024-08-16 16:48:05', NULL);
INSERT INTO `sys_oss_config` VALUES (2, 'qiniu', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 's3-cn-north-1.qiniucs.com', '', 'N', '', '1', '0', '', 'admin', '2024-06-13 16:06:38', 'admin', '2024-06-13 16:06:38', NULL);
INSERT INTO `sys_oss_config` VALUES (3, 'aliyun', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi', '', 'oss-cn-beijing.aliyuncs.com', '', 'N', '', '1', '0', '', 'admin', '2024-06-13 16:06:38', 'admin', '2024-07-10 17:50:41', NULL);
INSERT INTO `sys_oss_config` VALUES (4, 'qcloud', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXX', 'ruoyi-1250000000', '', 'cos.ap-beijing.myqcloud.com', '', 'N', 'ap-beijing', '1', '0', '', 'admin', '2024-06-13 16:06:38', 'admin', '2024-06-13 16:06:38', NULL);
INSERT INTO `sys_oss_config` VALUES (5, 'image', 'ruoyi', 'ruoyi123', 'ruoyi', 'image', '127.0.0.1:9000', '', 'N', '', '1', '0', '', 'admin', '2024-06-13 16:06:38', 'admin', '2024-06-13 16:06:38', NULL);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '1', 'admin', '2024-06-13 16:06:25', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '1', 'admin', '2024-06-13 16:06:25', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '1', 'admin', '2024-06-13 16:06:25', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '1', 'admin', '2024-06-13 16:06:25', '', NULL, '');
INSERT INTO `sys_post` VALUES (1811656351757385729, 'caiwu8989', '财务', 5, '1', 'admin', '2024-07-12 22:58:28', 'admin', '2024-07-12 14:58:38', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '1', '0', 'admin', '2024-06-13 16:06:26', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '1', '1', 'admin', '2024-06-13 16:06:26', 'admin', '2024-07-10 17:13:05', '普通角色');
INSERT INTO `sys_role` VALUES (1811607750859661314, '测试角色1', 'test1', 2, '1', 1, 1, '1', '1', 'admin', '2024-07-12 11:45:21', 'admin', '2024-07-12 11:45:21', NULL);
INSERT INTO `sys_role` VALUES (1811629311809396737, '测试角色2', 'test2', 3, '1', 1, 1, '1', '1', 'admin', '2024-07-12 13:11:01', 'admin', '2024-07-12 13:11:01', NULL);
INSERT INTO `sys_role` VALUES (1829105952432427010, '试用', 'trier', 0, '1', 1, 1, '1', '0', 'admin', '2024-08-29 18:36:57', 'admin', '2025-05-23 12:22:37', NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1808758090157985794);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1813458070128599041);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1813820131794837506);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1818123963605549057);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1820729144067321858);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1821075355068559361);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1829349433573822466);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1829350164603260929);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1829351081448755202);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1871838016122515458);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1871838227569963010);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1871838760355622913);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1871838986088869890);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1872981868162166786);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1872982566027243521);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1880968475272114177);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1881066113506050050);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1883103519470116865);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1883126645054566401);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1883412523677184001);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1883479508515627009);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1883751242967826433);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1883764644054380545);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1887717125616652290);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1887717372736655361);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1887717757459189762);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1887798227798597634);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1889973674825220098);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1890210720529526786);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1892153338725466113);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1892153338985512962);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1895048931932205058);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1899016688277135362);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1899291413045841922);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1899362260070707201);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1899635468703449090);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1899638949199822849);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1899653114815668225);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1902554435680022529);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1903972572161900545);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1909062390478917634);
INSERT INTO `sys_role_menu` VALUES (1829105952432427010, 1909062856000524290);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'sys_user' COMMENT '用户类型（sys_user系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '程序员诚哥', 'sys_user', 'zccbbg@qq.com', '18888888888', '0', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '1', '0', '223.64.124.132', '2025-05-23 23:27:26', 'admin', '2024-06-13 16:06:25', 'admin', '2025-05-23 23:27:26', '管理员');
INSERT INTO `sys_user` VALUES (1829105396288688129, 105, 'erp', 'erp', 'sys_user', '', '', '0', '', '$2a$10$hFs2Zoz.a1A6SJiKPMNqbem7CLz7enYlCNOor4KTo2pSFXIOaEp3a', '1', '0', '127.0.0.1', '2025-05-23 13:23:23', 'admin', '2024-08-29 18:34:44', 'erp', '2025-05-23 13:23:23', NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1829105396288688129, 1829105952432427010);

-- ----------------------------
-- Table structure for wms_check_doc
-- ----------------------------
DROP TABLE IF EXISTS `wms_check_doc`;
CREATE TABLE `wms_check_doc`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc_no` varchar(22) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盘点单号',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `checked_status` tinyint(4) NULL DEFAULT 11 COMMENT '库存盘点单状态 -1：作废 0：未盘库 1：已盘库',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `goods_qty` decimal(20, 2) NULL DEFAULT NULL COMMENT '盈亏数',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL,
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '所属仓库',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库存盘点单据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_check_doc
-- ----------------------------

-- ----------------------------
-- Table structure for wms_check_doc_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_check_doc_detail`;
CREATE TABLE `wms_check_doc_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL COMMENT '盘点单id',
  `sku_id` bigint(20) NOT NULL COMMENT '规格id',
  `qty` decimal(20, 2) NULL DEFAULT NULL COMMENT '库存数量',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `check_qty` decimal(20, 2) NULL DEFAULT NULL COMMENT '盘点数量',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '所属仓库',
  `inventory_id` bigint(20) NULL DEFAULT NULL COMMENT '库存id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库存盘点单据详情' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_check_doc_detail
-- ----------------------------

-- ----------------------------
-- Table structure for wms_inventory
-- ----------------------------
DROP TABLE IF EXISTS `wms_inventory`;
CREATE TABLE `wms_inventory`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '规格ID',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '所属仓库',
  `qty` decimal(20, 2) NULL DEFAULT NULL COMMENT '库存',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库存表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_inventory
-- ----------------------------

-- ----------------------------
-- Table structure for wms_inventory_history
-- ----------------------------
DROP TABLE IF EXISTS `wms_inventory_history`;
CREATE TABLE `wms_inventory_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '所属仓库',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '物料ID',
  `qty` decimal(20, 2) NULL DEFAULT NULL COMMENT '库存变化',
  `before_qty` decimal(20, 2) NULL DEFAULT NULL COMMENT '更新前数量',
  `after_qty` decimal(20, 2) NULL DEFAULT NULL COMMENT '更新后数量',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `opt_id` bigint(20) NULL DEFAULT NULL COMMENT '操作id（出库、入库、库存移动表单id）',
  `opt_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作单号（入库、出库、移库、盘库单号）',
  `opt_type` int(11) NULL DEFAULT NULL COMMENT '操作类型',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库存记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_inventory_history
-- ----------------------------

-- ----------------------------
-- Table structure for wms_movement_doc
-- ----------------------------
DROP TABLE IF EXISTS `wms_movement_doc`;
CREATE TABLE `wms_movement_doc`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '源仓库',
  `target_warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '目标仓库',
  `checked_status` tinyint(4) NULL DEFAULT NULL COMMENT '状态',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `goods_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '总数量',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移库单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_movement_doc
-- ----------------------------

-- ----------------------------
-- Table structure for wms_movement_doc_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_movement_doc_detail`;
CREATE TABLE `wms_movement_doc_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '移库单Id',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '规格id',
  `qty` decimal(20, 2) NULL DEFAULT NULL COMMENT '数量',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '源仓库',
  `target_warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '目标仓库',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '库存移动详情' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_movement_doc_detail
-- ----------------------------

-- ----------------------------
-- Table structure for wms_receipt_doc
-- ----------------------------
DROP TABLE IF EXISTS `wms_receipt_doc`;
CREATE TABLE `wms_receipt_doc`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '入库单号',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `goods_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品总数',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `checked_status` tinyint(4) NULL DEFAULT NULL COMMENT '入库状态',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '入库单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_receipt_doc
-- ----------------------------

-- ----------------------------
-- Table structure for wms_receipt_doc_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_receipt_doc_detail`;
CREATE TABLE `wms_receipt_doc_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '入库单号',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '规格id',
  `qty` decimal(20, 2) NULL DEFAULT NULL COMMENT '入库数量',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '所属仓库',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '入库单详情' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_receipt_doc_detail
-- ----------------------------

-- ----------------------------
-- Table structure for wms_shipment_doc
-- ----------------------------
DROP TABLE IF EXISTS `wms_shipment_doc`;
CREATE TABLE `wms_shipment_doc`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doc_no` varchar(22) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出库单号，系统自动生成',
  `doc_date` datetime(3) NULL DEFAULT NULL COMMENT '单据日期',
  `merchant_id` bigint(20) NULL DEFAULT NULL COMMENT '客户',
  `goods_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `goods_qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '总数量',
  `checked_status` tinyint(4) NULL DEFAULT NULL COMMENT '出库单状态',
  `checked_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核人',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '出库单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_shipment_doc
-- ----------------------------

-- ----------------------------
-- Table structure for wms_shipment_doc_detail
-- ----------------------------
DROP TABLE IF EXISTS `wms_shipment_doc_detail`;
CREATE TABLE `wms_shipment_doc_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '出库单',
  `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '所属仓库',
  `sku_id` bigint(20) NULL DEFAULT NULL COMMENT '规格id',
  `qty` decimal(10, 2) NULL DEFAULT NULL COMMENT '数量',
  `total_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '出库单详情' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_shipment_doc_detail
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

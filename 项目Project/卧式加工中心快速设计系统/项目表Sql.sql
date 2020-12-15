SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mt_atc
-- ----------------------------
DROP TABLE IF EXISTS `mt_atc`;
CREATE TABLE `mt_atc`  (
  `toolid` int(11) NOT NULL AUTO_INCREMENT,
  `tooldaihao` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `xingshi` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `qudong` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `daobing` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `capacity` int(30) NOT NULL,
  `maxd` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `maxd_null` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `length` int(30) NOT NULL,
  `t_weight` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tooltime` double(30, 2) NOT NULL,
  `zuanhole` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gongsi` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `xixue` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`toolid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_atc
-- ----------------------------
INSERT INTO `mt_atc` VALUES (1, 'HE63S-tool', '凸轮快换刀库', '伺服驱动', 'HSK-A100', 40, '125', '250', 450, '25', 2.25, '55', '45', '600', 'HE63S');
INSERT INTO `mt_atc` VALUES (2, 'HE100A-tool', '凸轮快换刀库', '伺服驱动', 'HSK-A100', 40, '200', '250', 450, '25', 2.25, '55', '45', '600', 'HE100A');
INSERT INTO `mt_atc` VALUES (3, 'HM50TS/TD-tool', '凸轮快换刀库', '伺服驱动', 'MAS403 BT40', 30, '80', '150', 350, '8', 2.33, '30', '20', '200', 'HM50TS');
INSERT INTO `mt_atc` VALUES (4, 'HM63TS/80TS/100TS-tool', '凸轮快换刀库', '伺服驱动', 'MAS403 BT50', 40, '125', '250', 400, '25', 4.75, '55', '45', '600', 'HM63TS');
INSERT INTO `mt_atc` VALUES (5, 'HM50TS/TD-tool', '凸轮快换刀库', '伺服驱动', 'MAS403 BT50', 24, '110', '250', 350, '20', 3.80, '35', '24', '250', 'HM50TD');
INSERT INTO `mt_atc` VALUES (6, 'HM125TS-tool', '液压刀库', '油压驱动', 'MAS403 BT50', 60, '125', '250', 600, '35', 7.50, '70', '50', '1000', 'HM125TS');
INSERT INTO `mt_atc` VALUES (7, 'HM100V-tool', '凸轮快换刀库', '伺服驱动', 'MAS403 BT50', 40, '125', '250', 500, '35', 5.50, '60', '48', '900', 'HM100VS');
INSERT INTO `mt_atc` VALUES (8, 'HM63V/80V-tool', '凸轮快换刀库', '伺服驱动', 'MAS403 BT50', 40, '125', '250', 500, '25', 3.45, '55', '45', '600', 'HM80VS');

-- ----------------------------
-- Table structure for mt_bed
-- ----------------------------
DROP TABLE IF EXISTS `mt_bed`;
CREATE TABLE `mt_bed`  (
  `bedid` int(11) NOT NULL AUTO_INCREMENT,
  `beddaihao` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `structure` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `material` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `length` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `width` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `height` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Xroute` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Yroute` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Zroute` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_distance` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `x_distance` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `caoshen` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `caokuan` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `caolength` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `holed` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `rib` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `kuanheng` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `kuanshu` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `zhaiheng` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `zhaishu` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ribthick` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `chip` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`bedid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_bed
-- ----------------------------
INSERT INTO `mt_bed` VALUES (1, 'HE63S-body', 'T型中央排屑结构', 'HT300', '3200', '2120', '1104', '1000', '850', '1000', '850', '750', '430', '680', '2420', '100', '环形和M型加强筋', '3环筋', '6环筋', '6环筋', '4环筋', '20', '螺旋式', 'HE63S');
INSERT INTO `mt_bed` VALUES (2, 'HM50TS-body', 'T型大跨距结构', 'HT300', '3170', '1870', '1121', '750', '650', '650', '750', '730', '430', '550', '1940', '100', '环形和M型加强筋', '3环筋', '5环筋', '5环筋', '4环筋', '20', '螺旋式', 'HM50TS');
INSERT INTO `mt_bed` VALUES (3, 'HM80TD-body', 'T型大跨距结构', 'HT300', '3880', '2530', '1125', '1400', '1050', '1050', '1070', '730', '430', '850', '2640', '110', '环形和M型加强筋', '3环筋', '6环筋', '7环筋', '5环筋', '20', '螺旋式', 'HM80TD');
INSERT INTO `mt_bed` VALUES (4, 'HM100TL-body', 'T型大跨距结构', 'HT300', '4330', '3240', '1130', '2100', '1300', '1300', '1270', '735', '430', '1050', '3090', '110', '环形和M型加强筋', '3环筋', '7环筋', '9环筋', '6环筋', '25', '螺旋式', 'HM100TL');
INSERT INTO `mt_bed` VALUES (5, 'HM125T-body', 'T型大跨距结构', 'HT300', '4790', '3355', '1130', '2200', '1500', '1500', '1520', '750', '430', '1300', '3540', '120', '环形和M型加强筋', '3环筋', '8环筋', '10环筋', '6环筋', '25', '螺旋式', 'HM125T');
INSERT INTO `mt_bed` VALUES (6, 'HE100A-body', 'T型中央排屑结构', 'HT300', '3200', '2120', '1104', '1000', '850', '1000', '850', '750', '430', '680', '2420', '100', '环形和M型加强筋', '3环筋', '6环筋', '6环筋', '4环筋', '20', '螺旋式', 'HE100AS');

-- ----------------------------
-- Table structure for mt_beddfresult
-- ----------------------------
DROP TABLE IF EXISTS `mt_beddfresult`;
CREATE TABLE `mt_beddfresult`  (
  `zj_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `df_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`zj_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_beddfresult
-- ----------------------------
INSERT INTO `mt_beddfresult` VALUES (1, 'zj-admin', '2020-06-28');
INSERT INTO `mt_beddfresult` VALUES (2, 'zj-002', '2020-06-28');

-- ----------------------------
-- Table structure for mt_bedweight
-- ----------------------------
DROP TABLE IF EXISTS `mt_bedweight`;
CREATE TABLE `mt_bedweight`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `b_length` double(11, 2) NOT NULL,
  `b_width` double(11, 2) NOT NULL,
  `b_height` double(11, 2) NOT NULL,
  `b_cd` double(11, 2) NOT NULL,
  `b_xL` double(11, 2) NOT NULL,
  `b_xD` double(11, 2) NOT NULL,
  `b_zL` double(11, 2) NOT NULL,
  `b_zD` double(11, 2) NOT NULL,
  `b_ribThick` double(11, 2) NOT NULL,
  `b_hole` double(11, 2) NOT NULL,
  `b_structure` double(11, 2) NOT NULL,
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_bedweight
-- ----------------------------
INSERT INTO `mt_bedweight` VALUES (1, 0.20, 0.16, 0.13, 0.09, 0.09, 0.09, 0.08, 0.08, 0.02, 0.02, 0.04);

-- ----------------------------
-- Table structure for mt_bedwgt_df
-- ----------------------------
DROP TABLE IF EXISTS `mt_bedwgt_df`;
CREATE TABLE `mt_bedwgt_df`  (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `b_length` int(11) NOT NULL,
  `b_width` int(11) NOT NULL,
  `b_height` int(11) NOT NULL,
  `b_cd` int(11) NOT NULL,
  `b_xL` int(11) NOT NULL,
  `b_xD` int(11) NOT NULL,
  `b_zL` int(11) NOT NULL,
  `b_zD` int(11) NOT NULL,
  `b_ribThick` int(11) NOT NULL,
  `b_hole` int(11) NOT NULL,
  `b_structure` int(11) NOT NULL,
  PRIMARY KEY (`bid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_bedwgt_df
-- ----------------------------
INSERT INTO `mt_bedwgt_df` VALUES (1, 1, 2, 2, 5, 4, 3, 3, 4, 6, 8, 5);
INSERT INTO `mt_bedwgt_df` VALUES (2, 0, 1, 2, 3, 3, 4, 4, 3, 6, 8, 5);
INSERT INTO `mt_bedwgt_df` VALUES (3, 0, 0, 1, 4, 4, 4, 4, 4, 5, 5, 4);
INSERT INTO `mt_bedwgt_df` VALUES (4, 0, 0, 0, 1, 2, 4, 2, 2, 5, 6, 4);
INSERT INTO `mt_bedwgt_df` VALUES (5, 0, 0, 0, 0, 1, 2, 2, 2, 6, 8, 5);
INSERT INTO `mt_bedwgt_df` VALUES (6, 0, 0, 0, 0, 0, 1, 2, 4, 6, 8, 5);
INSERT INTO `mt_bedwgt_df` VALUES (7, 0, 0, 0, 0, 0, 0, 1, 4, 6, 8, 5);
INSERT INTO `mt_bedwgt_df` VALUES (8, 0, 0, 0, 0, 0, 0, 0, 1, 6, 8, 5);
INSERT INTO `mt_bedwgt_df` VALUES (9, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3);
INSERT INTO `mt_bedwgt_df` VALUES (10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3);
INSERT INTO `mt_bedwgt_df` VALUES (11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
INSERT INTO `mt_bedwgt_df` VALUES (12, 1, 3, 3, 4, 5, 4, 2, 3, 6, 7, 4);
INSERT INTO `mt_bedwgt_df` VALUES (13, 0, 1, 2, 4, 3, 4, 4, 3, 5, 7, 5);
INSERT INTO `mt_bedwgt_df` VALUES (14, 0, 0, 1, 4, 4, 3, 3, 4, 4, 5, 4);
INSERT INTO `mt_bedwgt_df` VALUES (15, 0, 0, 0, 1, 2, 3, 2, 2, 5, 6, 3);
INSERT INTO `mt_bedwgt_df` VALUES (16, 0, 0, 0, 0, 1, 2, 2, 2, 5, 7, 5);
INSERT INTO `mt_bedwgt_df` VALUES (17, 0, 0, 0, 0, 0, 1, 2, 4, 6, 7, 5);
INSERT INTO `mt_bedwgt_df` VALUES (18, 0, 0, 0, 0, 0, 0, 1, 6, 6, 7, 4);
INSERT INTO `mt_bedwgt_df` VALUES (19, 0, 0, 0, 0, 0, 0, 0, 1, 5, 7, 5);
INSERT INTO `mt_bedwgt_df` VALUES (20, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3);
INSERT INTO `mt_bedwgt_df` VALUES (21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4);
INSERT INTO `mt_bedwgt_df` VALUES (22, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);

-- ----------------------------
-- Table structure for mt_screw
-- ----------------------------
DROP TABLE IF EXISTS `mt_screw`;
CREATE TABLE `mt_screw`  (
  `swId` int(30) NOT NULL AUTO_INCREMENT,
  `xinghao` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `jdlevel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `daochen` double(30, 2) NOT NULL,
  `gcd` double(30, 2) NOT NULL,
  `gunzhud` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `zhougd` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `eddn` int(30) NOT NULL,
  `edjn` int(30) NOT NULL,
  `power` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `djtype` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `speed` double(30, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`swId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_screw
-- ----------------------------
INSERT INTO `mt_screw` VALUES (1, 'NSK/EM5016-4E', 'C3', 16.00, 50.00, '7.144', '1150', 61800, 160000, '8.2/4.4', '1FK7105-2AF71-1RG1', 51);
INSERT INTO `mt_screw` VALUES (2, 'NSK/EM5020-6E', 'C3', 20.00, 50.00, '6.350', '1600', 73200, 206000, '8.2/4.4', '1FK7103-2AF71-1RG1 ', 64);
INSERT INTO `mt_screw` VALUES (3, 'NSK/EM5025-6E', 'C3', 25.00, 50.00, '7.144', '1620', 85600, 235000, '8.2/4.4', '1FK7103-2AF71-1RG1 ', 80);
INSERT INTO `mt_screw` VALUES (4, 'NSK/EM5030-6E', 'C3', 30.00, 50.00, '7.144', '1630', 85600, 235000, '8.2/4.4', '1FK7105-2AF71-1RG1', 96);
INSERT INTO `mt_screw` VALUES (5, 'NSK/EM4016-4E', 'C3', 16.00, 40.00, '7.144', '1020', 57100, 130000, '8.2/4.4', '1FK7105-2AF71-1RG1', 64);
INSERT INTO `mt_screw` VALUES (6, 'NSK/EM4020-6E', 'C3', 20.00, 40.00, '6.350', '1350', 66900, 165000, '8.2/4.4', '1FK7103-2AF71-1RG1 ', 80);
INSERT INTO `mt_screw` VALUES (8, 'NSK/EM4025-6E', 'C3', 25.00, 40.00, '7.144', '1370', 79100, 191000, '8.2/4.4', '1FK7103-2AF71-1RG1 ', 100);
INSERT INTO `mt_screw` VALUES (9, 'NSK/EM4030-6E', 'C3', 30.00, 40.00, '7.144', '1350', 79100, 191000, '8.2/4.4', '1FK7103-2AF71-1RG1 ', 120);
INSERT INTO `mt_screw` VALUES (10, 'NSK/EM4520-6E', 'C3', 20.00, 45.00, '6.350', '1470', 69100, 186000, '8.2/4.4', '1FK7103-2AF71-1RG1 ', 70);
INSERT INTO `mt_screw` VALUES (11, 'NSK/EM4525-6E', 'C3', 25.00, 45.00, '7.144', '1510', 82500, 213000, '8.2/4.4', '1FK7103-2AF71-1RG1 ', 88);
INSERT INTO `mt_screw` VALUES (12, 'NSK/EM4516-4E', 'C3', 16.00, 45.00, '7.144', '1060', 59600, 145000, '8.2/4.4', '1FK7103-2AF71-1RG1 ', 56);

-- ----------------------------
-- Table structure for mt_slideway
-- ----------------------------
DROP TABLE IF EXISTS `mt_slideway`;
CREATE TABLE `mt_slideway`  (
  `daogui_id` int(11) NOT NULL AUTO_INCREMENT,
  `xinghao` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `zhou` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `jdlevel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `width` int(11) NULL DEFAULT NULL,
  `length` int(30) NOT NULL,
  `aL` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `aR` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `distance` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `hks` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `eddn` int(30) NULL DEFAULT NULL,
  `edjn` int(30) NULL DEFAULT NULL,
  `yuya` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dwjd` double(30, 3) NOT NULL,
  `agdwjd` double(30, 4) NOT NULL,
  `course` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`daogui_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_slideway
-- ----------------------------
INSERT INTO `mt_slideway` VALUES (1, 'RUE-55-E', 'X', 'G2', 55, 3500, '40', '40', '750', 'W2', 136000, 320000, 'V3', 0.010, 0.0060, 'HE63S/100A');
INSERT INTO `mt_slideway` VALUES (2, 'RUE-55-E-H', 'Y', 'G2', 55, 1670, '25', '25', '640', 'W2', 136000, 320000, 'V3', 0.010, 0.0060, 'HE63S/100A');
INSERT INTO `mt_slideway` VALUES (3, 'RUE-55-E-L', 'Z', 'G2', 55, 1810, '35', '35', '850', 'W2', 167000, 415000, 'V3', 0.010, 0.0060, 'HE63S/100A');
INSERT INTO `mt_slideway` VALUES (4, 'RUE-35-E', NULL, 'G2', 35, 1770, '20', '31', '700', 'W2', 59000, 140000, 'V3', 0.010, 0.0060, NULL);
INSERT INTO `mt_slideway` VALUES (5, 'RUE-35-E-L', NULL, 'G2', 35, 1720, '20', '31', '750', 'W2', 70000, 175000, 'V3', 0.001, 0.0060, NULL);
INSERT INTO `mt_slideway` VALUES (6, 'RUE-45-E', NULL, 'G2', 45, 1540, '30', '30', '800', 'W2', 92000, 215000, 'V3', 0.010, 0.0060, NULL);
INSERT INTO `mt_slideway` VALUES (7, 'RUE-45-E-L', NULL, 'G2', 45, 1510, '50', '20', '650', 'W2', 115000, 275000, 'V3', 0.010, 0.0060, NULL);

-- ----------------------------
-- Table structure for mt_updfresult
-- ----------------------------
DROP TABLE IF EXISTS `mt_updfresult`;
CREATE TABLE `mt_updfresult`  (
  `zj_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `df_date` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`zj_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_updfresult
-- ----------------------------
INSERT INTO `mt_updfresult` VALUES (1, 'zj-admin', '2020-06-28');
INSERT INTO `mt_updfresult` VALUES (2, 'zj-002', '2020-06-28');

-- ----------------------------
-- Table structure for mt_upright
-- ----------------------------
DROP TABLE IF EXISTS `mt_upright`;
CREATE TABLE `mt_upright`  (
  `upright_id` int(11) NOT NULL AUTO_INCREMENT,
  `up_daihao` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `structure` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `material` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sidingthick` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `shangdi` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `xiadi` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `kuangdu` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `yRoute` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `height` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `diheight` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rail_distance` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `reducehole` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cehole` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `dinghole` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `rib` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hengxiang` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `shuxiang` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ribthinck` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`upright_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_upright
-- ----------------------------
INSERT INTO `mt_upright` VALUES (1, 'HE63S-upcolumn', '双柱对称框架式', 'HT300', '200', '575.0', '917', '920', '850', '2060', '206', '640', '10/单侧,7/顶部', '120', '70', '对称式纵横环向筋', '5环筋', '2环筋', '20', 'HE63S');
INSERT INTO `mt_upright` VALUES (2, 'HM80TD-upcolumn', '双柱对称框架式', 'HT300', '200', '580.0', '940', '940', '1050', '2262', '215', '645', '2/单侧，4/顶部', '70', '70', '对称式纵横环向筋', '6环筋', '2环筋', '20', 'HM80TD');
INSERT INTO `mt_upright` VALUES (3, 'HM50TS-upcolumn', '双柱对称框架式', 'HT300', '200', '560.0', '880', '920', '650', '1862', '200', '640', '2/单侧，4/顶部', '70', '70', '对称式纵横环向筋', '4环筋', '2环筋', '20', 'HM50TS');
INSERT INTO `mt_upright` VALUES (4, 'HM100TL-upcolumn', '双柱对称框架式', 'HT300', '250', '590.0', '950', '955', '1300', '2515', '220', '650', '4/单侧，4/顶部', '75', '75', '对称式纵横环向筋', '7环筋', '2环筋', '20', 'HM100TL');
INSERT INTO `mt_upright` VALUES (5, 'HM125TS-upcolumn', '双柱对称框架式', 'HT300', '250', '600.0', '970', '980', '1500', '2720', '240', '650', '5/单侧，2/顶部', '75', '75', '对称式纵横环向筋', '8环筋', '2环筋', '20', 'HM125TS');
INSERT INTO `mt_upright` VALUES (6, 'HE100A-upcolumn', '双柱对称框架式', 'HT300', '200', '575', '917', '920', '850', '2060', '206', '640', '10/单侧,7/顶部', '120', '70', '对称式纵横环向筋', '5环筋', '2环筋', '20', 'HE100A');

-- ----------------------------
-- Table structure for mt_upweight
-- ----------------------------
DROP TABLE IF EXISTS `mt_upweight`;
CREATE TABLE `mt_upweight`  (
  `wid` int(11) NOT NULL AUTO_INCREMENT,
  `Height` double(11, 2) NOT NULL,
  `Kuang` double(11, 2) NOT NULL,
  `yroute` double(11, 2) NOT NULL,
  `sdWidth` double(11, 2) NOT NULL,
  `sdHeight` double(11, 2) NOT NULL,
  `sideThick` double(11, 2) NOT NULL,
  `hole` double(11, 2) NOT NULL,
  `ribThick` double(11, 2) NOT NULL,
  `hNumber` double(11, 2) NOT NULL,
  `sNumber` double(11, 2) NOT NULL,
  PRIMARY KEY (`wid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_upweight
-- ----------------------------
INSERT INTO `mt_upweight` VALUES (1, 0.22, 0.20, 0.16, 0.12, 0.10, 0.10, 0.03, 0.03, 0.02, 0.02);

-- ----------------------------
-- Table structure for mt_upwgt_df
-- ----------------------------
DROP TABLE IF EXISTS `mt_upwgt_df`;
CREATE TABLE `mt_upwgt_df`  (
  `dfid` int(11) NOT NULL AUTO_INCREMENT,
  `Height` int(11) NOT NULL,
  `Kuang` int(11) NOT NULL,
  `yroute` int(11) NOT NULL,
  `sdWidth` int(11) NOT NULL,
  `sdHeight` int(11) NOT NULL,
  `sideThick` int(11) NOT NULL,
  `hole` int(11) NOT NULL,
  `ribThick` int(11) NOT NULL,
  `hNumber` int(11) NOT NULL,
  `sNumber` int(11) NOT NULL,
  PRIMARY KEY (`dfid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_upwgt_df
-- ----------------------------
INSERT INTO `mt_upwgt_df` VALUES (1, 1, 2, 4, 4, 5, 6, 7, 8, 8, 8);
INSERT INTO `mt_upwgt_df` VALUES (2, 0, 1, 4, 5, 5, 7, 8, 8, 8, 8);
INSERT INTO `mt_upwgt_df` VALUES (3, 0, 0, 1, 2, 3, 5, 6, 7, 6, 7);
INSERT INTO `mt_upwgt_df` VALUES (4, 0, 0, 0, 1, 2, 2, 4, 6, 5, 6);
INSERT INTO `mt_upwgt_df` VALUES (5, 0, 0, 0, 0, 1, 2, 4, 5, 6, 6);
INSERT INTO `mt_upwgt_df` VALUES (6, 0, 0, 0, 0, 0, 1, 5, 5, 6, 6);
INSERT INTO `mt_upwgt_df` VALUES (7, 0, 0, 0, 0, 0, 0, 1, 2, 3, 3);
INSERT INTO `mt_upwgt_df` VALUES (8, 0, 0, 0, 0, 0, 0, 0, 1, 4, 4);
INSERT INTO `mt_upwgt_df` VALUES (9, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2);
INSERT INTO `mt_upwgt_df` VALUES (10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
INSERT INTO `mt_upwgt_df` VALUES (11, 1, 3, 5, 5, 6, 7, 8, 8, 8, 8);
INSERT INTO `mt_upwgt_df` VALUES (12, 0, 1, 5, 5, 5, 6, 8, 8, 8, 8);
INSERT INTO `mt_upwgt_df` VALUES (13, 0, 0, 1, 4, 4, 5, 6, 6, 6, 7);
INSERT INTO `mt_upwgt_df` VALUES (14, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7);
INSERT INTO `mt_upwgt_df` VALUES (15, 0, 0, 0, 0, 1, 2, 3, 5, 6, 5);
INSERT INTO `mt_upwgt_df` VALUES (16, 0, 0, 0, 0, 0, 1, 4, 4, 4, 4);
INSERT INTO `mt_upwgt_df` VALUES (17, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2);
INSERT INTO `mt_upwgt_df` VALUES (18, 0, 0, 0, 0, 0, 0, 0, 1, 3, 3);
INSERT INTO `mt_upwgt_df` VALUES (19, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2);
INSERT INTO `mt_upwgt_df` VALUES (20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);

-- ----------------------------
-- Table structure for mt_whole
-- ----------------------------
DROP TABLE IF EXISTS `mt_whole`;
CREATE TABLE `mt_whole`  (
  `mt_id` int(11) NOT NULL AUTO_INCREMENT,
  `mt_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mt_wp` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mt_spindle` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mt_atc` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mt_screw` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mt_slideway` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mt_upright` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `mt_workbed` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`mt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_whole
-- ----------------------------
INSERT INTO `mt_whole` VALUES (1, 'HE63A', 'HMC-630ZH9', '罗翌RB5169', 'HE63S-tool', 'NSK/EM5020-6E', 'RUE-55-E', 'HE63S-upcolumn', 'HE63S-body');
INSERT INTO `mt_whole` VALUES (2, 'HE100A', 'NC-RUNDTISCH', 'DMS 132.80-697.089', 'HE100A-tool', 'NSK/EM5020-6E', 'RUE-55-E', 'HE100A-upcolumn', 'HE100A-body');

-- ----------------------------
-- Table structure for mt_workpiece
-- ----------------------------
DROP TABLE IF EXISTS `mt_workpiece`;
CREATE TABLE `mt_workpiece`  (
  `workid` int(30) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `zhuantai_maxd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `zhuantai_h` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gongjian_maxd` int(20) NULL DEFAULT NULL,
  `gongjian_h` int(20) NULL DEFAULT NULL,
  `over_height` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `fendu` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `re_ratio` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `zaihe` int(20) NULL DEFAULT NULL,
  `max_speed` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cutting_f` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`workid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_workpiece
-- ----------------------------
INSERT INTO `mt_workpiece` VALUES (1, 'HMC-630ZH9', '630', '630', 1000, 1000, '330', '0.001/1', '1:90', 1200, '30', '380', 'HE63S');
INSERT INTO `mt_workpiece` VALUES (2, 'NC-RUNDTISCH', '1000', '500', 1000, 550, '330', '0.001/1', '1:120', 1800, '16', '500', 'HE100A');
INSERT INTO `mt_workpiece` VALUES (3, 'HM50T', '500', '500', 630, 700, '340', '0.001/1', '1:80', 600, '10', '400', 'HM50TS');
INSERT INTO `mt_workpiece` VALUES (4, 'HM80TD', '800', '800', 1200, 1200, '350', '0.001/1', '1:60', 1600, '10', '450', 'HM80TD');
INSERT INTO `mt_workpiece` VALUES (5, 'HM125T', '1250', '1250', 2000, 2000, '350', '0.001/1', '1:80', 4000, '5.5', '600', 'HM125TS');
INSERT INTO `mt_workpiece` VALUES (6, 'HM50VS', '500', '500', 800, 800, '330', '0.001/1', '1:90', 600, '10', '400', 'HM50VS');
INSERT INTO `mt_workpiece` VALUES (7, 'HM100T/V', '1000', '1000', 1300, 1300, '350', '0.001/1', '1:120', 2000, '10', '450', 'HM100V');

-- ----------------------------
-- Table structure for mt_zhuzhou
-- ----------------------------
DROP TABLE IF EXISTS `mt_zhuzhou`;
CREATE TABLE `mt_zhuzhou`  (
  `sindle_id` int(11) NOT NULL AUTO_INCREMENT,
  `sd_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `kinds` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `power_min` double(30, 0) NULL DEFAULT NULL,
  `power_max` double(30, 0) NULL DEFAULT NULL,
  `power` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `max_speed` int(11) NULL DEFAULT NULL,
  `torsion` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `zhuikong` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `x_length` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `y_length` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `duanmian_zhuantai` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `zhongxin_min` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `zhongxin_max` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `zhongxin` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sindle_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mt_zhuzhou
-- ----------------------------
INSERT INTO `mt_zhuzhou` VALUES (1, '罗翌RB5169', '机械主轴', 22, 26, '22/26', 6000, '770/910', 'HSK-A100', '750', '580', '120-1120', '140', '990', 'HE63S/100A', '140/990');
INSERT INTO `mt_zhuzhou` VALUES (2, 'DMS 132.80-697.089', '电主轴', 50, 65, '50/65', 8000, '405/526', 'HSK-A100', '750', '580', '120-1120', '-425', '+425', 'HE63S/100A', '-425/425');
INSERT INTO `mt_zhuzhou` VALUES (3, 'HM50T', '机械主轴', 11, 15, '11/15', 8000, '140/191', '7:24锥度NO.40', '740', '580', '150-800', '120', '770', 'HM50TS', '120/770');
INSERT INTO `mt_zhuzhou` VALUES (4, 'HM63T', '机械主轴', 19, 22, '18.8/22', 4500, '647/770', '7:24锥度NO.50', '760', '590', '200-1100', '100', '950', 'HM63TS', '100/950');
INSERT INTO `mt_zhuzhou` VALUES (5, 'HM80T', '机械主轴', 22, 26, '22/26', 4500, '770/910', '7:24锥度NO.50', '750', '580', '250-1300', '120', '1170', 'HM80TS', '120/1170');
INSERT INTO `mt_zhuzhou` VALUES (6, 'HM50V', '机械主轴', 11, 15, '11/15', 10000, '70/95.4', '7:24锥度NO.40', '750', '600', '140-940', '50', '800', 'HM50VS', '50/800');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `userNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pwd` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` int(11) NOT NULL,
  `job` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `decription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'JS1605020', 'admin', 'admin', 'admin', 40, '技术部', '12345678910', '系统管理员');
INSERT INTO `user_info` VALUES (2, 'YF1405230', 'wang', 'wang', 'manager', 32, '研发部', '12345678910', '管理员');
INSERT INTO `user_info` VALUES (3, 'SC1642030', 'wang2', 'wang2', 'design', 26, '市场部', '12345678910', '设计员');
INSERT INTO `user_info` VALUES (4, 'GL0804050', 'admin2', 'admin2', 'admin', 28, '管理部', '12345678910', '系统管理员');
INSERT INTO `user_info` VALUES (5, 'JS0805084', 'admin1', 'admin1', 'design', 30, '技术部', '12345678910', '设计员');
INSERT INTO `user_info` VALUES (6, 'SH0704030', 'wang33', '111111', 'manager', 35, '市场部', '1234567890', 'manager');
INSERT INTO `user_info` VALUES (7, 'SX1805020', 'admiec', '111111', 'design', 20, '市场部', '11111111111', 'design');

-- ----------------------------
-- Table structure for user_test
-- ----------------------------
DROP TABLE IF EXISTS `user_test`;
CREATE TABLE `user_test`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `age` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` int(11) NOT NULL,
  `did` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_test
-- ----------------------------
INSERT INTO `user_test` VALUES (1, 'admin', 'admin', '24', 0, 1);
INSERT INTO `user_test` VALUES (2, 'admin1', 'admin1', '20', 1, 2);
INSERT INTO `user_test` VALUES (3, 'wang3', 'wang2', '20', 1, 2);
INSERT INTO `user_test` VALUES (4, 'yuk', 'kuk', 'kiu', 1, 2);
INSERT INTO `user_test` VALUES (5, 'hhu', 'kui', 'oo', 1, 1);

SET FOREIGN_KEY_CHECKS = 1;

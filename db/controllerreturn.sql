/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.108
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 192.168.1.108:3306
 Source Schema         : controllerreturn

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 28/04/2022 11:28:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tyzw_js_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_js_return`;
CREATE TABLE `tyzw_js_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题   ',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'RTC校时返回结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_add_students_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_add_students_return`;
CREATE TABLE `tyzw_scppro_add_students_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `devCOUNT` int(0) NULL DEFAULT NULL COMMENT '下发教师信息的个数',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '添加学生卡信息返回信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_add_teacher_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_add_teacher_return`;
CREATE TABLE `tyzw_scppro_add_teacher_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `devCOUNT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发卡数量',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '添加教师卡信息结果返回' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_card_log_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_card_log_return`;
CREATE TABLE `tyzw_scppro_card_log_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `rfidNumber` int(0) NULL DEFAULT NULL COMMENT '卡号',
  `carType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师、学生、无效卡',
  `devInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备ID',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正常、非预约时间段',
  `payCardTime` datetime(0) NULL DEFAULT NULL COMMENT '刷卡时间',
  `devstatus` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老师和无效卡显示空 设备状态（有效卡0表示关闭，1表示打卡，无效卡2，未到点3，设备异常4）',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '刷卡记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_ctl_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_ctl_return`;
CREATE TABLE `tyzw_scppro_ctl_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `status` int(0) NULL DEFAULT NULL COMMENT '#0表示关，1表示开',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 929 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备控制' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_dev_info_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_dev_info_return`;
CREATE TABLE `tyzw_scppro_dev_info_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `devInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备信息显示',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备信息获取' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_energy_clear_zero_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_energy_clear_zero_return`;
CREATE TABLE `tyzw_scppro_energy_clear_zero_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电表清零功能返回结果' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_energyclearzero_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_energyclearzero_return`;
CREATE TABLE `tyzw_scppro_energyclearzero_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varbinary(255) NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电表清零功能返回结果' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_modify_name_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_modify_name_return`;
CREATE TABLE `tyzw_scppro_modify_name_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备更名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_modifyname_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_modifyname_return`;
CREATE TABLE `tyzw_scppro_modifyname_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备更名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_query_status_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_query_status_return`;
CREATE TABLE `tyzw_scppro_query_status_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `isOnline` int(0) NULL DEFAULT NULL COMMENT '在线状态0离线1在线',
  `status` int(0) NULL DEFAULT NULL COMMENT '开关状态',
  `current` double NULL DEFAULT NULL COMMENT '电流值',
  `voltage` double NULL DEFAULT NULL COMMENT '电压值',
  `power` double NULL DEFAULT NULL COMMENT '有功功率',
  `energy` double NULL DEFAULT NULL COMMENT '有功总电能',
  `powerFacto` double(11, 0) NULL DEFAULT NULL COMMENT '功率因素  ',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 583 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '服务器要状态，电压，电量等信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_querystatus_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_querystatus_return`;
CREATE TABLE `tyzw_scppro_querystatus_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `isOnline` int(0) NULL DEFAULT NULL COMMENT '在线状态0离线1在线',
  `status` int(0) NULL DEFAULT NULL COMMENT '开关状态',
  `current` int(0) NULL DEFAULT NULL COMMENT '电流值',
  `voltage` int(0) NULL DEFAULT NULL COMMENT '电压值',
  `power` int(0) NULL DEFAULT NULL COMMENT '有功功率',
  `energy` int(0) NULL DEFAULT NULL COMMENT '有功总电能',
  `powerFacto` double(11, 0) NULL DEFAULT NULL COMMENT '功率因素  ',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '服务器要状态，电压，电量等信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_status_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_status_return`;
CREATE TABLE `tyzw_scppro_status_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明此topic的类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '发布topic的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '#0代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1240 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备状态' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tyzw_scppro_time_info_return
-- ----------------------------
DROP TABLE IF EXISTS `tyzw_scppro_time_info_return`;
CREATE TABLE `tyzw_scppro_time_info_return`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `commandID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID转换的唯一数据，不能重复，此字段用字符串表示',
  `IP` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的ip地址，不能ip冲突，底层可修改',
  `devId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备的唯一识别码，服务器识别的唯一标准',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `dateTime` datetime(0) NULL DEFAULT NULL COMMENT '设备的时间',
  `retStatus` int(0) NULL DEFAULT NULL COMMENT '代表命令解析正确，1表示命令解析错误，对接有协议问题',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '服务器查询设备时间' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

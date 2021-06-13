/*
 Navicat Premium Data Transfer

 Source Server         : mybatis-study
 Source Server Type    : MySQL
 Source Server Version : 50523
 Source Host           : localhost:3307
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 50523
 File Encoding         : 65001

 Date: 13/06/2021 17:30:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for perm
-- ----------------------------
DROP TABLE IF EXISTS `perm`;
CREATE TABLE `perm`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `PERM_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'PERM_NAME',
  `PERM_KEY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'PERM_KEY',
  `DESCRIPTION` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'DESCRIPTION',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'PERM ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ROLE_NAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ROLE_NAME',
  `ROLE_KEY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ROLE_KEY',
  `DESCRIPTION` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'DESCRIPTION',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'ROLE ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role_perm
-- ----------------------------
DROP TABLE IF EXISTS `role_perm`;
CREATE TABLE `role_perm`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `RID` bigint(20) NOT NULL COMMENT 'RID',
  `PID` bigint(20) NOT NULL COMMENT 'PID',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'ROLE_PERM ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `PHONE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'PHONE',
  `PASSWORD` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'PASSWORD',
  `SALT` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'SALT',
  `USERNAME` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'USERNAME',
  `MAIL` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'MAIL',
  `GENDER` tinyint(1) DEFAULT NULL COMMENT 'GENDER',
  `REVISION` int(11) DEFAULT 0 COMMENT '乐观锁',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `un_user_phone`(`PHONE`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = ' ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_perm
-- ----------------------------
DROP TABLE IF EXISTS `user_perm`;
CREATE TABLE `user_perm`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `UID` bigint(20) NOT NULL COMMENT 'UID',
  `PID` bigint(20) NOT NULL COMMENT 'PID',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'USER_PERM ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `UID` bigint(20) NOT NULL COMMENT 'UID',
  `RID` bigint(20) NOT NULL COMMENT 'RID',
  `REVISION` int(11) DEFAULT NULL COMMENT '乐观锁',
  `CREATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATED_BY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'USER_ROLE ' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

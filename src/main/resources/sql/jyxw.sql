/*
 Navicat Premium Data Transfer

 Source Server         : 111
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 06/06/2019 21:16:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jyxw
-- ----------------------------
DROP TABLE IF EXISTS `jyxw`;
CREATE TABLE `jyxw`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `JJMC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JJJC` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JJZDM` int(8) NOT NULL,
  `QSname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `QSnum` int(3) NULL DEFAULT NULL,
  `GPJE` double(20, 0) NULL DEFAULT NULL,
  `GPJEZB` double(5, 0) NULL DEFAULT NULL,
  `QSJE` double(20, 0) NULL DEFAULT NULL,
  `QSJEZB` double(5, 0) NULL DEFAULT NULL,
  `ZQJE` double(20, 0) NULL DEFAULT NULL,
  `ZQJEZB` double(5, 0) NULL DEFAULT NULL,
  `HGJE` double(20, 0) NULL DEFAULT NULL,
  `HGJEZB` double(5, 0) NULL DEFAULT NULL,
  `QZJE` double(20, 0) NULL DEFAULT NULL,
  `QZJEZB` double(5, 0) NULL DEFAULT NULL,
  `JJJE` double(20, 0) NULL DEFAULT NULL,
  `JJJEZB` double(5, 0) NULL DEFAULT NULL,
  `QHJE` double(20, 0) NULL DEFAULT NULL,
  `QHJEZB` double(5, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;



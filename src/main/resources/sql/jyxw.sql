/*
Navicat MySQL Data Transfer

Source Server         : lum
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-06-10 00:08:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jyxw
-- ----------------------------
DROP TABLE IF EXISTS `jyxw`;
CREATE TABLE `jyxw` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `JJMC` varchar(255) DEFAULT NULL,
  `JJJC` varchar(255) DEFAULT NULL,
  `JJZDM` varchar(8) NOT NULL,
  `JZRQ` varchar(255) NOT NULL,
  `NUM` int(3) NOT NULL,
  `QSname` varchar(255) NOT NULL,
  `QSnum` int(3) DEFAULT NULL,
  `GPJE` double(20,2) DEFAULT NULL,
  `GPJEZB` double(5,2) DEFAULT NULL,
  `QSJE` double(20,2) DEFAULT NULL,
  `QSJEZB` double(5,2) DEFAULT NULL,
  `ZQJE` double(20,2) DEFAULT NULL,
  `ZQJEZB` double(5,2) DEFAULT NULL,
  `HGJE` double(20,2) DEFAULT NULL,
  `HGJEZB` double(5,2) DEFAULT NULL,
  `QZJE` double(20,2) DEFAULT NULL,
  `QZJEZB` double(5,2) DEFAULT NULL,
  `JJJE` double(20,2) DEFAULT NULL,
  `JJJEZB` double(5,2) DEFAULT NULL,
  `QHJE` double(20,2) DEFAULT NULL,
  `QHJEZB` double(5,2) DEFAULT NULL,
  `XGSJ` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`,`JJZDM`,`QSname`)
) ENGINE=InnoDB AUTO_INCREMENT=7140 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

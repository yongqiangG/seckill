/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2019-11-29 09:00:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `seckill`
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL auto_increment COMMENT '秒杀商品ID',
  `name` varchar(120) NOT NULL COMMENT '秒杀商品名称',
  `number` int(11) NOT NULL COMMENT '秒杀商品数量',
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '记录生成时间',
  `start_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL default '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  PRIMARY KEY  (`seckill_id`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_start_time` (`start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='商品秒杀表';

-- ----------------------------
-- Records of seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('1000', '1000秒杀电视', '99', '2019-11-22 10:12:03', '2019-11-11 00:00:00', '2019-11-12 00:00:00');
INSERT INTO `seckill` VALUES ('1001', '2000秒杀冰箱', '200', '2019-11-22 10:12:03', '2019-11-11 00:00:00', '2019-11-12 00:00:00');
INSERT INTO `seckill` VALUES ('1002', '3000秒杀笔记本电脑', '300', '2019-11-22 10:12:03', '2019-11-11 00:00:00', '2019-11-12 00:00:00');
INSERT INTO `seckill` VALUES ('1003', '100秒杀耳机', '400', '2019-11-22 10:12:03', '2019-11-11 00:00:00', '2019-11-12 00:00:00');

-- ----------------------------
-- Table structure for `seckill_test`
-- ----------------------------
DROP TABLE IF EXISTS `seckill_test`;
CREATE TABLE `seckill_test` (
  `id` bigint(20) NOT NULL auto_increment COMMENT 'ID',
  `name` varchar(120) NOT NULL COMMENT '名称',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='测试表';

-- ----------------------------
-- Records of seckill_test
-- ----------------------------

-- ----------------------------
-- Table structure for `success_killed`
-- ----------------------------
DROP TABLE IF EXISTS `success_killed`;
CREATE TABLE `success_killed` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品ID',
  `user_phone` bigint(20) NOT NULL COMMENT '秒杀用户的手机号码',
  `state` tinyint(4) NOT NULL default '-1' COMMENT '秒杀状态:-1:无效，0：成功，1：已付款，2：已发货',
  `create_time` timestamp NOT NULL default CURRENT_TIMESTAMP COMMENT '记录生成时间',
  PRIMARY KEY  (`seckill_id`,`user_phone`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

-- ----------------------------
-- Records of success_killed
-- ----------------------------

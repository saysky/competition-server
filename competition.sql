/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50643
 Source Host           : localhost:3306
 Source Schema         : competition

 Target Server Type    : MySQL
 Target Server Version : 50643
 File Encoding         : 65001

 Date: 23/05/2020 13:41:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `competition_id` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES ('402888397227b25f017227be21770001', '2020-05-18 12:24:31', '2020-05-18 12:24:31', '402888397227b25f017227b3d92e0000', 'haha', 'admin', '-1');
INSERT INTO `comment` VALUES ('402888397227b25f017227c207170002', '2020-05-18 12:28:47', '2020-05-18 12:28:47', '402888397227b25f017227b3d92e0000', '2222', 'admin', '-1');
INSERT INTO `comment` VALUES ('402888397227c438017227c4bafa0000', '2020-05-18 12:31:44', '2020-05-18 12:31:44', '402888397227b25f017227b3d92e0000', '222222', 'admin', '-1');
INSERT INTO `comment` VALUES ('402888397227c438017227c523670001', '2020-05-18 12:32:11', '2020-05-18 12:32:11', '402888397227b25f017227b3d92e0000', '2222', 'admin', '-1');
INSERT INTO `comment` VALUES ('402888397227c438017227c5b51f0002', '2020-05-18 12:32:48', '2020-05-18 12:32:48', '402888397227b25f017227b3d92e0000', '222', 'admin', '-1');
INSERT INTO `comment` VALUES ('402888397227de07017227e9f18d0000', '2020-05-18 13:12:23', '2020-05-18 13:12:23', '402888397227b25f017227b3d92e0000', '666666', 'admin', '402888397227c438017227c5b51f0002');
INSERT INTO `comment` VALUES ('402888397227eba8017227ec38fd0000', '2020-05-18 13:14:52', '2020-05-18 13:14:52', '402888397227b25f017227b3d92e0000', '77', 'admin', '402888397227c438017227c5b51f0002');
INSERT INTO `comment` VALUES ('402888397227eba8017227ec54e80001', '2020-05-18 13:14:59', '2020-05-18 13:14:59', '402888397227b25f017227b3d92e0000', 'ok', 'admin', '402888397227c438017227c4bafa0000');
INSERT INTO `comment` VALUES ('402888397227eba8017227ec73890002', '2020-05-18 13:15:07', '2020-05-18 13:15:07', '402888397227b25f017227b3d92e0000', 'ahah', 'admin', '402888397227b25f017227be21770001');
INSERT INTO `comment` VALUES ('4028883972284522017228462a240000', '2020-05-18 14:53:06', '2020-05-18 14:53:06', '402888397227b25f017227b3d92e0000', '111', 'admin', '-1');
COMMIT;

-- ----------------------------
-- Table structure for competition
-- ----------------------------
DROP TABLE IF EXISTS `competition`;
CREATE TABLE `competition` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `grade_num` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `qualifications` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competition
-- ----------------------------
BEGIN;
INSERT INTO `competition` VALUES ('402888397227b25f017227b3d92e0000', '2020-05-18 12:13:17', '2020-05-18 12:13:17', '2020-05-18 12:13:17', '2016级', '111', '111', '竞赛测试');
COMMIT;

-- ----------------------------
-- Table structure for counselor
-- ----------------------------
DROP TABLE IF EXISTS `counselor`;
CREATE TABLE `counselor` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `counselor_id` varchar(255) DEFAULT NULL,
  `gender` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of counselor
-- ----------------------------
BEGIN;
INSERT INTO `counselor` VALUES ('40288839723ffa6501723ffd0d020001', '2020-05-23 05:24:08', '2020-05-23 05:24:08', '计算机学院', '360123198912111111', '10000', b'1', '李世民', '13567892358');
COMMIT;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_user_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for plan_file
-- ----------------------------
DROP TABLE IF EXISTS `plan_file`;
CREATE TABLE `plan_file` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `extra_id` varchar(255) DEFAULT NULL,
  `file` longblob,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `competition_id` varchar(255) DEFAULT NULL,
  `competition_name` varchar(255) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `prize` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of review
-- ----------------------------
BEGIN;
INSERT INTO `review` VALUES ('402888397227eba8017227f339e30007', '2020-05-18 13:22:00', '2020-05-18 13:25:20', '402888397227b25f017227b3d92e0000', '竞赛测试', NULL, '二等奖', '计算机科学与技术', 1, '20201212', '402888397227eba8017227eeee170004', '王小明');
INSERT INTO `review` VALUES ('402888397227eba8017227f51514000e', '2020-05-18 13:24:00', '2020-05-18 13:25:23', '402888397227b25f017227b3d92e0000', '竞赛测试', NULL, '三等奖', '1', 1, '1', '402888397227eba8017227f3e6940009', '1');
INSERT INTO `review` VALUES ('402888397227eba8017227f532ee000f', '2020-05-18 13:24:00', '2020-05-18 13:25:11', '402888397227b25f017227b3d92e0000', '竞赛测试', NULL, NULL, '2', 1, '2', '402888397227eba8017227f473b6000b', '2');
INSERT INTO `review` VALUES ('402888397227eba8017227f54f890010', '2020-05-18 13:24:00', '2020-05-18 13:25:18', '402888397227b25f017227b3d92e0000', '竞赛测试', NULL, '一等奖', '3', 1, '3', '402888397227eba8017227f4ed17000d', '3');
INSERT INTO `review` VALUES ('402888397227eba801722816ea330013', '2020-05-18 14:01:30', '2020-05-18 14:01:30', '402888397227b25f017227b3d92e0000', '竞赛测试', NULL, NULL, '1', 0, '6', '402888397227eba801722816b8370012', '6');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `class_num` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `college` varchar(255) DEFAULT NULL,
  `gender` bit(1) NOT NULL,
  `grade_num` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('402888397227eba8017227eeee170004', '2020-05-18 13:17:49', '2020-05-18 13:17:49', '005', '361212199912123231', '计算机学院', b'1', '2016级', '王小明', '15712341231', '计算机科学与技术', '20201212');
INSERT INTO `student` VALUES ('402888397227eba8017227f02ef40006', '2020-05-18 13:19:12', '2020-05-18 13:19:12', '008', '112312312312312312', '22', b'1', '2016级', '22', '13412341232', '22', '22');
INSERT INTO `student` VALUES ('402888397227eba8017227eeee170008', '2020-05-18 13:17:49', '2020-05-18 13:17:49', '005', '361212199912123231', '计算机学院', b'1', '2016级', '王小明', '15712341233', '计算机科学与技术', '20201211');
INSERT INTO `student` VALUES ('402888397227eba8017227f02ef40007', '2020-05-18 13:19:12', '2020-05-18 13:19:12', '008', '112312312312312312', '22', b'1', '2016级', '22', '13412341234', '22', '11');
INSERT INTO `student` VALUES ('402888397227eba8017227f3e6940009', '2020-05-18 13:23:15', '2020-05-18 13:23:15', '001', '123456789012345678', '1', b'1', '2016级', '1', '12345612345', '1', '1');
INSERT INTO `student` VALUES ('402888397227eba8017227f473b6000b', '2020-05-18 13:23:51', '2020-05-18 13:23:51', '002', '123456789123456780', '2', b'1', '2016级', '2', '13456789098', '2', '2');
INSERT INTO `student` VALUES ('402888397227eba8017227f4ed17000d', '2020-05-18 13:24:22', '2020-05-18 13:24:22', '007', '123123123123123451', '3', b'1', '2016级', '3', '12345612348', '3', '3');
INSERT INTO `student` VALUES ('402888397227eba801722816b8370012', '2020-05-18 14:01:17', '2020-05-18 14:01:17', '002', '123412341234123456', '1', b'1', '2016级', '6', '12345123451', '1', '6');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `mark` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pass_word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('402888397227a243017227a2488a0000', '2020-05-18 11:54:00', '2020-05-18 12:07:25', 2, 'admin', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('402888397227a243017227af1ae50001', '2020-05-18 12:08:00', '2020-05-18 12:11:54', 0, 'abc', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('402888397227eba8017227eeee0c0003', '2020-05-18 13:17:49', '2020-05-18 13:17:49', 0, '20201212', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('402888397227eba8017227f02ef30005', '2020-05-18 13:19:12', '2020-05-18 13:19:12', 0, '22', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('402888397227eba8017227f3e6930008', '2020-05-18 13:23:15', '2020-05-18 13:23:15', 0, '1', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('402888397227eba8017227f473b5000a', '2020-05-18 13:23:51', '2020-05-18 13:23:51', 0, '2', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('402888397227eba8017227f4ed15000c', '2020-05-18 13:24:22', '2020-05-18 13:24:22', 0, '3', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('402888397227eba801722816b8350011', '2020-05-18 14:01:17', '2020-05-18 14:01:17', 0, '6', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES ('40288839723ffa6501723ffd0cf10000', '2020-05-23 05:24:08', '2020-05-23 05:24:08', 1, '10000', 'e10adc3949ba59abbe56e057f20f883e');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

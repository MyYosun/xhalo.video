/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : video

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-03-14 10:47:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category_table
-- ----------------------------
DROP TABLE IF EXISTS `category_table`;
CREATE TABLE `category_table` (
  `id` bigint(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `belong_to_other` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_table
-- ----------------------------
INSERT INTO `category_table` VALUES ('1', 'Short Video', '0');
INSERT INTO `category_table` VALUES ('2', 'Movie', '0');
INSERT INTO `category_table` VALUES ('3', 'Music Video', '0');
INSERT INTO `category_table` VALUES ('4', 'Cartoon', '1');
INSERT INTO `category_table` VALUES ('5', 'News', '1');
INSERT INTO `category_table` VALUES ('6', 'Funny Video', '1');
INSERT INTO `category_table` VALUES ('7', 'Edu Video', '1');
INSERT INTO `category_table` VALUES ('8', 'Tech Video', '1');
INSERT INTO `category_table` VALUES ('9', 'Others', '1');

-- ----------------------------
-- Table structure for category_table_copy
-- ----------------------------
DROP TABLE IF EXISTS `category_table_copy`;
CREATE TABLE `category_table_copy` (
  `id` bigint(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `belong_to_other` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_table_copy
-- ----------------------------
INSERT INTO `category_table_copy` VALUES ('1', 'Short Video', '0');
INSERT INTO `category_table_copy` VALUES ('2', 'Movie', '0');
INSERT INTO `category_table_copy` VALUES ('3', 'Music Video', '0');
INSERT INTO `category_table_copy` VALUES ('4', 'Cartoon', '1');
INSERT INTO `category_table_copy` VALUES ('5', 'News', '1');
INSERT INTO `category_table_copy` VALUES ('6', 'Funny Video', '1');
INSERT INTO `category_table_copy` VALUES ('7', 'Edu Video', '1');
INSERT INTO `category_table_copy` VALUES ('8', 'Tech Video', '1');
INSERT INTO `category_table_copy` VALUES ('9', 'Others', '1');

-- ----------------------------
-- Table structure for user_like_video_table
-- ----------------------------
DROP TABLE IF EXISTS `user_like_video_table`;
CREATE TABLE `user_like_video_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `video_id` bigint(20) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1191 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_like_video_table
-- ----------------------------
INSERT INTO `user_like_video_table` VALUES ('1190', '1', '65', '2018-03-13 17:35:38');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `authority` varchar(20) DEFAULT '',
  `sign` varchar(50) DEFAULT NULL,
  `head_img` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_login_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES ('1', 'root', '{MD5}fc7fc678608590b123692867f176fe63', 'Administrator', '1', 'admin', '此人很懒，还没有签名~', 'root.png', '2018-03-13 16:28:59', '2018-03-13 16:28:59');
INSERT INTO `user_table` VALUES ('3', 'Yosun', '{bcrypt}$2a$10$Ppn9M7DOLGcX7VAey3nZHe9dp3ZGaXQvwIm5AoHn54umG6cRYs18q', 'MyYosuns', '1', 'admin', 'MyYosun', 'Yosun.png', '2018-03-14 10:05:25', '2018-03-14 10:05:25');
INSERT INTO `user_table` VALUES ('14', 'liqing', '{MD5}a280671d12c5ed49fc44f09e5deeb99d', '李庆', '1', 'user', '此人很懒，还没有签名~', 'liqing.png', '2018-03-09 15:42:08', '2018-03-09 15:42:08');
INSERT INTO `user_table` VALUES ('16', 'Yosuns', '{bcrypt}$2a$10$tHvAh0COpB9eVswcojvn/OdjKiavjYyWWCy5ZRyl/meBSpjGuHWSq', 'Yosuns', '1', 'user', '此人很懒，还没有签名~', 'Yosuns.png', '2018-03-09 15:42:13', '2018-03-09 15:42:13');
INSERT INTO `user_table` VALUES ('18', 'Yosunss', '{bcrypt}$2a$10$nwvGxlbCgZ/ok8IrRTLpGOkvRskQniZJ8ZJn8kRlvN8m59UnZvJvO', 'Yosunss', '1', 'user', '此人很懒，还没有签名~', 'Yosunss.png', '2018-03-09 15:42:18', '2018-03-09 15:42:18');
INSERT INTO `user_table` VALUES ('20', 'TestYosun', '{bcrypt}$2a$10$.Yh3O25/srlllC6AvKCiVOvklKFZtPQ3fQDVajIvXsO7vqXSb2AHK', 'TestYosun', '1', 'user', '此人很懒，还没有签名~', 'TestYosun.png', '2018-03-09 15:42:22', '2018-03-09 15:42:22');
INSERT INTO `user_table` VALUES ('24', 'changle', '{bcrypt}$2a$10$jwNVw2ahWBPBKtQLnaT67eG1IIxMfRmpwY66Kmn1NLPE2Bhff.3Em', '常乐', '1', 'user', '此人很懒，还没有签名~', 'changle.png', '2018-03-09 15:44:29', '2018-03-09 15:44:29');
INSERT INTO `user_table` VALUES ('25', 'xiehao', '{bcrypt}$2a$10$08CwvP.gGYS0Te.YQwPGv.8Os9Hq30qEqTZioW.mo1D7YVQRnFioa', 'xiehao', '1', 'user', '此人很懒，还没有签名~1', 'xiehao.png', '2018-03-12 09:16:16', '2018-03-12 09:16:16');
INSERT INTO `user_table` VALUES ('26', 'changle1', '{bcrypt}$2a$10$2dQuwfwGT11QAqmNzbiHUOT95GdPHWYHSI2awry/t11ea67juOJ8u', 'changle', '1', 'user', '此人很懒，还没有签名~', 'changle1.png', '2018-03-13 11:11:05', '2018-03-13 11:11:05');

-- ----------------------------
-- Table structure for video_comment_table
-- ----------------------------
DROP TABLE IF EXISTS `video_comment_table`;
CREATE TABLE `video_comment_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `video_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_comment_table
-- ----------------------------
INSERT INTO `video_comment_table` VALUES ('3', '1', '58', '测试一下', '2018-03-13 16:10:18');
INSERT INTO `video_comment_table` VALUES ('4', '1', '65', '来试一次评论，视频好好看哦！', '2018-03-13 17:35:57');
INSERT INTO `video_comment_table` VALUES ('5', '1', '65', '再是一次', '2018-03-13 17:37:12');
INSERT INTO `video_comment_table` VALUES ('6', '1', '65', '再来一次吧', '2018-03-13 17:37:29');
INSERT INTO `video_comment_table` VALUES ('7', '1', '68', '我试试看评论', '2018-03-14 09:25:26');

-- ----------------------------
-- Table structure for video_table
-- ----------------------------
DROP TABLE IF EXISTS `video_table`;
CREATE TABLE `video_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `click` int(11) DEFAULT NULL,
  `category` int(2) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `view` varchar(200) DEFAULT NULL,
  `duration` int(5) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_table
-- ----------------------------
INSERT INTO `video_table` VALUES ('55', '周杰伦-《等你下课》动画MV', '0', '1', '2018-03-05 15:21:15', '周杰伦-《等你下课》动画MVcfcfba869d7cf8ed654a3aa676eebbd1.mp4', '1', '周杰伦-《等你下课》动画MVcfcfba869d7cf8ed654a3aa676eebbd1.jpg', '275', '蜡笔小新新番947集 妮妮离家出走&新之助再次对战新哥斯拉');
INSERT INTO `video_table` VALUES ('56', '123qqqq', '1232', '1', '2018-03-05 15:31:31', '123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.mp4', '1', '123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg', '275', '蜡笔小新新番947集 妮妮离家出走&新之助再次对战新哥斯拉');
INSERT INTO `video_table` VALUES ('57', '123qqqq', '1233', '1', '2018-03-05 15:35:40', '123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.mp4', '1', '123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg', '275', '蜡笔小新新番947集 妮妮离家出走&新之助再次对战新哥斯拉');
INSERT INTO `video_table` VALUES ('58', '123qqqq', '1259', '1', '2018-03-05 15:36:03', '123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.mp4', '1', '123qqqqcfcfba869d7cf8ed654a3aa676eebbd1.jpg', '275', '蜡笔小新新番947集 妮妮离家出走&新之助再次对战新哥斯拉ssss');
INSERT INTO `video_table` VALUES ('65', '测试一下', '6', '1', '2018-03-13 17:30:34', '测试一下474d202d1f2d94810fa9ce1540ec9ee1.mp4', '1', '测试一下474d202d1f2d94810fa9ce1540ec9ee1.jpg', '1140', '测试的视频，看看ok不，嗷嗷嗷嗷啊啊啊啊啊 啊嗷嗷啊 啊啊啊啊');
INSERT INTO `video_table` VALUES ('68', '在测试一次', '29', '1', '2018-03-13 17:35:24', '在测试一次474d202d1f2d94810fa9ce1540ec9ee1.mp4', '1', '在测试一次474d202d1f2d94810fa9ce1540ec9ee1.jpg', '1140', '在测试一侧');

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `category_table`;
CREATE TABLE `category_table` (
  `id` bigint(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `belong_to_other` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_table(Default Add Nine categories)
-- ----------------------------
INSERT INTO `category_table` VALUES ('1', 'Short Video', '0');
INSERT INTO `category_table` VALUES ('2', 'VR (Beta)', '0');
INSERT INTO `category_table` VALUES ('3', 'Music Video', '0');
INSERT INTO `category_table` VALUES ('4', 'Cartoon', '1');
INSERT INTO `category_table` VALUES ('5', 'Movie', '1');
INSERT INTO `category_table` VALUES ('6', 'Funny Video', '1');
INSERT INTO `category_table` VALUES ('7', 'Edu Video', '1');
INSERT INTO `category_table` VALUES ('8', 'Tech Video', '1');
INSERT INTO `category_table` VALUES ('9', 'Others', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
  `create_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_table(Default Add Administrator)
-- ----------------------------
INSERT INTO `user_table` VALUES ('1', 'root', '{MD5}fc7fc678608590b123692867f176fe63', 'Administrator', '1', 'admin', '此人很懒，还没有签名~', 'root.png', '2018-03-13 16:28:59', '2018-03-13 16:28:59');
INSERT INTO `user_table` VALUES ('2', 'Yosun', '{bcrypt}$2a$10$Ppn9M7DOLGcX7VAey3nZHe9dp3ZGaXQvwIm5AoHn54umG6cRYs18q', 'MyYosun', '1', 'admin', 'MyYosun', 'Yosun.png', '2018-03-14 10:05:25', '2018-03-14 10:05:25');

-- ----------------------------
-- Table structure for video_comment_table
-- ----------------------------
DROP TABLE IF EXISTS `video_comment_table`;
CREATE TABLE `video_comment_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `video_id` bigint(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


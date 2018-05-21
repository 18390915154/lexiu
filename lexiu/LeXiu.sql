/*
Navicat MySQL Data Transfer

Source Server         : xiuluo
Source Server Version : 50631
Source Host           : 121.199.52.79:3306
Source Database       : LeXiu

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-08-25 13:48:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_about_us
-- ----------------------------
DROP TABLE IF EXISTS `t_about_us`;
CREATE TABLE `t_about_us` (
  `about_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id，自增长',
  `about_content` char(100) DEFAULT NULL COMMENT '关于我们描述',
  `about_status` int(11) DEFAULT NULL COMMENT '0.不使用、1.使用',
  `about_backup` char(50) DEFAULT NULL COMMENT '备份字段',
  PRIMARY KEY (`about_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='关于我们';

-- ----------------------------
-- Records of t_about_us
-- ----------------------------
INSERT INTO `t_about_us` VALUES ('1', 'test', '1', null);

-- ----------------------------
-- Table structure for t_assess
-- ----------------------------
DROP TABLE IF EXISTS `t_assess`;
CREATE TABLE `t_assess` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_assess
-- ----------------------------
INSERT INTO `t_assess` VALUES ('10', '3281', '待完成很有责任', '3', '', '2017-05-18 20:36:47');
INSERT INTO `t_assess` VALUES ('11', '3279', '待完成很有责任\n				很有礼貌', '3', '', '2017-05-18 21:02:12');
INSERT INTO `t_assess` VALUES ('12', '3275', '很有责任', '3', '', '2017-05-19 18:49:42');
INSERT INTO `t_assess` VALUES ('13', '3317', '待完成很有责任\n				很有礼貌', '2', '', '2017-05-20 00:17:21');
INSERT INTO `t_assess` VALUES ('14', '3329', '很有责任', '3', '', '2017-05-20 00:57:53');
INSERT INTO `t_assess` VALUES ('15', '3330', '待完成很有责任\n				很有礼貌', '3', '', '2017-05-20 01:00:18');
INSERT INTO `t_assess` VALUES ('16', '3331', '待完成很有礼貌', '1', '', '2017-05-20 01:02:05');
INSERT INTO `t_assess` VALUES ('33', '3335', '很有耐心', '1', '', '2017-05-20 02:24:03');
INSERT INTO `t_assess` VALUES ('34', '3346', '很有水平\n				很有礼貌', '3', '', '2017-05-20 15:34:00');
INSERT INTO `t_assess` VALUES ('35', '3334', '很有责任\n				很有礼貌', '5', '', '2017-05-20 15:50:40');
INSERT INTO `t_assess` VALUES ('36', '3358', '很有水平\n				很有礼貌', '3', '', '2017-05-20 18:28:10');
INSERT INTO `t_assess` VALUES ('37', '3365', '很有水平', '5', '', '2017-05-20 21:02:06');
INSERT INTO `t_assess` VALUES ('38', '3368', '很有责任\n				很有水平', '4', '', '2017-05-21 13:29:16');
INSERT INTO `t_assess` VALUES ('39', '3', '', '5', '', '2017-05-21 22:16:14');
INSERT INTO `t_assess` VALUES ('40', '4', '很有水平', '4', '', '2017-05-21 22:22:02');
INSERT INTO `t_assess` VALUES ('42', '5', '很有责任\n				很有水平\n				很有效率\n				很有礼貌\n				很有能力', '4', '', '2017-05-21 23:37:24');
INSERT INTO `t_assess` VALUES ('43', '3371', '很有水平\n				很有效率\n				很有能力', '1', '', '2017-05-22 10:52:13');
INSERT INTO `t_assess` VALUES ('44', '3374', '很有耐心', '2', '', '2017-05-22 12:17:04');
INSERT INTO `t_assess` VALUES ('45', '3382', '很有礼貌', '3', '', '2017-05-22 12:21:06');
INSERT INTO `t_assess` VALUES ('53', '3383', '很有礼貌\n				很有能力', '3', '', '2017-05-22 13:23:17');
INSERT INTO `t_assess` VALUES ('54', '3384', '很有责任\n				很有能力', '5', '', '2017-05-22 13:29:40');
INSERT INTO `t_assess` VALUES ('56', '3387', '很有责任\n				很有水平', '3', '', '2017-05-22 13:45:43');
INSERT INTO `t_assess` VALUES ('57', '3388', '很有能力', '1', '', '2017-05-22 18:38:47');
INSERT INTO `t_assess` VALUES ('58', '3405', '很有责任\n				很有礼貌', '3', '', '2017-05-22 18:43:34');
INSERT INTO `t_assess` VALUES ('59', '3406', '很有水平\n				很有能力', '4', '', '2017-05-22 18:44:44');
INSERT INTO `t_assess` VALUES ('60', '3415', '很有责任', '3', '', '2017-05-24 17:55:45');
INSERT INTO `t_assess` VALUES ('61', '3413', '很有责任', '3', '', '2017-05-24 17:55:58');
INSERT INTO `t_assess` VALUES ('62', '3430', '很有耐心', '2', '', '2017-05-24 17:57:03');
INSERT INTO `t_assess` VALUES ('63', '3431', null, '2', '', '2017-05-24 18:11:32');
INSERT INTO `t_assess` VALUES ('64', '3436', null, '4', '', '2017-05-25 02:36:52');
INSERT INTO `t_assess` VALUES ('65', '3445', null, '5', '', '2017-05-25 13:34:13');
INSERT INTO `t_assess` VALUES ('66', '3453', '很有责任', '3', '', '2017-05-27 14:25:57');
INSERT INTO `t_assess` VALUES ('67', '3447', null, '3', '', '2017-05-27 15:31:09');
INSERT INTO `t_assess` VALUES ('68', '3489', null, '2', '', '2017-06-02 12:12:45');
INSERT INTO `t_assess` VALUES ('69', '3527', null, '2', '', '2017-06-02 18:16:09');
INSERT INTO `t_assess` VALUES ('70', '3500', null, '2', '', '2017-06-02 18:16:36');
INSERT INTO `t_assess` VALUES ('71', '3523', null, '3', '', '2017-06-14 11:41:05');
INSERT INTO `t_assess` VALUES ('72', '3542', '很有耐心\n				很有责任\n				很有水平\n				很有效率\n				很有礼貌\n				很有能力', '5', '', '2017-06-29 16:52:34');
INSERT INTO `t_assess` VALUES ('73', '3543', '很有责任\n				很有礼貌', '5', null, '2017-07-03 11:51:39');

-- ----------------------------
-- Table structure for t_bank_logo
-- ----------------------------
DROP TABLE IF EXISTS `t_bank_logo`;
CREATE TABLE `t_bank_logo` (
  `logoid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `picurl` varchar(50) NOT NULL,
  `bankname` varchar(50) NOT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`logoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_bank_logo
-- ----------------------------

-- ----------------------------
-- Table structure for t_checkout
-- ----------------------------
DROP TABLE IF EXISTS `t_checkout`;
CREATE TABLE `t_checkout` (
  `checkoutid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type` smallint(1) NOT NULL,
  `number` double(100,2) NOT NULL DEFAULT '0.00',
  `addtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`checkoutid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_checkout
-- ----------------------------
INSERT INTO `t_checkout` VALUES ('1', '维修', '1', '1.00', '2017-05-27 15:30:56');
INSERT INTO `t_checkout` VALUES ('2', '家政', '2', '2.00', '2017-05-27 16:45:44');

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `companyid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `address` varchar(250) NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  `latitude` decimal(11,8) NOT NULL,
  `picurl` varchar(250) DEFAULT NULL,
  `typeid` varchar(50) NOT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`companyid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_company
-- ----------------------------
INSERT INTO `t_company` VALUES ('1', '小邹邹', '56874521558', '小周周', '浙江省杭州市和睦路525', '120.13538400', '30.31893700', 'lexiu/upload/123.jpg', '1,2,3,4,5', '2017-05-08 23:48:37');
INSERT INTO `t_company` VALUES ('2', '小周周', '56598482587', '小诹诹', '浙江省杭州市和睦路525', '120.13549800', '30.31896200', 'lexiu/upload/123.jpg', '1', '2017-05-05 17:48:50');
INSERT INTO `t_company` VALUES ('3', '小周周', '56598482587', '小诹诹', '浙江省杭州市和睦路525', '120.13545800', '30.31891200', 'lexiu/upload/123.jpg', '1', '2017-05-05 17:48:50');
INSERT INTO `t_company` VALUES ('4', '小邹邹', '56874521558', '小周周', '浙江省杭州市和睦路525', '120.13528400', '30.31873700', 'lexiu/upload/123.jpg', '1,2,3,4,5', '2017-05-08 23:48:37');

-- ----------------------------
-- Table structure for t_company_type
-- ----------------------------
DROP TABLE IF EXISTS `t_company_type`;
CREATE TABLE `t_company_type` (
  `companytypeid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`companytypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_company_type
-- ----------------------------
INSERT INTO `t_company_type` VALUES ('1', '卫生保洁', '2017-05-05 14:59:00');
INSERT INTO `t_company_type` VALUES ('2', '保姆月嫂', '2017-05-05 14:59:12');
INSERT INTO `t_company_type` VALUES ('3', '临时护工', '2017-05-05 14:59:24');
INSERT INTO `t_company_type` VALUES ('4', '修锁解锁', '2017-05-05 14:59:50');
INSERT INTO `t_company_type` VALUES ('5', '开凿钻孔', '2017-05-05 15:00:20');
INSERT INTO `t_company_type` VALUES ('6', '家电清洗', '2017-05-05 15:00:34');

-- ----------------------------
-- Table structure for t_feedback
-- ----------------------------
DROP TABLE IF EXISTS `t_feedback`;
CREATE TABLE `t_feedback` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `workerid` int(11) NOT NULL DEFAULT '0',
  `type` smallint(1) NOT NULL DEFAULT '3',
  `content` varchar(255) NOT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_feedback
-- ----------------------------
INSERT INTO `t_feedback` VALUES ('1', '0', '1', '1', '21321321321321', '2017-04-14 22:34:24');
INSERT INTO `t_feedback` VALUES ('2', '0', '1', '1', '321321', '2017-04-14 22:34:58');
INSERT INTO `t_feedback` VALUES ('3', '0', '1', '1', '321321', '2017-04-14 22:35:27');
INSERT INTO `t_feedback` VALUES ('4', '1', '0', '1', '张大大', '2017-04-14 22:59:58');
INSERT INTO `t_feedback` VALUES ('5', '1', '0', '1', '222222222222', '2017-04-16 21:54:57');
INSERT INTO `t_feedback` VALUES ('6', '10', '0', '1', '好好搞', '2017-04-27 16:40:49');
INSERT INTO `t_feedback` VALUES ('7', '10', '0', '1', '大', '2017-05-18 14:58:37');
INSERT INTO `t_feedback` VALUES ('8', '0', '19', '1', '大', '2017-05-18 14:59:11');
INSERT INTO `t_feedback` VALUES ('9', '1', '0', '1', '奖学金经常', '2017-05-20 02:30:25');
INSERT INTO `t_feedback` VALUES ('10', '1', '0', '1', '去去去去去', '2017-05-24 18:18:00');

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `goodsid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `shopid` int(11) NOT NULL,
  `score` float(8,2) NOT NULL,
  `name` varchar(250) NOT NULL,
  `profile` varchar(255) NOT NULL,
  `picurl` varchar(250) DEFAULT NULL,
  `delstate` smallint(1) NOT NULL DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`goodsid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', '1', '2.00', '汉堡', '放大放大', 'lexiu/upload/Lighthouse.jpg', '0', '2017-04-07 18:35:38');
INSERT INTO `t_goods` VALUES ('2', '1', '10.00', '布丁', '放大放大', 'lexiu/upload/Lighthouse.jpg', '0', '2017-04-07 18:36:01');
INSERT INTO `t_goods` VALUES ('3', '1', '15.00', '牛肉', '法法', 'lexiu/upload/Jellyfish.jpg', '0', '2017-04-08 10:59:09');
INSERT INTO `t_goods` VALUES ('4', '1', '20.00', '威哥', '反问我', 'lexiu/upload/Penguins.jpg', '0', '2017-04-08 10:59:28');
INSERT INTO `t_goods` VALUES ('8', '2', '77788.00', '77788', '857857575', 'lexiu/upload/jiutong.png', '0', '2017-04-25 22:59:27');

-- ----------------------------
-- Table structure for t_home_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_home_banner`;
CREATE TABLE `t_home_banner` (
  `bannerid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `picurl` varchar(250) NOT NULL,
  `sort` int(11) NOT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bannerid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_home_banner
-- ----------------------------
INSERT INTO `t_home_banner` VALUES ('1', 'lexiu/upload/zhiwuyuanzhiwuqiang2.jpg', '1', '2017-04-25 13:24:15');
INSERT INTO `t_home_banner` VALUES ('2', 'lexiu/upload/123.jpg', '2', '2017-03-09 10:02:09');
INSERT INTO `t_home_banner` VALUES ('3', 'lexiu/upload/zhiwuyuanzhiwuqiang2.jpg', '3', '2017-03-09 10:02:12');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `orderid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ordernum` varchar(25) NOT NULL,
  `starttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `overtime` timestamp NULL DEFAULT NULL,
  `address` varchar(250) NOT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `workerid` int(11) DEFAULT NULL,
  `companyid` int(11) NOT NULL DEFAULT '0',
  `typeid` int(11) NOT NULL,
  `counttime` varchar(50) DEFAULT NULL,
  `type` smallint(1) NOT NULL DEFAULT '0',
  `content` varchar(250) DEFAULT NULL,
  `fault` varchar(250) DEFAULT NULL,
  `longitude` decimal(11,8) NOT NULL,
  `latitude` decimal(11,8) NOT NULL,
  `delflg` smallint(1) NOT NULL DEFAULT '0',
  `reason` varchar(250) DEFAULT NULL,
  `voice` varchar(255) DEFAULT NULL,
  `detail` varchar(250) DEFAULT NULL,
  `picurl` longtext,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderid`),
  UNIQUE KEY `pre_unique` (`ordernum`),
  KEY `pre_userid` (`userid`),
  KEY `pre_workerid` (`workerid`)
) ENGINE=InnoDB AUTO_INCREMENT=3613 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', 'B201714000004', '2017-05-20 00:43:30', '2017-05-20 00:45:30', '临湘', '25.75', '1', '1', '0', '1', '2', '4', '不饿', '迪卡', '113.45714300', '29.48253600', '0', '', '/data/user/0/com.example.hello/files/d9d42c04-1b4d-4d91-8dae-c7d88400fb19.m4a', '', '', '2017-05-20 00:45:30');
INSERT INTO `t_order` VALUES ('3328', 'B201714000000', '2017-05-20 00:42:51', null, '临湘', null, '1', null, '0', '1', null, '0', null, null, '113.45714300', '29.48253600', '0', null, null, null, null, '2017-05-20 00:42:51');
INSERT INTO `t_order` VALUES ('3332', 'A201714001000', '2017-05-20 01:45:00', null, '浙江省杭州市和睦路525发广告', null, '1', null, '1', '1', null, '0', null, null, '113.04076900', '28.14227700', '0', null, null, '吃吃吃', 'lexiu/upload/IMG_20170502_091014_HDR.jpg,lexiu/upload/Screenshot_2017-05-18-11-43-44-640_com.example.hello.png,lexiu/upload/Screenshot_2017-05-18-11-43-44-640_com.example.hello.png,lexiu/upload/Screenshot_2017-05-18-11-43-44-640_com.example.hello.png,lexiu/upload/IMG_20170502_091014_HDR.jpg,lexiu/upload/IMG_20170430_185012_HDR.jpg', '2017-05-20 01:21:48');
INSERT INTO `t_order` VALUES ('3333', 'A201714001001', '2017-05-20 01:45:00', null, '浙江省杭州市和睦路525发广告', null, '1', null, '1', '1', null, '0', null, null, '113.04076900', '28.14227700', '0', null, null, '吃吃吃', 'lexiu/upload/IMG_20170502_091014_HDR.jpg,lexiu/upload/Screenshot_2017-05-18-11-43-44-640_com.example.hello.png,lexiu/upload/Screenshot_2017-05-18-11-43-44-640_com.example.hello.png,lexiu/upload/Screenshot_2017-05-18-11-43-44-640_com.example.hello.png,lexiu/upload/IMG_20170502_091014_HDR.jpg,lexiu/upload/IMG_20170430_185012_HDR.jpg', '2017-05-20 01:21:49');
INSERT INTO `t_order` VALUES ('3334', 'A201714001002', '2017-05-20 02:00:00', '2017-05-20 15:48:53', '浙江省杭州市和睦路525发酒疯放假', '103.00', '1', '1', '1', '1', '828', '4', '空调维修', '空调', '120.21871500', '30.24597700', '0', null, null, '挺讨厌', 'lexiu/upload/Screenshot_2017-05-18-11-43-44-640_com.example.hello.png,lexiu/upload/Screenshot_2017-05-11-13-20-14-602_com.p1.mobile.putong.png,lexiu/upload/IMG_20170502_091014_HDR.jpg,lexiu/upload/mmexport1493607344747.jpg,lexiu/upload/IMG_20170430_185012_HDR.jpg,lexiu/upload/IMG_20170422_101300.jpg', '2017-05-20 15:48:53');
INSERT INTO `t_order` VALUES ('3335', 'A201714001003', '2017-05-20 02:00:00', '2017-05-20 01:27:59', '浙江省杭州市和睦路525发酒疯放假', '22.66', '1', '1', '1', '1', '-33', '4', '444', '11', '120.21871500', '30.24597700', '0', null, null, '挺讨厌', 'lexiu/upload/Screenshot_2017-05-18-11-43-44-640_com.example.hello.png,lexiu/upload/Screenshot_2017-05-11-13-20-14-602_com.p1.mobile.putong.png,lexiu/upload/IMG_20170502_091014_HDR.jpg,lexiu/upload/mmexport1493607344747.jpg,lexiu/upload/IMG_20170430_185012_HDR.jpg,lexiu/upload/IMG_20170422_101300.jpg', '2017-05-20 01:27:59');
INSERT INTO `t_order` VALUES ('3336', 'B201714013000', '2017-05-20 13:41:46', null, '临湘', null, '1', null, '0', '1', null, '0', null, null, '113.45714300', '29.48253600', '1', '联系不上师傅', null, null, null, '2017-05-20 13:41:46');
INSERT INTO `t_order` VALUES ('3337', 'B201714013001', '2017-05-20 13:41:55', null, '浙江省杭州市和睦路525发酒疯放假', null, '1', null, '0', '2', null, '0', null, null, '113.45714300', '29.48253600', '1', '师傅以各种理由不来处理', null, null, null, '2017-05-20 13:41:55');
INSERT INTO `t_order` VALUES ('3338', 'B201714013002', '2017-05-20 13:42:03', null, '浙江省杭州市和睦路525发酒疯放假', null, '1', null, '0', '3', null, '0', null, null, '113.45714300', '29.48253600', '1', '等待时间过长', null, null, null, '2017-05-20 13:42:03');
INSERT INTO `t_order` VALUES ('3339', 'B201714013003', '2017-05-20 13:42:14', null, '临湘', null, '1', null, '0', '4', null, '0', null, null, '113.45714300', '29.48253600', '1', '师傅服务态度恶劣', null, null, null, '2017-05-20 13:42:14');
INSERT INTO `t_order` VALUES ('3340', 'B201714013004', '2017-05-20 13:42:23', null, '临湘', null, '1', null, '0', '1', null, '0', null, null, '113.45714300', '29.48253600', '1', '等待时间过长', null, null, null, '2017-05-20 13:42:23');
INSERT INTO `t_order` VALUES ('3341', 'B201714013005', '2017-05-20 13:42:44', null, '临湘', null, '1', null, '0', '1', null, '0', null, null, '113.45714300', '29.48253600', '1', '等待时间过长', null, null, null, '2017-05-20 13:42:44');
INSERT INTO `t_order` VALUES ('3342', 'B201714013006', '2017-05-20 13:42:59', null, '临湘', null, '1', null, '0', '2', null, '0', null, null, '113.45714300', '29.48253600', '1', '师傅以各种理由不来处理', null, null, null, '2017-05-20 13:42:59');
INSERT INTO `t_order` VALUES ('3343', 'B201714013007', '2017-05-20 13:47:17', null, '临湘', null, '1', '1', '0', '1', null, '1', null, null, '113.45714300', '29.48253600', '1', '下单超时', null, null, null, '2017-05-20 13:47:17');
INSERT INTO `t_order` VALUES ('3344', 'B201714013008', '2017-05-20 13:50:41', null, '临湘', null, '1', '1', '0', '1', null, '1', null, null, '113.45714300', '29.48253600', '1', '等待时间过长', null, null, null, '2017-05-20 13:50:41');
INSERT INTO `t_order` VALUES ('3345', 'B201714013009', '2017-05-20 13:53:06', null, '临湘', null, '1', '1', '0', '1', null, '1', null, null, '113.45714300', '29.48253600', '1', '师傅服务态度恶劣', null, null, null, '2017-05-20 13:53:06');
INSERT INTO `t_order` VALUES ('3346', 'B201714013010', '2017-05-20 13:57:37', '2017-05-20 15:20:43', '杭州市下城区q先生宠物生活馆', '77.25', '1', '1', '0', '1', '83', '4', '空调维修', '空调散热器不行', '120.18764500', '30.26541000', '0', null, null, null, null, '2017-05-20 15:20:43');
INSERT INTO `t_order` VALUES ('3347', 'B201714015001', '2017-05-20 15:35:18', null, '杭州市下城区q先生宠物生活馆', null, '1', null, '0', '1', null, '0', null, null, '120.18764500', '30.26541000', '1', '下单超时', null, null, null, '2017-05-20 15:35:18');
INSERT INTO `t_order` VALUES ('3348', 'B201714015003', '2017-05-20 15:51:21', null, '杭州市下城区q先生宠物生活馆', null, '1', null, '0', '1', null, '0', null, null, '120.18764500', '30.26541000', '1', '师傅服务态度恶劣', null, null, null, '2017-05-20 15:51:21');
INSERT INTO `t_order` VALUES ('3349', 'B201714015004', '2017-05-20 15:54:01', null, '杭州市下城区q先生宠物生活馆', null, '1', null, '0', '1', null, '0', null, null, '120.18764500', '30.26541000', '1', '等待时间过长', null, null, null, '2017-05-20 15:54:01');
INSERT INTO `t_order` VALUES ('3350', 'B201714015005', '2017-05-20 15:55:34', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-20 15:55:34');
INSERT INTO `t_order` VALUES ('3351', 'B201714015006', '2017-05-20 15:57:00', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '临时有事，下次再说吧', null, null, null, '2017-05-20 15:57:00');
INSERT INTO `t_order` VALUES ('3352', 'B201714015007', '2017-05-20 15:57:12', null, '杭州市下城区q先生宠物生活馆', null, '1', null, '0', '1', null, '0', null, null, '120.18764500', '30.26541000', '1', '师傅以各种理由不来处理', null, null, null, '2017-05-20 15:57:12');
INSERT INTO `t_order` VALUES ('3353', 'B201714015000', '2017-05-20 15:58:38', null, '杭州市下城区q先生宠物生活馆', null, '1', null, '0', '1', null, '0', null, null, '120.18764500', '30.26541000', '1', '价格太高', null, null, null, '2017-05-20 15:58:38');
INSERT INTO `t_order` VALUES ('3355', 'B201714016000', '2017-05-20 16:13:15', null, '杭州市拱墅区老头儿油爆虾胜利河店', null, '1', null, '0', '1', null, '0', null, null, '120.16110500', '30.30334800', '1', '下单超时', null, null, null, '2017-05-20 16:13:15');
INSERT INTO `t_order` VALUES ('3356', 'B201714016001', '2017-05-20 16:15:22', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '联系不上师傅', null, null, null, '2017-05-20 16:15:22');
INSERT INTO `t_order` VALUES ('3357', 'B201714017000', '2017-05-20 17:26:09', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '等待时间过长', null, null, null, '2017-05-20 17:26:09');
INSERT INTO `t_order` VALUES ('3358', 'A201714017001', '2017-05-20 17:30:40', '2017-05-20 18:27:40', '杭州市拱墅区老头儿油爆虾(和睦店)来呀', '17.16', '1', '1', '1', '2', '57', '4', '11', '11111', '120.13549800', '30.31896200', '0', null, null, '大大的', 'lexiu/upload/Desert.jpg,lexiu/upload/Koala.jpg', '2017-05-20 18:27:40');
INSERT INTO `t_order` VALUES ('3359', 'B201714017002', '2017-05-20 17:43:48', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅服务态度恶劣', null, null, null, '2017-05-20 17:43:48');
INSERT INTO `t_order` VALUES ('3360', 'B201714017003', '2017-05-20 17:44:19', null, '杭州市拱墅区老头儿油爆虾胜利河店', null, '1', null, '0', '1', null, '0', null, null, '120.16110500', '30.30334800', '1', '联系不上师傅', null, null, null, '2017-05-20 17:44:19');
INSERT INTO `t_order` VALUES ('3361', 'B201714017004', '2017-05-20 17:46:20', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅服务态度恶劣', null, null, null, '2017-05-20 17:46:20');
INSERT INTO `t_order` VALUES ('3362', 'B201714017005', '2017-05-20 17:47:47', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-20 17:47:47');
INSERT INTO `t_order` VALUES ('3363', 'A201714017006', '2017-05-20 17:57:43', null, '杭州市拱墅区老头儿油爆虾(和睦店)12', null, '1', '1', '1', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '联系不上用户', null, '呃呃呃', 'lexiu/upload/Jellyfish.jpg', '2017-05-20 17:57:43');
INSERT INTO `t_order` VALUES ('3364', 'B201714018000', '2017-05-20 18:01:10', null, '临湘', null, '1', null, '0', '1', null, '0', null, null, '113.45714300', '29.48253600', '1', '下单超时', null, null, null, '2017-05-20 18:01:10');
INSERT INTO `t_order` VALUES ('3365', 'B201714018002', '2017-05-20 18:39:27', '2017-05-20 19:20:55', '杭州市拱墅区老头儿油爆虾(和睦店)', '51.50', '1', '1', '0', '1', '41', '4', '1', '111', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-20 19:20:55');
INSERT INTO `t_order` VALUES ('3366', 'B201714018003', '2017-05-20 18:50:12', null, '临湘', null, '1', null, '0', '1', null, '0', null, null, '113.45714300', '29.48253600', '1', '下单超时', null, null, null, '2017-05-20 18:50:12');
INSERT INTO `t_order` VALUES ('3367', 'B201714021000', '2017-05-20 21:02:19', null, '临湘', null, '1', null, '0', '1', null, '0', null, null, '113.45714300', '29.48253600', '1', '师傅以各种理由不来处理', null, null, null, '2017-05-20 21:02:19');
INSERT INTO `t_order` VALUES ('3368', 'B201714021001', '2017-05-20 21:03:00', '2017-05-20 21:03:20', '杭州市拱墅区老头儿油爆虾(和睦店)', '0.00', '1', '1', '0', '1', '0', '4', '奋斗的奋斗', '发大水', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-20 21:03:20');
INSERT INTO `t_order` VALUES ('3369', 'B201714113010', '2017-05-21 13:27:25', null, '临湘', null, '1', null, '0', '1', null, '0', null, null, '113.45714300', '29.48253600', '1', '下单超时', null, null, null, '2017-05-21 13:27:25');
INSERT INTO `t_order` VALUES ('3370', 'B201714123000', '2017-05-21 23:46:37', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', null, '0', '4', null, '0', null, null, '120.21937500', '30.25924400', '1', '临时有事，下次再说吧', null, null, null, '2017-05-21 23:46:37');
INSERT INTO `t_order` VALUES ('3371', 'B201714123001', '2017-05-21 23:47:42', '2017-05-22 10:39:39', '杭州市江干区杭州绿叶休闲农庄', '566.50', '1', '1', '0', '1', '652', '4', '22', '22', '120.21937500', '30.25924400', '0', null, null, null, null, '2017-05-22 10:39:39');
INSERT INTO `t_order` VALUES ('3372', 'A201714210000', '2017-05-22 10:10:50', null, '浙江省杭州市和睦路525aa', null, '1', null, '1', '2', null, '0', null, null, '113.04076900', '28.14227700', '1', '下单超时', null, '啊', 'lexiu/upload/Jellyfish.jpg', '2017-05-22 10:10:50');
INSERT INTO `t_order` VALUES ('3373', 'B201714210001', '2017-05-22 10:39:10', null, '杭州市拱墅区拱墅区教育局', null, '1', null, '0', '1', null, '0', null, null, '120.13236700', '30.32580900', '0', null, null, null, null, '2017-05-22 10:39:10');
INSERT INTO `t_order` VALUES ('3374', 'B201714211000', '2017-05-22 11:24:54', '2017-05-22 11:26:37', '杭州市江干区杭州绿叶休闲农庄', '25.75', '1', '1', '0', '1', '2', '4', '1来了', '111', '120.21937500', '30.25924400', '0', null, null, null, null, '2017-05-22 11:26:37');
INSERT INTO `t_order` VALUES ('3375', 'B201714211001', '2017-05-22 11:30:41', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', null, '0', '1', null, '0', null, null, '120.21937500', '30.25924400', '1', '师傅服务态度恶劣', null, null, null, '2017-05-22 11:30:41');
INSERT INTO `t_order` VALUES ('3376', 'B201714211002', '2017-05-22 11:31:10', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '师傅服务态度恶劣', null, null, null, '2017-05-22 11:31:10');
INSERT INTO `t_order` VALUES ('3377', 'B201714211003', '2017-05-22 11:31:42', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', null, null, null, '2017-05-22 11:31:42');
INSERT INTO `t_order` VALUES ('3379', 'B201714211005', '2017-05-22 11:33:02', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '等待时间过长', null, null, null, '2017-05-22 11:33:02');
INSERT INTO `t_order` VALUES ('3380', 'B201714211006', '2017-05-22 11:33:14', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '等待时间过长', null, null, null, '2017-05-22 11:33:14');
INSERT INTO `t_order` VALUES ('3381', 'B201714211007', '2017-05-22 11:33:44', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-22 11:33:44');
INSERT INTO `t_order` VALUES ('3383', 'B201714212000', '2017-05-22 12:50:36', '2017-05-22 13:19:14', '杭州市江干区杭州绿叶休闲农庄', '25.75', '1', '1', '0', '1', '29', '4', '554', '4554', '120.21937500', '30.25924400', '0', null, null, null, null, '2017-05-22 13:19:14');
INSERT INTO `t_order` VALUES ('3384', 'B201714213012', '2017-05-22 13:24:49', '2017-05-22 13:25:13', '杭州市拱墅区老头儿油爆虾(和睦店)', '25.75', '1', '1', '0', '1', '1', '4', '是的', '没事了', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-22 13:25:13');
INSERT INTO `t_order` VALUES ('3385', 'B201714213013', '2017-05-22 13:41:40', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', null, '0', '1', null, '0', null, null, '120.21937500', '30.25924400', '1', '等待时间过长', null, null, null, '2017-05-22 13:41:40');
INSERT INTO `t_order` VALUES ('3386', 'B201714213014', '2017-05-22 13:44:15', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', null, '0', '1', null, '0', null, null, '120.21937500', '30.25924400', '0', null, null, null, null, '2017-05-22 13:44:15');
INSERT INTO `t_order` VALUES ('3387', 'B201714213015', '2017-05-22 13:44:50', '2017-05-22 13:45:09', '杭州市拱墅区老头儿油爆虾(和睦店)', '25.75', '1', '1', '0', '1', '1', '4', '回去了', 'is', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-22 13:45:09');
INSERT INTO `t_order` VALUES ('3388', 'B201714214000', '2017-05-22 14:44:49', '2017-05-22 14:45:14', '杭州市江干区杭州绿叶休闲农庄', '25.75', '1', '1', '0', '1', '1', '4', '指的，家里', 'is', '120.21937500', '30.25924400', '0', null, null, null, null, '2017-05-22 14:45:14');
INSERT INTO `t_order` VALUES ('3389', 'A201714217006', '2017-05-22 17:09:55', null, '浙江省杭州市和睦路525。', null, '1', null, '1', '1', null, '0', null, null, '113.04076900', '28.14227700', '1', '下单超时', null, '”', null, '2017-05-22 17:09:55');
INSERT INTO `t_order` VALUES ('3390', 'A201714217007', '2017-05-22 17:14:42', null, '长沙市雨花区长沙市雨花区政府小鸡小鸡吃笑口常开吃', null, '1', null, '1', '1', null, '0', null, null, '113.04437000', '28.14260300', '1', '下单超时', null, '小轿车坚持坚持', null, '2017-05-22 17:14:42');
INSERT INTO `t_order` VALUES ('3391', 'A201714217008', '2017-05-22 17:17:33', null, '长沙市雨花区圭塘河生态公园程序', null, '1', null, '1', '1', null, '0', null, null, '113.04139400', '28.14240400', '1', '下单超时', null, '超级超级超级', null, '2017-05-22 17:17:33');
INSERT INTO `t_order` VALUES ('3392', 'A201714217009', '2017-05-22 17:19:19', null, '浙江省杭州市和睦路525吹风机', null, '1', null, '1', '2', null, '0', null, null, '113.04076900', '28.14227700', '1', '下单超时', null, '费觉费觉', null, '2017-05-22 17:19:19');
INSERT INTO `t_order` VALUES ('3393', 'A201714217010', '2017-05-22 17:25:43', null, '杭州市拱墅区创客蜂房就来了', null, '1', null, '1', '2', null, '0', null, null, '120.13538400', '30.31893700', '1', '下单超时', null, '111', null, '2017-05-22 17:25:43');
INSERT INTO `t_order` VALUES ('3394', 'B201714218003', '2017-05-22 18:27:06', null, '杭州市滨江区第六空间家具馆', null, '1', null, '0', '1', null, '0', null, null, '120.19830800', '30.20285300', '1', '下单超时', null, null, null, '2017-05-22 18:27:06');
INSERT INTO `t_order` VALUES ('3395', 'B201714218004', '2017-05-22 18:31:59', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', null, '0', '1', null, '0', null, null, '120.21937500', '30.25924400', '1', '等待时间过长', null, null, null, '2017-05-22 18:31:59');
INSERT INTO `t_order` VALUES ('3396', 'B201714218005', '2017-05-22 18:34:00', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅以各种理由不来处理', null, null, null, '2017-05-22 18:34:00');
INSERT INTO `t_order` VALUES ('3397', 'B201714218006', '2017-05-22 18:34:19', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅以各种理由不来处理', null, null, null, '2017-05-22 18:34:19');
INSERT INTO `t_order` VALUES ('3398', 'B201714218007', '2017-05-22 18:34:45', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅以各种理由不来处理', null, null, null, '2017-05-22 18:34:45');
INSERT INTO `t_order` VALUES ('3399', 'B201714218008', '2017-05-22 18:35:02', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '纠结纠结', null, null, null, '2017-05-22 18:35:02');
INSERT INTO `t_order` VALUES ('3400', 'B201714218009', '2017-05-22 18:35:40', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '纠结纠结', null, null, null, '2017-05-22 18:35:40');
INSERT INTO `t_order` VALUES ('3401', 'B201714218010', '2017-05-22 18:37:01', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '纠结纠结', null, null, null, '2017-05-22 18:37:01');
INSERT INTO `t_order` VALUES ('3402', 'B201714218011', '2017-05-22 18:37:09', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-22 18:37:09');
INSERT INTO `t_order` VALUES ('3403', 'B201714218012', '2017-05-22 18:40:15', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-22 18:40:15');
INSERT INTO `t_order` VALUES ('3404', 'B201714218013', '2017-05-22 18:41:02', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', null, '0', '1', null, '0', null, null, '120.21937500', '30.25924400', '1', '师傅服务态度恶劣', null, null, null, '2017-05-22 18:41:02');
INSERT INTO `t_order` VALUES ('3405', 'B201714218014', '2017-05-22 18:41:18', '2017-05-22 18:42:55', '杭州市拱墅区老头儿油爆虾(和睦店)', '25.75', '1', '1', '0', '1', '1', '4', '要改行', '任天堂', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-22 18:42:55');
INSERT INTO `t_order` VALUES ('3406', 'B201714218015', '2017-05-22 18:44:05', '2017-05-22 18:44:16', '杭州市拱墅区老头儿油爆虾(和睦店)', '50.00', '1', '1', '0', '1', '0', '4', '刚刚', '发广告', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-22 18:44:16');
INSERT INTO `t_order` VALUES ('3407', 'B201714307000', '2017-05-23 07:21:23', null, '杭州市江干区中国计量大学-格致中楼', null, '7', null, '0', '0', null, '0', null, null, '120.36795500', '30.32822900', '1', '下单超时', null, null, null, '2017-05-23 07:21:23');
INSERT INTO `t_order` VALUES ('3408', 'B201714307001', '2017-05-23 07:27:15', null, '杭州市滨江区江南文化创意产业园', null, '7', null, '0', '0', null, '0', null, null, '120.17010200', '30.17158200', '1', '临时有事，下次再说吧', null, null, null, '2017-05-23 07:27:15');
INSERT INTO `t_order` VALUES ('3409', 'B201714307002', '2017-05-23 07:28:11', null, '杭州市滨江区江南文化创意产业园', null, '7', null, '0', '0', null, '0', null, null, '120.17010200', '30.17158200', '1', '临时有事，下次再说吧', null, null, null, '2017-05-23 07:28:11');
INSERT INTO `t_order` VALUES ('3410', 'B201714308000', '2017-05-23 08:53:11', null, '杭州市江干区中国计量大学-南门', null, '7', null, '0', '0', null, '0', null, null, '120.36674300', '30.32532700', '1', '下单超时', null, null, null, '2017-05-23 08:53:11');
INSERT INTO `t_order` VALUES ('3411', 'B201714309000', '2017-05-23 09:10:56', null, '杭州市江干区中国计量大学-南门', null, '7', null, '0', '0', null, '0', null, null, '120.36674300', '30.32532700', '1', '下单超时', null, null, null, '2017-05-23 09:10:56');
INSERT INTO `t_order` VALUES ('3412', 'B201714310003', '2017-05-23 10:54:29', null, '杭州市江干区中国计量大学-南门', null, '7', null, '0', '0', null, '0', null, null, '120.36674300', '30.32532700', '1', '下单超时', null, null, null, '2017-05-23 10:54:29');
INSERT INTO `t_order` VALUES ('3413', 'B201714316002', '2017-05-23 16:23:27', '2017-05-23 16:24:26', '杭州市拱墅区老头儿油爆虾(和睦店)', '25.75', '1', '1', '0', '1', '1', '4', 'fdfd', 'ddddddddddd', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-23 16:24:26');
INSERT INTO `t_order` VALUES ('3415', 'B201714317011', '2017-05-23 17:30:34', '2017-05-23 17:42:48', '杭州市拱墅区老头儿油爆虾(和睦店)', '25.75', '1', '1', '0', '1', '12', '4', '活的数据啊', '大大', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-23 17:42:48');
INSERT INTO `t_order` VALUES ('3416', 'B201714411008', '2017-05-24 11:35:44', null, '杭州市拱墅区平安银行ATM(和睦路支行)', null, '1', null, '0', '1', null, '0', null, null, '120.13528800', '30.31889800', '1', '师傅服务态度恶劣', null, null, null, '2017-05-24 11:35:44');
INSERT INTO `t_order` VALUES ('3417', 'B201714411009', '2017-05-24 11:36:23', null, '杭州市拱墅区平安银行ATM(和睦路支行)', null, '1', null, '0', '1', null, '0', null, null, '120.13528800', '30.31889800', '1', '师傅服务态度恶劣', null, null, null, '2017-05-24 11:36:23');
INSERT INTO `t_order` VALUES ('3418', 'B201714411010', '2017-05-24 11:39:22', null, '杭州市拱墅区平安银行ATM(和睦路支行)', null, '1', null, '0', '1', null, '0', null, null, '120.13528800', '30.31889800', '1', '下单超时', null, null, null, '2017-05-24 11:39:22');
INSERT INTO `t_order` VALUES ('3419', 'B201714411011', '2017-05-24 11:45:53', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-24 11:45:53');
INSERT INTO `t_order` VALUES ('3420', 'B201714411012', '2017-05-24 11:51:41', null, '北京市平谷区很多鱼', null, '1', null, '0', '1', null, '0', null, null, '117.12930000', '40.14407700', '1', '联系不上师傅', null, null, null, '2017-05-24 11:51:41');
INSERT INTO `t_order` VALUES ('3421', 'B201714411013', '2017-05-24 11:51:50', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '联系不上师傅', null, null, null, '2017-05-24 11:51:50');
INSERT INTO `t_order` VALUES ('3422', 'B201714411014', '2017-05-24 11:52:33', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '价格太高', null, null, null, '2017-05-24 11:52:33');
INSERT INTO `t_order` VALUES ('3423', 'B201714411015', '2017-05-24 11:55:09', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-24 11:55:09');
INSERT INTO `t_order` VALUES ('3424', 'B201714413016', '2017-05-24 13:20:10', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', null, null, null, '2017-05-24 13:20:10');
INSERT INTO `t_order` VALUES ('3425', 'B201714413017', '2017-05-24 13:27:56', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-24 13:27:56');
INSERT INTO `t_order` VALUES ('3426', 'B201714415009', '2017-05-24 15:12:27', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-24 15:12:27');
INSERT INTO `t_order` VALUES ('3427', 'B201714415010', '2017-05-24 15:14:16', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '联系不上用户', null, null, null, '2017-05-24 15:14:16');
INSERT INTO `t_order` VALUES ('3428', 'B201714417012', '2017-05-24 17:38:10', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅服务态度恶劣', null, null, null, '2017-05-24 17:38:10');
INSERT INTO `t_order` VALUES ('3429', 'B201714417013', '2017-05-24 17:39:49', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-24 17:39:49');
INSERT INTO `t_order` VALUES ('3430', 'B201714417014', '2017-05-24 17:40:05', '2017-05-24 17:53:28', '杭州市拱墅区老头儿油爆虾(和睦店)', '25.75', '1', '1', '0', '1', '13', '4', 'dff', 'dff', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-24 17:53:28');
INSERT INTO `t_order` VALUES ('3431', 'B201714417015', '2017-05-24 17:46:37', '2017-05-24 17:53:28', '杭州市拱墅区老头儿油爆虾(和睦店)', '10.00', '1', '1', '0', '1', '12', '4', null, null, '120.13549800', '30.31896200', '0', '', null, null, null, '2017-05-24 17:46:37');
INSERT INTO `t_order` VALUES ('3432', 'B201714420000', '2017-05-24 20:40:04', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '等待时间过长', null, null, null, '2017-05-24 20:40:04');
INSERT INTO `t_order` VALUES ('3433', 'B201714421002', '2017-05-24 21:17:29', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-24 21:17:29');
INSERT INTO `t_order` VALUES ('3434', 'B201714421003', '2017-05-24 21:24:56', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '等待时间过长', null, null, null, '2017-05-24 21:24:56');
INSERT INTO `t_order` VALUES ('3435', 'B201714421004', '2017-05-24 21:28:10', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-24 21:28:10');
INSERT INTO `t_order` VALUES ('3436', 'B201714421005', '2017-05-24 21:30:07', '2017-05-24 21:41:09', '杭州市拱墅区老头儿油爆虾(和睦店)', '25.75', '1', '1', '0', '1', '11', '4', '的撒打算', '发个非官方', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-24 21:41:09');
INSERT INTO `t_order` VALUES ('3437', 'B201714421006', '2017-05-24 21:37:06', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-05-24 21:37:06');
INSERT INTO `t_order` VALUES ('3438', 'B201714502000', '2017-05-25 02:39:59', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '价格太高', null, null, null, '2017-05-25 02:39:59');
INSERT INTO `t_order` VALUES ('3439', 'B201714502001', '2017-05-25 02:42:15', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '等待时间过长', null, null, null, '2017-05-25 02:42:15');
INSERT INTO `t_order` VALUES ('3440', 'B201714502002', '2017-05-25 02:42:45', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '等待时间过长', null, null, null, '2017-05-25 02:42:45');
INSERT INTO `t_order` VALUES ('3441', 'B201714502003', '2017-05-25 02:43:34', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '等待时间过长', null, null, null, '2017-05-25 02:43:34');
INSERT INTO `t_order` VALUES ('3442', 'B201714502004', '2017-05-25 02:48:35', null, '杭州市西湖区文二西路/丰潭路(路口)', null, '1', '1', '0', '1', null, '1', null, null, '120.11557600', '30.28826600', '1', '联系不上师傅', null, null, null, '2017-05-25 02:48:35');
INSERT INTO `t_order` VALUES ('3443', 'B201714511016', '2017-05-25 11:00:19', null, '呵呵', null, '1', null, '0', '1', null, '0', null, null, '120.21109400', '30.28316300', '1', '联系不上师傅', null, null, null, '2017-05-25 11:00:19');
INSERT INTO `t_order` VALUES ('3444', 'B201714511017', '2017-05-25 11:00:27', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '查看存储卡吃', null, null, null, '2017-05-25 11:00:27');
INSERT INTO `t_order` VALUES ('3445', 'B201714511018', '2017-05-25 11:01:13', '2017-05-25 11:13:45', '杭州市拱墅区老头儿油爆虾(和睦店)', '25.75', '1', '1', '0', '1', '12', '4', '就来了', '看看咯', '120.13549800', '30.31896200', '0', null, null, null, null, '2017-05-25 11:13:45');
INSERT INTO `t_order` VALUES ('3446', 'A201714513018', '2017-05-25 13:36:16', null, '和睦路527人一街道口', null, '1', '1', '1', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成等待时间过长', null, '不能', null, '2017-05-25 13:36:16');
INSERT INTO `t_order` VALUES ('3447', 'B201714711018', '2017-05-27 11:32:33', '2017-05-27 11:43:19', '杭州市江干区杭州绿叶休闲农庄', '25.75', '1', '1', '0', '1', '11', '4', '第三方单', '范德萨大', '120.21937500', '30.25924400', '0', null, null, null, null, '2017-05-27 11:43:19');
INSERT INTO `t_order` VALUES ('3448', 'B201714712019', '2017-05-27 12:58:21', null, '杭州市江干区休闲农庄', null, '1', null, '0', '1', null, '0', null, null, '120.38733300', '30.34838100', '1', '师傅以各种理由不来处理', null, null, null, '2017-05-27 12:58:21');
INSERT INTO `t_order` VALUES ('3451', 'B201714713019', '2017-05-27 13:00:21', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '19', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '待完成等待时间过长', null, null, null, '2017-05-27 13:00:21');
INSERT INTO `t_order` VALUES ('3452', 'B201714713020', '2017-05-27 13:01:04', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '19', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '待完成临时有事，下次再说吧', null, null, null, '2017-05-27 13:01:04');
INSERT INTO `t_order` VALUES ('3453', 'B201714713021', '2017-05-27 13:04:31', '2017-05-27 14:25:37', '杭州市江干区杭州绿叶休闲农庄', '77.25', '1', '19', '0', '1', '81', '4', 'dsad', 'dsads', '120.21937500', '30.25924400', '0', null, null, null, null, '2017-05-27 14:25:37');
INSERT INTO `t_order` VALUES ('3454', 'B201714714001', '2017-05-27 14:25:09', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '19', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '待完成师傅服务态度恶劣', null, null, null, '2017-05-27 14:25:09');
INSERT INTO `t_order` VALUES ('3455', 'B201714714003', '2017-05-27 14:34:20', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '19', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '0', null, '/data/user/0/com.example.hello/files/a76f3b4a-5a34-4237-aa37-dff14fbbfcb8.m4a', null, null, '2017-05-27 14:34:20');
INSERT INTO `t_order` VALUES ('3456', 'B201714715011', '2017-05-27 15:28:24', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '联系不上师傅', null, null, null, '2017-05-27 15:28:24');
INSERT INTO `t_order` VALUES ('3457', 'B201714715012', '2017-05-27 15:31:41', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅以各种理由不来处理', null, null, null, '2017-05-27 15:31:41');
INSERT INTO `t_order` VALUES ('3458', 'B201714715013', '2017-05-27 15:32:08', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', null, null, null, '2017-05-27 15:32:08');
INSERT INTO `t_order` VALUES ('3459', 'B201714715014', '2017-05-27 15:33:32', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', '/data/user/0/com.example.hello/files/24dae798-62ce-441d-ab24-0b4df74defac.m4a', null, null, '2017-05-27 15:33:32');
INSERT INTO `t_order` VALUES ('3460', 'B201714715015', '2017-05-27 15:37:21', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '待完成待完成师傅服务态度恶劣', '/data/user/0/com.example.hello/files/1052b623-d175-4188-9235-7cb16e56c3bf.m4a', null, null, '2017-05-27 15:37:21');
INSERT INTO `t_order` VALUES ('3461', 'B201714715016', '2017-05-27 15:38:57', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成等待时间过长', '/data/user/0/com.example.hello/files/0b5430d6-ab3c-4b26-a6aa-149e05a60083.m4a', null, null, '2017-05-27 15:38:57');
INSERT INTO `t_order` VALUES ('3462', 'B201714715017', '2017-05-27 15:42:33', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', '/data/user/0/com.example.hello/files/1d1be116-e885-4678-b087-1fb82f70b3d8.m4a', null, null, '2017-05-27 15:42:33');
INSERT INTO `t_order` VALUES ('3463', 'B201714715018', '2017-05-27 15:54:37', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', null, null, null, '2017-05-27 15:54:37');
INSERT INTO `t_order` VALUES ('3464', 'B201714715019', '2017-05-27 15:56:55', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '联系不上师傅', null, null, null, '2017-05-27 15:56:55');
INSERT INTO `t_order` VALUES ('3465', 'B201714715003', '2017-05-27 15:58:25', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '联系不上师傅', '/data/user/0/com.example.hello/files/19621235-1ce7-450a-859f-d80fde734dfa.m4a', null, null, '2017-05-27 15:58:25');
INSERT INTO `t_order` VALUES ('3466', 'B201714716003', '2017-05-27 16:13:40', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '联系不上师傅', null, null, null, '2017-05-27 16:13:40');
INSERT INTO `t_order` VALUES ('3467', 'B201714716004', '2017-05-27 16:14:00', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '联系不上师傅', null, null, null, '2017-05-27 16:14:00');
INSERT INTO `t_order` VALUES ('3468', 'B201714716005', '2017-05-27 16:18:36', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上师傅联系不上用户', '/data/user/0/com.example.hello/files/e9fff41f-90c4-4cce-a3bb-25fb4a596917.m4a', null, null, '2017-05-27 16:18:36');
INSERT INTO `t_order` VALUES ('3469', 'B201714716006', '2017-05-27 16:20:18', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', '/data/user/0/com.example.hello/files/9f869a9a-1200-4ecd-a696-d654bcaff397.m4a', null, null, '2017-05-27 16:20:18');
INSERT INTO `t_order` VALUES ('3470', 'B201714716007', '2017-05-27 16:30:04', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成师傅服务态度恶劣', '/data/user/0/com.example.hello/files/4ed61fb3-a4c9-42fd-b43a-01d232c8e807.m4a', null, null, '2017-05-27 16:30:04');
INSERT INTO `t_order` VALUES ('3471', 'B201714716008', '2017-05-27 16:36:25', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅服务态度恶劣', null, null, null, '2017-05-27 16:36:25');
INSERT INTO `t_order` VALUES ('3472', 'B201714716018', '2017-05-27 16:38:29', '2017-05-27 17:32:03', '杭州市拱墅区老头儿油爆虾(和睦店)', '0.01', '1', '1', '0', '1', '54', '3', 'fdsf', 'ffds', '120.13549800', '30.31896200', '0', null, 'file:////data/user/0/com.example.hello/files/dbdf07a5-79d1-4947-8638-bd3645f9ca9d.m4a', null, null, '2017-05-27 17:32:03');
INSERT INTO `t_order` VALUES ('3473', 'B201715223001', '2017-06-01 23:43:04', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', null, '0', '1', null, '0', null, null, '120.21937500', '30.25924400', '1', '联系不上师傅', null, null, null, '2017-06-01 23:43:04');
INSERT INTO `t_order` VALUES ('3474', 'B201715223002', '2017-06-01 23:44:04', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '联系不上师傅', null, null, null, '2017-06-01 23:44:04');
INSERT INTO `t_order` VALUES ('3475', 'B201715223003', '2017-06-01 23:50:31', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '联系不上师傅', null, null, null, '2017-06-01 23:50:31');
INSERT INTO `t_order` VALUES ('3476', 'B201715223004', '2017-06-01 23:51:14', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '待完成等待时间过长', null, null, null, '2017-06-01 23:51:14');
INSERT INTO `t_order` VALUES ('3477', 'B201715223005', '2017-06-01 23:56:36', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '联系不上用户', null, null, null, '2017-06-01 23:56:36');
INSERT INTO `t_order` VALUES ('3478', 'B201715300002', '2017-06-02 00:11:50', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '联系不上师傅', null, null, null, '2017-06-02 00:11:50');
INSERT INTO `t_order` VALUES ('3479', 'B201715300003', '2017-06-02 00:12:46', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '联系不上师傅', null, null, null, '2017-06-02 00:12:46');
INSERT INTO `t_order` VALUES ('3480', 'B201715300004', '2017-06-02 00:15:38', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '师傅服务态度恶劣', null, null, null, '2017-06-02 00:15:38');
INSERT INTO `t_order` VALUES ('3481', 'B201715300005', '2017-06-02 00:45:00', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '联系不上用户', null, null, null, '2017-06-02 00:45:00');
INSERT INTO `t_order` VALUES ('3482', 'B201715300006', '2017-06-02 00:50:10', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '临时有事，下次再说吧', null, null, null, '2017-06-02 00:50:10');
INSERT INTO `t_order` VALUES ('3483', 'B201715300007', '2017-06-02 00:53:19', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '待完成联系不上用户', null, null, null, '2017-06-02 00:53:19');
INSERT INTO `t_order` VALUES ('3484', 'B201715300008', '2017-06-02 00:56:18', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '联系不上用户', null, null, null, '2017-06-02 00:56:18');
INSERT INTO `t_order` VALUES ('3485', 'B201715301003', '2017-06-02 01:09:12', '2017-06-02 01:21:14', '杭州市江干区杭州绿叶休闲农庄', '25.50', '1', '1', '0', '1', '12', '3', '的', '的', '120.21937500', '30.25924400', '0', null, null, null, null, '2017-06-02 01:21:14');
INSERT INTO `t_order` VALUES ('3486', 'B201715301004', '2017-06-02 01:21:33', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '师傅以各种理由不来处理', null, null, null, '2017-06-02 01:21:33');
INSERT INTO `t_order` VALUES ('3487', 'B201715301005', '2017-06-02 01:29:09', null, '杭州市江干区杭州绿叶休闲农庄', null, '1', '1', '0', '1', null, '1', null, null, '120.21937500', '30.25924400', '1', '联系不上用户', null, null, null, '2017-06-02 01:29:09');
INSERT INTO `t_order` VALUES ('3488', 'B201715301006', '2017-06-02 01:41:05', '2017-06-02 10:53:27', '杭州市江干区杭州绿叶休闲农庄', '306.00', '1', '1', '0', '1', '552', '3', '方法', '股份的股份', '120.21937500', '30.25924400', '0', null, 'lexiu/upload/cb13a558-4650-4630-9303-39c588f71bdd.m4a', null, null, '2017-06-02 10:53:27');
INSERT INTO `t_order` VALUES ('3489', 'B201715310005', '2017-06-02 10:54:30', '2017-06-02 11:58:43', '杭州市江干区杭州绿叶休闲农庄', '76.50', '1', '1', '0', '1', '64', '4', '范德萨范德萨', '范德萨范德萨', '120.21937500', '30.25924400', '0', null, 'lexiu/upload/71139e38-d164-43e6-b954-b0d22ec6c045.m4a', null, null, '2017-06-02 11:58:43');
INSERT INTO `t_order` VALUES ('3490', 'B201715312001', '2017-06-02 12:10:30', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-06-02 12:10:30');
INSERT INTO `t_order` VALUES ('3491', 'B201715312002', '2017-06-02 12:49:50', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '联系不上师傅', null, null, null, '2017-06-02 12:49:50');
INSERT INTO `t_order` VALUES ('3492', 'B201715312003', '2017-06-02 12:57:35', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '联系不上用户', null, null, null, '2017-06-02 12:57:35');
INSERT INTO `t_order` VALUES ('3493', 'B201715313021', '2017-06-02 13:04:30', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成师傅服务态度恶劣', null, null, null, '2017-06-02 13:04:30');
INSERT INTO `t_order` VALUES ('3494', 'B201715313022', '2017-06-02 13:06:29', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅服务态度恶劣', null, null, null, '2017-06-02 13:06:29');
INSERT INTO `t_order` VALUES ('3495', 'B201715313023', '2017-06-02 13:06:48', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', null, null, null, '2017-06-02 13:06:48');
INSERT INTO `t_order` VALUES ('3496', 'B201715313024', '2017-06-02 13:10:08', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '师傅服务态度恶劣', null, null, null, '2017-06-02 13:10:08');
INSERT INTO `t_order` VALUES ('3497', 'B201715313025', '2017-06-02 13:13:00', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', null, null, null, '2017-06-02 13:13:00');
INSERT INTO `t_order` VALUES ('3498', 'B201715313026', '2017-06-02 13:18:23', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '4', null, '0', null, null, '120.13549800', '30.31896200', '1', '联系不上师傅', null, null, null, '2017-06-02 13:18:23');
INSERT INTO `t_order` VALUES ('3499', 'B201715313027', '2017-06-02 13:18:49', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '待完成联系不上用户', null, null, null, '2017-06-02 13:18:49');
INSERT INTO `t_order` VALUES ('3500', 'B201715313028', '2017-06-02 13:21:07', '2017-06-02 13:31:07', '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '3', null, null, '120.13549800', '30.31896200', '0', null, 'lexiu/upload/accdf715-e985-435b-97ea-021839df9eb6.m4a', null, null, '2017-06-02 13:21:07');
INSERT INTO `t_order` VALUES ('3501', 'A201715314004', '2017-06-02 14:11:13', null, '杭州市余杭区宜家家居(杭州商场)111', null, '1', null, '1', '2', null, '0', null, null, '120.29071000', '30.34911700', '1', '下单超时', null, '三千四', 'lexiu/upload/IMG_20170507_192658_HHT.jpg,lexiu/upload/IMG_20170528_044446.jpg', '2017-06-02 14:11:13');
INSERT INTO `t_order` VALUES ('3502', 'A201715314005', '2017-06-02 14:19:27', null, '浙江省杭州市和睦路525ddd', null, '1', null, '1', '2', null, '0', null, null, '120.13538400', '30.31893700', '1', '下单超时', null, 'dsds', 'lexiu/upload/ac18d71c4f903ca5430712b21ca1067f_5387fafb3e1b1.jpg,lexiu/upload/ac18d71c4f903ca5430712b21ca1067f_5387fafb3e1b1.jpg,lexiu/upload/549738472615065747.png,lexiu/upload/300838.jpg', '2017-06-02 14:19:27');
INSERT INTO `t_order` VALUES ('3520', 'C201715317017', '2017-06-02 17:13:02', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-06-02 17:13:02');
INSERT INTO `t_order` VALUES ('3521', 'C201715317018', '2017-06-02 17:39:28', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-06-02 17:39:28');
INSERT INTO `t_order` VALUES ('3522', 'B201715317019', '2017-06-02 17:47:39', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, null, null, '2017-06-02 17:47:39');
INSERT INTO `t_order` VALUES ('3523', 'B201715317020', '2017-06-02 17:39:49', '2017-06-02 18:01:40', '杭州市拱墅区老头儿油爆虾(和睦店)', '25.50', '1', '1', '0', '1', '22', '4', '起来', '回来了', '120.13549800', '30.31896200', '0', null, 'lexiu/upload/4c794eb5-e323-4fd8-b60f-62ae756b23ab.m4a', null, null, '2017-06-02 18:01:40');
INSERT INTO `t_order` VALUES ('3524', 'C201715317021', '2017-06-02 17:52:12', null, '充值', '0.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-06-02 17:52:12');
INSERT INTO `t_order` VALUES ('3525', 'C201715317022', '2017-06-02 17:52:28', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-06-02 17:52:28');
INSERT INTO `t_order` VALUES ('3526', 'B201715318016', '2017-06-02 18:00:55', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '师傅以各种理由不来处理', null, null, null, '2017-06-02 18:00:55');
INSERT INTO `t_order` VALUES ('3527', 'A201715318018', '2017-06-02 17:54:13', '2017-06-02 18:06:50', '浙江省杭州市和睦路525不开笔记本', '1.01', '1', '1', '1', '1', '12', '4', '打', 'ccc', '120.13538400', '30.31893700', '0', null, null, '觉得飞机场', null, '2017-06-02 18:06:50');
INSERT INTO `t_order` VALUES ('3528', 'C201715318019', '2017-06-02 18:13:59', null, '充值', '11.00', '1', '1', '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-06-02 18:13:59');
INSERT INTO `t_order` VALUES ('3529', 'C201715318020', '2017-06-02 18:15:21', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-06-02 18:15:21');
INSERT INTO `t_order` VALUES ('3530', 'C201715716009', '2017-06-06 16:02:29', null, '充值', '11.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-06-06 16:02:29');
INSERT INTO `t_order` VALUES ('3531', 'A201716511020', '2017-06-14 11:46:47', null, '浙江省杭州市和睦路525给个', null, '1', null, '1', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, '呵呵', 'lexiu/upload/IMG_2140.JPG,lexiu/upload/IMG_2143.PNG,lexiu/upload/IMG_2144.JPG,lexiu/upload/IMG_2145.JPG,lexiu/upload/IMG_2146.JPG,lexiu/upload/IMG_2147.PNG,lexiu/upload/IMG_2141.PNG,lexiu/upload/IMG_2142.PNG', '2017-06-14 11:46:47');
INSERT INTO `t_order` VALUES ('3532', 'A201716511021', '2017-06-14 11:47:06', null, '浙江省杭州市和睦路525给个', null, '1', null, '1', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, '呵呵', 'lexiu/upload/IMG_2140.JPG,lexiu/upload/IMG_2143.PNG,lexiu/upload/IMG_2144.JPG,lexiu/upload/IMG_2145.JPG,lexiu/upload/IMG_2146.JPG,lexiu/upload/IMG_2147.PNG,lexiu/upload/IMG_2141.PNG,lexiu/upload/IMG_2142.PNG', '2017-06-14 11:47:06');
INSERT INTO `t_order` VALUES ('3533', 'A201716511022', '2017-06-14 11:47:38', null, '浙江省杭州市和睦路525给个', null, '1', null, '1', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '下单超时', null, '呵呵', 'lexiu/upload/IMG_2140.JPG,lexiu/upload/IMG_2143.PNG,lexiu/upload/IMG_2144.JPG,lexiu/upload/IMG_2145.JPG,lexiu/upload/IMG_2146.JPG,lexiu/upload/IMG_2147.PNG,lexiu/upload/IMG_2141.PNG,lexiu/upload/IMG_2142.PNG', '2017-06-14 11:47:38');
INSERT INTO `t_order` VALUES ('3534', 'A201716511023', '2017-06-14 11:47:59', null, '浙江省杭州市和睦路525哈哈哈', null, '1', null, '1', '1', null, '0', null, null, '120.13538400', '30.31893700', '1', '下单超时', null, '呵呵', null, '2017-06-14 11:47:59');
INSERT INTO `t_order` VALUES ('3535', 'A201718015021', '2017-06-29 15:51:01', null, '浙江省杭州市和睦路525111', null, '1', '1', '1', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '待完成师傅服务态度恶劣', null, '111', 'lexiu/upload/323769329225614717.jpg', '2017-06-29 15:51:01');
INSERT INTO `t_order` VALUES ('3536', 'B201718015022', '2017-06-29 15:51:27', null, '杭州市江干区浙江理工大学(下沙校区)', null, '16', null, '0', '1', null, '0', null, null, '120.35966900', '30.31964800', '1', '价格太高', null, null, null, '2017-06-29 15:51:27');
INSERT INTO `t_order` VALUES ('3538', 'B201718016010', '2017-06-29 16:02:58', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', null, '0', '1', null, '0', null, null, '120.13549800', '30.31896200', '1', '师傅以各种理由不来处理', null, null, null, '2017-06-29 16:02:58');
INSERT INTO `t_order` VALUES ('3539', 'B201718016011', '2017-06-29 16:08:18', null, '杭州市拱墅区创客蜂房', null, '16', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '待完成联系不上用户', null, null, null, '2017-06-29 16:08:18');
INSERT INTO `t_order` VALUES ('3540', 'B201718016012', '2017-06-29 16:12:15', null, '杭州市拱墅区创客蜂房', null, '16', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '待完成师傅服务态度恶劣', null, null, null, '2017-06-29 16:12:15');
INSERT INTO `t_order` VALUES ('3541', 'B201718016013', '2017-06-29 16:15:18', null, '杭州市拱墅区老头儿油爆虾(和睦店)', null, '1', '1', '0', '1', null, '1', null, null, '120.13549800', '30.31896200', '1', '等待时间过长', null, null, null, '2017-06-29 16:15:18');
INSERT INTO `t_order` VALUES ('3542', 'B201718016014', '2017-06-29 16:23:58', '2017-06-29 16:41:57', '杭州市拱墅区创客蜂房', '25.50', '16', '1', '0', '1', '18', '4', '就来了', '纠结纠结', '120.13538400', '30.31893700', '0', null, null, null, null, '2017-06-29 16:41:57');
INSERT INTO `t_order` VALUES ('3543', 'B201718017022', '2017-06-29 17:12:39', '2017-06-29 17:24:28', '杭州市拱墅区创客蜂房', '25.50', '1', '1', '0', '1', '12', '4', '就来了', '就来了', '120.13538400', '30.31893700', '0', null, null, null, null, '2017-06-29 17:24:28');
INSERT INTO `t_order` VALUES ('3544', 'B201718415023', '2017-07-03 15:52:51', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '联系不上师傅', null, null, null, '2017-07-03 15:52:51');
INSERT INTO `t_order` VALUES ('3545', 'B201718415024', '2017-07-03 15:54:25', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '联系不上用户', null, null, null, '2017-07-03 15:54:25');
INSERT INTO `t_order` VALUES ('3546', 'B201718415025', '2017-07-03 15:57:03', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '师傅服务态度恶劣', null, null, null, '2017-07-03 15:57:03');
INSERT INTO `t_order` VALUES ('3547', 'B201718416015', '2017-07-03 16:00:46', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '师傅以各种理由不来处理', null, null, null, '2017-07-03 16:00:46');
INSERT INTO `t_order` VALUES ('3548', 'B201718416016', '2017-07-03 16:19:09', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '师傅服务态度恶劣', null, null, null, '2017-07-03 16:19:09');
INSERT INTO `t_order` VALUES ('3549', 'B201718416017', '2017-07-03 16:19:58', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '待完成等待时间过长', null, null, null, '2017-07-03 16:19:58');
INSERT INTO `t_order` VALUES ('3550', 'B201718416018', '2017-07-03 16:25:46', '2017-07-03 16:35:52', '杭州市拱墅区创客蜂房', '25.50', '1', '1', '0', '1', '10', '3', 'ggg', 'vvg', '120.13538400', '30.31893700', '0', null, null, null, null, '2017-07-03 16:35:52');
INSERT INTO `t_order` VALUES ('3551', 'B201718416019', '2017-07-03 16:36:34', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '待完成师傅服务态度恶劣', null, null, null, '2017-07-03 16:36:34');
INSERT INTO `t_order` VALUES ('3552', 'B201718416020', '2017-07-03 16:40:49', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '待完成联系不上用户', null, null, null, '2017-07-03 16:40:49');
INSERT INTO `t_order` VALUES ('3553', 'B201718416021', '2017-07-03 16:43:14', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '联系不上师傅', null, null, null, '2017-07-03 16:43:14');
INSERT INTO `t_order` VALUES ('3554', 'B201718416022', '2017-07-03 16:46:45', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '联系不上用户', null, null, null, '2017-07-03 16:46:45');
INSERT INTO `t_order` VALUES ('3555', 'B201718416023', '2017-07-03 16:51:41', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '联系不上用户', null, null, null, '2017-07-03 16:51:41');
INSERT INTO `t_order` VALUES ('3556', 'B201718416024', '2017-07-03 16:52:37', '2017-07-03 17:24:46', '杭州市拱墅区创客蜂房', '51.00', '1', '1', '0', '1', '32', '3', 'fgg', 'vgg', '120.13538400', '30.31893700', '0', null, null, null, null, '2017-07-03 17:24:46');
INSERT INTO `t_order` VALUES ('3557', 'B201718417024', '2017-07-03 17:25:10', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '待完成等待时间过长', null, null, null, '2017-07-03 17:25:10');
INSERT INTO `t_order` VALUES ('3558', 'B201718417025', '2017-07-03 17:26:23', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '待完成联系不上用户', null, null, null, '2017-07-03 17:26:23');
INSERT INTO `t_order` VALUES ('3559', 'B201718417026', '2017-07-03 17:29:36', null, '杭州市拱墅区创客蜂房', null, '1', '1', '0', '1', null, '1', null, null, '120.13538400', '30.31893700', '1', '师傅以各种理由不来处理', null, null, null, '2017-07-03 17:29:36');
INSERT INTO `t_order` VALUES ('3560', 'B201718417027', '2017-07-03 17:31:37', '2017-07-05 14:49:29', '杭州市拱墅区创客蜂房', '306.00', '1', '1', '0', '1', '2718', '3', 'xxd', 'xxx', '120.13538400', '30.31893700', '0', null, null, null, null, '2017-07-05 14:49:29');
INSERT INTO `t_order` VALUES ('3561', 'B201718614007', '2017-07-05 14:40:04', '2017-07-05 14:52:22', '杭州市拱墅区创客蜂房', '0.01', '1', '1', '0', '1', '12', '3', '维修', '水管堵塞', '120.13538400', '30.31893700', '0', null, 'lexiu/upload/81e2015c-ea56-4116-8eb9-ba71a9bd7fc6.m4a', null, null, '2017-07-05 14:52:22');
INSERT INTO `t_order` VALUES ('3562', 'C201719315026', '2017-07-12 15:18:43', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-12 15:18:43');
INSERT INTO `t_order` VALUES ('3563', 'C201719315027', '2017-07-12 15:24:25', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-12 15:24:25');
INSERT INTO `t_order` VALUES ('3564', 'C201719316024', '2017-07-12 16:24:47', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-12 16:24:47');
INSERT INTO `t_order` VALUES ('3565', 'C201719316025', '2017-07-12 16:27:21', null, '充值', '23.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-12 16:27:21');
INSERT INTO `t_order` VALUES ('3566', 'C201719316026', '2017-07-12 16:29:09', null, '充值', '67.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-12 16:29:09');
INSERT INTO `t_order` VALUES ('3567', 'C201719317021', '2017-07-12 17:59:54', null, '充值', '76.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-12 17:59:54');
INSERT INTO `t_order` VALUES ('3568', 'C201719318021', '2017-07-12 18:10:49', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-12 18:10:49');
INSERT INTO `t_order` VALUES ('3569', 'C201719411024', '2017-07-13 11:13:10', null, '充值', '58.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 11:13:10');
INSERT INTO `t_order` VALUES ('3570', 'C201719415028', '2017-07-13 15:39:50', null, '充值', '7.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 15:39:50');
INSERT INTO `t_order` VALUES ('3571', 'B201719416027', '2017-07-13 16:11:03', null, '杭州市上城区杭州市第一人民医院', null, '7', null, '0', '1', null, '0', null, null, '120.17320700', '30.26069100', '1', '临时有事，下次再说吧', null, null, null, '2017-07-13 16:11:03');
INSERT INTO `t_order` VALUES ('3572', 'C201719416028', '2017-07-13 16:49:55', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:55');
INSERT INTO `t_order` VALUES ('3573', 'C201719416029', '2017-07-13 16:49:56', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:56');
INSERT INTO `t_order` VALUES ('3574', 'C201719416030', '2017-07-13 16:49:57', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:57');
INSERT INTO `t_order` VALUES ('3575', 'C201719416031', '2017-07-13 16:49:57', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:57');
INSERT INTO `t_order` VALUES ('3576', 'C201719416032', '2017-07-13 16:49:57', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:57');
INSERT INTO `t_order` VALUES ('3577', 'C201719416033', '2017-07-13 16:49:57', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:57');
INSERT INTO `t_order` VALUES ('3578', 'C201719416034', '2017-07-13 16:49:58', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:58');
INSERT INTO `t_order` VALUES ('3579', 'C201719416035', '2017-07-13 16:49:58', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:58');
INSERT INTO `t_order` VALUES ('3580', 'C201719416036', '2017-07-13 16:49:58', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:58');
INSERT INTO `t_order` VALUES ('3581', 'C201719416037', '2017-07-13 16:49:58', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:58');
INSERT INTO `t_order` VALUES ('3582', 'C201719416038', '2017-07-13 16:49:59', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:59');
INSERT INTO `t_order` VALUES ('3583', 'C201719416039', '2017-07-13 16:49:59', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:59');
INSERT INTO `t_order` VALUES ('3584', 'C201719416040', '2017-07-13 16:49:59', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:59');
INSERT INTO `t_order` VALUES ('3585', 'C201719416041', '2017-07-13 16:49:59', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:59');
INSERT INTO `t_order` VALUES ('3586', 'C201719416042', '2017-07-13 16:49:59', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:49:59');
INSERT INTO `t_order` VALUES ('3587', 'C201719416043', '2017-07-13 16:50:00', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:50:00');
INSERT INTO `t_order` VALUES ('3588', 'C201719416044', '2017-07-13 16:50:00', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:50:00');
INSERT INTO `t_order` VALUES ('3589', 'C201719416045', '2017-07-13 16:52:21', null, '充值', '8.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 16:52:21');
INSERT INTO `t_order` VALUES ('3590', 'C201719417028', '2017-07-13 17:07:59', null, '充值', '4.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-13 17:07:59');
INSERT INTO `t_order` VALUES ('3591', 'C201719508001', '2017-07-14 08:35:12', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-14 08:35:12');
INSERT INTO `t_order` VALUES ('3592', 'C201719514008', '2017-07-14 14:00:18', null, '充值', '45.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-14 14:00:18');
INSERT INTO `t_order` VALUES ('3593', 'C201719514009', '2017-07-14 14:36:20', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-14 14:36:20');
INSERT INTO `t_order` VALUES ('3594', 'C201719514010', '2017-07-14 14:44:51', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-14 14:44:51');
INSERT INTO `t_order` VALUES ('3595', 'C201719514011', '2017-07-14 14:47:34', null, '充值', '1.00', null, '1', '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-14 14:47:34');
INSERT INTO `t_order` VALUES ('3596', 'C201719515029', '2017-07-14 15:14:12', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-14 15:14:12');
INSERT INTO `t_order` VALUES ('3597', 'C201719515030', '2017-07-14 15:16:20', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-14 15:16:20');
INSERT INTO `t_order` VALUES ('3598', 'C201719515031', '2017-07-14 15:16:21', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-14 15:16:21');
INSERT INTO `t_order` VALUES ('3599', 'C201719515032', '2017-07-14 15:54:04', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-14 15:54:04');
INSERT INTO `t_order` VALUES ('3600', 'C201719812004', '2017-07-17 12:12:22', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-17 12:12:22');
INSERT INTO `t_order` VALUES ('3601', 'C201719812005', '2017-07-17 12:12:39', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-17 12:12:39');
INSERT INTO `t_order` VALUES ('3602', 'C201719812006', '2017-07-17 12:12:57', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-17 12:12:57');
INSERT INTO `t_order` VALUES ('3603', 'C201719812007', '2017-07-17 12:13:13', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-17 12:13:13');
INSERT INTO `t_order` VALUES ('3604', 'C201719812008', '2017-07-17 12:14:47', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-17 12:14:47');
INSERT INTO `t_order` VALUES ('3605', 'C201719812009', '2017-07-17 12:15:28', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-17 12:15:28');
INSERT INTO `t_order` VALUES ('3606', 'C201719812010', '2017-07-17 12:19:04', null, '充值', '1.00', null, '1', '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-17 12:19:04');
INSERT INTO `t_order` VALUES ('3607', 'C201719911025', '2017-07-18 11:38:55', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-18 11:38:55');
INSERT INTO `t_order` VALUES ('3608', 'C201719911026', '2017-07-18 11:39:07', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-18 11:39:07');
INSERT INTO `t_order` VALUES ('3609', 'C201719911027', '2017-07-18 11:40:45', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-18 11:40:45');
INSERT INTO `t_order` VALUES ('3610', 'C201719911028', '2017-07-18 11:40:47', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-18 11:40:47');
INSERT INTO `t_order` VALUES ('3611', 'C201719911029', '2017-07-18 11:40:57', null, '充值', '1.00', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-18 11:40:57');
INSERT INTO `t_order` VALUES ('3612', 'C201719911030', '2017-07-18 11:41:14', null, '充值', '0.01', '1', null, '0', '0', null, '9', null, null, '0.00000000', '0.00000000', '0', null, null, null, null, '2017-07-18 11:41:14');

-- ----------------------------
-- Table structure for t_recharge_set
-- ----------------------------
DROP TABLE IF EXISTS `t_recharge_set`;
CREATE TABLE `t_recharge_set` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `money` decimal(10,2) NOT NULL,
  `detail` varchar(250) NOT NULL,
  `type` smallint(1) NOT NULL,
  `score` int(50) NOT NULL DEFAULT '0',
  `delstate` smallint(1) NOT NULL DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_recharge_set
-- ----------------------------
INSERT INTO `t_recharge_set` VALUES ('1', '1', '100.00', '充值', '1', '0', '0', '2017-04-13 18:24:28');
INSERT INTO `t_recharge_set` VALUES ('2', '1', '50.00', '维修支出', '0', '0', '0', '2017-04-13 18:24:38');
INSERT INTO `t_recharge_set` VALUES ('3', '1', '100.00', '提现', '2', '0', '0', '2017-05-09 16:32:58');
INSERT INTO `t_recharge_set` VALUES ('4', '10', '51.50', '维修支出', '0', '0', '0', '2017-05-18 20:35:22');
INSERT INTO `t_recharge_set` VALUES ('5', '10', '390.00', '维修支出', '0', '0', '0', '2017-05-18 20:58:57');
INSERT INTO `t_recharge_set` VALUES ('6', '10', '390.00', '维修支出', '0', '0', '0', '2017-05-18 23:22:32');
INSERT INTO `t_recharge_set` VALUES ('7', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-20 00:14:08');
INSERT INTO `t_recharge_set` VALUES ('8', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-20 00:50:09');
INSERT INTO `t_recharge_set` VALUES ('9', '1', '22.66', '维修支出', '0', '0', '0', '2017-05-20 02:10:02');
INSERT INTO `t_recharge_set` VALUES ('10', '1', '22.66', '维修支出', '0', '0', '0', '2017-05-20 02:12:53');
INSERT INTO `t_recharge_set` VALUES ('11', '1', '77.25', '维修支出', '0', '0', '0', '2017-05-20 15:33:36');
INSERT INTO `t_recharge_set` VALUES ('12', '1', '103.00', '维修支出', '0', '0', '0', '2017-05-20 15:50:24');
INSERT INTO `t_recharge_set` VALUES ('13', '1', '17.16', '维修支出', '0', '0', '0', '2017-05-20 18:28:02');
INSERT INTO `t_recharge_set` VALUES ('14', '1', '51.50', '维修支出', '0', '0', '0', '2017-05-20 20:59:55');
INSERT INTO `t_recharge_set` VALUES ('15', '1', '0.00', '维修支出', '0', '0', '0', '2017-05-21 01:47:45');
INSERT INTO `t_recharge_set` VALUES ('16', '1', '566.50', '维修支出', '0', '0', '0', '2017-05-22 10:50:25');
INSERT INTO `t_recharge_set` VALUES ('17', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-22 11:30:00');
INSERT INTO `t_recharge_set` VALUES ('18', '1', '51.50', '维修支出', '0', '0', '0', '2017-05-22 12:20:38');
INSERT INTO `t_recharge_set` VALUES ('19', '1', '51.50', '维修支出', '0', '0', '0', '2017-05-22 12:21:37');
INSERT INTO `t_recharge_set` VALUES ('20', '1', '51.50', '维修支出', '0', '0', '0', '2017-05-22 12:21:37');
INSERT INTO `t_recharge_set` VALUES ('21', '1', '51.50', '维修支出', '0', '0', '0', '2017-05-22 12:21:37');
INSERT INTO `t_recharge_set` VALUES ('22', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-22 13:23:08');
INSERT INTO `t_recharge_set` VALUES ('23', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-22 13:29:26');
INSERT INTO `t_recharge_set` VALUES ('24', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-22 13:45:28');
INSERT INTO `t_recharge_set` VALUES ('25', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-22 14:45:57');
INSERT INTO `t_recharge_set` VALUES ('26', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-22 18:43:24');
INSERT INTO `t_recharge_set` VALUES ('27', '1', '50.00', '维修支出', '0', '0', '0', '2017-05-22 18:44:37');
INSERT INTO `t_recharge_set` VALUES ('28', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-23 16:48:24');
INSERT INTO `t_recharge_set` VALUES ('29', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-23 18:08:13');
INSERT INTO `t_recharge_set` VALUES ('30', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-23 18:08:55');
INSERT INTO `t_recharge_set` VALUES ('31', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-24 17:55:00');
INSERT INTO `t_recharge_set` VALUES ('32', '1', '10.00', '维修支出', '0', '0', '0', '2017-05-24 18:10:46');
INSERT INTO `t_recharge_set` VALUES ('33', '1', '10.00', '维修支出', '0', '0', '0', '2017-05-24 18:10:59');
INSERT INTO `t_recharge_set` VALUES ('34', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-25 02:36:45');
INSERT INTO `t_recharge_set` VALUES ('35', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-25 13:33:10');
INSERT INTO `t_recharge_set` VALUES ('36', '1', '77.25', '维修支出', '0', '0', '0', '2017-05-27 14:25:47');
INSERT INTO `t_recharge_set` VALUES ('37', '1', '25.75', '维修支出', '0', '0', '0', '2017-05-27 15:30:51');
INSERT INTO `t_recharge_set` VALUES ('38', '1', '0.01', '维修支出', '0', '0', '0', '2017-05-31 09:54:31');
INSERT INTO `t_recharge_set` VALUES ('39', '1', '0.01', '维修支出', '0', '0', '0', '2017-05-31 11:10:14');
INSERT INTO `t_recharge_set` VALUES ('40', '1', '25.50', '维修支出', '0', '0', '0', '2017-06-02 01:21:24');
INSERT INTO `t_recharge_set` VALUES ('41', '1', '306.00', '维修支出', '0', '0', '0', '2017-06-02 10:54:04');
INSERT INTO `t_recharge_set` VALUES ('42', '1', '76.50', '维修支出', '0', '0', '0', '2017-06-02 12:12:15');
INSERT INTO `t_recharge_set` VALUES ('48', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 17:12:18');
INSERT INTO `t_recharge_set` VALUES ('49', '1', '0.01', '维修支出', '0', '0', '0', '2017-06-02 17:16:06');
INSERT INTO `t_recharge_set` VALUES ('50', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 17:21:31');
INSERT INTO `t_recharge_set` VALUES ('51', '1', '0.01', '维修支出', '0', '0', '0', '2017-06-02 17:24:47');
INSERT INTO `t_recharge_set` VALUES ('52', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 17:28:07');
INSERT INTO `t_recharge_set` VALUES ('53', '1', '0.01', '维修支出', '0', '0', '0', '2017-06-02 17:32:31');
INSERT INTO `t_recharge_set` VALUES ('54', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 17:36:17');
INSERT INTO `t_recharge_set` VALUES ('55', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 17:39:37');
INSERT INTO `t_recharge_set` VALUES ('56', '1', '0.01', '维修支出', '0', '0', '0', '2017-06-02 17:40:19');
INSERT INTO `t_recharge_set` VALUES ('57', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 17:55:26');
INSERT INTO `t_recharge_set` VALUES ('58', '1', '25.50', '维修支出', '0', '0', '0', '2017-06-02 18:02:06');
INSERT INTO `t_recharge_set` VALUES ('59', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 18:03:17');
INSERT INTO `t_recharge_set` VALUES ('60', '1', '1.01', '维修支出', '0', '0', '0', '2017-06-02 18:07:54');
INSERT INTO `t_recharge_set` VALUES ('61', '1', '0.00', '充值', '1', '0', '0', '2017-06-02 18:33:41');
INSERT INTO `t_recharge_set` VALUES ('62', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 18:37:05');
INSERT INTO `t_recharge_set` VALUES ('63', '1', '0.01', '维修支出', '0', '0', '0', '2017-06-02 18:41:39');
INSERT INTO `t_recharge_set` VALUES ('64', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 19:04:34');
INSERT INTO `t_recharge_set` VALUES ('65', '1', '0.00', '充值', '1', '0', '0', '2017-06-02 20:36:32');
INSERT INTO `t_recharge_set` VALUES ('66', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 20:40:48');
INSERT INTO `t_recharge_set` VALUES ('67', '1', '0.01', '维修支出', '0', '0', '0', '2017-06-02 20:44:08');
INSERT INTO `t_recharge_set` VALUES ('68', '1', '0.01', '充值', '1', '0', '0', '2017-06-02 21:07:44');
INSERT INTO `t_recharge_set` VALUES ('69', '1', '0.00', '充值', '1', '0', '0', '2017-06-03 02:34:31');
INSERT INTO `t_recharge_set` VALUES ('70', '1', '0.01', '充值', '1', '0', '0', '2017-06-03 02:38:07');
INSERT INTO `t_recharge_set` VALUES ('71', '1', '0.01', '维修支出', '0', '0', '0', '2017-06-03 02:41:42');
INSERT INTO `t_recharge_set` VALUES ('72', '1', '0.01', '充值', '1', '0', '0', '2017-06-03 03:06:18');
INSERT INTO `t_recharge_set` VALUES ('73', '1', '0.00', '充值', '1', '0', '0', '2017-06-03 17:34:04');
INSERT INTO `t_recharge_set` VALUES ('74', '1', '0.01', '充值', '1', '0', '0', '2017-06-03 17:37:11');
INSERT INTO `t_recharge_set` VALUES ('75', '1', '0.01', '维修支出', '0', '0', '0', '2017-06-03 17:42:19');
INSERT INTO `t_recharge_set` VALUES ('76', '1', '0.01', '充值', '1', '0', '0', '2017-06-03 18:05:10');
INSERT INTO `t_recharge_set` VALUES ('77', '1', '100.00', '提现', '2', '0', '0', '2017-06-12 23:01:03');
INSERT INTO `t_recharge_set` VALUES ('78', '16', '25.50', '维修支出', '0', '0', '0', '2017-06-29 16:51:24');
INSERT INTO `t_recharge_set` VALUES ('79', '1', '11.00', '提现', '2', '0', '0', '2017-06-29 17:05:39');
INSERT INTO `t_recharge_set` VALUES ('80', '1', '99999999.99', 'df', '0', '0', '0', '2017-06-30 14:11:25');
INSERT INTO `t_recharge_set` VALUES ('81', '1', '99999999.99', 'df', '0', '0', '0', '2017-06-30 14:12:06');
INSERT INTO `t_recharge_set` VALUES ('82', '1', '25.50', '维修支出', '0', '0', '0', '2017-06-30 17:38:27');
INSERT INTO `t_recharge_set` VALUES ('83', '1', '25.50', '维修支出', '0', '0', '0', '2017-07-03 16:36:28');
INSERT INTO `t_recharge_set` VALUES ('84', '1', '51.00', '维修支出', '0', '0', '0', '2017-07-03 17:25:02');
INSERT INTO `t_recharge_set` VALUES ('85', '1', '306.00', '维修支出', '0', '0', '0', '2017-07-05 14:49:59');
INSERT INTO `t_recharge_set` VALUES ('86', '1', '0.01', '维修支出', '0', '0', '0', '2017-07-05 15:05:08');
INSERT INTO `t_recharge_set` VALUES ('87', '1', '0.01', '维修支出', '0', '0', '0', '2017-07-05 15:09:47');
INSERT INTO `t_recharge_set` VALUES ('88', '1', '0.01', '维修支出', '0', '0', '0', '2017-07-05 15:19:41');
INSERT INTO `t_recharge_set` VALUES ('89', '1', '0.01', '维修支出', '0', '0', '0', '2017-07-05 15:29:29');
INSERT INTO `t_recharge_set` VALUES ('90', '1', '0.01', '维修支出', '0', '0', '0', '2017-07-05 16:29:21');
INSERT INTO `t_recharge_set` VALUES ('91', '1', '0.01', '维修支出', '0', '0', '0', '2017-07-05 18:30:13');
INSERT INTO `t_recharge_set` VALUES ('92', '1', '0.01', '维修支出', '0', '0', '0', '2017-07-06 00:29:47');
INSERT INTO `t_recharge_set` VALUES ('93', '1', '0.01', '维修支出', '0', '0', '0', '2017-07-06 15:29:15');

-- ----------------------------
-- Table structure for t_recharge_worker
-- ----------------------------
DROP TABLE IF EXISTS `t_recharge_worker`;
CREATE TABLE `t_recharge_worker` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `workerid` int(11) NOT NULL,
  `money` decimal(10,2) NOT NULL,
  `detail` varchar(250) NOT NULL,
  `type` smallint(1) NOT NULL,
  `score` int(50) NOT NULL DEFAULT '0',
  `delstate` smallint(1) NOT NULL DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_recharge_worker
-- ----------------------------
INSERT INTO `t_recharge_worker` VALUES ('1', '1', '100.00', '充值', '1', '0', '0', '2017-04-13 18:24:28');
INSERT INTO `t_recharge_worker` VALUES ('2', '1', '50.00', '提现', '2', '0', '0', '2017-04-13 18:24:38');
INSERT INTO `t_recharge_worker` VALUES ('3', '1', '51.50', '收入', '0', '0', '0', '2017-05-18 20:35:22');
INSERT INTO `t_recharge_worker` VALUES ('4', '19', '378.64', '收入', '0', '0', '0', '2017-05-18 20:59:01');
INSERT INTO `t_recharge_worker` VALUES ('5', '19', '378.64', '收入', '0', '0', '0', '2017-05-18 23:22:32');
INSERT INTO `t_recharge_worker` VALUES ('6', '1', '25.00', '收入', '0', '0', '0', '2017-05-20 00:14:08');
INSERT INTO `t_recharge_worker` VALUES ('7', '1', '25.00', '收入', '0', '0', '0', '2017-05-20 00:50:09');
INSERT INTO `t_recharge_worker` VALUES ('8', '1', '22.00', '收入', '0', '0', '0', '2017-05-20 02:10:02');
INSERT INTO `t_recharge_worker` VALUES ('9', '1', '22.00', '收入', '0', '0', '0', '2017-05-20 02:12:53');
INSERT INTO `t_recharge_worker` VALUES ('10', '1', '75.00', '收入', '0', '0', '0', '2017-05-20 15:33:36');
INSERT INTO `t_recharge_worker` VALUES ('11', '1', '100.00', '收入', '0', '0', '0', '2017-05-20 15:50:24');
INSERT INTO `t_recharge_worker` VALUES ('12', '1', '16.66', '收入', '0', '0', '0', '2017-05-20 18:28:02');
INSERT INTO `t_recharge_worker` VALUES ('13', '1', '50.00', '收入', '0', '0', '0', '2017-05-20 20:59:55');
INSERT INTO `t_recharge_worker` VALUES ('14', '1', '0.00', '收入', '0', '0', '0', '2017-05-21 01:47:45');
INSERT INTO `t_recharge_worker` VALUES ('15', '1', '550.00', '收入', '0', '0', '0', '2017-05-22 10:50:25');
INSERT INTO `t_recharge_worker` VALUES ('16', '1', '25.00', '收入', '0', '0', '0', '2017-05-22 11:30:00');
INSERT INTO `t_recharge_worker` VALUES ('17', '1', '50.00', '收入', '0', '0', '0', '2017-05-22 12:20:38');
INSERT INTO `t_recharge_worker` VALUES ('18', '1', '50.00', '收入', '0', '0', '0', '2017-05-22 12:21:37');
INSERT INTO `t_recharge_worker` VALUES ('19', '1', '50.00', '收入', '0', '0', '0', '2017-05-22 12:21:37');
INSERT INTO `t_recharge_worker` VALUES ('20', '1', '50.00', '收入', '0', '0', '0', '2017-05-22 12:21:37');
INSERT INTO `t_recharge_worker` VALUES ('21', '1', '25.00', '收入', '0', '0', '0', '2017-05-22 13:23:09');
INSERT INTO `t_recharge_worker` VALUES ('22', '1', '25.00', '收入', '0', '0', '0', '2017-05-22 13:29:26');
INSERT INTO `t_recharge_worker` VALUES ('23', '1', '25.00', '收入', '0', '0', '0', '2017-05-22 13:45:28');
INSERT INTO `t_recharge_worker` VALUES ('24', '1', '25.00', '收入', '0', '0', '0', '2017-05-22 14:45:57');
INSERT INTO `t_recharge_worker` VALUES ('25', '1', '25.00', '收入', '0', '0', '0', '2017-05-22 18:43:24');
INSERT INTO `t_recharge_worker` VALUES ('26', '1', '48.54', '收入', '0', '0', '0', '2017-05-22 18:44:37');
INSERT INTO `t_recharge_worker` VALUES ('27', '1', '25.00', '收入', '0', '0', '0', '2017-05-23 16:48:24');
INSERT INTO `t_recharge_worker` VALUES ('28', '1', '25.00', '收入', '0', '0', '0', '2017-05-23 18:08:13');
INSERT INTO `t_recharge_worker` VALUES ('29', '1', '25.00', '收入', '0', '0', '0', '2017-05-23 18:08:55');
INSERT INTO `t_recharge_worker` VALUES ('30', '1', '25.00', '收入', '0', '0', '0', '2017-05-24 17:55:00');
INSERT INTO `t_recharge_worker` VALUES ('31', '1', '9.71', '收入', '0', '0', '0', '2017-05-24 18:10:46');
INSERT INTO `t_recharge_worker` VALUES ('32', '1', '9.71', '收入', '0', '0', '0', '2017-05-24 18:10:59');
INSERT INTO `t_recharge_worker` VALUES ('33', '1', '25.00', '收入', '0', '0', '0', '2017-05-25 02:36:46');
INSERT INTO `t_recharge_worker` VALUES ('34', '1', '25.00', '收入', '0', '0', '0', '2017-05-25 13:33:10');
INSERT INTO `t_recharge_worker` VALUES ('35', '19', '75.00', '收入', '0', '0', '0', '2017-05-27 14:25:47');
INSERT INTO `t_recharge_worker` VALUES ('36', '1', '25.00', '收入', '0', '0', '0', '2017-05-27 15:30:51');
INSERT INTO `t_recharge_worker` VALUES ('37', '1', '0.01', '收入', '0', '0', '0', '2017-05-31 11:10:14');
INSERT INTO `t_recharge_worker` VALUES ('38', '1', '25.00', '收入', '0', '0', '0', '2017-06-02 01:21:24');
INSERT INTO `t_recharge_worker` VALUES ('39', '1', '300.00', '收入', '0', '0', '0', '2017-06-02 10:54:04');
INSERT INTO `t_recharge_worker` VALUES ('40', '1', '75.00', '收入', '0', '0', '0', '2017-06-02 12:12:15');
INSERT INTO `t_recharge_worker` VALUES ('41', '1', '0.01', '收入', '0', '0', '0', '2017-06-02 17:16:06');
INSERT INTO `t_recharge_worker` VALUES ('42', '1', '0.01', '收入', '0', '0', '0', '2017-06-02 17:24:47');
INSERT INTO `t_recharge_worker` VALUES ('43', '1', '0.01', '收入', '0', '0', '0', '2017-06-02 17:32:31');
INSERT INTO `t_recharge_worker` VALUES ('44', '1', '0.01', '收入', '0', '0', '0', '2017-06-02 17:40:19');
INSERT INTO `t_recharge_worker` VALUES ('45', '1', '25.00', '收入', '0', '0', '0', '2017-06-02 18:02:06');
INSERT INTO `t_recharge_worker` VALUES ('46', '1', '1.00', '收入', '0', '0', '0', '2017-06-02 18:07:54');
INSERT INTO `t_recharge_worker` VALUES ('47', '1', '0.01', '收入', '0', '0', '0', '2017-06-02 18:41:39');
INSERT INTO `t_recharge_worker` VALUES ('48', '1', '0.01', '收入', '0', '0', '0', '2017-06-02 20:44:08');
INSERT INTO `t_recharge_worker` VALUES ('49', '1', '0.01', '收入', '0', '0', '0', '2017-06-03 02:41:42');
INSERT INTO `t_recharge_worker` VALUES ('50', '1', '0.01', '收入', '0', '0', '0', '2017-06-03 17:42:19');
INSERT INTO `t_recharge_worker` VALUES ('51', '1', '25.00', '收入', '0', '0', '0', '2017-06-29 16:51:24');
INSERT INTO `t_recharge_worker` VALUES ('52', '1', '25.00', '收入', '0', '0', '0', '2017-06-30 17:38:27');
INSERT INTO `t_recharge_worker` VALUES ('53', '1', '25.00', '收入', '0', '0', '0', '2017-07-03 16:36:28');
INSERT INTO `t_recharge_worker` VALUES ('54', '1', '50.00', '收入', '0', '0', '0', '2017-07-03 17:25:02');
INSERT INTO `t_recharge_worker` VALUES ('55', '1', '300.00', '收入', '0', '0', '0', '2017-07-05 14:49:59');
INSERT INTO `t_recharge_worker` VALUES ('56', '1', '0.01', '收入', '0', '0', '0', '2017-07-05 15:05:08');
INSERT INTO `t_recharge_worker` VALUES ('57', '1', '0.01', '收入', '0', '0', '0', '2017-07-05 15:09:47');
INSERT INTO `t_recharge_worker` VALUES ('58', '1', '0.01', '收入', '0', '0', '0', '2017-07-05 15:19:41');
INSERT INTO `t_recharge_worker` VALUES ('59', '1', '0.01', '收入', '0', '0', '0', '2017-07-05 15:29:29');
INSERT INTO `t_recharge_worker` VALUES ('60', '1', '0.01', '收入', '0', '0', '0', '2017-07-05 16:29:21');
INSERT INTO `t_recharge_worker` VALUES ('61', '1', '0.01', '收入', '0', '0', '0', '2017-07-05 18:30:13');
INSERT INTO `t_recharge_worker` VALUES ('62', '1', '0.01', '收入', '0', '0', '0', '2017-07-06 00:29:47');
INSERT INTO `t_recharge_worker` VALUES ('63', '1', '0.01', '收入', '0', '0', '0', '2017-07-06 15:29:15');

-- ----------------------------
-- Table structure for t_shop
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `shopid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `longitude` decimal(11,8) NOT NULL,
  `latitude` decimal(11,8) NOT NULL,
  `address` varchar(250) NOT NULL,
  `contact` varchar(250) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `picurl` varchar(250) DEFAULT NULL,
  `delflg` smallint(1) NOT NULL DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`shopid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_shop
-- ----------------------------
INSERT INTO `t_shop` VALUES ('1', '肯德基', '120.13575841', '30.31885438', '浙江省杭州市拱墅区和睦路', '府东', '15648787445', 'lexiu/upload/头像@3x.png', '1', '2017-04-07 18:15:31');
INSERT INTO `t_shop` VALUES ('10', 'Java', '120.13549800', '30.31896200', '杭州市拱墅区和睦路', 'Java', '18778414646', 'lexiu/upload/main_a_bg02.png', '0', '2017-05-12 11:15:42');

-- ----------------------------
-- Table structure for t_shop_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_shop_banner`;
CREATE TABLE `t_shop_banner` (
  `shop_bannerid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `shopid` int(11) NOT NULL,
  `picurl` varchar(250) NOT NULL,
  `sort` int(11) NOT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`shop_bannerid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_shop_banner
-- ----------------------------
INSERT INTO `t_shop_banner` VALUES ('1', '1', 'lexiu/upload/main_a_bg02.png', '0', '2017-04-24 22:17:48');
INSERT INTO `t_shop_banner` VALUES ('2', '1', 'lexiu/upload/feeeaa8df377b81ec71184ce9fabbdcf.jpg', '1', '2017-04-24 22:17:55');
INSERT INTO `t_shop_banner` VALUES ('3', '1', 'lexiu/upload/feeeaa8df377b81ec71184ce9fabbdcf.jpg', '2', '2017-05-12 10:20:35');
INSERT INTO `t_shop_banner` VALUES ('4', '10', 'lexiu/upload/banner1.png', '0', '2017-05-12 11:15:42');
INSERT INTO `t_shop_banner` VALUES ('5', '10', 'lexiu/upload/footerlogo.png', '0', '2017-05-12 11:15:42');
INSERT INTO `t_shop_banner` VALUES ('6', '10', 'lexiu/upload/main_a_bg02.png', '0', '2017-05-12 11:15:42');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `typeid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `typenum` varchar(250) NOT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', '普通电工', '电工', '2017-05-05 14:57:37');
INSERT INTO `t_type` VALUES ('2', '资深电工', '电工', '2017-05-05 14:57:44');
INSERT INTO `t_type` VALUES ('3', '管道工', '管道工', '2017-05-05 14:57:52');
INSERT INTO `t_type` VALUES ('4', '空调工', '空调工', '2017-05-05 14:58:02');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `paypassword` varchar(50) DEFAULT NULL,
  `avatar` varchar(250) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `age` int(11) DEFAULT NULL,
  `money` decimal(10,2) NOT NULL DEFAULT '0.00',
  `score` int(50) NOT NULL DEFAULT '0',
  `address` varchar(250) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '18077401327', '张啾啾', '123456', '123456', 'lexiu/upload/123.jpg', '男', '2017-05-22 11:26:01', '23', '3358.59', '0', '呵呵', '2017-04-07 17:23:15');
INSERT INTO `t_user` VALUES ('2', '00000000000', 'admin', 'admin', null, null, null, '2017-05-18 20:37:25', null, '87.84', '0', null, '2017-04-20 15:40:52');
INSERT INTO `t_user` VALUES ('7', '13695869508', 'lexiu_158763', '123456', null, null, null, '2017-04-20 18:22:09', null, '0.00', '0', null, '2017-04-18 16:02:04');
INSERT INTO `t_user` VALUES ('8', '13758550137', 'lexiu_158764', '20170418', null, null, null, '2017-04-20 18:22:07', null, '0.00', '0', '柯南', '2017-04-18 16:05:19');
INSERT INTO `t_user` VALUES ('10', '18390915154', 'lexiu_872929', '666666', '666666', null, null, '2017-05-18 23:24:08', null, '10.00', '0', '武汉市', '2017-04-20 21:30:47');
INSERT INTO `t_user` VALUES ('12', '13806750760', 'user_773340', '114563', null, null, null, null, null, '0.00', '0', null, '2017-05-17 10:48:35');
INSERT INTO `t_user` VALUES ('13', '13161351351', 'user_292920', 'kube0312', null, null, null, null, null, '0.00', '0', null, '2017-05-17 19:30:01');
INSERT INTO `t_user` VALUES ('14', '17706511924', 'user_670206', '123456', null, null, null, null, null, '0.00', '0', null, '2017-06-12 13:57:00');
INSERT INTO `t_user` VALUES ('15', '18811032154', 'user_435364', 'z111111', null, null, null, null, null, '0.00', '0', null, '2017-06-13 11:42:00');
INSERT INTO `t_user` VALUES ('16', '18158108007', 'user_147335', '11111111', '222222', null, null, '2017-06-29 16:49:06', null, '74.50', '0', null, '2017-06-29 15:07:59');
INSERT INTO `t_user` VALUES ('17', '17557289941', 'user_603480', '123456', null, null, null, null, null, '0.00', '0', null, '2017-06-29 15:39:46');
INSERT INTO `t_user` VALUES ('18', '15906614704', 'user_054064', '123456', null, null, null, null, null, '0.00', '0', null, '2017-07-05 15:17:27');

-- ----------------------------
-- Table structure for t_user_bankcard
-- ----------------------------
DROP TABLE IF EXISTS `t_user_bankcard`;
CREATE TABLE `t_user_bankcard` (
  `cardid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `cardnumber` varchar(50) NOT NULL,
  `bankname` varchar(250) NOT NULL,
  `username` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `type` smallint(1) NOT NULL DEFAULT '0' COMMENT '0.储蓄卡  1.信用卡',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cardid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_bankcard
-- ----------------------------
INSERT INTO `t_user_bankcard` VALUES ('1', '1', '6432987203775974302', '中国建设银行', '张家富', '12342137446', '0', '2017-04-21 15:00:34');

-- ----------------------------
-- Table structure for t_withdraw
-- ----------------------------
DROP TABLE IF EXISTS `t_withdraw`;
CREATE TABLE `t_withdraw` (
  `withdrawid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `persionid` int(11) NOT NULL,
  `monery` decimal(11,2) NOT NULL,
  `account` varchar(250) NOT NULL,
  `userorwork` smallint(1) NOT NULL,
  `isok` smallint(1) NOT NULL DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`withdrawid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_withdraw
-- ----------------------------
INSERT INTO `t_withdraw` VALUES ('1', '1', '100.00', '123123', '0', '1', '2017-05-09 15:54:59');
INSERT INTO `t_withdraw` VALUES ('2', '1', '100.00', '123123', '0', '1', '2017-05-09 15:54:59');
INSERT INTO `t_withdraw` VALUES ('3', '1', '11.00', '11111', '0', '1', '2017-06-06 16:07:06');

-- ----------------------------
-- Table structure for t_worker
-- ----------------------------
DROP TABLE IF EXISTS `t_worker`;
CREATE TABLE `t_worker` (
  `workerid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `companyid` int(11) DEFAULT NULL,
  `phone` varchar(50) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `paypassword` varchar(50) DEFAULT NULL,
  `avatar` varchar(250) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `contact_tel` varchar(50) DEFAULT NULL COMMENT '紧急联系方式',
  `tel` varchar(50) DEFAULT NULL COMMENT '联系方式',
  `companies_tel` varchar(50) DEFAULT NULL COMMENT '所属公司的联系方式',
  `identity_front` varchar(250) DEFAULT NULL COMMENT '身份证正面图片',
  `identity_rear` varchar(250) DEFAULT NULL COMMENT '身份证照片反面',
  `credentials_front` varchar(250) DEFAULT NULL COMMENT '证件照正面',
  `credentials_rear` varchar(250) DEFAULT NULL COMMENT '证件照片反面',
  `skill` varchar(250) DEFAULT NULL COMMENT '擅长技能',
  `grade` float(8,2) NOT NULL DEFAULT '5.00',
  `money` decimal(10,2) NOT NULL DEFAULT '0.00',
  `score` int(50) NOT NULL DEFAULT '0',
  `idcard` varchar(250) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `isok` smallint(1) DEFAULT '0',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`workerid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_worker
-- ----------------------------
INSERT INTO `t_worker` VALUES ('1', '1', '12345678910', '王师傅', '123456', null, 'lexiu/upload/123.jpg', '男', '1980-02-12', '20', '1784569852', '1578452365', null, 'lexiu/upload/123.jpg', 'lexiu/upload/123.jpg', 'lexiu/upload/123.jpg', 'lexiu/upload/123.jpg', '饭打发额为', '2.44', '2429.80', '0', null, '1', '1', '2017-03-09 10:59:06');
INSERT INTO `t_worker` VALUES ('19', '0', '18390915154', 'sadsa', '666666', null, null, '女', '1111-11-11', '906', '0', '11111111', '', 'lexiu/upload/Tulips.jpg', 'lexiu/upload/Koala.jpg', 'lexiu/upload/Lighthouse.jpg', 'lexiu/upload/Tulips.jpg', '水电维修', '3.00', '832.28', '0', '0', '4', '1', '2017-05-12 16:29:26');
INSERT INTO `t_worker` VALUES ('20', null, '13918176139', 'work_973955', '123qwe', null, null, null, null, null, null, null, null, null, null, null, null, null, '5.00', '0.00', '0', null, null, '0', '2017-05-17 11:17:58');
INSERT INTO `t_worker` VALUES ('21', '0', '17706511924', 'work_735318', '123456', null, null, null, null, null, null, null, null, null, null, null, null, null, '5.00', '0.00', '0', null, null, '0', '2017-06-12 13:56:27');
INSERT INTO `t_worker` VALUES ('22', '0', '17557289941', '11', '123456', null, null, '女', '1111-11-11', '906', '1784569852', '0', '', 'lexiu/upload/323769329225614717.jpg', 'lexiu/upload/323769329225614717.jpg', 'lexiu/upload/323769329225614717.jpg', 'lexiu/upload/323769329225614717.jpg', '电器维修', '0.00', '0.00', '0', '0', '2', '0', '2017-06-30 16:16:19');
INSERT INTO `t_worker` VALUES ('28', '1', '15906614704', '震震', '123456', null, null, '女', '1992-02-13', '25', '12345678900', '12345678910', '15687942554', 'lexiu/upload/549738472615065747.png', 'lexiu/upload/549738472615065747.png', 'lexiu/upload/549738472615065747.png', 'lexiu/upload/549738472615065747.png', '电器维修,水管维修', '0.00', '0.00', '0', '432232199202132345', '1', '0', '2017-07-05 18:56:29');
INSERT INTO `t_worker` VALUES ('29', '0', '17826857579', 'work_209821', '123456', null, null, null, null, null, null, null, null, null, null, null, null, null, '5.00', '0.00', '0', null, null, '0', '2017-07-13 11:24:56');
INSERT INTO `t_worker` VALUES ('30', '0', '13806750760', 'work_069216', '13806750760', null, null, null, null, null, null, null, null, null, null, null, null, null, '5.00', '0.00', '0', null, null, '0', '2017-07-14 16:32:17');
INSERT INTO `t_worker` VALUES ('31', '0', '18077401327', '韦爱', '123456', null, null, '男', '1993-07-07', '24', '18077401327', '18077401327', '', 'lexiu/upload/549738472615065747.png', 'lexiu/upload/549738472615065747.png', 'lexiu/upload/549738472615065747.png', 'lexiu/upload/549738472615065747.png', '电器维修', '5.00', '0.00', '0', '430674199306371327', '1', '1', '2017-07-17 15:41:10');

-- ----------------------------
-- Table structure for t_worker_bankcard
-- ----------------------------
DROP TABLE IF EXISTS `t_worker_bankcard`;
CREATE TABLE `t_worker_bankcard` (
  `cardid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `workerid` int(11) NOT NULL,
  `cardnumber` varchar(50) NOT NULL,
  `bankname` varchar(250) NOT NULL,
  `username` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `type` smallint(1) NOT NULL DEFAULT '0' COMMENT '0.储蓄卡  1.信用卡',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cardid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_worker_bankcard
-- ----------------------------

-- ----------------------------
-- Table structure for t_worker_gps
-- ----------------------------
DROP TABLE IF EXISTS `t_worker_gps`;
CREATE TABLE `t_worker_gps` (
  `gpsid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `workerid` int(11) NOT NULL,
  `longitude` decimal(11,8) DEFAULT NULL,
  `latitude` decimal(11,8) DEFAULT NULL,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`gpsid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_worker_gps
-- ----------------------------
INSERT INTO `t_worker_gps` VALUES ('1', '1', '120.13536737', '30.31875229', '2017-07-14 11:32:09');
INSERT INTO `t_worker_gps` VALUES ('9', '19', '120.21937542', '30.25924446', '2017-05-27 15:29:13');
INSERT INTO `t_worker_gps` VALUES ('10', '20', null, null, '2017-05-18 22:54:05');
INSERT INTO `t_worker_gps` VALUES ('11', '21', '120.13546670', '30.31895702', '2017-06-12 13:57:24');
INSERT INTO `t_worker_gps` VALUES ('12', '29', '120.13535840', '30.31875508', '2017-07-13 11:47:13');
INSERT INTO `t_worker_gps` VALUES ('13', '30', null, null, '2017-07-14 16:32:17');

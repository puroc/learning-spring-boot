/*
Navicat MySQL Data Transfer

Source Server         : fcs
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : skeleton_shiro

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-05-02 17:15:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` tinyint(2) DEFAULT NULL COMMENT '权限类型',
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', null, '用户创建', null, 'admin:user:create');
INSERT INTO `permission` VALUES ('2', null, '用户查看', null, 'admin:user:view');
INSERT INTO `permission` VALUES ('3', null, '用户删除', null, 'admin:user:delete');
INSERT INTO `permission` VALUES ('4', null, '用户更新', null, 'admin:user:update');
INSERT INTO `permission` VALUES ('5', null, '角色查看', null, 'admin:role:view');
INSERT INTO `permission` VALUES ('6', null, '权限查看', null, 'admin:perm:view');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '管理员');
INSERT INTO `role` VALUES ('2', 'leader', '公司领导');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) DEFAULT NULL,
  `perid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1');
INSERT INTO `role_permission` VALUES ('2', '1', '2');
INSERT INTO `role_permission` VALUES ('3', '1', '3');
INSERT INTO `role_permission` VALUES ('4', '1', '4');
INSERT INTO `role_permission` VALUES ('5', '2', '1');
INSERT INTO `role_permission` VALUES ('6', '2', '5');
INSERT INTO `role_permission` VALUES ('7', '1', '6');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT '',
  `password` varchar(50) NOT NULL,
  `phone` varchar(255) DEFAULT '',
  `sex` tinyint(2) DEFAULT '1' COMMENT '1-男  2-女',
  `state` tinyint(2) DEFAULT '1' COMMENT '启用/禁用',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=839662116311633921 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'active', '31321', '15669852653', '2', '1', null);
INSERT INTO `user` VALUES ('836043268161261568', 'rabbit', '456', '15526538456', null, '1', null);
INSERT INTO `user` VALUES ('837134894975549440', 'nginx', '456', '15526538456', null, '1', null);
INSERT INTO `user` VALUES ('837135134361255936', 'cat', '456', '15526538456', '1', '0', null);
INSERT INTO `user` VALUES ('837135380675952640', 'tom', '456', '15526538456', '1', '1', '一个小角色');
INSERT INTO `user` VALUES ('837135380675952641', 'admin', '123', null, '1', '1', '这是大boss');
INSERT INTO `user` VALUES ('839659443315163136', 'lua', '456', '15526538456', '1', '1', null);
INSERT INTO `user` VALUES ('839660000863993856', 'golang', '456', '15526538456', '1', '1', '不足为患');
INSERT INTO `user` VALUES ('839660220125437952', 'resin', '456', '15526538456', '2', '1', null);
INSERT INTO `user` VALUES ('839660221983514624', 'jetty', '456', '15526538456', '2', '1', null);
INSERT INTO `user` VALUES ('839661736089612288', 'debbie', '456', '15526538456', '1', '1', null);
INSERT INTO `user` VALUES ('839662116311633920', 'keep', '456', '15526538456', '1', '1', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` bigint(20) DEFAULT NULL,
  `roleid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '837135380675952641', '1');
INSERT INTO `user_role` VALUES ('2', '837135380675952641', '2');

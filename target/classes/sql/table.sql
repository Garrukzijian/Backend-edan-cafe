/*
 Navicat Premium Data Transfer

 Source Server         : Lei的Mysql数据库
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : table

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 21/10/2022 13:11:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `loginName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作用户登录名称',
  `operation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作文字',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方法',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作方法',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4564 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (4564, '华仔', 'admin', '获取左边菜单栏的页面', 'com.whut.springbootshiro.controller.SysPermissionController.getUserMenu', '[]', '127.0.0.1', '2022-10-21 05:09:25');
INSERT INTO `sys_log` VALUES (4565, '华仔', 'admin', '获取权限，带有树形结构带有孩子的结构', 'com.whut.springbootshiro.controller.SysPermissionController.getAllWithChild', '[]', '127.0.0.1', '2022-10-21 05:09:30');
INSERT INTO `sys_log` VALUES (4566, '华仔', 'admin', '获取权限的页面的数据', 'com.whut.springbootshiro.controller.SysPermissionController.getPageList', '[{\"limit\":5,\"page\":1}]', '127.0.0.1', '2022-10-21 05:09:30');
INSERT INTO `sys_log` VALUES (4567, '华仔', 'admin', '删除权限', 'com.whut.springbootshiro.controller.SysPermissionController.delete', '[27]', '127.0.0.1', '2022-10-21 05:09:33');
INSERT INTO `sys_log` VALUES (4568, '华仔', 'admin', '获取权限，带有树形结构带有孩子的结构', 'com.whut.springbootshiro.controller.SysPermissionController.getAllWithChild', '[]', '127.0.0.1', '2022-10-21 05:09:34');
INSERT INTO `sys_log` VALUES (4569, '华仔', 'admin', '获取当前权限', 'com.whut.springbootshiro.controller.SysPermissionController.getCurPerm', '[]', '127.0.0.1', '2022-10-21 05:09:34');
INSERT INTO `sys_log` VALUES (4570, '华仔', 'admin', '获取权限的页面的数据', 'com.whut.springbootshiro.controller.SysPermissionController.getPageList', '[{\"limit\":5,\"page\":1}]', '127.0.0.1', '2022-10-21 05:09:34');
INSERT INTO `sys_log` VALUES (4571, '华仔', 'admin', '添加权限', 'com.whut.springbootshiro.controller.SysPermissionController.add', '[{\"icon\":\"el-icon-s-order\",\"sort\":92,\"title\":\"日志管理\",\"type\":1,\"parentId\":6,\"spread\":1,\"id\":29,\"href\":\"/log\",\"tag\":\"log:list\"}]', '127.0.0.1', '2022-10-21 05:10:43');
INSERT INTO `sys_log` VALUES (4572, '华仔', 'admin', '获取当前权限', 'com.whut.springbootshiro.controller.SysPermissionController.getCurPerm', '[]', '127.0.0.1', '2022-10-21 05:10:43');
INSERT INTO `sys_log` VALUES (4573, '华仔', 'admin', '获取权限，带有树形结构带有孩子的结构', 'com.whut.springbootshiro.controller.SysPermissionController.getAllWithChild', '[]', '127.0.0.1', '2022-10-21 05:10:43');
INSERT INTO `sys_log` VALUES (4574, '华仔', 'admin', '获取权限的页面的数据', 'com.whut.springbootshiro.controller.SysPermissionController.getPageList', '[{\"limit\":5,\"page\":1}]', '127.0.0.1', '2022-10-21 05:10:43');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `title` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限图标',
  `href` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限连接 菜单请求的URL地址',
  `spread` bit(1) NULL DEFAULT b'0' COMMENT '是否展开 1.展开 0.不展开',
  `type` int(0) NOT NULL COMMENT '权限类型 1.菜单 2.按钮',
  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限自定义标识',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序 越大越靠前',
  `parent_id` int(0) NOT NULL DEFAULT 0 COMMENT '父权限ID 默认0 表示一级菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (6, '系统管理', 'el-icon-setting', '#', b'1', 1, 'sys:admin', 100, 0);
INSERT INTO `sys_permission` VALUES (7, '用户管理', 'el-icon-user', '/user', b'0', 1, 'user:list', 95, 6);
INSERT INTO `sys_permission` VALUES (8, '角色管理', 'el-icon-menu', '/roles', b'0', 1, 'role:list', 94, 6);
INSERT INTO `sys_permission` VALUES (9, '权限管理', 'el-icon-place', '/perm', b'0', 1, 'permission:list', 93, 6);
INSERT INTO `sys_permission` VALUES (29, '日志管理', 'el-icon-s-order', '/log', b'1', 1, 'log:list', 92, 6);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色标识',
  `descp` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', '管理员');
INSERT INTO `sys_role` VALUES (6, '学生', 'student', '主要的借用对象');

-- ----------------------------
-- Table structure for sys_role_per_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_per_rel`;
CREATE TABLE `sys_role_per_rel`  (
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `per_id` int(0) NOT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_per_rel
-- ----------------------------
INSERT INTO `sys_role_per_rel` VALUES (1, 6);
INSERT INTO `sys_role_per_rel` VALUES (1, 7);
INSERT INTO `sys_role_per_rel` VALUES (1, 8);
INSERT INTO `sys_role_per_rel` VALUES (1, 9);
INSERT INTO `sys_role_per_rel` VALUES (6, 6);
INSERT INTO `sys_role_per_rel` VALUES (6, 7);
INSERT INTO `sys_role_per_rel` VALUES (1, 29);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `login_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `realname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `id_card` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号',
  `sex` int(0) NOT NULL COMMENT '性别  1 男  2 女',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '地址',
  `img` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图像',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '46eb73c57df9aa4243b445f0bffa6d38', '13888888888', '华仔', '411111111111111111', 1, '香港', 'imgs/20210814164611.jpg', '2020-11-30 15:20:55');
INSERT INTO `sys_user` VALUES (2, 'xiaowu', '46eb73c57df9aa4243b445f0bffa6d38', '18111111111', '吴彦祖', '421181111111111111', 1, '武汉', 'resources/images/face.jpg', '2020-12-01 19:39:49');
INSERT INTO `sys_user` VALUES (3, 'xiaojin', 'cafe5b9a578b701a1110cb22f733d17d', '18111111112', '金志贤', '411111111111111112', 2, '棒子国', 'imgs/20210818231924.jpg', '2020-12-01 19:41:41');

-- ----------------------------
-- Table structure for sys_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_rel`;
CREATE TABLE `sys_user_role_rel`  (
  `user_id` int(0) NOT NULL COMMENT '用户ID',
  `role_id` int(0) NOT NULL COMMENT '角色ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_rel
-- ----------------------------
INSERT INTO `sys_user_role_rel` VALUES (1, 1);
INSERT INTO `sys_user_role_rel` VALUES (2, 1);
INSERT INTO `sys_user_role_rel` VALUES (3, 6);

SET FOREIGN_KEY_CHECKS = 1;

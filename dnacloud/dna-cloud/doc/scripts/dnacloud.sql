/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : dnacloud

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 25/12/2020 15:03:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=FIXED COMMENT='序列';

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
BEGIN;
INSERT INTO `hibernate_sequence` VALUES (1);
COMMIT;

-- ----------------------------
-- Table structure for nt_data_source_config
-- ----------------------------
DROP TABLE IF EXISTS `nt_data_source_config`;
CREATE TABLE `nt_data_source_config` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `connect_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '数据源连接名称',
  `db_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据库类型',
  `ds_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据源类型',
  `db_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '数据库名称',
  `ip_address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据库连接地址',
  `port` int DEFAULT NULL COMMENT '端口号',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `driver_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '驱动名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `dept_code` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门编码',
  `dept_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_connect_name` (`connect_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='数据源';

-- ----------------------------
-- Records of nt_data_source_config
-- ----------------------------
BEGIN;
INSERT INTO `nt_data_source_config` VALUES ('4028e591736543e001736547e9950000', 'cloud', 'mysql', 'db', 'cloud', '192.168.1.1', 3306, 'root', '1234', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://192.168.1.1:3306/cloud', '2020-07-19 12:14:38', '2020-07-19 14:29:33', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for nt_gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `nt_gateway_route`;
CREATE TABLE `nt_gateway_route` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `filters` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `predicates` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `route_order` int NOT NULL,
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of nt_gateway_route
-- ----------------------------
BEGIN;
INSERT INTO `nt_gateway_route` VALUES ('bazl-dna-files', '[{"name":"StripPrefix","args":{"parts":"1"}}]', '[{"name":"Path","args":{"pattern":"/files/**"}}]', 1, 'lb://bazl-dna-files', '1');
INSERT INTO `nt_gateway_route` VALUES ('bazl-dna-monitor', '[{"args": {"parts": "1"}, "name": "StripPrefix"}]', '[{"args": {"pattern": "/monitor/**"}, "name": "Path"}]', 1, 'lb://bazl-dna-monitor', '1');
INSERT INTO `nt_gateway_route` VALUES ('bazl-dna-system', '[{"args": {"parts": "1"}, "name": "StripPrefix"}]', '[{"args": {"pattern": "/system/**"}, "name": "Path"}]', 1, 'lb://bazl-dna-system', '1');
INSERT INTO `nt_gateway_route` VALUES ('bazl-dna-elasticsearch', '[{"args": {"parts": "1"}, "name": "StripPrefix"}]', '[{"args": {"pattern": "/elasticsearch/**"}, "name": "Path"}]', 1, 'lb://bazl-dna-elasticsearch', '1');
INSERT INTO `nt_gateway_route` VALUES ('bazl-dna-generator', '[{"args": {"parts": "1"}, "name": "StripPrefix"}]', '[{"args": {"pattern": "/generator/**"}, "name": "Path"}]', 1, 'lb://bazl-dna-generator', '1');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_dict_item`;
CREATE TABLE `nt_sys_dict_item` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_item_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_item_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_item_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dict_item_order` int DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_dict_item_code` (`dict_item_code`) USING BTREE,
  KEY `idx_dict_item_type_id` (`dict_item_type_id`) USING BTREE,
  CONSTRAINT `fk_dict_item_type_id` FOREIGN KEY (`dict_item_type_id`) REFERENCES `nt_sys_dict_item_type` (`dict_type_code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典信息';

-- ----------------------------
-- Records of nt_sys_dict_item
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_dict_item` VALUES ('1101', 'sys_normal_disable', '1', '有效', 1, NULL, 1);
INSERT INTO `nt_sys_dict_item` VALUES ('1102', 'sys_normal_disable', '0', '无效', 2, NULL, 1);
INSERT INTO `nt_sys_dict_item` VALUES ('1103', 'sys_user_sex', '0', '男', 1, NULL, 1);
INSERT INTO `nt_sys_dict_item` VALUES ('1104', 'sys_user_sex', '1', '女', 2, NULL, 1);
INSERT INTO `nt_sys_dict_item` VALUES ('4028b88174aa011e0174aa0a28c30004', 'sys_task_group', 'DEFAULT', '默认', 1, NULL, 1);
INSERT INTO `nt_sys_dict_item` VALUES ('4028b88174aa011e0174aa0a51bc0005', 'sys_task_group', 'SYSTEM', '系统', 2, NULL, 1);
INSERT INTO `nt_sys_dict_item` VALUES ('4028b88174aa011e0174aa0b35eb0006', 'sys_task_status', '1', '正常', 1, NULL, 1);
INSERT INTO `nt_sys_dict_item` VALUES ('4028b88174aa011e0174aa0b66630007', 'sys_task_status', '0', '暂停', 2, NULL, 1);
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_dict_item_type
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_dict_item_type`;
CREATE TABLE `nt_sys_dict_item_type` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `dict_item_type_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分类名称',
  `dict_type_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_item_type_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分类描述',
  `dict_item_type_order` int DEFAULT NULL COMMENT '排序',
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_dict_type_code` (`dict_type_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典分类';

-- ----------------------------
-- Records of nt_sys_dict_item_type
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_dict_item_type` VALUES ('sys_normal_disable', '状态', 'sys_normal_disable', '22ee22', NULL, '1');
INSERT INTO `nt_sys_dict_item_type` VALUES ('sys_task_group', '任务分组', 'sys_task_group', '任务分组', NULL, '1');
INSERT INTO `nt_sys_dict_item_type` VALUES ('sys_task_status', '任务状态', 'sys_task_status', NULL, NULL, '1');
INSERT INTO `nt_sys_dict_item_type` VALUES ('sys_user_sex', '性别', 'sys_user_sex', '111', NULL, '1');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_job
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_job`;
CREATE TABLE `nt_sys_job` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_info` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `job_logo` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `job_logo_url` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `job_order` int NOT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_job_name` (`job_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户职位';

-- ----------------------------
-- Records of nt_sys_job
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_job` VALUES ('1', '研发部', '管理员', NULL, NULL, 1, '1', '1', '2020-04-24 14:31:41', '2020-09-05 20:43:36');
INSERT INTO `nt_sys_job` VALUES ('4028b881745e46ae01745e4c3cd90008', '财务部', '财务', NULL, NULL, 2, NULL, '0', '2020-09-05 20:44:49', '2020-09-18 22:36:12');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_log`;
CREATE TABLE `nt_sys_log` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `operation_ip` varchar(32) DEFAULT NULL COMMENT '操作IP',
  `method_name` varchar(8) DEFAULT NULL COMMENT '请求类型',
  `operation_name` varchar(255) DEFAULT NULL COMMENT '操作名称',
  `operation_path` varchar(255) DEFAULT NULL COMMENT '操作地址',
  `operation_context` text COMMENT '操作内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Table structure for nt_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_menu`;
CREATE TABLE `nt_sys_menu` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `menu_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `component` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `menu_order` int NOT NULL,
  `menu_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `another_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_menu_type_id` (`menu_type_id`) USING BTREE,
  KEY `idx_parent_id` (`parent_id`) USING BTREE,
  CONSTRAINT `fk_sys_menu_menu_type_id` FOREIGN KEY (`menu_type_id`) REFERENCES `nt_sys_menu_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统菜单';

-- ----------------------------
-- Records of nt_sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_menu` VALUES ('101', '1', '系统设置', '-1', 'Layout', '/system', 1, '系统设置', 'system', 1, '2020-05-11 14:00:21', '2020-05-11 14:00:25', NULL);
INSERT INTO `nt_sys_menu` VALUES ('1011', '1', '单位管理', '101', 'system/org/index', '/org', 11, '单位管理', 'tree', 1, '2020-05-11 14:03:06', '2020-05-11 14:03:11', NULL);
INSERT INTO `nt_sys_menu` VALUES ('1012', '1', '角色管理', '101', 'system/role/index', '/role', 12, '角色管理', 'peoples', 1, '2020-05-11 14:08:39', '2020-05-11 14:08:43', NULL);
INSERT INTO `nt_sys_menu` VALUES ('1013', '1', '用户职位', '101', 'system/job/index', '/job', 13, '用户职位', 'post', 1, '2020-08-05 21:48:33', '2020-08-05 21:48:37', NULL);
INSERT INTO `nt_sys_menu` VALUES ('1014', '1', '用户管理', '101', 'system/user/index', '/user', 14, '用户管理', 'user', 1, '2020-09-05 20:30:00', '2020-09-05 20:30:00', NULL);
INSERT INTO `nt_sys_menu` VALUES ('1015', '1', '字典管理', '101', 'system/dict/index', '/dict', 15, '字典管理', 'dict', 1, '2020-09-05 20:30:00', '2020-09-05 20:30:00', NULL);
INSERT INTO `nt_sys_menu` VALUES ('1016', '1', '菜单管理', '101', 'system/menu/index', '/menu', 16, '菜单管理', 'tree-table', 1, '2020-09-05 20:30:00', '2020-09-05 20:30:00', NULL);
INSERT INTO `nt_sys_menu` VALUES ('201', '1', '系统监控', '-1', 'Layout', '/monitor', 2, '系统监控', 'monitor', 1, '2020-09-03 17:20:13', '2020-09-17 20:57:13', NULL);
INSERT INTO `nt_sys_menu` VALUES ('2011', '1', '服务监控', '201', 'monitor/service/index', '/service', 21, '服务监控', 'druid', 1, '2020-09-03 17:21:32', '2020-09-17 20:57:02', NULL);
INSERT INTO `nt_sys_menu` VALUES ('2012', '1', '服务器监控', '201', 'monitor/server/index', '/server', 22, '服务器监控', 'server', 1, '2020-09-03 17:22:24', '2020-09-12 17:27:11', NULL);
INSERT INTO `nt_sys_menu` VALUES ('2013', '1', '定时任务', '201', 'monitor/task/index', '/task', 23, '定时任务', 'job', 1, '2020-09-19 11:57:23', '2020-09-19 11:57:25', NULL);
INSERT INTO `nt_sys_menu` VALUES ('2014', '1', '路由监控', '201', 'monitor/route/index', '/route', 24, '路由监控', 'cascader', 1, '2020-09-19 11:57:23', '2020-09-19 11:57:23', NULL);
INSERT INTO `nt_sys_menu` VALUES ('2015', '1', '缓存监控', '201', 'monitor/cache/index', '/cache', 25, NULL, 'time-range', 1, '2020-12-08 14:00:34', '2020-12-08 14:00:51', NULL);
INSERT INTO `nt_sys_menu` VALUES ('2016', '1', 'ES监控', '201', 'monitor/elasticsearch/index', '/elasticsearch', 26, 'ES监控', 'drag', 1, '2020-12-25 14:47:37', '2020-12-25 14:50:14', NULL);
INSERT INTO `nt_sys_menu` VALUES ('2017', '1', '队列监控', '201', 'monitor/rabbitmq/index', '/rabbitmq', 27, '队列监控', 'example', 1, '2020-12-25 14:48:35', '2020-12-25 14:50:19', NULL);
INSERT INTO `nt_sys_menu` VALUES ('2018', '1', '文档监控', '201', 'monitor/fastdfs/index', '/fastdfs', 28, '文档监控', 'documentation', 1, '2020-12-25 14:49:33', '2020-12-25 14:50:24', NULL);
INSERT INTO `nt_sys_menu` VALUES ('301', '1', '日志管理', '-1', 'Layout', '/log', 3, '日志管理', 'log', 1, '2020-11-16 15:36:00', '2020-11-16 15:36:00', NULL);
INSERT INTO `nt_sys_menu` VALUES ('3011', '1', '服务日志', '301', 'system/log/index', '/syslog', 31, '服务日志', 'druid', 1, '2020-11-16 15:36:00', '2020-11-16 15:36:00', NULL);
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_menu_oper
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_menu_oper`;
CREATE TABLE `nt_sys_menu_oper` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operation_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_operation_id` (`operation_id`) USING BTREE,
  KEY `idx_menu_id` (`menu_id`) USING BTREE,
  CONSTRAINT `fk_menu_oper_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `nt_sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_menu_oper_operation_id` FOREIGN KEY (`operation_id`) REFERENCES `nt_sys_operation` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统菜单操作';

-- ----------------------------
-- Table structure for nt_sys_menu_type
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_menu_type`;
CREATE TABLE `nt_sys_menu_type` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_type_code` (`type_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统菜单类型';

-- ----------------------------
-- Records of nt_sys_menu_type
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_menu_type` VALUES ('1', 'nt_sys', '统一用户认证', 1, '2013-01-01 10:00:00', '2013-01-01 10:00:00');
INSERT INTO `nt_sys_menu_type` VALUES ('2', 'nt_mix', '混合库', 1, '2013-01-01 10:00:00', '2013-01-01 10:00:00');
INSERT INTO `nt_sys_menu_type` VALUES ('3', 'nt_database', 'DNA数据库比对管理系统', 1, '2013-01-01 10:00:00', '2013-01-01 10:00:00');
INSERT INTO `nt_sys_menu_type` VALUES ('4', 'nt_case', '综合受理接案平台', 1, '2020-05-12 09:14:12', '2020-05-12 09:14:42');
INSERT INTO `nt_sys_menu_type` VALUES ('4028e5917365edc4017365efa54d0000', '222', '22', 1, '2020-07-19 00:00:00', '2020-07-19 00:00:00');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_message
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_message`;
CREATE TABLE `nt_sys_message` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `queue_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '队列类型',
  `queue_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '队列标识',
  `message_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `message_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '名称',
  `type_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字段id',
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '内容',
  `send_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '发送者',
  `receive_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '接收者',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_message_type` (`message_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统消息';

-- ----------------------------
-- Records of nt_sys_message
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_message` VALUES ('40288112729c3ca101729cab601d0000', 'sysMessageDirect', 'sysMessageKey', 'test', '测试', NULL, '内容', 'admin', 'admin', '2020-06-10 13:19:20', '2020-06-10 13:19:20');
INSERT INTO `nt_sys_message` VALUES ('ff80808174dcef8f0174dcf068ec0000', 'sysMessageDirect', 'sysMessageKey', 'test', '测试', NULL, '123456', 'admin', 'admin', '2020-09-30 10:56:17', NULL);
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_operation
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_operation`;
CREATE TABLE `nt_sys_operation` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operation_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operation_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统操作';

-- ----------------------------
-- Records of nt_sys_operation
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_operation` VALUES ('1', 'select', '查询', '1', '2013-01-01 10:00:00', '2013-01-01 10:00:00');
INSERT INTO `nt_sys_operation` VALUES ('2', 'add', '添加', '1', '2013-01-01 10:00:00', '2013-01-01 10:00:00');
INSERT INTO `nt_sys_operation` VALUES ('3', 'edit', '编辑', '1', '2013-01-01 10:00:00', '2020-07-19 13:09:58');
INSERT INTO `nt_sys_operation` VALUES ('4', 'save', '保存', '1', '2013-01-01 10:00:00', '2020-07-19 14:12:47');
INSERT INTO `nt_sys_operation` VALUES ('5', 'delete', '删除', '1', '2013-01-01 10:00:00', '2013-01-01 10:00:00');
INSERT INTO `nt_sys_operation` VALUES ('6', 'imported', '导入', '1', '2013-01-01 10:00:00', '2013-01-01 10:00:00');
INSERT INTO `nt_sys_operation` VALUES ('7', 'exported', '导出', '1', '2013-01-01 10:00:00', '2013-01-01 10:00:00');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_organization`;
CREATE TABLE `nt_sys_organization` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位名称',
  `org_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构代码',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父节点',
  `ancestors` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `org_order` int DEFAULT NULL COMMENT '排序',
  `org_area_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '行政区划代码',
  `org_credit_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '统一社会信用代码',
  `org_logo` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Logo',
  `org_logo_url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Logo地址',
  `org_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `business_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务负责人姓名',
  `business_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务负责人电话',
  `technical_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技术负责人姓名',
  `technical_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '技术负责人电话',
  `config_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sys_org_config_id` (`config_id`) USING BTREE,
  KEY `idx_sys_org_org_code` (`org_code`) USING BTREE,
  KEY `idx_sys_org_parent_id` (`parent_id`) USING BTREE,
  CONSTRAINT `fk_sys_org_config_id` FOREIGN KEY (`config_id`) REFERENCES `nt_data_source_config` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='机构信息';

-- ----------------------------
-- Records of nt_sys_organization
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_organization` VALUES ('110101', '北京市公安局东城分局', '110101000000', '13', '-1,2,13', 1, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110102', '北京市公安局西城分局', '110102000000', '13', '-1,2,13', 2, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110105', '北京市公安局朝阳分局', '110105000000', '13', '-1,2,13', 3, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110106', '北京市公安局丰台分局', '110106000000', '13', '-1,2,13', 4, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110107', '北京市公安局石景山分局', '110107000000', '13', '-1,2,13', 5, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110108', '北京市公安局海淀分局', '110108000000', '13', '-1,2,13', 6, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110109', '北京市公安局门头沟分局', '110109000000', '13', '-1,2,13', 7, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110111', '北京市公安局房山分局', '110111000000', '13', '-1,2,13', 8, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110112', '北京市公安局通州分局', '110112000000', '13', '-1,2,13', 9, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110113', '北京市公安局顺义分局', '110113000000', '13', '-1,2,13', 10, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110114', '北京市公安局昌平分局', '110114000000', '13', '-1,2,13', 11, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110115', '北京市公安局大兴分局', '110115000000', '13', '-1,2,13', 12, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110116', '北京市公安局怀柔分局', '110116000000', '13', '-1,2,13', 13, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110117', '北京市公安局平谷分局', '110117000000', '13', '-1,2,13', 14, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110228', '北京市公安局密云分局', '110228000000', '13', '-1,2,13', 15, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('110229', '北京市公安局延庆分局', '110229000000', '13', '-1,2,13', 16, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('13', '北京市公安局治安管理总队', '110000200000', '2', '-1,2', 17, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('14', '北京市公安局刑事侦查总队', '110000210000', '2', '-1,2', 18, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('15', '北京市公安局公共交通安全保卫总队', '110084000000', '2', '-1,2', 19, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('16', '北京市公安局内部单位保卫局', '110086000000', '2', '-1,2', 20, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('17', '北京市公安局经济犯罪侦查总队', '110317000000', '2', '-1,2', 21, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('18', '北京市公安局便衣侦查总队', '110318000000', '2', '-1,2', 22, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('19', '北京市公安局禁毒总队', '110319000000', '2', '-1,2', 23, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('2', '北京市公安局', '110000000000', '-1', '-1', 1, NULL, NULL, NULL, NULL, NULL, '1', '1', '2020-06-11 17:33:58', '2020-09-17 21:21:04', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('20', '北京市公安局公安交通管理局', '110320000000', '2', '-1,2', 24, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('21', '北京海关缉私局', '110000H00500', '2', '-1,2', 25, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('22', '北京市森林公安局', '110000S00500', '2', '-1,2', 26, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('3', '北京市法医中心', '110000000000', '2', '-1,2', 2, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-12 13:12:49', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('31', '北京市公安局天安门地区分局', '110091000000', '2', '-1,2', 27, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('32', '北京市公安局北京西站分局', '110092000000', '2', '-1,2', 28, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('33', '北京市公安局经济技术开发区分局', '110095000000', '2', '-1,2', 29, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('34', '北京市公安局清河分局', '110097000000', '2', '-1,2', 30, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `nt_sys_organization` VALUES ('4', '北京市法医中心实验室', '110000000001', '3', '-1,2,3', 1, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-06-11 17:33:58', NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_role`;
CREATE TABLE `nt_sys_role` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_des` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统角色';

-- ----------------------------
-- Records of nt_sys_role
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_role` VALUES ('1', '22222222', '超管', '权限最大用户', '1', '2018-10-10 16:08:02', '2020-12-25 14:49:43');
INSERT INTO `nt_sys_role` VALUES ('ff80808172a150110172a159d9c40000', '1591844919746', '职员', '普通用户', '1', '2020-06-11 11:08:40', '2020-09-19 12:00:16');
INSERT INTO `nt_sys_role` VALUES ('ff80808172a624c00172a67c70c30007', '3333', '警员', '一般警员', '0', '2020-06-12 11:04:33', '2020-09-18 22:47:00');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_role_menu`;
CREATE TABLE `nt_sys_role_menu` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_menu_menu_id` (`menu_id`) USING BTREE,
  KEY `idx_role_menu_role_id` (`role_id`) USING BTREE,
  KEY `idx_role_menu_menu_type_id` (`menu_type_id`) USING BTREE,
  CONSTRAINT `fk_role_menu_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `nt_sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_menu_menu_type_id` FOREIGN KEY (`menu_type_id`) REFERENCES `nt_sys_menu_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_menu_role_id` FOREIGN KEY (`role_id`) REFERENCES `nt_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单权限';

-- ----------------------------
-- Records of nt_sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_role_menu` VALUES ('11101', '101', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('111011', '1011', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('111012', '1012', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('111013', '1013', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('111014', '1014', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('111015', '1015', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('111016', '1016', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('11201', '201', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('112011', '2011', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('112012', '2012', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('112013', '2013', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('112014', '2014', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('112015', '2015', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('112016', '2016', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('112017', '2017', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('112018', '2018', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('11301', '301', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('113011', '3011', '1', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('ff80808172a150110172a159d9c400001101', '101', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('ff80808172a150110172a159d9c400001201', '201', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('ff80808172a150110172a159d9c4000012011', '2011', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('ff80808172a150110172a159d9c4000012012', '2012', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO `nt_sys_role_menu` VALUES ('ff80808172a150110172a159d9c4000012013', '2013', 'ff80808172a150110172a159d9c40000', '1');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_role_menu_oper
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_role_menu_oper`;
CREATE TABLE `nt_sys_role_menu_oper` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `operation_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_role_menu_oper_menu_id` (`menu_id`) USING BTREE,
  KEY `idx_role_menu_oper_operation_id` (`operation_id`) USING BTREE,
  KEY `idx_role_menu_oper_role_id` (`role_id`) USING BTREE,
  KEY `idx_role_menu_oper_menu_type_id` (`menu_type_id`) USING BTREE,
  CONSTRAINT `fk_role_menu_oper_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `nt_sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_menu_oper_menu_type_id` FOREIGN KEY (`menu_type_id`) REFERENCES `nt_sys_menu_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_menu_oper_operation_id` FOREIGN KEY (`operation_id`) REFERENCES `nt_sys_menu_oper` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_menu_oper_role_id` FOREIGN KEY (`role_id`) REFERENCES `nt_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单操作权限';

-- ----------------------------
-- Table structure for nt_sys_sequence
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_sequence`;
CREATE TABLE `nt_sys_sequence` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `table_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '表名称',
  `sequence` int DEFAULT NULL COMMENT '序列',
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='序列';

-- ----------------------------
-- Table structure for nt_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_user`;
CREATE TABLE `nt_sys_user` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '性别',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证件号码',
  `last_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `user_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '证件类型',
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态1有效, 0无效',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `is_admin` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '是否为管理员',
  `active_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `default_password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户';

-- ----------------------------
-- Records of nt_sys_user
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_user` VALUES ('1', 'admin', 'C4CA4238A0B923820DCC509A6F75849B', '超管', 'https://www.oner365.com/group1/M00/00/04/rBqcll9Qsc-ADt_9AAAk7-agPWw680.jpg', '0', '1', 'admin@qq.com', '110103197707250933', '', '2020-09-04 15:53:30', '13800138000', '', 'beijing', '1', '123', '1', '1', '123456', '2018-10-09 14:19:44');
INSERT INTO `nt_sys_user` VALUES ('2', 'shy', 'C4CA4238A0B923820DCC509A6F75849B', '张3', '', '0', '1', NULL, NULL, NULL, NULL, '13800138000', NULL, NULL, '1', NULL, '0', '1', '123456', '2020-05-12 09:47:07');
INSERT INTO `nt_sys_user` VALUES ('5', 'ls', 'C4CA4238A0B923820DCC509A6F75849B', '王老师', '', '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, '1', '1', NULL, '2020-05-12 17:05:23');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_user_job
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_user_job`;
CREATE TABLE `nt_sys_user_job` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `position_order` int NOT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `job_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_job_job_id` (`job_id`) USING BTREE,
  KEY `idx_user_job_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `fk_user_job_job_id` FOREIGN KEY (`job_id`) REFERENCES `nt_sys_job` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_job_user_id` FOREIGN KEY (`user_id`) REFERENCES `nt_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户职位权限';

-- ----------------------------
-- Records of nt_sys_user_job
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_user_job` VALUES ('4028b881745ea0f901745ea77e07001a', 1, '1', '2020-09-05 22:24:29', NULL, '1', '1');
INSERT INTO `nt_sys_user_job` VALUES ('ff8080817640f2d4017640f8fca70001', 1, '1', '2020-12-08 14:10:28', NULL, '1', '2');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_user_org`;
CREATE TABLE `nt_sys_user_org` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `position_order` int NOT NULL,
  `status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `org_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_org_org_id` (`org_id`) USING BTREE,
  KEY `idx_user_org_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `fk_user_org_org_id` FOREIGN KEY (`org_id`) REFERENCES `nt_sys_organization` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_org_user_id` FOREIGN KEY (`user_id`) REFERENCES `nt_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户职位权限';

-- ----------------------------
-- Records of nt_sys_user_org
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_user_org` VALUES ('4028b881745ea0f901745ea77e0f001b', 1, '1', '2020-09-05 22:24:29', NULL, '3', '1');
INSERT INTO `nt_sys_user_org` VALUES ('4028b881745ea0f901745ea77e14001c', 1, '1', '2020-09-05 22:24:29', NULL, '4', '1');
INSERT INTO `nt_sys_user_org` VALUES ('4028b881745ea0f901745ea77e19001d', 1, '1', '2020-09-05 22:24:29', NULL, '110101', '1');
INSERT INTO `nt_sys_user_org` VALUES ('4028b881745ea0f901745ea77e23001e', 1, '1', '2020-09-05 22:24:29', NULL, '110102', '1');
INSERT INTO `nt_sys_user_org` VALUES ('4028b881745ea0f901745ea77e2a001f', 1, '1', '2020-09-05 22:24:29', NULL, '110105', '1');
INSERT INTO `nt_sys_user_org` VALUES ('4028b881745ea0f901745ea77e320020', 1, '1', '2020-09-05 22:24:29', NULL, '14', '1');
INSERT INTO `nt_sys_user_org` VALUES ('4028b881745ea0f901745ea77e3a0021', 1, '1', '2020-09-05 22:24:29', NULL, '15', '1');
INSERT INTO `nt_sys_user_org` VALUES ('4028b881745ea0f901745ea77e420022', 1, '1', '2020-09-05 22:24:29', NULL, '16', '1');
INSERT INTO `nt_sys_user_org` VALUES ('ff8080817640f2d4017640f8fcb10002', 1, '1', '2020-12-08 14:10:28', NULL, '110101', '2');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_user_role`;
CREATE TABLE `nt_sys_user_role` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_user_role_role_id` (`role_id`) USING BTREE,
  KEY `idx_user_role_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `fk_user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `nt_sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `nt_sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色权限';

-- ----------------------------
-- Records of nt_sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_user_role` VALUES ('4028b881745ea0f901745ea77dfa0018', '1', '1');
INSERT INTO `nt_sys_user_role` VALUES ('4028b881745ea0f901745ea77dfe0019', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO `nt_sys_user_role` VALUES ('ff80808172a7b2ce0172bacc3b9c0009', '1', '5');
INSERT INTO `nt_sys_user_role` VALUES ('ff8080817640f2d4017640f8fc990000', 'ff80808172a150110172a159d9c40000', '2');
COMMIT;

-- ----------------------------
-- Table structure for nt_sys_fastdfs_file
-- ----------------------------
DROP TABLE IF EXISTS `nt_sys_fastdfs_file`;
CREATE TABLE `nt_sys_fastdfs_file`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `file_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `display_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示名称',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `file_suffix` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `file_size` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件大小',
  `fastdfs_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件地址',
  `is_directory` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否目录',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='文件信息';

-- ----------------------------
-- Records of nt_sys_fastdfs_file
-- ----------------------------
BEGIN;
INSERT INTO `nt_sys_fastdfs_file` VALUES ('group1/M00/00/00/wKhlgl71yDKAHZxUAAeFJC8wk5A780.jpg', 'wKhlgl71yDKAHZxUAAeFJC8wk5A780.jpg', '3242.jpg', 'http://192.168.101.130/group1/M00/00/00/wKhlgl71yDKAHZxUAAeFJC8wk5A780.jpg', 'jpg', '481 KB', 'http://192.168.101.130', '0', '2021-02-22 16:53:56');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_mysql
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 06/07/2021 16:08:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) DEFAULT NULL,
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) DEFAULT NULL,
  `c_use` varchar(64) DEFAULT NULL,
  `effect` varchar(64) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (1, 'application-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # servlet file\n  servlet:\n    multipart:\n      enabled: true\n      max-file-size: 1024MB\n      max-request-size: 1024MB\n\n  main:\n    allow-bean-definition-overriding: true\n\n  data:\n    redis:\n      repositories:\n        enabled: false\n    \n  # redis\n  redis:\n    enable: true\n    host: localhost\n    port: 6379\n    password:\n    database: 0\n    timeout: 10s\n    lettuce:\n      pool:\n        min-idle: 0\n        max-idle: 8\n        max-active: 8\n        max-wait: -1ms\n\n  # rabbitmq\n  rabbitmq:\n    host: localhost\n    port: 5672\n    username: admin\n    password: admin123\n    connection-timeout: 6000\n\n  jpa:\n    open-in-view: true\n\n  # 线程池\n  task:\n    scheduling:\n      thread-name-prefix: SyncDataTask-\n    execution:\n      pool:\n        allow-core-thread-timeout: false\n        max-size: 60\n        core-size: 10\n        queue-capacity: 100\n        keep-alive: 90\n\n# 请求处理的超时时间\nribbon:\n  ReadTimeout: 10000\n  ConnectTimeout: 10000\n\n# feign 配置\nfeign:\n  sentinel:\n    enabled: true\n  okhttp:\n    enabled: true\n  httpclient:\n    enabled: false\n  client:\n    config:\n      default:\n        connectTimeout: 10000\n        readTimeout: 10000\n  compression:\n    request:\n      enabled: true\n    response:\n      enabled: true\n\n# 暴露监控端点\nmanagement:\n  endpoints:\n    web:\n      exposure:\n        include: \'*\'\n\n# Access Token\nACCESS_TOKEN_SECRET: test\nACCESS_TOKEN_EXPIRY_MIN: 14400\n# 白名单\nno_author: /api,/system/auth,/system/file/download\n\n# 文件上传下载地址\nfile:\n  # web访问地址  如不使用fastdfs 访问web路径\n  web: http://localhost/uploads\n  # 上传地址\n  path: /Users/zhaoyong/Downloads\n  excel:\n    suffix: .xlsx\n\n    ', '51b769c9ccbb5450745149bd02530e4c', '2021-07-04 06:36:29', '2021-07-06 02:35:27', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (2, 'bazl-dna-system-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # datasource\n  datasource:\n    druid:\n      db-type: com.alibaba.druid.pool.DruidDataSource\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      url: jdbc:mysql://localhost:3306/dnacloud?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8\n      username: root\n      password: 1234\n      \n      test-while-idle: true\n      initial-size: 1\n      max-active: 10\n      min-idle: 1\n    \n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: com.bazl.dna.sys.entity\n\nlogging:\n  config: classpath:logback-spring.xml\n  ', '7e50879250f1f11e9c3863ec149098ee', '2021-07-04 06:37:04', '2021-07-06 02:34:58', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (3, 'bazl-dna-gateway-dev.yml', 'DEFAULT_GROUP', 'spring:\n  cloud:\n    gateway:\n      discovery:\n        locator:\n          enabled: true\n          lower-case-service-id: true\n          \n  datasource:\n    #mysql\n    driver-class-name: com.mysql.cj.jdbc.Driver\n    url: jdbc:mysql://localhost:3306/dnacloud?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8\n    username: root\n    password: 1234\n    \n  jpa: \n    hibernate:\n      ddl-auto: update\n    show-sql: false\n    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\n    open-in-view: true\n      \n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8 \n\nlogging:\n  config: classpath:logback-spring.xml\n      \n', '56090bcca845378f742acfcafb2572e9', '2021-07-04 10:54:06', '2021-07-05 07:03:21', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (4, 'bazl-dna-monitor-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # datasource\n  datasource:\n    druid:\n      db-type: com.alibaba.druid.pool.DruidDataSource\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      url: jdbc:mysql://localhost:3306/dnacloud?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8\n      username: root\n      password: 1234\n      \n      test-while-idle: true\n      initial-size: 1\n      max-active: 10\n      min-idle: 1\n\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\n  jpa: \n    hibernate:\n      ddl-auto: update\n    show-sql: false\n    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect\n    open-in-view: true\n    \n  redis:\n    database: 1\n  \n  data:\n    redis:\n      repositories:\n        enabled: false\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: com.bazl.dna.monitor.entity\n\n#fdfs\nfdfs:\n  ip: 192.168.101.130\n  port: 22\n  user: root\n  password: root\n  storage:\n    path: /data/fastdfs-storage/data/M00/00\n  webServerUrl:          \n  \n#服务器配置  \nservers:\n  deploy: true\n  path: /usr/local/tomcat/dnacloud\n  version: 1.0.0-SNAPSHOT\n  suffix: jar\n  #部署多台服务器 必须包含ip 端口 账号 密码 逗号分隔\n  ip: 192.168.101.130\n  port: 22\n  username: root\n  password: root\n\nlogging:\n  config: classpath:logback-spring.xml\n  ', '8ebd6064c7afcd0e2ec517ff91df0ea5', '2021-07-04 12:15:05', '2021-07-06 06:28:53', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (5, 'bazl-dna-files-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # datasource\n  datasource:\n    druid:\n      db-type: com.alibaba.druid.pool.DruidDataSource\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      url: jdbc:mysql://localhost:3306/dnacloud?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8\n      username: root\n      password: 1234\n      \n      test-while-idle: true\n      initial-size: 1\n      max-active: 10\n      min-idle: 1\n      \n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8 \n        \n# FastDFS\nfdfs:\n  connect-timeout: 60\n  so-timeout: 1500\n  tracker-list: 192.168.101.130:22122\n  thumb-image:\n    height: 150\n    width: 150\n  web-server-url: http://192.168.101.130\n  ip: 192.168.101.130\n  port: 22\n  user: root\n  password: root\n  storage:\n    # 用于文件路径展示\n    path: /data/fastdfs-storage/data\n  pool:\n    #连接池最大数量\n    max-total: 200\n    #每个tracker地址的最大连接数\n    max-total-per-key: 50\n    #连接耗尽时等待获取连接的最大毫秒数\n    max-wait-millis: 5000\n\nlogging:\n  config: classpath:logback-spring.xml\n  ', '810e7b4096c25e2c79fe1af9c17e45ac', '2021-07-05 05:12:43', '2021-07-05 08:12:37', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (6, 'bazl-dna-elasticsearch-dev.yml', 'DEFAULT_GROUP', 'spring:\n  \n  autoconfigure:\n    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure\n    \n  elasticsearch:\n    rest:\n      connection-timeout: 60000\n      read-timeout: 60000\n      uris: localhost:9200\n  data:\n    elasticsearch:\n      repositories:\n        enabled: true\n      client:\n        reactive:\n          connection-timeout: 60000\n          socket-timeout: 60000\n          max-in-memory-size: 500MB\n\nlogging:\n  config: classpath:logback-spring.xml', 'e4141635f1f06570fec46c12aa16a33e', '2021-07-05 06:48:49', '2021-07-05 07:45:01', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (7, 'bazl-dna-generator-dev.yml', 'DEFAULT_GROUP', 'spring:\n  # datasource\n  datasource:\n    druid:\n      db-type: com.alibaba.druid.pool.DruidDataSource\n      driver-class-name: com.mysql.cj.jdbc.Driver\n      url: jdbc:mysql://localhost:3306/dnacloud?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=GMT%2B8\n      username: root\n      password: 1234\n      \n      test-while-idle: true\n      initial-size: 1\n      max-active: 10\n      min-idle: 1\n\n  jackson:\n    date-format: yyyy-MM-dd HH:mm:ss\n    time-zone: GMT+8\n\nmybatis:\n  mapper-locations: classpath:mapper/*.xml\n  type-aliases-package: com.bazl.dna.generator.entity\n\nlogging:\n  config: classpath:logback-spring.xml\n  ', 'cf76ae18dec8da6901375d30d6d0296d', '2021-07-05 08:01:44', '2021-07-05 08:30:31', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (8, 'bazl-dna-kafka-dev.yml', 'DEFAULT_GROUP', 'spring:\n\n  kafka:\n    bootstrap-servers: localhost:9092\n    listener:\n      missing-topics-fatal: false\n      \n    producer:\n      retries: 0\n      acks: 1\n      batch-size: 16384\n      buffer-memory: 33554432\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer   \n      properties:\n        linger:\n          ms: 0     \n      \n    consumer:\n      group-id: defaultConsumerGroup\n      enable-auto-commit: true\n      auto-offset-reset: latest\n      auto-commit-interval: 1000\n      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer\n      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer\n      properties:\n        session:\n          timeout:\n            ms: 120000\n        request:\n          timeout:\n            ms: 120000\n            \nlogging:\n  config: classpath:logback-spring.xml', 'a996e85dfc3f65cfc02375ffefc19776', '2021-07-05 08:15:52', '2021-07-05 08:22:38', NULL, '0:0:0:0:0:0:0:1', '', '', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (20, 'sentinel-dna-gateway.json', 'DEFAULT_GROUP', '[\n    {\n        \"resource\": \"bazl-dna-system\",\n        \"count\": 500,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },\n	{\n        \"resource\": \"bazl-dna-monitor\",\n        \"count\": 1000,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },\n	{\n        \"resource\": \"bazl-dna-files\",\n        \"count\": 200,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },\n	{\n        \"resource\": \"bazl-dna-elasticsearch\",\n        \"count\": 300,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },\n	{\n        \"resource\": \"bazl-dna-kafka\",\n        \"count\": 300,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    },\n	{\n        \"resource\": \"bazl-dna-generator\",\n        \"count\": 300,\n        \"grade\": 1,\n        \"limitApp\": \"default\",\n        \"strategy\": 0,\n        \"controlBehavior\": 0\n    }\n]', 'f64e89fcf2c8a86d8fa4f01e432fbe37', '2021-07-06 07:04:18', '2021-07-06 07:04:18', NULL, '0:0:0:0:0:0:0:1', '', '', NULL, NULL, NULL, 'json', NULL);
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) NOT NULL COMMENT 'datum_id',
  `content` longtext NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='增加租户字段';

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='config_info_beta';

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(50) DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='config_info_tag';

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint NOT NULL COMMENT 'id',
  `tag_name` varchar(128) NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='config_tag_relation';

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint unsigned NOT NULL,
  `nid` bigint unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `src_ip` varchar(50) DEFAULT NULL,
  `op_type` char(10) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='多租户改造';

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `role` varchar(50) NOT NULL,
  `resource` varchar(255) NOT NULL,
  `action` varchar(8) NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户容量信息表';

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='tenant_info';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

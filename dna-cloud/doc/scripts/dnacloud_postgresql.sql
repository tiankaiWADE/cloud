create table hibernate_sequence
(
    next_val bigint
);

comment on table hibernate_sequence is '序列表';

alter table hibernate_sequence
    owner to postgres;

create table nt_data_source_config
(
    id           varchar(64) not null
        constraint nt_data_source_config_pkey
            primary key,
    connect_name varchar(64) not null,
    db_type      varchar(20),
    ds_type      varchar(20),
    db_name      varchar(50),
    ip_address   varchar(20),
    port         integer,
    user_name    varchar(20),
    password     varchar(32),
    driver_name  varchar(255),
    url          varchar(255),
    create_time  timestamp,
    update_time  timestamp,
    dept_code    varchar(40),
    dept_name    varchar(40)
);

comment on table nt_data_source_config is '数据源表';

comment on column nt_data_source_config.id is '主键';

comment on column nt_data_source_config.connect_name is '连接名称';

comment on column nt_data_source_config.db_type is '数据库类型';

comment on column nt_data_source_config.ds_type is '数据源类型';

comment on column nt_data_source_config.db_name is '数据库名称';

comment on column nt_data_source_config.ip_address is 'ip地址';

comment on column nt_data_source_config.port is '端口';

comment on column nt_data_source_config.user_name is '账号';

comment on column nt_data_source_config.password is '密码';

comment on column nt_data_source_config.driver_name is '驱动名称';

comment on column nt_data_source_config.url is '连接地址';

comment on column nt_data_source_config.create_time is '创建时间';

comment on column nt_data_source_config.update_time is '更新时间';

comment on column nt_data_source_config.dept_code is '部门编号';

comment on column nt_data_source_config.dept_name is '部门名称';

alter table nt_data_source_config
    owner to postgres;

create unique index uk_dsc_connect_name
    on nt_data_source_config (connect_name);

INSERT INTO nt_data_source_config (id, connect_name, db_type, ds_type, db_name, ip_address, port, user_name, password, driver_name, url, create_time, update_time, dept_code, dept_name) VALUES ('4028e591736543e001736547e9950000', 'cloud', 'mysql', 'db', 'cloud', '192.168.1.1', 3306, 'root', '1234', 'com.mysql.cj.jdbc.Driver', 'jdbc:mysql://192.168.1.1:3306/cloud', '2020-07-19 12:14:38.000000', '2020-07-19 14:29:33.000000', null, null);

create table nt_gateway_route
(
    id          varchar(255) not null
        constraint nt_gateway_route_pkey
            primary key,
    filters     varchar(255),
    predicates  varchar(255),
    route_order integer      not null,
    uri         varchar(255) not null,
    status      varchar(8)
);

comment on table nt_gateway_route is '路由表';

comment on column nt_gateway_route.id is '主键';

comment on column nt_gateway_route.filters is '过滤规则';

comment on column nt_gateway_route.predicates is '表达式';

comment on column nt_gateway_route.route_order is '排序';

comment on column nt_gateway_route.uri is '地址';

comment on column nt_gateway_route.status is '状态';

alter table nt_gateway_route
    owner to postgres;

INSERT INTO nt_gateway_route (id, filters, predicates, route_order, uri, status) VALUES ('bazl-dna-files', '[{"name":"StripPrefix","args":{"parts":"1"}}]', '[{"name":"Path","args":{"pattern":"/files/**"}}]', 1, 'lb://bazl-dna-files', '1');
INSERT INTO nt_gateway_route (id, filters, predicates, route_order, uri, status) VALUES ('bazl-dna-monitor', '[{"args": {"parts": "1"}, "name": "StripPrefix"}]', '[{"args": {"pattern": "/monitor/**"}, "name": "Path"}]', 1, 'lb://bazl-dna-monitor', '1');
INSERT INTO nt_gateway_route (id, filters, predicates, route_order, uri, status) VALUES ('bazl-dna-system', '[{"args": {"parts": "1"}, "name": "StripPrefix"}]', '[{"args": {"pattern": "/system/**"}, "name": "Path"}]', 1, 'lb://bazl-dna-system', '1');
INSERT INTO nt_gateway_route (id, filters, predicates, route_order, uri, status) VALUES ('bazl-dna-elasticsearch', '[{"args": {"parts": "1"}, "name": "StripPrefix"}]', '[{"args": {"pattern": "/elasticsearch/**"}, "name": "Path"}]', 1, 'lb://bazl-dna-elasticsearch', '1');
INSERT INTO nt_gateway_route (id, filters, predicates, route_order, uri, status) VALUES ('bazl-dna-generator', '[{"args": {"parts": "1"}, "name": "StripPrefix"}]', '[{"args": {"pattern": "/generator/**"}, "name": "Path"}]', 1, 'lb://bazl-dna-generator', '1');

create table nt_sys_dict_item_type
(
    id                   varchar(32) not null
        constraint nt_sys_dict_item_type_pkey
            primary key,
    dict_item_type_name  varchar(32),
    dict_type_code       varchar(32) not null,
    dict_item_type_des   varchar(255),
    dict_item_type_order integer,
    status               varchar(8)
);

comment on table nt_sys_dict_item_type is '字典类型表';

comment on column nt_sys_dict_item_type.id is '主键';

comment on column nt_sys_dict_item_type.dict_item_type_name is '类型名称';

comment on column nt_sys_dict_item_type.dict_type_code is '类型编号';

comment on column nt_sys_dict_item_type.dict_item_type_des is '描述';

comment on column nt_sys_dict_item_type.dict_item_type_order is '排序';

comment on column nt_sys_dict_item_type.status is '状态';

alter table nt_sys_dict_item_type
    owner to postgres;

create unique index nt_sys_dict_item_type_dict_type_code_uindex
    on nt_sys_dict_item_type (dict_type_code);

INSERT INTO nt_sys_dict_item_type (id, dict_item_type_name, dict_type_code, dict_item_type_des, dict_item_type_order, status) VALUES ('sys_normal_disable', '状态', 'sys_normal_disable', '22ee22', null, '1');
INSERT INTO nt_sys_dict_item_type (id, dict_item_type_name, dict_type_code, dict_item_type_des, dict_item_type_order, status) VALUES ('sys_task_group', '任务分组', 'sys_task_group', '任务分组', null, '1');
INSERT INTO nt_sys_dict_item_type (id, dict_item_type_name, dict_type_code, dict_item_type_des, dict_item_type_order, status) VALUES ('sys_task_status', '任务状态', 'sys_task_status', null, null, '1');
INSERT INTO nt_sys_dict_item_type (id, dict_item_type_name, dict_type_code, dict_item_type_des, dict_item_type_order, status) VALUES ('sys_user_sex', '性别', 'sys_user_sex', '111', null, '1');

create table nt_sys_dict_item
(
    id                varchar(255)      not null
        constraint nt_sys_dict_item_pkey
            primary key,
    dict_item_type_id varchar(32)       not null
        constraint idx_dict_item_type_id
            references nt_sys_dict_item_type,
    dict_item_code    varchar(32)       not null,
    dict_item_name    varchar(32),
    dict_item_order   integer,
    parent_id         varchar(255),
    status      varchar(8)
);

comment on table nt_sys_dict_item is '字典表';

comment on column nt_sys_dict_item.id is '主键';

comment on column nt_sys_dict_item.dict_item_type_id is '字典类型编号';

comment on column nt_sys_dict_item.dict_item_code is '字典编号';

comment on column nt_sys_dict_item.dict_item_name is '字典名称';

comment on column nt_sys_dict_item.dict_item_order is '排序';

comment on column nt_sys_dict_item.parent_id is '上级id';

comment on column nt_sys_dict_item.status is '状态';

alter table nt_sys_dict_item
    owner to postgres;

INSERT INTO nt_sys_dict_item (id, dict_item_type_id, dict_item_code, dict_item_name, dict_item_order, parent_id, status) VALUES ('1101', 'sys_normal_disable', '1', '有效', 1, null, '1');
INSERT INTO nt_sys_dict_item (id, dict_item_type_id, dict_item_code, dict_item_name, dict_item_order, parent_id, status) VALUES ('1102', 'sys_normal_disable', '0', '无效', 2, null, '1');
INSERT INTO nt_sys_dict_item (id, dict_item_type_id, dict_item_code, dict_item_name, dict_item_order, parent_id, status) VALUES ('1103', 'sys_user_sex', '0', '男', 1, null, '1');
INSERT INTO nt_sys_dict_item (id, dict_item_type_id, dict_item_code, dict_item_name, dict_item_order, parent_id, status) VALUES ('1104', 'sys_user_sex', '1', '女', 2, null, '1');
INSERT INTO nt_sys_dict_item (id, dict_item_type_id, dict_item_code, dict_item_name, dict_item_order, parent_id, status) VALUES ('4028b88174aa011e0174aa0a28c30004', 'sys_task_group', 'DEFAULT', '默认', 1, null, '1');
INSERT INTO nt_sys_dict_item (id, dict_item_type_id, dict_item_code, dict_item_name, dict_item_order, parent_id, status) VALUES ('4028b88174aa011e0174aa0a51bc0005', 'sys_task_group', 'SYSTEM', '系统', 2, null, '1');
INSERT INTO nt_sys_dict_item (id, dict_item_type_id, dict_item_code, dict_item_name, dict_item_order, parent_id, status) VALUES ('4028b88174aa011e0174aa0b35eb0006', 'sys_task_status', '1', '正常', 1, null, '1');
INSERT INTO nt_sys_dict_item (id, dict_item_type_id, dict_item_code, dict_item_name, dict_item_order, parent_id, status) VALUES ('4028b88174aa011e0174aa0b66630007', 'sys_task_status', '0', '暂停', 2, null, '1');

create table nt_sys_job
(
    id           varchar(255) not null
        constraint nt_sys_job_pkey
            primary key,
    job_name     varchar(32)  not null,
    job_info     varchar(32),
    job_logo     varchar(32),
    job_logo_url varchar(32),
    job_order    integer      not null,
    parent_id    varchar(32),
    status       varchar(8)  not null,
    create_time  timestamp    not null,
    update_time  timestamp
);

comment on table nt_sys_job is '用户职位表';

comment on column nt_sys_job.id is '主键';

comment on column nt_sys_job.job_name is '职位名称';

comment on column nt_sys_job.job_info is '职位信息';

comment on column nt_sys_job.job_logo is '职位Logo';

comment on column nt_sys_job.job_logo_url is 'Logo地址';

comment on column nt_sys_job.job_order is '排序';

comment on column nt_sys_job.parent_id is '上级id';

comment on column nt_sys_job.status is '状态';

comment on column nt_sys_job.create_time is '创建时间';

comment on column nt_sys_job.update_time is '更新时间';

alter table nt_sys_job
    owner to postgres;

create unique index uk_nsj_job_name
    on nt_sys_job (job_name);

INSERT INTO nt_sys_job (id, job_name, job_info, job_logo, job_logo_url, job_order, parent_id, status, create_time, update_time) VALUES ('1', '研发部', '管理员', null, null, 1, '1', '1', '2020-04-24 14:31:41.000000', '2020-09-05 20:43:36.000000');
INSERT INTO nt_sys_job (id, job_name, job_info, job_logo, job_logo_url, job_order, parent_id, status, create_time, update_time) VALUES ('4028b881745e46ae01745e4c3cd90008', '财务部', '财务', null, null, 2, null, '0', '2020-09-05 20:44:49.000000', '2020-09-18 22:36:12.000000');

create table nt_sys_log
(
    id                varchar(64) not null
        constraint nt_sys_log_pkey
            primary key,
    operation_ip      varchar(32),
    method_name       varchar(8),
    operation_name    varchar(255),
    operation_path    varchar(255),
    operation_context text,
    create_time       timestamp
);

comment on table nt_sys_log is '系统日志表';

comment on column nt_sys_log.id is '主键';

comment on column nt_sys_log.operation_ip is '操作ip';

comment on column nt_sys_log.method_name is '请求方法';

comment on column nt_sys_log.operation_name is '名称';

comment on column nt_sys_log.operation_path is '请求路径';

comment on column nt_sys_log.operation_context is '请求内容';

comment on column nt_sys_log.create_time is '创建时间';

alter table nt_sys_log
    owner to postgres;

create table nt_sys_menu_type
(
    id          varchar(255) not null
        constraint nt_sys_menu_type_pkey
            primary key,
    type_code   varchar(32)  not null,
    type_name   varchar(32)  not null,
    status      varchar(8),
    create_time timestamp,
    update_time timestamp
);

comment on table nt_sys_menu_type is '菜单类型表';

comment on column nt_sys_menu_type.id is '主键';

comment on column nt_sys_menu_type.type_code is '类型标识';

comment on column nt_sys_menu_type.type_name is '类型名称';

comment on column nt_sys_menu_type.status is '状态';

comment on column nt_sys_menu_type.create_time is '创建时间';

comment on column nt_sys_menu_type.update_time is '更新时间';

alter table nt_sys_menu_type
    owner to postgres;

create unique index uk_smt_type_code
    on nt_sys_menu_type (type_code);

INSERT INTO nt_sys_menu_type (id, type_code, type_name, status, create_time, update_time) VALUES ('1', 'nt_sys', '统一用户认证', '1', '2013-01-01 10:00:00.000000', '2013-01-01 10:00:00.000000');
INSERT INTO nt_sys_menu_type (id, type_code, type_name, status, create_time, update_time) VALUES ('2', 'nt_mix', '混合库', '1', '2013-01-01 10:00:00.000000', '2013-01-01 10:00:00.000000');
INSERT INTO nt_sys_menu_type (id, type_code, type_name, status, create_time, update_time) VALUES ('3', 'nt_database', 'DNA数据库比对管理系统', '1', '2013-01-01 10:00:00.000000', '2013-01-01 10:00:00.000000');
INSERT INTO nt_sys_menu_type (id, type_code, type_name, status, create_time, update_time) VALUES ('4', 'nt_case', '综合受理接案平台', '1', '2020-05-12 09:14:12.000000', '2020-05-12 09:14:42.000000');
INSERT INTO nt_sys_menu_type (id, type_code, type_name, status, create_time, update_time) VALUES ('4028e5917365edc4017365efa54d0000', '222', '22', '1', '2020-07-19 00:00:00.000000', '2020-07-19 00:00:00.000000');



create table nt_sys_menu
(
    id               varchar(255) not null
        constraint nt_sys_menu_pkey
            primary key,
    menu_type_id     varchar(255)
        constraint fk_sm_menu_type_id
            references nt_sys_menu_type,
    menu_name        varchar(32)  not null,
    parent_id        varchar(32)  not null,
    component        varchar(64),
    path             varchar(64),
    menu_order       integer      not null,
    menu_description varchar(255),
    icon             varchar(255),
    status      varchar(8),
    create_time      timestamp,
    update_time      timestamp,
    another_name     varchar(32)
);

comment on table nt_sys_menu is '系统菜单表';

comment on column nt_sys_menu.id is '主键';

comment on column nt_sys_menu.menu_type_id is '菜单类型编号';

comment on column nt_sys_menu.menu_name is '菜单名称';

comment on column nt_sys_menu.parent_id is '上级菜单';

comment on column nt_sys_menu.component is '元素';

comment on column nt_sys_menu.path is '路径';

comment on column nt_sys_menu.menu_order is '排序';

comment on column nt_sys_menu.menu_description is '描述';

comment on column nt_sys_menu.icon is 'Logo';

comment on column nt_sys_menu.status is '状态';

comment on column nt_sys_menu.create_time is '创建时间';

comment on column nt_sys_menu.update_time is '更新时间';

comment on column nt_sys_menu.another_name is '别名';

alter table nt_sys_menu
    owner to postgres;

create index idx_menu_type_id
    on nt_sys_menu (menu_type_id);

create index idx_parent_id
    on nt_sys_menu (parent_id);

INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('101', '1', '系统设置', '-1', 'Layout', '/system', 1, '系统设置', 'system', '1', '2020-05-11 14:00:21.000000', '2020-05-11 14:00:25.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('1011', '1', '单位管理', '101', 'system/org/index', '/org', 11, '单位管理', 'tree', '1', '2020-05-11 14:03:06.000000', '2020-05-11 14:03:11.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('1012', '1', '角色管理', '101', 'system/role/index', '/role', 12, '角色管理', 'peoples', '1', '2020-05-11 14:08:39.000000', '2020-05-11 14:08:43.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('1013', '1', '用户职位', '101', 'system/job/index', '/job', 13, '用户职位', 'post', '1', '2020-08-05 21:48:33.000000', '2020-08-05 21:48:37.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('1014', '1', '用户管理', '101', 'system/user/index', '/user', 14, '用户管理', 'user', '1', '2020-09-05 20:30:00.000000', '2020-09-05 20:30:00.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('1015', '1', '字典管理', '101', 'system/dict/index', '/dict', 15, '字典管理', 'dict', '1', '2020-09-05 20:30:00.000000', '2020-09-05 20:30:00.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('1016', '1', '菜单管理', '101', 'system/menu/index', '/menu', 16, '菜单管理', 'tree-table', '1', '2020-09-05 20:30:00.000000', '2020-09-05 20:30:00.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('201', '1', '系统监控', '-1', 'Layout', '/monitor', 2, '系统监控', 'monitor', '1', '2020-09-03 17:20:13.000000', '2020-09-17 20:57:13.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('2011', '1', '服务监控', '201', 'monitor/service/index', '/service', 21, '服务监控', 'druid', '1', '2020-09-03 17:21:32.000000', '2020-09-17 20:57:02.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('2012', '1', '服务器监控', '201', 'monitor/server/index', '/server', 22, '服务器监控', 'server', '1', '2020-09-03 17:22:24.000000', '2020-09-12 17:27:11.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('2013', '1', '定时任务', '201', 'monitor/task/index', '/task', 23, '定时任务', 'job', '1', '2020-09-19 11:57:23.000000', '2020-09-19 11:57:25.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('2014', '1', '路由监控', '201', 'monitor/route/index', '/route', 24, '路由监控', 'cascader', '1', '2020-09-19 11:57:23.000000', '2020-09-19 11:57:23.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('2015', '1', '缓存监控', '201', 'monitor/cache/index', '/cache', 25, null, 'time-range', '1', '2020-12-08 14:00:34.000000', '2020-12-08 14:00:51.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('2016', '1', 'ES监控', '201', 'monitor/elasticsearch/index', '/elasticsearch', 26, 'ES监控', 'drag', '1', '2020-12-25 14:47:37.000000', '2020-12-25 14:50:14.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('2017', '1', '队列监控', '201', 'monitor/rabbitmq/index', '/rabbitmq', 27, '队列监控', 'example', '1', '2020-12-25 14:48:35.000000', '2020-12-25 14:50:19.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('2018', '1', '文档监控', '201', 'monitor/fastdfs/index', '/fastdfs', 28, '文档监控', 'documentation', '1', '2020-12-25 14:49:33.000000', '2020-12-25 14:50:24.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('301', '1', '日志管理', '-1', 'Layout', '/log', 3, '日志管理', 'log', '1', '2020-11-16 15:36:00.000000', '2020-11-16 15:36:00.000000', null);
INSERT INTO nt_sys_menu (id, menu_type_id, menu_name, parent_id, component, path, menu_order, menu_description, icon, status, create_time, update_time, another_name) VALUES ('3011', '1', '服务日志', '301', 'system/log/index', '/syslog', 31, '服务日志', 'druid', '1', '2020-11-16 15:36:00.000000', '2020-11-16 15:36:00.000000', null);


create table nt_sys_operation
(
    id             varchar(255)      not null
        constraint nt_sys_operation_pkey
            primary key,
    operation_type varchar(32)       not null,
    operation_name varchar(32)       not null,
    status      varchar(8),
    create_time    timestamp,
    update_time    timestamp
);

comment on table nt_sys_operation is '操作权限表';

comment on column nt_sys_operation.id is '主键';

comment on column nt_sys_operation.operation_type is '操作类型';

comment on column nt_sys_operation.operation_name is '操作名称';

comment on column nt_sys_operation.status is '状态';

comment on column nt_sys_operation.create_time is '创建时间';

comment on column nt_sys_operation.update_time is '更新时间';

alter table nt_sys_operation
    owner to postgres;

INSERT INTO nt_sys_operation (id, operation_type, operation_name, status, create_time, update_time) VALUES ('1', 'select', '查询', '1', '2013-01-01 10:00:00.000000', '2013-01-01 10:00:00.000000');
INSERT INTO nt_sys_operation (id, operation_type, operation_name, status, create_time, update_time) VALUES ('2', 'add', '添加', '1', '2013-01-01 10:00:00.000000', '2013-01-01 10:00:00.000000');
INSERT INTO nt_sys_operation (id, operation_type, operation_name, status, create_time, update_time) VALUES ('3', 'edit', '编辑', '1', '2013-01-01 10:00:00.000000', '2020-07-19 13:09:58.000000');
INSERT INTO nt_sys_operation (id, operation_type, operation_name, status, create_time, update_time) VALUES ('4', 'save', '保存', '1', '2013-01-01 10:00:00.000000', '2020-07-19 14:12:47.000000');
INSERT INTO nt_sys_operation (id, operation_type, operation_name, status, create_time, update_time) VALUES ('5', 'delete', '删除', '1', '2013-01-01 10:00:00.000000', '2013-01-01 10:00:00.000000');
INSERT INTO nt_sys_operation (id, operation_type, operation_name, status, create_time, update_time) VALUES ('6', 'imported', '导入', '1', '2013-01-01 10:00:00.000000', '2013-01-01 10:00:00.000000');
INSERT INTO nt_sys_operation (id, operation_type, operation_name, status, create_time, update_time) VALUES ('7', 'exported', '导出', '1', '2013-01-01 10:00:00.000000', '2013-01-01 10:00:00.000000');


create table nt_sys_menu_oper
(
    id           varchar(255) not null
        constraint nt_sys_menu_oper_pkey
            primary key,
    menu_id      varchar(32)  not null
        constraint idx_menu_id
            references nt_sys_menu,
    operation_id varchar(255)
        constraint idx_operation_id
            references nt_sys_operation
);

comment on table nt_sys_menu_oper is '菜单操作权限表';

comment on column nt_sys_menu_oper.id is '主键';

comment on column nt_sys_menu_oper.menu_id is '菜单编号';

comment on column nt_sys_menu_oper.operation_id is '操作编号';

alter table nt_sys_menu_oper
    owner to postgres;

create table nt_sys_message
(
    id           varchar(64) not null
        constraint nt_sys_message_pkey
            primary key,
    queue_type   varchar(64) not null,
    queue_key    varchar(64) not null,
    message_type varchar(32) not null,
    message_name varchar(64),
    type_id      varchar(64),
    context      varchar(255),
    send_user    varchar(32),
    receive_user varchar(32),
    create_time  timestamp,
    update_time  timestamp
);

comment on table nt_sys_message is '系统消息表';

comment on column nt_sys_message.id is '主键';

comment on column nt_sys_message.queue_type is '队列类型';

comment on column nt_sys_message.queue_key is '队列标识';

comment on column nt_sys_message.message_type is '消息类型';

comment on column nt_sys_message.message_name is '消息名称';

comment on column nt_sys_message.type_id is '类型编号';

comment on column nt_sys_message.context is '内容';

comment on column nt_sys_message.send_user is '发送人';

comment on column nt_sys_message.receive_user is '接收人';

comment on column nt_sys_message.create_time is '创建时间';

comment on column nt_sys_message.update_time is '更新时间';

alter table nt_sys_message
    owner to postgres;

create index idx_message_type
    on nt_sys_message (message_type);

INSERT INTO nt_sys_message (id, queue_type, queue_key, message_type, message_name, type_id, context, send_user, receive_user, create_time, update_time) VALUES ('40288112729c3ca101729cab601d0000', 'sysMessageDirect', 'sysMessageKey', 'test', '测试', null, '内容', 'admin', 'admin', '2020-06-10 13:19:20.000000', '2020-06-10 13:19:20.000000');
INSERT INTO nt_sys_message (id, queue_type, queue_key, message_type, message_name, type_id, context, send_user, receive_user, create_time, update_time) VALUES ('ff80808174dcef8f0174dcf068ec0000', 'sysMessageDirect', 'sysMessageKey', 'test', '测试', null, '123456', 'admin', 'admin', '2020-09-30 10:56:17.000000', null);

create table nt_sys_sequence
(
    id          varchar(32) not null
        constraint nt_sys_sequence_pkey
            primary key,
    table_name  varchar(32),
    sequence    integer,
    status      varchar(8),
    create_time timestamp
);

comment on table nt_sys_sequence is '序列表';

comment on column nt_sys_sequence.id is '主键';

comment on column nt_sys_sequence.table_name is '表名';

comment on column nt_sys_sequence.sequence is '序号';

comment on column nt_sys_sequence.status is '状态';

comment on column nt_sys_sequence.create_time is '创建时间';

alter table nt_sys_sequence
    owner to postgres;


create table nt_sys_organization
(
    id              varchar(255) not null
        constraint nt_sys_organization_pkey
            primary key,
    org_name        varchar(255) not null,
    org_code        varchar(255) not null,
    parent_id       varchar(32),
    ancestors       varchar(255),
    org_order       integer,
    org_area_code   varchar(255),
    org_credit_code varchar(255),
    org_logo        varchar(64),
    org_logo_url    varchar(64),
    org_type        varchar(32),
    create_user     varchar(64),
    status          varchar(8)  not null,
    create_time     timestamp,
    update_time     timestamp,
    business_name   varchar(32),
    business_phone  varchar(32),
    technical_name  varchar(32),
    technical_phone varchar(32),
    config_id       varchar(64)
        constraint idx_sys_org_config_id
            references nt_data_source_config
);

comment on table nt_sys_organization is '机构表';

comment on column nt_sys_organization.id is '主键';

comment on column nt_sys_organization.org_name is '机构名称';

comment on column nt_sys_organization.org_code is '机构编号';

comment on column nt_sys_organization.parent_id is '上级编号';

comment on column nt_sys_organization.ancestors is '总称';

comment on column nt_sys_organization.org_order is '编号';

comment on column nt_sys_organization.org_area_code is '地区';

comment on column nt_sys_organization.org_credit_code is '行政区号';

comment on column nt_sys_organization.org_logo is 'Logo';

comment on column nt_sys_organization.org_logo_url is 'Logo地址';

comment on column nt_sys_organization.org_type is '类型';

comment on column nt_sys_organization.create_user is '创建人';

comment on column nt_sys_organization.status is '状态';

comment on column nt_sys_organization.create_time is '创建时间';

comment on column nt_sys_organization.update_time is '更新时间';

comment on column nt_sys_organization.business_name is '负责人';

comment on column nt_sys_organization.business_phone is '负责人电话';

comment on column nt_sys_organization.technical_name is '技术负责人';

comment on column nt_sys_organization.technical_phone is '负责人电话';

comment on column nt_sys_organization.config_id is '数据源id';

alter table nt_sys_organization
    owner to postgres;

create index idx_sys_org_org_code
    on nt_sys_organization (org_code);

create index idx_sys_org_parent_id
    on nt_sys_organization (parent_id);

INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110101', '北京市公安局东城分局', '110101000000', '13', '-1,2,13', 1, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110102', '北京市公安局西城分局', '110102000000', '13', '-1,2,13', 2, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110105', '北京市公安局朝阳分局', '110105000000', '13', '-1,2,13', 3, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110106', '北京市公安局丰台分局', '110106000000', '13', '-1,2,13', 4, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110107', '北京市公安局石景山分局', '110107000000', '13', '-1,2,13', 5, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110108', '北京市公安局海淀分局', '110108000000', '13', '-1,2,13', 6, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110109', '北京市公安局门头沟分局', '110109000000', '13', '-1,2,13', 7, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110111', '北京市公安局房山分局', '110111000000', '13', '-1,2,13', 8, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110112', '北京市公安局通州分局', '110112000000', '13', '-1,2,13', 9, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110113', '北京市公安局顺义分局', '110113000000', '13', '-1,2,13', 10, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110114', '北京市公安局昌平分局', '110114000000', '13', '-1,2,13', 11, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110115', '北京市公安局大兴分局', '110115000000', '13', '-1,2,13', 12, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110116', '北京市公安局怀柔分局', '110116000000', '13', '-1,2,13', 13, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110117', '北京市公安局平谷分局', '110117000000', '13', '-1,2,13', 14, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110228', '北京市公安局密云分局', '110228000000', '13', '-1,2,13', 15, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('110229', '北京市公安局延庆分局', '110229000000', '13', '-1,2,13', 16, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('13', '北京市公安局治安管理总队', '110000200000', '2', '-1,2', 17, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('14', '北京市公安局刑事侦查总队', '110000210000', '2', '-1,2', 18, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('15', '北京市公安局公共交通安全保卫总队', '110084000000', '2', '-1,2', 19, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('16', '北京市公安局内部单位保卫局', '110086000000', '2', '-1,2', 20, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('17', '北京市公安局经济犯罪侦查总队', '110317000000', '2', '-1,2', 21, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('18', '北京市公安局便衣侦查总队', '110318000000', '2', '-1,2', 22, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('19', '北京市公安局禁毒总队', '110319000000', '2', '-1,2', 23, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('2', '北京市公安局', '110000000000', '-1', '-1', 1, null, null, null, null, null, '1', '1', '2020-06-11 17:33:58.000000', '2020-09-17 21:21:04.000000', null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('20', '北京市公安局公安交通管理局', '110320000000', '2', '-1,2', 24, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('21', '北京海关缉私局', '110000H00500', '2', '-1,2', 25, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('22', '北京市森林公安局', '110000S00500', '2', '-1,2', 26, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('3', '北京市法医中心', '110000000000', '2', '-1,2', 2, null, null, null, null, null, null, '1', '2020-06-12 13:12:49.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('31', '北京市公安局天安门地区分局', '110091000000', '2', '-1,2', 27, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('32', '北京市公安局北京西站分局', '110092000000', '2', '-1,2', 28, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('33', '北京市公安局经济技术开发区分局', '110095000000', '2', '-1,2', 29, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('34', '北京市公安局清河分局', '110097000000', '2', '-1,2', 30, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);
INSERT INTO nt_sys_organization (id, org_name, org_code, parent_id, ancestors, org_order, org_area_code, org_credit_code, org_logo, org_logo_url, org_type, create_user, status, create_time, update_time, business_name, business_phone, technical_name, technical_phone, config_id) VALUES ('4', '北京市法医中心实验室', '110000000001', '3', '-1,2,3', 1, null, null, null, null, null, null, '1', '2020-06-11 17:33:58.000000', null, null, null, null, null, null);

create table nt_sys_role
(
    id          varchar(255) not null
        constraint nt_sys_role_pkey
            primary key,
    role_code   varchar(32),
    role_name   varchar(32)  not null,
    role_des    varchar(32),
    status      varchar(8),
    create_time timestamp,
    update_time timestamp
);

comment on table nt_sys_role is '角色表';

comment on column nt_sys_role.id is '主键';

comment on column nt_sys_role.role_code is '角色编号';

comment on column nt_sys_role.role_name is '角色名称';

comment on column nt_sys_role.role_des is '角色描述';

comment on column nt_sys_role.status is '状态';

comment on column nt_sys_role.create_time is '创建时间';

comment on column nt_sys_role.update_time is '更新时间';

alter table nt_sys_role
    owner to postgres;

INSERT INTO nt_sys_role (id, role_code, role_name, role_des, status, create_time, update_time) VALUES ('1', '22222222', '超管', '权限最大用户', '1', '2018-10-10 16:08:02.000000', '2020-12-25 14:49:43.000000');
INSERT INTO nt_sys_role (id, role_code, role_name, role_des, status, create_time, update_time) VALUES ('ff80808172a150110172a159d9c40000', '1591844919746', '职员', '普通用户', '1', '2020-06-11 11:08:40.000000', '2020-09-19 12:00:16.000000');
INSERT INTO nt_sys_role (id, role_code, role_name, role_des, status, create_time, update_time) VALUES ('ff80808172a624c00172a67c70c30007', '3333', '警员', '一般警员', '0', '2020-06-12 11:04:33.000000', '2020-09-18 22:47:00.000000');

create table nt_sys_role_menu_oper
(
    id           varchar(255) not null
        constraint nt_sys_role_menu_oper_pkey
            primary key,
    menu_id      varchar(255) not null
        constraint idx_role_menu_oper_menu_id
            references nt_sys_menu,
    operation_id varchar(255) not null
        constraint idx_role_menu_oper_operation_id
            references nt_sys_operation,
    role_id      varchar(255) not null
        constraint idx_role_menu_oper_role_id
            references nt_sys_role,
    menu_type_id varchar(255) not null
        constraint idx_role_menu_oper_menu_type_id
            references nt_sys_menu_type
);

comment on table nt_sys_role_menu_oper is '角色菜单操作权限表';

comment on column nt_sys_role_menu_oper.id is '主键';

comment on column nt_sys_role_menu_oper.menu_id is '菜单id';

comment on column nt_sys_role_menu_oper.operation_id is '操作id';

comment on column nt_sys_role_menu_oper.role_id is '角色id';

comment on column nt_sys_role_menu_oper.menu_type_id is '类型id';

alter table nt_sys_role_menu_oper
    owner to postgres;

create table nt_sys_role_menu
(
    id           varchar(255) not null
        constraint nt_sys_role_menu_pkey
            primary key,
    menu_id      varchar(255)
        constraint idx_role_menu_menu_id
            references nt_sys_menu,
    role_id      varchar(255) not null
        constraint idx_role_menu_role_id
            references nt_sys_role,
    menu_type_id varchar(255) not null
        constraint idx_role_menu_menu_type_id
            references nt_sys_menu_type
);

comment on table nt_sys_role_menu is '角色菜单表';

comment on column nt_sys_role_menu.id is '主键';

comment on column nt_sys_role_menu.menu_id is '菜单id';

comment on column nt_sys_role_menu.role_id is '角色id';

comment on column nt_sys_role_menu.menu_type_id is '类型id';

alter table nt_sys_role_menu
    owner to postgres;

INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('11101', '101', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('111011', '1011', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('111012', '1012', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('111013', '1013', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('111014', '1014', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('111015', '1015', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('111016', '1016', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('11201', '201', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('112011', '2011', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('112012', '2012', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('112013', '2013', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('112014', '2014', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('112015', '2015', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('112016', '2016', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('112017', '2017', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('112018', '2018', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('11301', '301', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('113011', '3011', '1', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('ff80808172a150110172a159d9c400001101', '101', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('ff80808172a150110172a159d9c400001201', '201', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('ff80808172a150110172a159d9c4000012011', '2011', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('ff80808172a150110172a159d9c4000012012', '2012', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO nt_sys_role_menu (id, menu_id, role_id, menu_type_id) VALUES ('ff80808172a150110172a159d9c4000012013', '2013', 'ff80808172a150110172a159d9c40000', '1');

create table nt_sys_user
(
    id               varchar(255) not null
        constraint nt_sys_user_pkey
            primary key,
    user_name        varchar(32)  not null,
    password         varchar(32)  not null,
    real_name        varchar(32),
    avatar           varchar(255),
    sex              char default '0'::bpchar,
    user_type        varchar(2),
    email            varchar(32),
    id_card          varchar(32),
    last_ip          varchar(32),
    last_time        timestamp,
    phone            varchar(32),
    user_code        varchar(32),
    id_type          varchar(8),
    status           varchar(8),
    remark           varchar(255),
    is_admin         varchar(8),
    active_status    varchar(10),
    default_password varchar(32),
    create_time      timestamp
);

comment on table nt_sys_user is '用户表';

comment on column nt_sys_user.id is '主键';

comment on column nt_sys_user.user_name is '账号';

comment on column nt_sys_user.password is '密码';

comment on column nt_sys_user.real_name is '姓名';

comment on column nt_sys_user.avatar is '头像';

comment on column nt_sys_user.sex is '性别';

comment on column nt_sys_user.user_type is '类型';

comment on column nt_sys_user.email is '邮箱';

comment on column nt_sys_user.id_card is '身份证';

comment on column nt_sys_user.last_ip is '登录ip';

comment on column nt_sys_user.last_time is '登录时间';

comment on column nt_sys_user.phone is '电话';

comment on column nt_sys_user.user_code is '编号';

comment on column nt_sys_user.id_type is '证件类型';

comment on column nt_sys_user.status is '状态';

comment on column nt_sys_user.remark is '备注';

comment on column nt_sys_user.is_admin is '是否管理员';

comment on column nt_sys_user.active_status is '状态';

comment on column nt_sys_user.default_password is '默认密码';

comment on column nt_sys_user.create_time is '创建时间';

alter table nt_sys_user
    owner to postgres;

create index idx_user_user_name
    on nt_sys_user (user_name);

INSERT INTO nt_sys_user (id, user_name, password, real_name, avatar, sex, user_type, email, id_card, last_ip, last_time, phone, user_code, id_type, status, remark, is_admin, active_status, default_password, create_time) VALUES ('1', 'admin', 'C4CA4238A0B923820DCC509A6F75849B', '超管', 'https://www.oner365.com/group1/M00/00/04/rBqcll9Qsc-ADt_9AAAk7-agPWw680.jpg', '0', '1', 'admin@qq.com', '110103197707250933', '', '2020-09-04 15:53:30.000000', '13800138000', '', 'beijing', '1', '123', '1', '1', '123456', '2018-10-09 14:19:44.000000');
INSERT INTO nt_sys_user (id, user_name, password, real_name, avatar, sex, user_type, email, id_card, last_ip, last_time, phone, user_code, id_type, status, remark, is_admin, active_status, default_password, create_time) VALUES ('2', 'shy', 'C4CA4238A0B923820DCC509A6F75849B', '张3', '', '0', '1', null, null, null, null, '13800138000', null, null, '1', null, '0', '1', '123456', '2020-05-12 09:47:07.000000');
INSERT INTO nt_sys_user (id, user_name, password, real_name, avatar, sex, user_type, email, id_card, last_ip, last_time, phone, user_code, id_type, status, remark, is_admin, active_status, default_password, create_time) VALUES ('5', 'ls', 'C4CA4238A0B923820DCC509A6F75849B', '王老师', '', '0', '1', null, null, null, null, null, null, null, '0', null, '1', '1', null, '2020-05-12 17:05:23.000000');

create table nt_sys_user_job
(
    id             varchar(255) not null
        constraint nt_sys_user_job_pkey
            primary key,
    position_order integer      not null,
    status         varchar(8)  not null,
    create_time    timestamp,
    update_time    timestamp,
    job_id         varchar(255)
        constraint idx_user_job_job_id
            references nt_sys_job,
    user_id        varchar(255)
        constraint idx_user_job_user_id
            references nt_sys_user
);

comment on table nt_sys_user_job is '用户职位表';

comment on column nt_sys_user_job.id is '主键';

comment on column nt_sys_user_job.position_order is '排序';

comment on column nt_sys_user_job.status is '状态';

comment on column nt_sys_user_job.create_time is '创建时间';

comment on column nt_sys_user_job.update_time is '更新时间';

comment on column nt_sys_user_job.job_id is '职位编号';

comment on column nt_sys_user_job.user_id is '用户编号';

alter table nt_sys_user_job
    owner to postgres;

INSERT INTO nt_sys_user_job (id, position_order, status, create_time, update_time, job_id, user_id) VALUES ('4028b881745ea0f901745ea77e07001a', 1, '1', '2020-09-05 22:24:29.000000', null, '1', '1');
INSERT INTO nt_sys_user_job (id, position_order, status, create_time, update_time, job_id, user_id) VALUES ('ff8080817640f2d4017640f8fca70001', 1, '1', '2020-12-08 14:10:28.000000', null, '1', '2');

create table nt_sys_user_org
(
    id             varchar(255) not null
        constraint nt_sys_user_org_pkey
            primary key,
    position_order integer      not null,
    status         varchar(8)  not null,
    create_time    timestamp,
    update_time    timestamp,
    org_id         varchar(255)
        constraint idx_user_org_org_id
            references nt_sys_organization,
    user_id        varchar(255)
        constraint idx_user_org_user_id
            references nt_sys_user
);

comment on table nt_sys_user_org is '用户机构权限表';

comment on column nt_sys_user_org.id is '主键';

comment on column nt_sys_user_org.position_order is '排序';

comment on column nt_sys_user_org.status is '状态';

comment on column nt_sys_user_org.create_time is '创建时间';

comment on column nt_sys_user_org.update_time is '更新时间';

comment on column nt_sys_user_org.org_id is '机构编号';

comment on column nt_sys_user_org.user_id is '用户编号';

alter table nt_sys_user_org
    owner to postgres;

INSERT INTO nt_sys_user_org (id, position_order, status, create_time, update_time, org_id, user_id) VALUES ('4028b881745ea0f901745ea77e0f001b', 1, '1', '2020-09-05 22:24:29.000000', null, '3', '1');
INSERT INTO nt_sys_user_org (id, position_order, status, create_time, update_time, org_id, user_id) VALUES ('4028b881745ea0f901745ea77e14001c', 1, '1', '2020-09-05 22:24:29.000000', null, '4', '1');
INSERT INTO nt_sys_user_org (id, position_order, status, create_time, update_time, org_id, user_id) VALUES ('4028b881745ea0f901745ea77e19001d', 1, '1', '2020-09-05 22:24:29.000000', null, '110101', '1');
INSERT INTO nt_sys_user_org (id, position_order, status, create_time, update_time, org_id, user_id) VALUES ('4028b881745ea0f901745ea77e23001e', 1, '1', '2020-09-05 22:24:29.000000', null, '110102', '1');
INSERT INTO nt_sys_user_org (id, position_order, status, create_time, update_time, org_id, user_id) VALUES ('4028b881745ea0f901745ea77e2a001f', 1, '1', '2020-09-05 22:24:29.000000', null, '110105', '1');
INSERT INTO nt_sys_user_org (id, position_order, status, create_time, update_time, org_id, user_id) VALUES ('4028b881745ea0f901745ea77e320020', 1, '1', '2020-09-05 22:24:29.000000', null, '14', '1');
INSERT INTO nt_sys_user_org (id, position_order, status, create_time, update_time, org_id, user_id) VALUES ('4028b881745ea0f901745ea77e3a0021', 1, '1', '2020-09-05 22:24:29.000000', null, '15', '1');
INSERT INTO nt_sys_user_org (id, position_order, status, create_time, update_time, org_id, user_id) VALUES ('4028b881745ea0f901745ea77e420022', 1, '1', '2020-09-05 22:24:29.000000', null, '16', '1');
INSERT INTO nt_sys_user_org (id, position_order, status, create_time, update_time, org_id, user_id) VALUES ('ff8080817640f2d4017640f8fcb10002', 1, '1', '2020-12-08 14:10:28.000000', null, '110101', '2');

create table nt_sys_user_role
(
    id      varchar(64) not null
        constraint nt_sys_user_role_pkey
            primary key,
    role_id varchar(64) not null
        constraint idx_user_role_role_id
            references nt_sys_role,
    user_id varchar(64) not null
        constraint idx_user_role_user_id
            references nt_sys_user
);

comment on table nt_sys_user_role is '用户角色权限表';

comment on column nt_sys_user_role.id is '主键';

comment on column nt_sys_user_role.role_id is '角色编号';

comment on column nt_sys_user_role.user_id is '用户编号';

alter table nt_sys_user_role
    owner to postgres;

INSERT INTO nt_sys_user_role (id, role_id, user_id) VALUES ('4028b881745ea0f901745ea77dfa0018', '1', '1');
INSERT INTO nt_sys_user_role (id, role_id, user_id) VALUES ('4028b881745ea0f901745ea77dfe0019', 'ff80808172a150110172a159d9c40000', '1');
INSERT INTO nt_sys_user_role (id, role_id, user_id) VALUES ('ff80808172a7b2ce0172bacc3b9c0009', '1', '5');
INSERT INTO nt_sys_user_role (id, role_id, user_id) VALUES ('ff8080817640f2d4017640f8fc990000', 'ff80808172a150110172a159d9c40000', '2');


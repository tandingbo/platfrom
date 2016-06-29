
-- 流水号生成配制表
CREATE TABLE sys_serial_number (
  "id" varchar(32) COLLATE "default" NOT NULL comment 'id',
  "module_name" varchar(50) COLLATE "default" comment '模块名称',
  "module_code" varchar(50) COLLATE "default" comment '模块编码',
  "config_templet" varchar(50) COLLATE "default" comment '当前模块 使用的序列号模板',
  "max_serial" varchar(32) COLLATE "default" comment '存放当前序列号的值',
  "pre_max_num" varchar(32) COLLATE "default" comment '预生成序列号存放到缓存的个数',
  "is_auto_increment" char(1) COLLATE "default" comment '是否自动增长模式，0：否  1：是',

  primary key (id)
) engine=innodb default charset=utf8 comment '流水号生成配制表';

insert into sys_serial_number(id, module_name, module_code, config_templet, max_serial, pre_max_num, is_auto_increment)
values('00000000000000000000000000000001', '项目', 'PJ', 'CX00000000${DATE}', '2650', '100', '1');

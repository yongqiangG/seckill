-- 测试表
CREATE TABLE seckill_test(
    `id` bigint not null  auto_increment comment 'ID',
    `name` varchar(120) not null comment '名称',
    primary key (id)
)engine=InnoDB auto_increment=100 default charset=utf8 comment '测试表'
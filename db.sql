CREATE TABLE IF NOT EXISTS `tbl_user` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `name` varchar(128) NOT NULL default '' comment '姓名',
 `face` varchar(128) NOT NULL default '' comment '头像',
 `mobile` varchar(16) NOT NULL default '' comment '手机',
 `password` varchar(128) NOT NULL default '' comment '密码',
 `type` tinyint(3) NOT NULL DEFAULT 0 comment '用户类型',
 `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 `deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除，0-正常 1-删除',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 auto_increment = 10000 COMMENT='用户';
CREATE TABLE IF NOT EXISTS `tbl_chat` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT 'id',
 `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 `deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除，0-正常 1-删除',
 PRIMARY KEY (`id`),
 key idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 auto_increment = 10000 COMMENT='聊天';
CREATE TABLE IF NOT EXISTS `tbl_chat_record` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `question` varchar(512) NOT NULL DEFAULT '' comment '问题',
 `answer` varchar(512) NOT NULL DEFAULT '' comment '答案',
 `chat_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '聊天Id',
 `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户Id',
 `question_time` datetime DEFAULT NULL COMMENT '提问时间',
 `answer_time` datetime DEFAULT NULL COMMENT '回答时间',
 `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 `deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除，0-正常 1-删除',
 PRIMARY KEY (`id`),
 key idx_chat_id (`chat_id`),
 key idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 auto_increment = 10000 COMMENT='聊天记录';
CREATE TABLE IF NOT EXISTS `tbl_vip` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `name` varchar(128) NOT NULL default '' comment '名称',
 `period` tinyint(3) NOT NULL DEFAULT 0 COMMENT '有效期',
 `times` int(8) NOT NULL DEFAULT 0 COMMENT '次数',
 `price` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '价格',
 `desc` varchar(512) NOT NULL default '' comment '描述',
 `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 `deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除，0-正常 1-删除',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 auto_increment = 10000 COMMENT='会员';
insert into tbl_vip(`name`, `period`, `times`, `price`, `desc`) values('周会员', 1, -1, 38.00, '有效期7天 次数不限');
insert into tbl_vip(`name`, `period`, `times`, `price`, `desc`) values('月会员', 2, -1, 68.00, '有效期1个月 次数不限');
insert into tbl_vip(`name`, `period`, `times`, `price`, `desc`) values('年会员', 3, -1, 198.00, '有效期1年 次数不限');
CREATE TABLE IF NOT EXISTS `tbl_user_right` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户Id',
 `available_times` int(8) NOT NULL default 0 comment '可用次数',
 `start_available_time` datetime NULL comment '可用开始时间',
 `end_available_time` datetime NULL comment '可用结束时间',
 `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 `deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除，0-正常 1-删除',
 PRIMARY KEY (`id`) ,
 key idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 auto_increment = 10000 COMMENT='用户权益';
CREATE TABLE IF NOT EXISTS `tbl_user_right` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户Id',
 `available_times` int(8) NOT NULL default 0 comment '可用次数',
 `start_available_time` datetime NULL comment '可用开始时间',
 `end_available_time` datetime NULL comment '可用结束时间',
 `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 `deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除，0-正常 1-删除',
 PRIMARY KEY (`id`) ,
 key idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 auto_increment = 10000 COMMENT='用户权益';
CREATE TABLE IF NOT EXISTS `tbl_user_right_detail` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `right_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '权益Id',
 `desc` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
 `source` tinyint(3) NOT NULL DEFAULT 0 COMMENT '来源，1-系统 2-用户',
 `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 `deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除，0-正常 1-删除',
 PRIMARY KEY (`id`) ,
 key idx_right_id (`right_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 auto_increment = 10000 COMMENT='用户权益明细';
CREATE TABLE IF NOT EXISTS `tbl_cost` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户Id',
 `product_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '产品Id',
 `amount` decimal(8, 2) NOT NULL DEFAULT 0.00 COMMENT '金额',
 `order_time` datetime NULL COMMENT '下单时间',
 `pay_time` datetime NULL COMMENT '支付时间',
 `pay_status` tinyint(3) NOT NULL DEFAULT 0 COMMENT '支付状态',
 `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 `deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除，0-正常 1-删除',
 PRIMARY KEY (`id`),
 key idx_user_id (`user_id`),
 key idx_product_id (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 auto_increment = 10000 COMMENT='消费';

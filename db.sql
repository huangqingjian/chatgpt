CREATE TABLE IF NOT EXISTS `tbl_user` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
 `name` varchar(128) NOT NULL default '' comment '姓名',
 `face` varchar(128) NOT NULL default '' comment '头像',
 `mobile` varchar(16) NOT NULL default '' comment '手机',
 `password` varchar(128) NOT NULL default '' comment '密码',
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
 `create_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建人',
 `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 `update_by` bigint(20) NOT NULL DEFAULT 0 COMMENT '更新人',
 `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 `deleted` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否删除，0-正常 1-删除',
 PRIMARY KEY (`id`),
 key idx_chat_id (`chat_id`),
 key idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 auto_increment = 10000 COMMENT='聊天记录';

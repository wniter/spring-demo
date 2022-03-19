package com.example.springboot.mybatis.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @create 2022-03-05 15:34
 */
public class JdbcTest {
    @Value ("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.driver-class-name}")
    String dri;
    @Value("${spring.datasource.username}")
    String name;
    @Value("${spring.datasource.passworld}")
    String passworld;
/**
 * 测试数据库
 * CREATE TABLE `student`(
 *   `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '唯一标识id',
 *   `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
 *   `age` int(3) NOT NULL COMMENT '年龄',
 *   PRIMARY KEY (`id`) USING BTREE
 * ) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;
 */

}

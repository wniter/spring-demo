package com.example.springboot.mysql;

import com.example.springboot.mysql.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;


/**
 * @create 2022-03-05 15:30
 */
public class JdbcTest {


    String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&serverTimezone=GMT%2B8";

    String dri = "com.mysql.cj.jdbc.Driver";

    String name = "root";

    String passworld = "123456";


    //测试数据库的连接
    @Test
    public void JdbcTest() throws Exception{
        Class.forName(dri);
        Connection connection = DriverManager.getConnection(url,  name, passworld);
        System.out.println(connection);

    }
//    private ApplicationContext ctx = null;
//    private JdbcTemplate jdbcTemplate = null;
//
//    {
//        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
//        jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//    }

//    /**
//     * 执行 INSERT
//     */
//    @Test
//    public void testInsert() {
//        String sql = "INSERT INTO xwj_user(id, last_name, age) VALUES(?, ?, ?)";
//        jdbcTemplate.update(sql, "1", "a-xwj", 0);
//    }
//
//    /**
//     * 执行UPDATE
//     */
//    @Test
//    public void testUpdate() {
//        String sql = "UPDATE xwj_user SET last_name = ? WHERE id = ?";
//        jdbcTemplate.update(sql, "b-xwj", 1);
//    }
//
//    /**
//     * 执行 DELETE
//     */
//    @Test
//    public void testDelete() {
//        String sql = "DELETE from xwj_user WHERE id = ?";
//        jdbcTemplate.update(sql, 1);
//    }
//
//    /**
//     * 测试批量更新操作 最后一个参数是 Object[] 的 List 类型：因为修改一条记录需要一个 Object 数组，修改多条记录就需要一个
//     * List 来存放多个数组。
//     */
//    @Test
//    public void testBatchUpdate() {
//        String sql = "INSERT INTO xwj_user(id, last_name, email) VALUES(?, ?, ?)";
//
//        List<Object[]> batchArgs = new ArrayList<>();
//        batchArgs.add(new Object[] { "2", "AA", "aa@atguigu.com" });
//        batchArgs.add(new Object[] { "3", "BB", "bb@atguigu.com" });
//        batchArgs.add(new Object[] { "4", "CC", "cc@atguigu.com" });
//        batchArgs.add(new Object[] { "5", "DD", "dd@atguigu.com" });
//
//        jdbcTemplate.batchUpdate(sql, batchArgs);
//    }
//
//    /**
//     * 从数据库中获取一条记录，实际得到对应的一个对象 注意：不是调用 queryForObject(String sql,Class<Employee> requiredType, Object... args) 方法!
//     * 而需要调用queryForObject(String sql, RowMapper<Employee> rowMapper, Object... args)
//     * 1、其中的 RowMapper 指定如何去映射结果集的行，常用的实现类为 BeanPropertyRowMapper
//     * 2、使用SQL中的列的别名完成列名和类的属性名的映射，例如 last_name lastName
//     * 3、不支持级联属性。 JdbcTemplate只能作为一个 JDBC 的小工具, 而不是 ORM 框架
//     */
//    @Test
//    public void testQueryForObject() {
//        String sql = "SELECT id, last_name lastName, email FROM xwj_user WHERE ID = ?";
//        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
//        // 在将数据装入对象时需要调用set方法。
//        User user = jdbcTemplate.queryForObject(sql, rowMapper, 2);
//        System.out.println(user);
//    }
//
//    /**
//     * 一次查询多个对象
//     * 注意：调用的不是 queryForList 方法
//     */
//    @Test
//    public void testQueryForList() {
//        String sql = "SELECT id, name, email FROM xwj_user WHERE id > ?";
//        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
//        List<User> userList = jdbcTemplate.query(sql, rowMapper, 1);
//        if (!CollectionUtils.isEmpty(userList)) {
//            userList.forEach(user -> {
//                System.out.println(user);
//            });
//        }
//    }
//
//    /**
//     * 获取单个列的值或做统计查询
//     * 使用 queryForObject(String sql, Class<Long> requiredType)
//     */
//    @Test
//    public void testQueryForCount() {
//        String sql = "SELECT count(id) FROM xwj_user";
//        long count = jdbcTemplate.queryForObject(sql, Long.class);
//
//        System.out.println(count);
//    }

}

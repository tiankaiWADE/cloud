package com.bazl.dna.test.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bazl.dna.test.dao.IUserDao;
import com.bazl.dna.test.entity.User;

/**
 * 测试用户
 * @author zhaoyong
 */
@Component
public class UserDaoImpl implements IUserDao {

    /**
     * 通过@Resource注解引入JdbcTemplate对象。
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User findUserById(Long id) {
        // 1. 定义一个sql语句
        String querySql = "select * from dna_user where id = ?";

        // 2. 定义一个RowMapper
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        // 3. 执行查询方法

        return jdbcTemplate.queryForObject(querySql, new Object[]{id}, rowMapper);
    }

    @Override
    public List<User> findAllUsers() {
        // 1. 定义一个sql语句
        String querySql = "select * from dna_user";

        // 2. 定义一个RowMapper
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

        // 3. 执行查询方法

        return jdbcTemplate.query(querySql, new Object[]{}, rowMapper);
    }

    @Override
    public int insertUser(User user) {
        // 1. 定义一个sql语句
        String execSql = "INSERT into dna_user (username, name, age, balance) values (?, ?, ?, ?)";

        // 2. 执行查询方法

        return jdbcTemplate.update(execSql,
                new Object[]{user.getUsername(), user.getName(), user.getAge(), user.getBalance()});
    }

}

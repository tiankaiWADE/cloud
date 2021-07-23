package com.bazl.dna.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.bazl.dna.common.ResponseData;
import com.bazl.dna.common.exception.DnaRuntimeException;
import com.bazl.dna.test.client.IUserServiceClient;
import com.bazl.dna.test.dao.IUserDao;
import com.bazl.dna.test.entity.User;
import com.bazl.dna.test.mapper.UserMapper;
import com.bazl.dna.test.service.IUserService;

/**
 * 用户服务实现类
 *
 * @author zhaoyong
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserServiceClient userServiceClient;

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    @Transactional(rollbackFor = DnaRuntimeException.class)
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public ResponseData login(String json) {
        return userServiceClient.login(json);
    }

    @Override
    public ResponseData getTypeById(String id) {
        return userServiceClient.getTypeById(id);
    }

    @Override
    public ResponseData findTypeList(JSONObject paramJson) {
        return userServiceClient.findTypeList(paramJson);
    }
}

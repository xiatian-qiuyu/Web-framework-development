package com.briup.service.Impl;

import com.briup.bean.User;
import com.briup.mapper.IUserMapper;
import com.briup.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl implements IUserService {

    @Autowired
    private IUserMapper userMapper;

    @Override
    public User findByLoginName(String loginName) {
        return userMapper.findByLoginName(loginName);
    }

    @Override
    public User login(String loginName, String passwordMd5) {
        return userMapper.login(loginName,passwordMd5);
    }

    @Override
    public int register(User user) {
        return userMapper.register(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }
}

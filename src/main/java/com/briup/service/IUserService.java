package com.briup.service;

import com.briup.bean.User;
public interface IUserService {

    User findByLoginName(String loginName);
    /*
    登陆
     */
    User login(String loginName, String passwordMd5);
    /*
    注册
     */
    int register(User user) ;

    void update(User user);
}
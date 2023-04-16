package com.briup.mapper;

import com.briup.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.ResponseBody;

@Mapper
@ResponseBody
public interface IUserMapper {
    User findByLoginName(String loginName);

    User login(String loginName, String passwordMd5);
    /*
    注册
     */
    int register(User user);

    void update(User user);
}

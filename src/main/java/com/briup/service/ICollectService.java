package com.briup.service;

import com.briup.bean.Collect;

import java.util.List;
public interface ICollectService {
    // 查询用户所有收藏列表
    List<Collect> findUserAllCollect(Long id);
    int deleteCollect(Long collectId);

    // 添加收藏
    int addCollect(Long userId, Long shopId);

    int findCollect(Long id, Long shopId);

    Collect findOne(Long collectId);
}

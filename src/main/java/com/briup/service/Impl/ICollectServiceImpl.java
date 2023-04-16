package com.briup.service.Impl;

import com.briup.bean.Collect;
import com.briup.mapper.ICollectMapper;
import com.briup.service.ICollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICollectServiceImpl implements ICollectService {

    @Autowired
    private ICollectMapper collectMapper;
    @Override
    public List<Collect> findUserAllCollect(Long id) {
        return collectMapper.findByUserId(id);
    }

    @Override
    public int deleteCollect(Long collectId) {
        return collectMapper.deleteByCollectId(collectId);

    }

    @Override
    public int addCollect(Long userId, Long shopId) {
        return collectMapper.addCollect(userId,shopId);
    }

    @Override
    public int findCollect(Long id,Long shopId) {
        return collectMapper.findCollect(id,shopId) ;
    }

    @Override
    public Collect findOne(Long collectId) {
        return null;
    }
}

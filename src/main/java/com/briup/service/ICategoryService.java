package com.briup.service;

import com.briup.bean.vo.CategoryVO;

import java.util.List;

public interface ICategoryService {
    //查询所有分类
    List<CategoryVO> findAllCategoey();
}

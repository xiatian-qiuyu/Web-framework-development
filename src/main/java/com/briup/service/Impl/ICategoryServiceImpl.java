package com.briup.service.Impl;

import com.briup.bean.Category;
import com.briup.bean.vo.CategoryVO;
import com.briup.mapper.ICategoryMapper;
import com.briup.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryServiceImpl implements ICategoryService {
    @Autowired
    private ICategoryMapper categoryMapper;

    //查询所有分类
    @Override
    public List<CategoryVO> findAllCategoey() {
        //一级分类
        List<CategoryVO> categoryList = categoryMapper.findByParentIdIsNull();
        //二级分类
        for(CategoryVO category:categoryList){
            List<Category> categoryList1 = categoryMapper.findByParentId(category.getId());
            category.setCategories(categoryList1);
        }
        return categoryList;

    }
}

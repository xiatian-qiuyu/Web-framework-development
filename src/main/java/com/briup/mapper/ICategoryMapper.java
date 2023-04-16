package com.briup.mapper;

import com.briup.bean.Category;
import com.briup.bean.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Mapper
@ResponseBody
public interface ICategoryMapper {
    //一级分类
    List<CategoryVO> findByParentIdIsNull();
    // 二级分类
    List<Category> findByParentId(Long id);
}

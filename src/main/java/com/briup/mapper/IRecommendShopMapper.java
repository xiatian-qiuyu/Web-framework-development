package com.briup.mapper;

import com.briup.bean.RecommendShop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@ResponseBody
public interface IRecommendShopMapper {
   List<RecommendShop> findByUserId(Long userId);
}

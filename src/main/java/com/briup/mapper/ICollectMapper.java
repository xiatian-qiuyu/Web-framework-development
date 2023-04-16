package com.briup.mapper;

import com.briup.bean.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@ResponseBody
public interface ICollectMapper {
	List<Collect> findByUserId(long id);
	int deleteByCollectId(Long collectId);

    Collect findByUserIdAndShopId(Long userId, Long shopId);
	//添加收藏
	int addCollect(@Param("userId") Long userId, @Param("shopId") Long shopId);

	int findCollect(Long id, Long shopId);
}

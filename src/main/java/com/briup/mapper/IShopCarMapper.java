package com.briup.mapper;

import com.briup.bean.ShopCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@ResponseBody
public interface IShopCarMapper {
	ShopCar findByShopIdAndUserId(long shopId,long userId);
	List<ShopCar> findUserShopCar(Long userId);

	void updateShopcar(int num,long id);

	List<ShopCar> findByUserId(long id);

	//删除购物车的某件商品
	void deleteById(long id);

	void saveByshopIdUserId(int num,long shopId,long userId);

	List<ShopCar> findShopCarByIds(List<Long> list);

	ShopCar findShopCarById(long id);

	void deleteShopCarByIds(@Param("list") List<Long> ids);
	//q:报错Parameter 'id' not found. Available parameters are [list, param1]的原因是什么？
	//a:因为在xml中的参数是list，而在接口中的参数是id，所以会报错

}

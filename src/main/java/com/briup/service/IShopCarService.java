package com.briup.service;


import com.briup.bean.ShopCar;

import java.util.List;
public interface IShopCarService {
    ShopCar findByShopIdAndUserId(long shopId,long userId);
    List<ShopCar> findUserAllShopCar(Long shopId,Long userId);
    //查询用户购物车
    public List<ShopCar> findUserShopCar(Long userId);
    //添加商品到购物车
    void saveShopCar(int num,Long userId,Long shopId);
    //删除购物车的某件商品
    void deleteShopCar(Long id);
    //批量删除
    void deleteShopCarByIds(List<Long> list);
    //更新购物车的某件商品的数量
    void updateShopCar(Long id,int num);
    //根据id查询购物车
    List<ShopCar> findShopCars(Long[] ids);

}

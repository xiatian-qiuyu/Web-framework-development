package com.briup.service;


import com.briup.bean.Shop;

import java.util.List;
public interface IShopService {
    List<Shop> findAllShops();
    Shop findShopById(Long id);
    List<Shop> findDiscount();
    List<Shop> recommendShop(Long userId);
    List<Shop> findRecommend();
    List<Shop> findByCategory(Long category);

    //添加商品到购物车
    void addShopToShopCar(Long shopId,Long userId);
    List<Shop> searchShop(String shopName);
}

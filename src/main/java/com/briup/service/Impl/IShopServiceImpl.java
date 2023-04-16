package com.briup.service.Impl;

import com.briup.bean.Shop;
import com.briup.mapper.IShopMapper;
import com.briup.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IShopServiceImpl implements IShopService {
    @Autowired
    private IShopMapper shopMapper;
    @Override
    public List<Shop> findAllShops() {
        return shopMapper.findAllShopByVisitVolumeDesc();
    }

    @Override
    public Shop findShopById(Long id) {
        return shopMapper.findShopById(id);
    }


    //查询折扣商品
    @Override
    public List<Shop> findDiscount() {
        return shopMapper.findByDiscountOrderBySalesVolumeDesc(true);
    }

    @Override
    public List<Shop> recommendShop(Long userId) {
        return null;
    }

    @Override
    public List<Shop> findRecommend() {
        return shopMapper.findByRecommendOrderBySalesVolumeDesc();
    }

    @Override
    public List<Shop> findByCategory(Long category) {

        return shopMapper.findByCategoryId(category);
    }

    //添加商品到购物车
    @Override
    public void addShopToShopCar(Long shopId, Long userId) {
        shopMapper.addShopToShopCar(shopId,userId,1);
    }

    //模糊查询商品
    @Override
    public List<Shop> searchShop(String shopName) {
        return shopMapper.findByNameContaining(shopName);
    }
}

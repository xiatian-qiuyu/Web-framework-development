package com.briup.service.Impl;

import com.briup.bean.ShopCar;
import com.briup.mapper.IShopCarMapper;
import com.briup.service.IShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class IShopCarServiceImpl implements IShopCarService {
    @Autowired
    private IShopCarMapper shopCarMapper;

    @Override
    public ShopCar findByShopIdAndUserId(long shopId, long userId) {
        return shopCarMapper.findByShopIdAndUserId(shopId, userId);
    }

    @Override
    public List<ShopCar> findUserAllShopCar(Long shopId,Long userId) {
        return null;
    }
    @Override
    public List<ShopCar> findUserShopCar(Long userId) {
        return shopCarMapper.findUserShopCar(userId);
    }

    @Override
    public void saveShopCar(int num, Long userId, Long shopId) {
        shopCarMapper.saveByshopIdUserId(num, shopId, userId);

    }

    @Override
    public void deleteShopCar(Long id) {
        shopCarMapper.deleteById(id);
    }

    @Override
    public void deleteShopCarByIds(List<Long> list) {
//         shopCarMapper.deleteShopCarByIds(list);
        for(Long id:list){
            shopCarMapper.deleteById(id);
        }
    }

    @Override
    public void updateShopCar(Long id, int num) {
        shopCarMapper.updateShopcar(num,id);
    }

    @Override
    public List<ShopCar> findShopCars(Long[] ids) {
        List<ShopCar> shopCarList = new ArrayList<>();
        for (Long id : ids) {
            ShopCar shopCarById = shopCarMapper.findShopCarById(id);
            shopCarList.add(shopCarById);
        }
        return shopCarList;
    }
}

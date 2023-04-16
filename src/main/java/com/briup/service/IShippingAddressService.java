package com.briup.service;


import com.briup.bean.ShippingAddress;

import java.util.List;

public interface IShippingAddressService {
    void saveShippingAddress(ShippingAddress shippingAddress);
    List<ShippingAddress> findUserAllShippingAddress(Long user_id);

    void deleteShippingAddress(Long addressId);

    //根据id查询收货地址
    ShippingAddress findAddressById(Long addressId);

    //修改收货地址
    void updateShippingAddress(ShippingAddress shippingAddress);

    void setDefault(Long addressId, Long userId,boolean isDefault);
}

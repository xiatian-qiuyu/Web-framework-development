package com.briup.service.Impl;

import com.briup.bean.ShippingAddress;
import com.briup.mapper.IShippingAddressMapper;
import com.briup.service.IShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IShipingAddressServiceImpl implements IShippingAddressService {
    @Autowired
    private IShippingAddressMapper iShippingAddressMapper;

    @Override
    public void saveShippingAddress(ShippingAddress shippingAddress) {
        iShippingAddressMapper.saveShippingAddress(shippingAddress);
    }

    @Override
    public List<ShippingAddress> findUserAllShippingAddress(Long user_id) {
        return iShippingAddressMapper.findByUserId(user_id);
    }

    @Override
    public void deleteShippingAddress(Long addressId) {
        iShippingAddressMapper.deleteShippingAddress(addressId);
    }

    @Override
    public ShippingAddress findAddressById(Long addressId) {
        return iShippingAddressMapper.findAddressById(addressId);
    }

    @Override
    public void updateShippingAddress(ShippingAddress shippingAddress) {
        iShippingAddressMapper.updateShippingAddress(shippingAddress);
    }

    @Override
    public void setDefault(Long addressId, Long userId,boolean isDefault) {
        iShippingAddressMapper.setDefault(addressId, userId, isDefault);
    }
}

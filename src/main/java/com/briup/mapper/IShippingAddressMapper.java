package com.briup.mapper;


import com.briup.bean.ShippingAddress;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@ResponseBody
public interface IShippingAddressMapper {
    List<ShippingAddress> findByUserId(Long userId);
    void saveShippingAddress(ShippingAddress shippingAddress);

    void deleteShippingAddress(Long addressId);

    ShippingAddress findAddressById(Long addressId);

    void updateShippingAddress(ShippingAddress shippingAddress);

    void setDefault(Long addressId,Long userId,boolean isDefault);
}

package com.briup.mapper;


import com.briup.bean.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@ResponseBody
public interface IOrderItemMapper {
    void saveOrderItem(OrderItem orderItem);
    //根据订单id查询订单项
    List<OrderItem> findOrderItemByOrderId(String orderId);
}

package com.briup.service;

import com.briup.bean.OrderItem;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IOrderItemService {
    void saveOrderItem(OrderItem orderItem);
    //根据订单id查询订单项
    List<OrderItem> findOrderItemByOrderId(String orderId);
}

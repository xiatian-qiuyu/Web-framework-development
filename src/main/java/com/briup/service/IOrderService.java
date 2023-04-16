package com.briup.service;

import com.briup.bean.Order;
import com.briup.bean.User;


import java.util.List;
public interface IOrderService {

    List<Order> findUserAllOrders(Long userId);
    void saveOrder(Order order);

    void paySuccess(String orderId);

    Order findById(String orderId);

    void deleteOrderByOrderId(String orderId);
}

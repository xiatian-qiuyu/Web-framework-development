package com.briup.service.Impl;

import com.briup.bean.Order;
import com.briup.mapper.IOrderMapper;
import com.briup.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderMapper iOrderMapper;
    @Override
    public List<Order> findUserAllOrders(Long userId) {
        return iOrderMapper.findByUserId(userId);
    }

    @Override
    public void saveOrder(Order order) {
         iOrderMapper.saveOrder(order);
    }

    @Override
    public void paySuccess(String orderId) {
        iOrderMapper.updateOrder(4,orderId);
    }

    @Override
    public Order findById(String orderId) {
        return iOrderMapper.findOrderByOrderId(orderId);
    }

    @Override
    public void deleteOrderByOrderId(String orderId) {
        iOrderMapper.deleteOrderByOrderId(orderId);
    }
}

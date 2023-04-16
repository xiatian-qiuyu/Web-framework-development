package com.briup.service.Impl;

import com.briup.bean.OrderItem;
import com.briup.mapper.IOrderItemMapper;
import com.briup.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrderItemServiceImpl implements IOrderItemService {
    @Autowired
    private IOrderItemMapper iOrderItemMapper;
    public void saveOrderItem(OrderItem orderItem) {
        iOrderItemMapper.saveOrderItem(orderItem);
    }

    @Override
    public List<OrderItem> findOrderItemByOrderId(String orderId) {
        return iOrderItemMapper.findOrderItemByOrderId(orderId);
    }
}

package com.briup.mapper;

import com.briup.bean.Order;
import com.briup.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@ResponseBody
public interface IOrderMapper {
	List<Order> findByUserId(Long userId);
	void saveOrder(Order order);

	Order findOrderByOrderId(String order_id);
	void updateOrder(int status,String orderId);

    void deleteOrderByOrderId(String orderId);
}

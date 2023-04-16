package com.briup.web.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.briup.bean.*;
import com.briup.config.AliPayConfig;
import com.briup.service.IOrderItemService;
import com.briup.service.IOrderService;
import com.briup.service.IShippingAddressService;
import com.briup.service.IShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IShopCarService shopCarService;
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private IShippingAddressService shippingAddressService;

    // 预付款
    @PostMapping("/advanceOrder")
    public String advanceOrder(@RequestParam("ids") Long[] shopCarIds, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        //购物车勾选的商品列表
        List<ShopCar> shopCarList = shopCarService.findShopCars(shopCarIds);
       //查找收货地址信息
        List<ShippingAddress> userAddressList = shippingAddressService.findUserAllShippingAddress(user.getId());
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        //计算总价
        for (ShopCar shopCar : shopCarList) {
            if(shopCar.getShop().getDiscountPrice()!=null){
                sumPrice =sumPrice.add(shopCar.getShop().getDiscountPrice().multiply(BigDecimal.valueOf(shopCar.getNum())));
            }else{
                sumPrice = sumPrice.add(shopCar.getShop().getSellingPrice().multiply(BigDecimal.valueOf(shopCar.getNum())));
            }
        }
        session.setAttribute("shopCarList",shopCarList);
        session.setAttribute("userAddressList",userAddressList);
        session.setAttribute("sumPrice",sumPrice);

        model.addAttribute("userAddressList",userAddressList);
        model.addAttribute("shopCarList",shopCarList);
        model.addAttribute("sumPrice",sumPrice);
        return "confirm";
    }

    // 创建订单
    @PostMapping("/createOrder")
    public void createOrder(HttpSession session, Model model,
                              @RequestParam("ids") Long[] shopCarIds,
                              @RequestParam("addressId") Long addressId,
                              @RequestParam("payType") String payType,
                              @RequestParam("sendType") String sendType,
                              HttpServletResponse response,
                              HttpServletRequest request) throws ServletException, IOException {

        User user = (User) session.getAttribute("user");
        List<ShopCar> shopCarList = (List<ShopCar>) session.getAttribute("shopCarList");
        List<ShippingAddress> userAddressList = (List<ShippingAddress>) session.getAttribute("userAddressList");
        BigDecimal sumPrice= (BigDecimal) session.getAttribute("sumPrice");
        Order order = new Order();
        order.setUser(user);
        order.setSumPrice(sumPrice);
        order.setCreateDate(new Date());
        //如果点击结算但是没有付款，订单状态为1
        order.setStatus(1);
        //因为在前端页面中，我们只能选择一个收货地址，所以这里只能取第一个
        order.setShippingAddress(userAddressList.get(0));
        List<OrderItem> orderItems = new ArrayList<>();
        orderService.saveOrder(order);
        //创建订单项
        for (ShopCar shopCar : shopCarList) {
            OrderItem orderItem = new OrderItem(shopCar);
            orderItem.setOrder(order);
            orderItems.add(orderItem);
            orderItemService.saveOrderItem(orderItem);
        }
        order.setOrderItems(orderItems);
        //删除购物车中的商品
        shopCarService.deleteShopCarByIds(Arrays.asList(shopCarIds));
        request.setAttribute("order",order);
        request.getRequestDispatcher("/order/payOrder").forward(request,response);
    }

    //将我的订单中选中的未支付的订单去支付
    //通过订单id查询订单，然后将订单信息重定向到/order/payOrder
    @GetMapping("/selectOrder")
    public void selectOrder(@RequestParam("id") String id,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Order order = orderService.findById(id);
        //查询订单项
        List<OrderItem> orderItems= orderItemService.findOrderItemByOrderId(id);
        order.setOrderItems(orderItems);
        //根据订单项查询商品，计算订单价格
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        for (OrderItem orderItem : orderItems) {
            if(orderItem.getShop().getDiscountPrice()!=null){
                sumPrice =sumPrice.add(orderItem.getShop().getDiscountPrice().multiply(BigDecimal.valueOf(orderItem.getNum())));
            }else{
                sumPrice = sumPrice.add(orderItem.getShop().getSellingPrice().multiply(BigDecimal.valueOf(orderItem.getNum())));
            }
        }
        request.setAttribute("order",order);
        request.getRequestDispatcher("/order/payOrder").forward(request,response);
    }


    // 结算支付
    @RequestMapping("/payOrder")
    @ResponseBody
    public void payOrder(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, AlipayApiException {
        Order order = (Order) request.getAttribute("order");
        request.setAttribute("order",order);
        String path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/order/paySuccess/?id="+order.getId();
        //这个方法是将订单信息传递给支付宝，然后支付宝会返回一个表单，这个表单就是支付宝的支付页面
        AlipayClient alipayClient = AliPayConfig.getAlipayClient();
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.page.pay
        AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。。
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();//创建API对应的model类
        //订单支付
        model.setBody("商品描述");
        //订单名称
        model.setSubject("杰普购物订单");
        //订单号
        model.setOutTradeNo(order.getId());
        //过期时间
        model.setTimeoutExpress("30m");
        //订单金额
        model.setTotalAmount(order.getSumPrice().toString());
        //产品码
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        //设置参数
        alipayTradePagePayRequest.setBizModel(model);
        //设置回调地址
        alipayTradePagePayRequest.setReturnUrl(path);
        //客户端执行，拿到支付结果
        AlipayTradePagePayResponse pageExecute = alipayClient.pageExecute(alipayTradePagePayRequest);
        String result = pageExecute.getBody();
        //将支付结果返回给前端
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(result);
        response.getWriter().flush();
        response.getWriter().close();

    }
    // 支付成功
    @RequestMapping("/paySuccess")
    public String paySuccess(@RequestParam("id") String orderId, Model model,HttpSession session){
        orderService.paySuccess(orderId);
        //根据订单id查找订单
        Order order = orderService.findById(orderId);
        //根据订单id查找订单项
        List<OrderItem> orderItems = orderItemService.findOrderItemByOrderId(orderId);
        order.setOrderItems(orderItems);
        model.addAttribute("order",order);
        return "confirmSuc";
    }
    // 查看所有订单
    @GetMapping("/findAllOrders")
    public String findAllOrders(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<Order> orderList = orderService.findUserAllOrders(user.getId());
        System.out.println("订单："+orderList);
        //根据订单id查找订单项
        for (Order order : orderList) {
            List<OrderItem> orderItems= orderItemService.findOrderItemByOrderId(order.getId());
            order.setOrderItems(orderItems);
        }
        model.addAttribute("orderList",orderList);
        System.out.println("订单："+orderList);
        return "order";
    }

    //删除订单
    @DeleteMapping("/deleteOrder")
    @ResponseBody
    public void deleteOrder(@RequestParam("id") String orderId,HttpSession session){
        orderService.deleteOrderByOrderId(orderId);
    }

}

package com.briup.web.controller;

import com.briup.bean.ShippingAddress;
import com.briup.bean.User;
import com.briup.service.IShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/shippingAddress")
public class ShippingAddressController {
    @Autowired
    private IShippingAddressService shippingAddressService;
    //跳转到收货地址页面
    @RequestMapping("/toShippingAddress")
    public String toshippingAddress(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<ShippingAddress> addressList = shippingAddressService.findUserAllShippingAddress(user.getId());
        model.addAttribute("addressList", addressList);
        return "addShippingAddress";
    }
    //添加收货地址
    @PostMapping("/addShippingAddress")
    public String addShippingAddress(ShippingAddress shippingAddress, HttpSession session) {
        User user = (User) session.getAttribute("user");
        shippingAddress.setUser(user);
        shippingAddressService.saveShippingAddress(shippingAddress);
        return "redirect:/shippingAddress/toShippingAddress";
    }

    //删除收货地址
    @DeleteMapping("/delAddress")
    @ResponseBody
    public void delAddress(Long addressId) {
        shippingAddressService.deleteShippingAddress(addressId);
    }

    //修改收货地址
    //1.获取收货地址信息
    @GetMapping("/getAddress")
    @ResponseBody
    public ShippingAddress getAddress(Long addressId) {
        ShippingAddress shippingAddress= shippingAddressService.findAddressById(addressId);
        return shippingAddress;
    }
    //2/修改收货地址
    @PostMapping("/updateAddress")
    public String updateAddress(ShippingAddress shippingAddress) {
        shippingAddressService.updateShippingAddress(shippingAddress);
        return "redirect:/shippingAddress/toShippingAddress";
    }

    //设置默认地址
    @PutMapping("/setDefault")
    @ResponseBody
    public void setDefault(@RequestParam("addressId") Long id,HttpSession session) {
        User user = (User) session.getAttribute("user");
        shippingAddressService.setDefault(id,user.getId(),true);
        List<ShippingAddress> addressList = shippingAddressService.findUserAllShippingAddress(user.getId());
       //将其他地址设置为非默认地址
        for (ShippingAddress address : addressList) {
            if(address.getId()!=id){
                shippingAddressService.setDefault(address.getId(),user.getId(),false);
            }
        }
    }

}


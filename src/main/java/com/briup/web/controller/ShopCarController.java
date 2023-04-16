package com.briup.web.controller;

import com.briup.bean.ShopCar;
import com.briup.bean.User;
import com.briup.service.IShopCarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class ShopCarController {
    @Autowired
    private IShopCarService shopCarService;

    //展示购物车
    @RequestMapping("/toShopCar")
    public String shopCar(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<ShopCar> shopCar = shopCarService.findUserShopCar(user.getId());
        model.addAttribute("shopCarList",shopCar);
        return "shopCar";
    }

    //更新购物车商品数量
    @RequestMapping("/updateShopCar")
    @ResponseBody
    public String updateShopCar(@RequestParam("shopCarId") Long id,
                                @RequestParam("num") Integer num,
                                Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("/updateShopCar",user.getId(),id,num,new Date().getTime());
        shopCarService.updateShopCar(id,num);
        List<ShopCar> shopCar = shopCarService.findUserShopCar(user.getId());
        model.addAttribute("shopCarList",shopCar);
        return "success";
    }
    //单个删除
    @GetMapping("/delShopCar")
    @ResponseBody
    public String delCar(@RequestParam("shopCarId") Long id,
                         Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("/delShopCar",user.getId(),id,new Date().getTime());
        shopCarService.deleteShopCar(id);
        List<ShopCar> shopCar = shopCarService.findUserShopCar(user.getId());
        model.addAttribute("shopCarList",shopCar);
        return "success";
    }
    // 批量删除
    @GetMapping("/batchDelCar")
    @ResponseBody
    public String batchDelCar(@RequestParam("shopCarIds") List<Long> ids,
                              Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("/batchDelCar",user.getId(),ids,new Date().getTime());
        shopCarService.deleteShopCarByIds(ids);
        List<ShopCar> shopCar = shopCarService.findUserShopCar(user.getId());
        model.addAttribute("shopCarList",shopCar);
        return "success";
    }
}

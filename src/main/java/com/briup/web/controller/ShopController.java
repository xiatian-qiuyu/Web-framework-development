package com.briup.web.controller;

import com.briup.bean.Shop;
import com.briup.bean.User;
import com.briup.service.IShopCarService;
import com.briup.service.IShopService;
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
@RequestMapping("/shop")
@Slf4j
public class ShopController {

    @Autowired
    private IShopService shopService;
    @Autowired
    private IShopCarService shopCarService;

    @RequestMapping ("/searchShop")
    public String searchShop(@RequestParam("shopName") String shopName, Model model){
        List<Shop> shopList = shopService.searchShop(shopName);
        model.addAttribute("shopList",shopList);
        return "list";
    }
    @RequestMapping("/toViewShop")
    public String toViewShop(@RequestParam ("shopId") long shopId,Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("shop/toViewShop",user.getId(),shopId,new Date().getTime());
        Shop shop = shopService.findShopById(shopId);
        model.addAttribute("shop",shop);
        return "viewShop";
    }

    @GetMapping("/addShopCar")
    @ResponseBody
    public void addShopCar(@RequestParam("shopId") Long shopId, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("shop/addShopCar",user.getId(),shopId,new Date().getTime());
        //判断如果商品已经在购物车中，点击就添加购物车中该商品的数量，否则就添加商品到购物车
        if(shopCarService.findByShopIdAndUserId(shopId,user.getId())!=null){
            //获取购物车中该商品的数量
            int num = shopCarService.findByShopIdAndUserId(shopId,user.getId()).getNum();
            //将商品数量加1
            num++;
            //更新购物车中该商品的数量
            shopCarService.updateShopCar(shopId,num);

        }else {
            shopService.addShopToShopCar(shopId,user.getId());
        }
    }

    // 根据商品分类查询商品
    @RequestMapping("/toList")
    public String toShopLisByCategory(@RequestParam("categoryId") Long id,Model model){
        List<Shop> byCategory = shopService.findByCategory(id);
        model.addAttribute("shopList",byCategory);
        return "list";
    }

}

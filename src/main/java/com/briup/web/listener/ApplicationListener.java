package com.briup.web.listener;

import com.briup.bean.Shop;
import com.briup.bean.vo.CategoryVO;
import com.briup.service.ICategoryService;
import com.briup.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.List;
@Component
public class ApplicationListener implements ServletContextListener {
    @Autowired
    private IShopService shopService;

    @Autowired
    private ICategoryService categoryService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //特价商品
        List<Shop> shopList= shopService.findDiscount();
        //发现好物
        List<Shop> shops = shopService.findAllShops();
        //推荐商品
        List<Shop> recommendList = shopService.findRecommend();
        //商品分类
        List<CategoryVO> categoriesList = categoryService.findAllCategoey();
        sce.getServletContext().setAttribute("discountList",shopList);
        sce.getServletContext().setAttribute("shops",shops);
        sce.getServletContext().setAttribute("categories",categoriesList);
        sce.getServletContext().setAttribute("recommendList",recommendList);
        System.out.println("contextInitialized");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed");
    }
}

//掌握项目编写的业务流程的方法
//1.先写接口
//2.再写实现类
//3.再写controller
//4.再写jsp
//5.再写service
//6.再写mapper
//7.再写bean
//8.再写配置文件
//9.再写测试类
//10.再写工具类
//11.再写监听器
//12.再写过滤器
//13.再写拦截器
//14.再写异常处理器





package com.briup.web.controller;

import com.briup.bean.Collect;
import com.briup.bean.User;
import com.briup.service.ICollectService;
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
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private ICollectService collectService;
    // 添加收藏
    @GetMapping("/addCollect")
    @ResponseBody
    public boolean addCollect(@RequestParam("shopId") Long shopId, HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("/collect/addCollect",user.getId(),shopId,new Date().getTime());
        int i = collectService.addCollect(user.getId(),shopId);
        return i == 1;
    }
    // 收藏列表
    @GetMapping("/toCollect")
    public String toCollect(HttpSession session, Model model ){
        User user = (User) session.getAttribute("user");
        List<Collect> userAllCollect = collectService.findUserAllCollect(user.getId());
        model.addAttribute("collects",userAllCollect);
        return "collect";
    }
    //查询是否收藏
    @GetMapping("/findCollect")
    @ResponseBody
    public int findCollect(@RequestParam("shopId") Long shopId, HttpSession session){
        User user = (User) session.getAttribute("user");
        return collectService.findCollect(user.getId(), shopId);
    }
    // 取消收藏
    @GetMapping("/delCollect")
    @ResponseBody
    public int delCollect(@RequestParam("collectId") Long collectId, HttpSession session){
        User user = (User) session.getAttribute("user");
        log.info("/collect/delCollect",user.getId(),collectId,new Date().getTime());
        int i = collectService.deleteCollect(collectId);
        return i == 1 ? 1 : 0;
    }
}

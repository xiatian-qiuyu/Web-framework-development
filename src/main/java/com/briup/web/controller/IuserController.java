package com.briup.web.controller;

import com.briup.bean.ShippingAddress;
import com.briup.bean.User;
import com.briup.md5Util.MD5Util;
import com.briup.service.IShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.briup.service.IUserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class IuserController {

    @Autowired
    private User user;
    @Autowired
    private IUserService userService;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private IShippingAddressService shippingAddressService;

    public IuserController() {
    }

    //个人中心
    @RequestMapping("/center")
    public String center(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        String  msg = (String) session.getAttribute("msg");
        model.addAttribute("msg",msg);
        return "userinfo";
    }

    //修改个人信息
    @GetMapping("/update")
    public String update(@RequestParam("nickName") String nickName,
                         @RequestParam("password") String password,
                         @RequestParam("phone") String phone,
                         @RequestParam("eMail") String eMail,
                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        user.setNickName(nickName);
        user.setPasswordMd5(md5Util.md5(password));
        User findUserByPhone = userService.findByLoginName(phone);
        User findUserByEmail = userService.findByLoginName(eMail);
        if (findUserByPhone != null && !findUserByPhone.getId().equals(user.getId())){
            session.setAttribute("msg", "该手机号已被注册！");
            return "redirect:/user/center";
        }
        if (findUserByEmail != null && !findUserByEmail.getId().equals(user.getId())) {
            session.setAttribute("msg", "该邮箱已被注册！");
            return "redirect:/user/center";
        }
        else {
            user.setPhone(phone);
            user.setEMail(eMail);
            userService.update(user);
            //更新session
            session.setAttribute("user", user);
            session.removeAttribute("msg");
            return "redirect:/user/center";
        }
    }

    //登录
    @PostMapping("/login")
    public String login(String loginName,
                        String password,
                        HttpSession session, Model model) {

        String passwordMd5 = md5Util.md5(password);
        User user = userService.login(loginName, passwordMd5);
        if (user != null) {
            session.setAttribute("user", user);
            session.setAttribute("loginSession", user.getLoginName());
            return "redirect:/toIndex";
        } else {
            model.addAttribute("msg", "请先注册！");
            return "login";
        }
    }

    //注册
    @PostMapping("/register")
    public String register(User user,
                           @RequestParam("password") String password,
                           Model model) {
        String passwordMd5=md5Util.md5(password);
        user.setPasswordMd5(passwordMd5);
        user.setLock(false);
        User findUserByLoginName = userService.findByLoginName(user.getLoginName());
        User findUserByPhone = userService.findByLoginName(user.getPhone());
        User findUserByEmail = userService.findByLoginName(user.getEMail());
        if (findUserByLoginName != null) {
            model.addAttribute("msg", "该登录名已存在，请重新注册！");
            return "register";
        }
        if (findUserByPhone != null) {
            model.addAttribute("msg", "该手机号已存在，请重新注册！");
            return "register";
        }
        if (findUserByEmail != null) {
            model.addAttribute("msg", "该邮箱已存在，请重新注册！");
            return "register";
        }
        int isOk = userService.register(user);
        if (isOk == 1) {
            return "login";
        } else {
            return "register";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/toIndex";
    }
}

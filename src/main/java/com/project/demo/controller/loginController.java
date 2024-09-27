package com.project.demo.controller;

import com.project.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Objects;

@Controller
public class loginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        int Id = userService.userLoginCheck(username, password);
        if (Id != 0) {
            String role = userService.findById(Id).getRole();
            if (Objects.equals(role, "1")) {
                //管理员登陆成功
                session.setAttribute("loginUser", Id);
                return "redirect:/admin/dashboard.html";
            } else if (Objects.equals(role, "0")) {
                //普通用户登录成功
                session.setAttribute("loginUser", Id);
                map.put("id", Id);
                return "webService/menu";
            }
        }
        //登陆失败
        map.put("msg", "用户名或密码错误");
        return "login/login";
    }

    @PostMapping(value = "/user/register")
    public String register(@RequestParam("registerUsername") String name, @RequestParam("email") String email,
                           @RequestParam("registerPassword") String password,Map<String, Object> map,
                           @RequestParam("confirmPassword") String pwd, HttpSession session) {
        if (pwd.equals(password)&&!email.isEmpty()&&!name.isEmpty()){
            session.setAttribute("loginUser", name);
            userService.addUser(name, email, password);
            System.out.println(pwd);
            System.out.println(password);
            return "redirect:/login.html";
        }else {
            map.put("notice", "请检查输入是否正确");
            return "login/register";
        }
    }
}

package com.project.demo.controller;

import com.project.demo.Response.UserInfoResponse;
import com.project.demo.service.UserService;
import com.project.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 管理员
     * 查询操作
     */
    //按id查询用户信息
    @RequestMapping("/admin/user/{id}")
    public UserInfoResponse getUserById(@PathVariable int id){
        UserVo data = userService.findById(id);
        boolean success = true;
        String msg = null;
        if(data == null){success = false;msg = "用户不存在";}
        return new UserInfoResponse(data, success, msg);
    }

    //查询所有用户
    @RequestMapping("/admin/users")
    public UserInfoResponse getUsers(){
        return new UserInfoResponse(userService.findAll(), true, null);
    }

    //删除用户
    @DeleteMapping("/admin/delUser/{id}")
    public UserInfoResponse delUserById(@PathVariable int id){
        userService.delUser(id);
        return new UserInfoResponse("success del user" + id, true, null);
    }

    //修改用户信息
    @PostMapping("/admin/editUser/{id}")
    public UserInfoResponse editUserById(@PathVariable int id){
        // userService.editUser(user);
        return new UserInfoResponse("success edit user" + id, true, null);
    }

    /**
     * 前端访问数据展示
     */
    @RequestMapping("/api/getId")
    public UserInfoResponse getUserId(){

        return new UserInfoResponse("success get userid", true, null);
    }
}

package com.project.demo.service;

import com.project.demo.entity.User;
import com.project.demo.mapper.UserMapper;
import com.project.demo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int userLoginCheck(String username, String password) {
        int flag = 0;
        for (int i = 0; i < findAll().size(); i++) {
            String Id = String.valueOf(findAll().get(i).getId());
            String name = findAll().get(i).getName();
            String Password = findPassword().get(i).getPassword();
            String Email = findAll().get(i).getEmail();
            String role = findAll().get(i).getRole();
            //登录身份验证
            if (Password.equals(password) && (Id.equals(username)||Email.equals(username)||name.equals(username))) {
                flag = Integer.parseInt(Id);
                return flag;
            }
        }
        return flag;
    }

    public UserVo findById(int id) {
        return userMapper.findById(id);
    }

    public List<UserVo> findAll(){
        return userMapper.findAll();
    }

    public List<User> findPassword() {
        return userMapper.findPassword();
    }

    public void addUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        Date date = new Date(System.currentTimeMillis());
        user.setRegisterDate(date);
        userMapper.userInsert(user);
    }

    public void changeUserStatus(int userId, boolean isOnline) {
        int status = isOnline ? 1 : 0;
        userMapper.updateUserStatus(userId, status);
    }

    public void delUser(int userId) {
        userMapper.deleteUser(userId);
    }

    public void editUser(int id,String name, String email, String password) {

    }
}

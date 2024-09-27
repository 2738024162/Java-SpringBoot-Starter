package com.project.demo.mapper;

import com.project.demo.entity.User;
import com.project.demo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper{
    List<UserVo> findAll();

    UserVo findById(int id);

    List<User> findPassword();

    void userInsert(User user);

    void editUser(User user);

    void updateUserStatus(int id, int status);

    void deleteUser(int id);
}

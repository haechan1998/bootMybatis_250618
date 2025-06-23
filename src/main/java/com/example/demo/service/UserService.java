package com.example.demo.service;

import com.example.demo.domain.UserVO;

import java.util.List;

public interface UserService {
    void insertUser(UserVO userVO);

    List<UserVO> getUserList();

    UserVO getUser(UserVO userVO);

    void updateUser(UserVO userVO);

    void removeUser(UserVO userVO);
}

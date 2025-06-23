package com.example.demo.repository;

import com.example.demo.domain.AuthVO;
import com.example.demo.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(UserVO userVO);

    void insertAuth(String email);

    UserVO selectUser(String username);

    List<AuthVO> selectAuths(String username);

    List<UserVO> getUserList();

    List<AuthVO> getAuthList(String email);

    UserVO getUser(UserVO userVO);

    void updateUser(UserVO userVO);

    void removeUser(UserVO userVO);

    void removeUserAuth(UserVO userVO);
}

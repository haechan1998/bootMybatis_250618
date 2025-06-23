package com.example.demo.service;

import com.example.demo.domain.UserVO;
import com.example.demo.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;

    @Transactional
    @Override
    public void insertUser(UserVO userVO) {
        userMapper.insertUser(userVO);
        userMapper.insertAuth(userVO.getEmail());

    }

    @Override
    public List<UserVO> getUserList() {
        List<UserVO> userList = userMapper.getUserList();
        for(UserVO user : userList){
            user.setAuthList(userMapper.getAuthList(user.getEmail()));
        }

        return userList;
    }

    @Override
    public UserVO getUser(UserVO userVO) {
        return userMapper.getUser(userVO);
    }

    @Override
    public void updateUser(UserVO userVO) {
        userMapper.updateUser(userVO);

    }

    @Transactional
    @Override
    public void removeUser(UserVO userVO) {
        userMapper.removeUserAuth(userVO);
        userMapper.removeUser(userVO);

    }
}

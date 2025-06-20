package com.example.demo.service;

import com.example.demo.domain.UserVO;
import com.example.demo.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}

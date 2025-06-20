package com.example.demo.controller;

import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/*")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public void signup(){}

    @PostMapping("/signup")
    public String signupInsert(UserVO userVO){

        // password 인코딩 설정
        userVO.setPwd(passwordEncoder.encode(userVO.getPwd()));
        log.info(">> userVO > {}", userVO);

        userService.insertUser(userVO);

        return "redirect:/"; // 회원가입하면 루트로
    }

    @GetMapping("/login")
    public void login(){}

}

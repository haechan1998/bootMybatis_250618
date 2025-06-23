package com.example.demo.controller;

import com.example.demo.domain.UserVO;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    @GetMapping("/list")
    public void list(Model model){

        List<UserVO> userList = userService.getUserList();

        model.addAttribute("userList",userList);

    }
    @GetMapping("/modify")
    public void modify(){}

    @PostMapping("/update")
    public String updateUser(UserVO userVO){
        if(userVO.getPwd() == "" || userVO.getPwd() == null){
            UserVO user = userService.getUser(userVO); // 비밀번호를 바꾸지 않고 빈값을 제출 했을경우
            userVO.setPwd(user.getPwd());
        }else{
            // 변경이 되었을 경우 새롭게 인코딩
            userVO.setPwd(passwordEncoder.encode(userVO.getPwd()));
        }

        userService.updateUser(userVO);

        return "redirect:/user/logout";
    }

    @PostMapping("/removeUser")
    public String removeUser(UserVO userVO){

        userService.removeUser(userVO);

        return "redirect:/user/logout";
    }

}

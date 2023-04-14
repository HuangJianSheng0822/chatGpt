package com.gpt.chatgpt.controller;


import com.gpt.chatgpt.dto.LoginUser;
import com.gpt.chatgpt.pojo.User;
import com.gpt.chatgpt.service.UserService;
import com.gpt.chatgpt.vo.ResponceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    ResponceData login(@RequestBody LoginUser user, HttpServletRequest req){
        req.getSession().setAttribute("email",user.getEmail());
        User user1 = new User().setEmail(user.getEmail()).setPwd(user.getPwd());
        boolean login = userService.login(user1);
        if (login){
            return new ResponceData(200,"success","YES");
        }else{
            return new ResponceData(200,"登录失败","NO");
        }
    }

    @PostMapping("/register")
    ResponceData register(@RequestParam("email") String email,@RequestParam("pwd") String pwd,@RequestParam("code") String code,HttpServletRequest req){
        String sessionCode = (String) req.getSession().getAttribute("code");
        if (sessionCode.equals(code)){
            boolean register = userService.register(new User().setEmail(email).setPwd(pwd));
            if (register){
                return new ResponceData(200,"success","YES");
            }else{
                return new ResponceData(200,"注册失败","NO");
            }
        }else{
            return new ResponceData(200,"验证码错误","NO");
        }

    }


}

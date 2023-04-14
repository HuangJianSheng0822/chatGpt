package com.gpt.chatgpt.controller;

import com.gpt.chatgpt.service.MailService;
import com.gpt.chatgpt.util.VerCodeGenerateUtil;
import com.gpt.chatgpt.vo.ResponceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MailController {

    @Autowired
    MailService mailService;
    @PostMapping("/code")
    ResponceData sendCode(String email, HttpServletRequest req){
        String code = VerCodeGenerateUtil.generateVerCode();
        req.getSession().setAttribute("code",code);
        boolean reg = mailService.sendSimpleCode(email, "REG", code);
        return new ResponceData(200,null,reg);
    }
}

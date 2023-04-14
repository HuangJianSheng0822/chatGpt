package com.gpt.chatgpt.controller;

import com.gpt.chatgpt.service.MessageService;
import com.gpt.chatgpt.vo.ResponceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/sendMessage")
    ResponceData sendMessage(@RequestParam("content") String content, HttpServletRequest req){
        String email = (String) req.getSession().getAttribute("email");
        if (email!=null){
            String con = messageService.sendMsg(content,email);
            Calendar instance = Calendar.getInstance();
            int hours = instance.get(Calendar.HOUR_OF_DAY);
            int minutes = instance.get(Calendar.MINUTE);
            HashMap<Object, Object> map = new HashMap<>();
            map.put("date",hours+":"+minutes);
            map.put("content",con);
            return new ResponceData(200,"OK",map);
        }else{
            return new ResponceData(200,"非法登录",null);
        }

    }


}

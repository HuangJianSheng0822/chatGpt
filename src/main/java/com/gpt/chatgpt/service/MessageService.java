package com.gpt.chatgpt.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gpt.chatgpt.pojo.BaseReqParam;
import com.gpt.chatgpt.pojo.Message;
import com.gpt.chatgpt.pojo.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

public interface MessageService extends IService<Message> {

    String sendMsg(String content,String email);

}

package com.gpt.chatgpt.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpt.chatgpt.mapper.MessageMapper;
import com.gpt.chatgpt.pojo.BaseReqParam;
import com.gpt.chatgpt.pojo.Message;
import com.gpt.chatgpt.pojo.MyMessage;
import com.gpt.chatgpt.pojo.RespEntity;
import com.gpt.chatgpt.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper,Message> implements MessageService{

    @Autowired
    private BaseReqParam baseReqParam;

    @Autowired
    private MessageMapper messageMapper;


    public String sendMsg(String content,String email){

        //url
        String url = baseReqParam.getUrl();

        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",baseReqParam.getAuthorization());

        //请求体
        JSONObject paramMap = new JSONObject();
        paramMap.put("model",baseReqParam.getModel());
        paramMap.put("max_tokens",baseReqParam.getMax_tokens());
        paramMap.put("temperature",baseReqParam.getTemperature());
        paramMap.put("top_p",baseReqParam.getTop_p());
        paramMap.put("n",baseReqParam.getN());
        paramMap.put("stream",baseReqParam.isBstream());

        //上下文
        Page<Message> objectPage = new Page<>();
        QueryWrapper<Message> qw = new QueryWrapper<>();
        qw.orderByAsc("created");
        qw.eq("email",email);
        Page<Message> p = messageMapper.selectPage(objectPage, qw);
        List<Message> records = p.getRecords();
        ArrayList<MyMessage> myMsg = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            myMsg.add(new MyMessage(records.get(i).getRole(),records.get(i).getContent()));
        }
        myMsg.add(new MyMessage(MyMessage.USER,content));
        paramMap.put("messages", myMsg);


        //整合请求头和请求参数
        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(paramMap, headers);

        //请求客户端
        RestTemplate rt = new RestTemplate();


        //发起请求
        ResponseEntity<RespEntity> result = rt.postForEntity(url, httpEntity, RespEntity.class);


        if (result.getStatusCodeValue()==200){
            Message message = result.getBody().getChoices().get(0).getMessage();

            //上下文
            int user = messageMapper.insert(new Message().setRole(Message.USER).setContent(content).setEmail(email));
            int ass = messageMapper.insert(new Message().setRole(Message.ASSISTANT).setContent(message.getContent()).setEmail(email));
            //显示
            return message.getContent();
        }else {
            return "null";
        }


    }



}

package com.gpt.chatgpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gpt.chatgpt.pojo.User;
import com.gpt.chatgpt.vo.ResponceData;
import org.springframework.stereotype.Service;

public interface UserService extends IService<User> {
    boolean login(User user);

    boolean register(User user);
}

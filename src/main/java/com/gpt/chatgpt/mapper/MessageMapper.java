package com.gpt.chatgpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gpt.chatgpt.pojo.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMapper extends BaseMapper<Message> {
}

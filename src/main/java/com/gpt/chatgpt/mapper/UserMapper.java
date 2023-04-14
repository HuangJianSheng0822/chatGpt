package com.gpt.chatgpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gpt.chatgpt.pojo.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends BaseMapper<User>  {
}

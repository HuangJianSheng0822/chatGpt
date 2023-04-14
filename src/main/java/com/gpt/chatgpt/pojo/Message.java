package com.gpt.chatgpt.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    public static final String SYSTEM="system";
    public static final String USER="user";
    public static final String ASSISTANT="assistant";
    @TableId(value = "msg_id",type = IdType.ASSIGN_ID)
    private String msgId;
    private String email;
    private String role;
    private String content;
    @TableField(fill = FieldFill.INSERT)
    private Date created;
}

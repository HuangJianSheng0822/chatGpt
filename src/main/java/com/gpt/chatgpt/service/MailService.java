package com.gpt.chatgpt.service;

public interface MailService {
    /**
     *
     * @param email
     * @param subject 主题
     * @param content 内容
     * @return
     */
    boolean sendSimpleCode(String email,String subject,String content);
}

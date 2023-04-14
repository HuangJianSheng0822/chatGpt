package com.gpt.chatgpt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@Accessors(chain = true)
public class BaseReqParam {
    @Value("${baseReqParam.url}")
    private String url;
    @Value("${baseReqParam.authorization}")
    private String authorization;
    @Value("${baseReqParam.model}")
    private String model;
    @Value("${baseReqParam.max_tokens}")
    private int max_tokens;
    @Value("${baseReqParam.temperature}")
    private double temperature;
    @Value("${baseReqParam.top_p}")
    private int top_p;
    @Value("${baseReqParam.n}")
    private int n;
    @Value("${baseReqParam.stream}")
    private boolean bstream;
}

package com.gpt.chatgpt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}

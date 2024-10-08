package com.example.springboot.common;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class    AIChatService {

    public String getAIResponse(String question) throws ApiException, NoApiKeyException, InputRequiredException {
        Generation gen = new Generation();

        // Define system and user messages
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.")
                .build();

        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(question)
                .build();

        // Build the parameter for the API call
        GenerationParam param = GenerationParam.builder()
                .apiKey("sk-4274ae7906434642940ca9dd01e6befd")
                .model("qwen-turbo")  // Specify the model you're using
                .messages(Arrays.asList(systemMsg, userMsg))
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .build();

        // Call the API and return the result
        GenerationResult result = gen.call(param);
        return result.getOutput().getChoices().get(0).getMessage().getContent();
    }
}


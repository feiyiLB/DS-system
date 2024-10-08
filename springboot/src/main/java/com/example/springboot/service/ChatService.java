package com.example.springboot.service;

import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.AIChatService;
import com.example.springboot.entity.Chat;
import com.example.springboot.entity.Logs;
import com.example.springboot.mapper.ChatMapper;
import com.example.springboot.mapper.LogsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChatService extends ServiceImpl<ChatMapper, Chat> {
    @Resource
    ChatMapper chatMapper;

    @Autowired
    private AIChatService aiChatService;  // AI API service for getting responses

    /**
     * Save chat question and get AI-generated answer
     */
    public void saveChatWithAnswer(Chat chat) {
        // Save the question in DB first
        this.save(chat);  // MyBatis Plus built-in save method

        try {
            // Call AI API to get the answer
            String answer = aiChatService.getAIResponse(chat.getQuestion());

            // Set the answer to the chat
            chat.setAnswer(answer);

            // Update the chat with the answer in DB
            this.updateById(chat);  // MyBatis Plus built-in update method
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            String errorMessage = "An error occurred while calling the generation service: " + e.getMessage();
            chat.setAnswer(errorMessage);  // Store the error message in the answer field
            e.printStackTrace();  // Optional: Print stack trace for debugging
        }
}


    public List<Chat> getChatsByUserId(Integer userId) {
        QueryWrapper<Chat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).orderByAsc("time");  // Query for chats by user_id and order by time (most recent first)
        return this.list(queryWrapper);  // MyBatis Plus built-in method to return the list
    }
}

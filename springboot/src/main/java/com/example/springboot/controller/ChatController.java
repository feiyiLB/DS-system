package com.example.springboot.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.HoneyLogs;
import com.example.springboot.common.LogType;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Chat;
import com.example.springboot.entity.User;
import com.example.springboot.service.ChatService;
import com.example.springboot.service.UserService;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    UserService userService;

    /**
     * 新增信息
     */
    @PostMapping("/add")
    public Result add(@RequestBody Chat chat) {
        User currentUser = TokenUtils.getCurrentUser();  // 获取到当前登录的用户信息
        chat.setUserId(currentUser.getId());
        chat.setTime(DateUtil.now());  //   2023-09-12 21:09:12

//        to be added
        chatService.saveChatWithAnswer(chat);
        return Result.success(chat);
    }

    /**
     * 获取信息
     */
    @GetMapping("/getChat")
    public Result getChat() {
        User currentUser = TokenUtils.getCurrentUser();
        Integer userId = currentUser.getId();
        // Fetch the chat history for the user
        List<Chat> chatList = chatService.getChatsByUserId(userId);

        // Return the chat list as the response
        return Result.success(chatList);
    }

    /**
     * 删除信息
     */
    @HoneyLogs(operation = "新闻", type = LogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        chatService.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除信息
     */
    @HoneyLogs(operation = "新闻", type = LogType.BATCH_DELETE)
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        chatService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Chat> userList = chatService.list(new QueryWrapper<Chat>().orderByDesc("id"));
        return Result.success(userList);
    }

    /**
     * 根据ID查询信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Chat chat = chatService.getById(id);
        User user = userService.getById(chat.getUserId());
        if (user != null) {
            chat.setId(user.getId());
        }
        return Result.success(chat);
    }




}

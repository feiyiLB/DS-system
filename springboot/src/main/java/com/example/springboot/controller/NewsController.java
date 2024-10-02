package com.example.springboot.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.HoneyLogs;
import com.example.springboot.common.LogType;
import com.example.springboot.common.Result;
import com.example.springboot.entity.News;
import com.example.springboot.entity.User;
import com.example.springboot.service.NewsService;
import com.example.springboot.service.UserService;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    /**
     * 新增信息
     */
    @HoneyLogs(operation = "新闻", type = LogType.ADD)
    @PostMapping("/add")
    public Result add(@RequestBody News news) {
        User currentUser = TokenUtils.getCurrentUser();  // 获取到当前登录的用户信息
        news.setAuthorid(currentUser.getId());
        news.setTime(DateUtil.now());  //   2023-09-12 21:09:12
        newsService.save(news);
        return Result.success();
    }

    /**
     * 修改信息
     */
    @HoneyLogs(operation = "新闻", type = LogType.UPDATE)
    @PutMapping("/update")
    public Result update(@RequestBody News news) {
        newsService.updateById(news);
        return Result.success();
    }

    /**
     * 删除信息
     */
    @HoneyLogs(operation = "新闻", type = LogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        newsService.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除信息
     */
    @HoneyLogs(operation = "新闻", type = LogType.BATCH_DELETE)
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        newsService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<News> userList = newsService.list(new QueryWrapper<News>().orderByDesc("id"));
        return Result.success(userList);
    }

    /**
     * 根据ID查询信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        News news = newsService.getById(id);
        User user = userService.getById(news.getAuthorid());
        if (user != null) {
            news.setAuthor(user.getName());
        }
        return Result.success(news);
    }


    /**
     * 多条件模糊查询信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String title) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().orderByDesc("id");  // 默认倒序，让最新的数据在最上面
        queryWrapper.like(StrUtil.isNotBlank(title), "title", title);
        Page<News> page = newsService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<News> records = page.getRecords();
//        List<User> list = userService.list();
        for (News record : records) {
            Integer authorid = record.getAuthorid();
            User user = userService.getById(authorid);
//            String author = list.stream().filter(u -> u.getId().equals(authorid)).findFirst().map(User::getName).orElse("");
            if (user != null) {
                record.setAuthor(user.getName());
            }
        }
        return Result.success(page);
    }


}

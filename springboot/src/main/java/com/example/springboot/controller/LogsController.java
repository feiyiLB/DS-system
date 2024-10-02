package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Logs;
import com.example.springboot.service.LogsService;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统日志相关接口
 */
@RestController
@RequestMapping("/logs")
public class LogsController {

    @Autowired
    LogsService logsService;

    @Autowired
    UserService userService;


    /**
     * 删除信息
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        logsService.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除信息
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        logsService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 多条件模糊查询信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String operation,
                               @RequestParam String type,
                               @RequestParam String user) {
        QueryWrapper<Logs> queryWrapper = new QueryWrapper<Logs>().orderByDesc("id");  // 默认倒序，让最新的数据在最上面
        queryWrapper.like(StrUtil.isNotBlank(operation), "operation", operation);
        queryWrapper.like(StrUtil.isNotBlank(type), "type", type);
        queryWrapper.like(StrUtil.isNotBlank(user), "user", user);
        Page<Logs> page = logsService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }

}


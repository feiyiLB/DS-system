package com.example.springboot.service.aop;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import com.example.springboot.common.HoneyLogs;
import com.example.springboot.entity.Logs;
import com.example.springboot.entity.User;
import com.example.springboot.service.LogsService;
import com.example.springboot.utils.IpUtils;
import com.example.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class LogsAspect {

    @Resource
    LogsService logsService;

    @AfterReturning(pointcut = "@annotation(honeyLogs)", returning = "jsonResult")
    public void recordLog(JoinPoint joinPoint, HoneyLogs honeyLogs, Object jsonResult) {
        // 获取当前登录的用户的信息
        User loginUser = TokenUtils.getCurrentUser();
        if (loginUser == null) { // 用户未登录的情况下  loginUser是null  是null的话我们就要从参数里面获取操作人信息
            // 登录、注册
            Object[] args = joinPoint.getArgs();
            if (ArrayUtil.isNotEmpty(args)) {
                if (args[0] instanceof User) {
                    loginUser = (User) args[0];
                }
            }
        }
        if (loginUser == null) {
            log.error("记录日志信息报错，未获取到当前操作用户信息");
            return;
        }
        // 获取HttpServletRequest对象
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 获取IP信息
        String ipAddr = IpUtils.getIpAddr(request);
        //组装日志的实体对象
        Logs logs = Logs.builder()
                .user(loginUser.getUsername())
                .operation(honeyLogs.operation())
                .type(honeyLogs.type().getValue())
                .ip(ipAddr)
                .time(DateUtil.now())
                .build();

        // 插入数据到数据库
        ThreadUtil.execAsync(() -> {
            logsService.save(logs);
        });
    }
}

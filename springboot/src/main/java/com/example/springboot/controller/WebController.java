package com.example.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.AuthAccess;
import com.example.springboot.common.HoneyLogs;
import com.example.springboot.common.LogType;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Orders;
import com.example.springboot.entity.User;
import com.example.springboot.service.OrdersService;
import com.example.springboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 功能：提供接口返回数据
 * 作者：程序员青戈
 * 日期：2023/8/13 15:55
 */
@RestController
public class WebController {

    @Resource
    UserService userService;

    @Resource
    OrdersService ordersService;

    @AuthAccess
    @GetMapping("/")
    public Result hello() {
        return Result.success("success");
    }

    @HoneyLogs(operation = "用户", type = LogType.LOGIN)
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            return Result.error("数据输入不合法");
        }
        user = userService.login(user);
        return Result.success(user);
    }

    @HoneyLogs(operation = "用户", type = LogType.REGISTER)
    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword()) || StrUtil.isBlank(user.getRole())) {
            return Result.error("数据输入不合法");
        }
        if (user.getUsername().length() > 10 || user.getPassword().length() > 20) {
            return Result.error("数据输入不合法");
        }
        user = userService.register(user);
        return Result.success(user);
    }

    /**
     * 重置密码
     */
    @HoneyLogs(operation = "用户", type = LogType.UPDATE)
    @AuthAccess
    @PutMapping("/password")
    public Result password(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPhone())) {
            return Result.error("数据输入不合法");
        }
        userService.resetPassword(user);
        return Result.success();
    }

    /**
     * 获取统计图数据
     * @return 动态数据
     */
    @GetMapping("/charts")
    public Result charts() {
        // 包装折线图的数据
        List<Orders> list = ordersService.list();
        Set<String> dates = list.stream().map(Orders::getDate).collect(Collectors.toSet());
        List<String> dateList = CollUtil.newArrayList(dates);
        dateList.sort(Comparator.naturalOrder());
        List<Dict> lineList = new ArrayList<>();
        for (String date : dateList) {
            // 统计当前日期的所有金额总数和
            BigDecimal sum = list.stream().filter(orders -> orders.getDate().equals(date)).map(Orders::getMoney)
                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            Dict dict = Dict.create();
            Dict line = dict.set("date", date).set("value", sum);
            lineList.add(line);
        }

        // 柱状图数据
        List<Dict> barList = new ArrayList<>();
        Set<String> categories = list.stream().map(Orders::getCategory).collect(Collectors.toSet());
        for (String cate : categories) {
            // 统计当前日期的所有金额总数和
            BigDecimal sum = list.stream().filter(orders -> orders.getCategory().equals(cate)).map(Orders::getMoney)
                    .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            Dict dict = Dict.create();
            Dict bar = dict.set("name", cate).set("value", sum);
            barList.add(bar);
        }


        // 包装所有数据
        Dict res = Dict.create().set("line", lineList).set("bar", barList);
        return Result.success(res);
    }

}

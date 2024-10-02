package com.example.springboot.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.HoneyLogs;
import com.example.springboot.common.LogType;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Orders;
import com.example.springboot.entity.User;
import com.example.springboot.service.OrdersService;
import com.example.springboot.service.UserService;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    UserService userService;

    /**
     * 新增信息
     */
    @HoneyLogs(operation = "订单", type = LogType.ADD)
    @PostMapping("/add")
    public Result add(@RequestBody Orders orders) {
        User currentUser = TokenUtils.getCurrentUser();  // 获取到当前登录的用户信息
        orders.setUserid(currentUser.getId());
        orders.setDate(DateUtil.today());  //   2023-10-08
        orders.setNo(IdUtil.fastSimpleUUID());
        ordersService.save(orders);
        return Result.success();
    }

    /**
     * 修改信息
     */
    @HoneyLogs(operation = "订单", type = LogType.UPDATE)
    @PutMapping("/update")
    public Result update(@RequestBody Orders orders) {
        ordersService.updateById(orders);
        return Result.success();
    }

    /**
     * 删除信息
     */
    @HoneyLogs(operation = "订单", type = LogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        ordersService.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除信息
     */
    @HoneyLogs(operation = "订单", type = LogType.BATCH_DELETE)
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        ordersService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Orders> ordersList = ordersService.list(new QueryWrapper<Orders>().orderByDesc("id"));
        return Result.success(ordersList);
    }

    /**
     * 根据ID查询信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Orders orders = ordersService.getById(id);
        User user = userService.getById(orders.getUserid());
        if (user != null) {
            orders.setUser(user.getName());
        }
        return Result.success(orders);
    }


    /**
     * 多条件模糊查询信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<Orders>().orderByDesc("id");  // 默认倒序，让最新的数据在最上面
        queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        Page<Orders> page = ordersService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<Orders> records = page.getRecords();
        for (Orders record : records) {
            Integer authorid = record.getUserid();
            User user = userService.getById(authorid);
            if (user != null) {
                record.setUser(user.getName());
            }
        }
        return Result.success(page);
    }


}

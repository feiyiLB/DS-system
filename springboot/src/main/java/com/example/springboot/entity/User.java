package com.example.springboot.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {

    @TableId(type=IdType.AUTO)
    @Alias("序号")
    private Integer id;
    @Alias("用户名")
    private String username;
    @Alias("密码")
    private String password;
    @Alias("姓名")
    private String name;
    @Alias("电话")
    private String phone;
    @Alias("邮箱")
    private String email;
    @Alias("地址")
    private String address;
    @Alias("头像")
    private String avatar;
    @Alias("角色")
    private String role;

    @TableField(exist = false)
    private String token;

}

package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String no;
    private String name;
    private BigDecimal money;
    private Integer userid;
    private String category;
    private String date;

    @TableField(exist = false)
    private String user;
}

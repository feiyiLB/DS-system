package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.News;
import com.example.springboot.entity.Notice;
import com.example.springboot.mapper.NewsMapper;
import com.example.springboot.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {

    @Resource
    NoticeMapper noticeMapper;

}

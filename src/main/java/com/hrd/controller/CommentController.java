package com.hrd.controller;

import com.hrd.com.hrd.Entity.comment;
import com.hrd.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/getComment")
    public Page<comment> getByVideoId(@RequestParam("type") String type, @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize){

          return commentRepository.findComment(type,PageRequest.of(pageIndex,pageSize));
    }
    @PostMapping("/addComment")
    public comment CommentAdd(@RequestParam("type") String type,@RequestParam("content") String content,@RequestParam("icon") String icon,@RequestParam("username") String username)
    {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//获取系统时间
        comment cm = new comment();
        cm.setComment_date(dateFormat.format(date));//系统时间
        cm.setContent(content);//系统内容
        cm.setType(type); //会议编号
        cm.setUsername(username);//用户名称
        cm.setIcon(icon);//图标
        return commentRepository.save(cm);


    }
    @PostMapping("/deleComment")
    public void deledata(@RequestParam("type") String type)
    {
       commentRepository.deleteByType(type);
    }







}

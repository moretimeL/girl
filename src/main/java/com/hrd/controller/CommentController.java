package com.hrd.controller;

import com.hrd.comment;
import com.hrd.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/getComment")
    public Page<comment> getByVideoId(int type, int pageIndex, int pageSize){

          return commentRepository.findComment(type,PageRequest.of(pageIndex,pageSize));
    }



}

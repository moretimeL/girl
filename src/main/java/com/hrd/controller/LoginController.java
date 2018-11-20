package com.hrd.controller;

import com.hrd.com.hrd.Entity.meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import com.hrd.repository.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Controller

public class LoginController {
    @Autowired
    private meetingRepository meetingRepository;

    @ResponseBody
    @GetMapping("/getMeetingMessage")
    public List<meeting> getMeetingList() {

        return meetingRepository.findAll();
    }



   // @GetMapping("/delMeetingMessage")
   // public void dele() {
   //     System.out.println("test");
   //     meetingRepository.deleteById(1);
   // }


        //@GetMapping("/LoadMeetingMessage")
        // public String load(@RequestParam("operationID") String operationID,)
        //public List<meeting> getByVideoId(@RequestParam("type") String type, @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize){

        //   return commentRepository.findComment(type,PageRequest.of(pageIndex,pageSize));
        // }
        //public List<comment> girlList(){

        // return girlRepository.findAll();
        //    return (List<comment>) girlRepository.findAll();
        // }
    }


package com.hrd.controller;

import com.hrd.com.hrd.Entity.meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MeetingController {
    @Autowired
    private com.hrd.repository.meetingRepository meetingRepository;

    @GetMapping("/GetStreamURI")
    public Optional<meeting> getMeeting(@RequestParam("id") String id){

        return meetingRepository.findById(Integer.parseInt(id));
    }


}

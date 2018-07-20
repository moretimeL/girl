package com.hrd.com.hrd.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity    //说明该类是映射数据对象
public class meeting {
    @Id
    @GeneratedValue
    private Integer id;    //id是主键且自增长
    private Integer meeting_id;//会议室ID

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    private String playName;//播放名字


    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    private String login_password;//后台登录密码


    public String getMeeting_stream() {
        return meeting_stream;
    }

    public void setMeeting_stream(String meeting_stream) {
        this.meeting_stream = meeting_stream;
    }

    private String meeting_stream;//视频流路径

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public Integer getMeeting_count() {
        return meeting_count;
    }

    public void setMeeting_count(Integer meeting_count) {
        this.meeting_count = meeting_count;
    }

    public String getConference_picture_url() {
        return conference_picture_url;
    }

    public void setConference_picture_url(String conference_picture_url) {
        this.conference_picture_url = conference_picture_url;
    }

    private Integer meeting_count; //会场人数
    private String conference_picture_url;//会议介绍图片


}

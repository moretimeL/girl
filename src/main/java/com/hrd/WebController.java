package com.hrd;


import com.hrd.com.hrd.Entity.meeting;
import com.hrd.pojo.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;

import com.hrd.repository.*;

@Controller

public class WebController {

    @Value("girl.uploadPath")
    private String uploadPath;

    @Autowired
    private meetingRepository meetingRepository;
    HttpClient httpClient = new HttpClient();
    //返回网页
    @RequestMapping(value= "kk" , method= RequestMethod.GET)
    public String index(@RequestParam(value="id",required=false,defaultValue="0") Integer id)
    {
       return "index";
    }
    @RequestMapping(value= "/lg" , method= RequestMethod.GET)
    public String kw1122(){

        return "lgo";}

    //登录主页面
    @RequestMapping(value= "/main" , method= RequestMethod.GET)

     public String kw12(){



        return "loginMain";}
    @RequestMapping(value= "/password" , method= RequestMethod.GET)
    public String password(@RequestParam("password") String password){
        List<meeting> list=meetingRepository.findAll();
        meeting temp = list.get(0);
        meeting meet = new meeting();
        meet.setId(1);
        meet.setConference_picture_url(temp.getConference_picture_url());
        meet.setLogin_password(password);
        meet.setPlayName(temp.getPlayName());
        meet.setMeeting_stream(temp.getMeeting_stream());
        meetingRepository.save(meet);


        return "loginMain";

    }
    //public String page2(Model model){
      //  model.addAttribute("hello", "world");
      //  return "play4";
    //}
    @RequestMapping(value="/redirect", method= RequestMethod.GET)
    public String page2(Model model){
         model.addAttribute("hello", "world");
          return "test";
        }
        //会议室入口1
    @RequestMapping(value="/login1", method= RequestMethod.GET)
    public String login(){

        return "auth";
    }
       //会议室入口2
       @RequestMapping(value="/login2", method= RequestMethod.GET)
    public String login1()
    {
           return "auth1";

    }
    @RequestMapping(value="/login3", method= RequestMethod.GET)
    public String login2()
    {
        return "auth2";

    }
    @RequestMapping(value="/login4", method= RequestMethod.GET)
    public String login3()
    {
        return "auth3";

    }

    @RequestMapping(value = "/getCode",method=RequestMethod.GET)
    public String getCode(@RequestParam("code") String code,Model model)
    {
        //获取用户名验证
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", "wx5d6517be203ab637");
        requestUrl = requestUrl.replace("SECRET","e943caf002df1515d7fe93dd335aa224");
        requestUrl = requestUrl.replace("CODE", code);
        HttpMethod method = HttpMethod.GET;
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
//        params.add("access_token","ttttt");
        String requestbody = httpClient.client(requestUrl,method,params);
        // System.out.println("1="+requestbody);
        //{"access_token":"8_SffqhYK3Chcpe-o-Z4V9laeYFwqqvEo61hndG-THqrM_fudXCj2SAtJqtQF5mGdFR8LGJ7raFW-hspgkE5ULxA","expires_in":7200,"refresh_token":"8_4PxBAxH0HmfCKkOX8erOn4hsewUhr9MejVwDW5pLbnrkLkBsTi1Gzam4SOaRPJ-BzPG6s23TJvYhVMXkD-gakQ","openid":"ozDFe0ZzcxYruyPAxoN-Ai2vOOy8","scope":"snsapi_userinfo"}
        String access_token=requestbody.split(",")[0].split(":")[1];
        //System.out.println("2="+access_token);
        access_token=access_token.substring(1,access_token.length() - 1);
        //System.out.println("2="+access_token);
        requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", "wx591d0d4203841689");
        //System.out.println("3="+requestUrl);
        requestbody= httpClient.client(requestUrl,method,params);
       // System.out.println("4="+requestbody);
        String result = null;
        try {
            result = new String(requestbody.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
       System.out.println("result="+result);
        //return result;
        String username=result.split(",")[1].split(":")[1];
       // String headimgurl=result.split(",")[7].split(":")[1];
        int start=result.indexOf("headimgurl");
        int end=result.indexOf("privilege");
        username=username.substring(1,username.length()-1);
        String headimgurl = result.substring(start+13,end-3);
        System.out.println("username="+username);
        System.out.println("headimgurl="+headimgurl);
        headimgurl= headimgurl.replace("\\","");
        model.addAttribute("username", username);
        model.addAttribute("headimgurl",headimgurl);
        return "play1";

    }
    @RequestMapping(value = "/getCode1",method=RequestMethod.GET)
    public String getCode1(@RequestParam("code") String code,Model model)
    {
        //获取用户名验证
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", "wx5d6517be203ab637");
        requestUrl = requestUrl.replace("SECRET","e943caf002df1515d7fe93dd335aa224");
        requestUrl = requestUrl.replace("CODE", code);
        HttpMethod method = HttpMethod.GET;
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
//        params.add("access_token","ttttt");
        String requestbody = httpClient.client(requestUrl,method,params);
        // System.out.println("1="+requestbody);
        //{"access_token":"8_SffqhYK3Chcpe-o-Z4V9laeYFwqqvEo61hndG-THqrM_fudXCj2SAtJqtQF5mGdFR8LGJ7raFW-hspgkE5ULxA","expires_in":7200,"refresh_token":"8_4PxBAxH0HmfCKkOX8erOn4hsewUhr9MejVwDW5pLbnrkLkBsTi1Gzam4SOaRPJ-BzPG6s23TJvYhVMXkD-gakQ","openid":"ozDFe0ZzcxYruyPAxoN-Ai2vOOy8","scope":"snsapi_userinfo"}
        String access_token=requestbody.split(",")[0].split(":")[1];
        //System.out.println("2="+access_token);
        access_token=access_token.substring(1,access_token.length() - 1);
        //System.out.println("2="+access_token);
        requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", "wx591d0d4203841689");
        //System.out.println("3="+requestUrl);
        requestbody= httpClient.client(requestUrl,method,params);
        // System.out.println("4="+requestbody);
        String result = null;
        try {
            result = new String(requestbody.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("result="+result);
        //return result;
        String username=result.split(",")[1].split(":")[1];
        // String headimgurl=result.split(",")[7].split(":")[1];
        int start=result.indexOf("headimgurl");
        int end=result.indexOf("privilege");
        username=username.substring(1,username.length()-1);
        String headimgurl = result.substring(start+13,end-3);
        System.out.println("username="+username);
        System.out.println("headimgurl="+headimgurl);
        headimgurl= headimgurl.replace("\\","");
        model.addAttribute("username", username);
        model.addAttribute("headimgurl",headimgurl);
        return "play2";

    }
    @RequestMapping(value = "/getCode2",method=RequestMethod.GET)
    public String getCode2(@RequestParam("code") String code,Model model)
    {
        //获取用户名验证
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", "wx5d6517be203ab637");
        requestUrl = requestUrl.replace("SECRET","e943caf002df1515d7fe93dd335aa224");
        requestUrl = requestUrl.replace("CODE", code);
        HttpMethod method = HttpMethod.GET;
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
//        params.add("access_token","ttttt");
        String requestbody = httpClient.client(requestUrl,method,params);
        // System.out.println("1="+requestbody);
        //{"access_token":"8_SffqhYK3Chcpe-o-Z4V9laeYFwqqvEo61hndG-THqrM_fudXCj2SAtJqtQF5mGdFR8LGJ7raFW-hspgkE5ULxA","expires_in":7200,"refresh_token":"8_4PxBAxH0HmfCKkOX8erOn4hsewUhr9MejVwDW5pLbnrkLkBsTi1Gzam4SOaRPJ-BzPG6s23TJvYhVMXkD-gakQ","openid":"ozDFe0ZzcxYruyPAxoN-Ai2vOOy8","scope":"snsapi_userinfo"}
        String access_token=requestbody.split(",")[0].split(":")[1];
        //System.out.println("2="+access_token);
        access_token=access_token.substring(1,access_token.length() - 1);
        //System.out.println("2="+access_token);
        requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", "wx591d0d4203841689");
        //System.out.println("3="+requestUrl);
        requestbody= httpClient.client(requestUrl,method,params);
        // System.out.println("4="+requestbody);
        String result = null;
        try {
            result = new String(requestbody.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("result="+result);
        //return result;
        String username=result.split(",")[1].split(":")[1];
        // String headimgurl=result.split(",")[7].split(":")[1];
        int start=result.indexOf("headimgurl");
        int end=result.indexOf("privilege");
        username=username.substring(1,username.length()-1);
        String headimgurl = result.substring(start+13,end-3);
        System.out.println("username="+username);
        System.out.println("headimgurl="+headimgurl);
        headimgurl= headimgurl.replace("\\","");
        model.addAttribute("username", username);
        model.addAttribute("headimgurl",headimgurl);
        return "play3";

    }
    @RequestMapping(value = "/getCode3",method=RequestMethod.GET)
    public String getCode3(@RequestParam("code") String code,Model model)
    {
        //获取用户名验证
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", "wx5d6517be203ab637");
        requestUrl = requestUrl.replace("SECRET","e943caf002df1515d7fe93dd335aa224");
        requestUrl = requestUrl.replace("CODE", code);
        HttpMethod method = HttpMethod.GET;
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
//        params.add("access_token","ttttt");
        String requestbody = httpClient.client(requestUrl,method,params);
        // System.out.println("1="+requestbody);
        //{"access_token":"8_SffqhYK3Chcpe-o-Z4V9laeYFwqqvEo61hndG-THqrM_fudXCj2SAtJqtQF5mGdFR8LGJ7raFW-hspgkE5ULxA","expires_in":7200,"refresh_token":"8_4PxBAxH0HmfCKkOX8erOn4hsewUhr9MejVwDW5pLbnrkLkBsTi1Gzam4SOaRPJ-BzPG6s23TJvYhVMXkD-gakQ","openid":"ozDFe0ZzcxYruyPAxoN-Ai2vOOy8","scope":"snsapi_userinfo"}
        String access_token=requestbody.split(",")[0].split(":")[1];
        //System.out.println("2="+access_token);
        access_token=access_token.substring(1,access_token.length() - 1);
        //System.out.println("2="+access_token);
        requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", "wx591d0d4203841689");
        //System.out.println("3="+requestUrl);
        requestbody= httpClient.client(requestUrl,method,params);
        // System.out.println("4="+requestbody);
        String result = null;
        try {
            result = new String(requestbody.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("result="+result);
        //return result;
        String username=result.split(",")[1].split(":")[1];
        // String headimgurl=result.split(",")[7].split(":")[1];
        int start=result.indexOf("headimgurl");
        int end=result.indexOf("privilege");
        username=username.substring(1,username.length()-1);
        String headimgurl = result.substring(start+13,end-3);
        System.out.println("username="+username);
        System.out.println("headimgurl="+headimgurl);
        headimgurl= headimgurl.replace("\\","");
        model.addAttribute("username", username);
        model.addAttribute("headimgurl",headimgurl);
        return "play4";

    }
    @PostMapping("/upload")
    public String singleFileUpload(  @RequestParam(value="operationID") String operationID,
                                     @RequestParam(value="operationName") String operationName,
                                     @RequestParam(value="mobelwebname") String mobelwebname,
                                     @RequestParam(value = "inputfile") MultipartFile inputfile,
                                     @RequestParam(value="videopath") String  videopath) {
        File path = null;
       // List<meeting> list=meetingRepository.findAll();
        meeting temp= null;
        if(!meetingRepository.existsById(Integer.parseInt(operationID))){
            temp = new meeting();
        }else{
            temp = meetingRepository.getOne(Integer.parseInt(operationID));
        }
        String password = temp.getLogin_password();
        meeting meet = new meeting();
        meet.setId(Integer.parseInt(operationID));
        meet.setMeeting_stream(videopath);
        meet.setPlayName(mobelwebname);
        meet.setLogin_password(password);
        try {
            System.out.println("id="+operationID);
            String filename= operationID+".png";  //文件名字
            meet.setConference_picture_url(filename);

            if (!inputfile.isEmpty()) {
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(uploadPath+"/" + filename)));//保存图片到目录下
                out.write(inputfile.getBytes());
                out.flush();
                out.close();

            }
        }catch (Exception e) {
            System.out.println("上传文件错误!");
            e.printStackTrace();
        }

        meetingRepository.save(meet);
        return "loginMain";
    }



}

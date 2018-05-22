package com.hrd.girl;

import com.hrd.pojo.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Controller

public class WebController {
    HttpClient httpClient = new HttpClient();
    //返回网页
    @RequestMapping(value= "kk" , method= RequestMethod.GET)
    public String index(@RequestParam(value="id",required=false,defaultValue="0") Integer id)
    {
       return "index";
    }

    @RequestMapping(value= "/play2" , method= RequestMethod.GET)
    public String kw1(){return "play2";}
    @RequestMapping(value= "/play3" , method= RequestMethod.GET)
    public String kw3(){return "play3";}
    @RequestMapping(value="/redirect", method= RequestMethod.GET)
    //public String page2(Model model){
      //  model.addAttribute("hello", "world");
      //  return "play4";
    //}
    public String page2(Model model){
         model.addAttribute("hello", "world");
          return "test";
        }
        //系统入口
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login(){

        return "auth";
    }

    @RequestMapping(value = "/getCode",method=RequestMethod.GET)
    public String getCode(@RequestParam("code") String code,Model model)
    {
        //获取用户名验证
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", "wx591d0d4203841689");
        requestUrl = requestUrl.replace("SECRET","40842ad4821bf22990e864fc462363dd");
        requestUrl = requestUrl.replace("CODE", code);
        HttpMethod method = HttpMethod.GET;
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
//        params.add("access_token","ttttt");
        String requestbody = httpClient.client(requestUrl,method,params);
        // System.out.println(requestbody);
        //{"access_token":"8_SffqhYK3Chcpe-o-Z4V9laeYFwqqvEo61hndG-THqrM_fudXCj2SAtJqtQF5mGdFR8LGJ7raFW-hspgkE5ULxA","expires_in":7200,"refresh_token":"8_4PxBAxH0HmfCKkOX8erOn4hsewUhr9MejVwDW5pLbnrkLkBsTi1Gzam4SOaRPJ-BzPG6s23TJvYhVMXkD-gakQ","openid":"ozDFe0ZzcxYruyPAxoN-Ai2vOOy8","scope":"snsapi_userinfo"}
        String access_token=requestbody.split(",")[0].split(":")[1];
        //System.out.println("1="+access_token);
        access_token=access_token.substring(1,access_token.length() - 1);
        //System.out.println("2="+access_token);
        requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", "wx591d0d4203841689");
        //System.out.println(requestUrl);
        requestbody= httpClient.client(requestUrl,method,params);
        System.out.println(requestbody);
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
}

package com.hrd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController

public class HelloController {
    @Autowired
    private Girlproperty gp;


    @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;

    @RequestMapping(value={"/hi","hi2"},method= RequestMethod.GET)
    public String say(){

        return gp.getAge()+"";
    }

    /*
       以下方法工程用于微信的初级验证
       参数设置在CheckUtil.checkSignature()方法中
     */
    @RequestMapping(value = "/wx",method=RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response){
        //微信验证返回
        System.out.println("success");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if(CheckUtil.checkSignature(signature, timestamp, nonce)){
                out.write(echostr);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            out.close();
        }

    }

}

package com.hrd;

import com.hrd.pojo.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GirlController
{
    @Autowired
    private GirlRespository girlRepository;
   // @Autowired
   // private CommentRespository commentRespository;
    HttpClient httpClient = new HttpClient();
    @GetMapping("/load")
    public String load(){

        String url = "http://localhost://girls";
        HttpMethod method = HttpMethod.GET;
        MultiValueMap<String,String> params = new LinkedMultiValueMap<String,String>();
//        params.add("access_token","ttttt");
        return httpClient.client(url,method,params);

    }

    /**
     * 查寻所有评论信息
     * @return
     */
    @GetMapping(value="/comments")
    public List<comment> girlList(){

          return girlRepository.findAll();
         //return (List<comment>) commentRespository.findAll();
    }
    @PostMapping( value = "/girls" )
    public comment girlAdd(@RequestParam("content") String content,
                          @RequestParam("type") Integer type,
                               @RequestParam("username")  String username)
    {
        comment cmm = new comment();
        cmm.setContent(content);
        cmm.setType(type);
       // cmm.setDt(dt);
        cmm.setUsername(username);
        return girlRepository.save(cmm);

    }
    @RequestMapping(value="/MP_verify_nNVmgkWK7BkIutr7.txt",produces= MediaType.TEXT_PLAIN_VALUE)
    public Resource downloadTxt2(){
        return new FileSystemResource("C:\\Users\\Administrator\\girl\\src\\main\\resources\\templates\\MP_verify_nNVmgkWK7BkIutr7.txt");
    }


}

package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.Oneway;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    @RequestMapping("hello")
    public String hello(){
        return "你稻不好";
    }
    @RequestMapping("hello2")
    public Map<String,Object> helloMap(){
        Map<String, Object> map =new HashMap<>();
        map.put("名字","张三");
        return map;
    }
}

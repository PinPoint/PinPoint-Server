package de.pinpoint.server;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    private int i;

    @RequestMapping("/")
    public String index(){
        return "Hello world!";
    }

    @RequestMapping("/randomToken")
    public String randomToken(){
        return UUID.randomUUID().toString();
    }

    @RequestMapping("/count")
    public String count(){
        return String.valueOf(++i);
    }
}

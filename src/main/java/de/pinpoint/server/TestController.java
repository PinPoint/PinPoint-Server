package de.pinpoint.server;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @RequestMapping("/")
    public String index(){
        return "Hello world!";
    }

    @RequestMapping("/randomToken")
    public String randomToken(){
        return UUID.randomUUID().toString();
    }
}

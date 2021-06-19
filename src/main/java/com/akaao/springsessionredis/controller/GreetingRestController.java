package com.akaao.springsessionredis.controller;

import com.akaao.springsessionredis.info.GreetingInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/greeting")
public class GreetingRestController {
    private final String instanceName;
    private final GreetingInfo greetingInfo;

    public GreetingRestController(@Value("${instance.name:ap}") String instanceName, GreetingInfo greetingInfo) {
        this.instanceName = instanceName;
        this.greetingInfo = greetingInfo;
    }


    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        if(greetingInfo.getName() == null) {
            greetingInfo.setName(name.orElse("anonymous"));
            greetingInfo.setCreateAt(new Date());
            greetingInfo.setCreateBy(instanceName);
        }

        return "Hi " + greetingInfo.getName() + "/ Enter at " + greetingInfo.getCreateAt() + " by " + greetingInfo.getCreateBy() + " (Echo by " + instanceName + ")";
    }

    @GetMapping("/goodbye")
    public String goodbye(HttpSession session) {
        Optional<String> name = Optional.ofNullable(greetingInfo.getName());
        session.invalidate();
        return "Goodbye " + name.orElse("anonymous") + " (Echo by " + instanceName + ")";
    }
}

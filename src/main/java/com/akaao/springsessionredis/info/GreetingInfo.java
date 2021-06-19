package com.akaao.springsessionredis.info;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Date;

@SessionScope
@Component
@Data
public class GreetingInfo implements Serializable {
    private static final long serialVersionUID = 8048097948251750715L;
    private String name;
    private Date createAt;
    private String createBy;
}

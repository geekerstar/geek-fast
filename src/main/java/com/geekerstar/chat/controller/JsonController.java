package com.geekerstar.chat.controller;

import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author geekerstar
 * @date 2020/2/9 21:31
 * @description
 */
@Api(tags = "即时通讯数据源(临时测试用)")
@RestController()
@CrossOrigin(allowedHeaders = "*", allowCredentials = "true")
public class JsonController {
    @GetMapping("/json/getList")
    public String getList() throws IOException {
        ClassPathResource jsonFile = new ClassPathResource("json/getList.json");
        return IOUtils.toString(new InputStreamReader(jsonFile.getInputStream(), "UTF-8"));
    }


    @GetMapping("/json/getMembers")
    public String getMembers() throws IOException {
        ClassPathResource jsonFile = new ClassPathResource("json/getMembers.json");
        return IOUtils.toString(new InputStreamReader(jsonFile.getInputStream(), "UTF-8"));
    }


}


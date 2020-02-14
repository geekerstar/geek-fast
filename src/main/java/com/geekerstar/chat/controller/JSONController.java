package com.geekerstar.chat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.util.GeekUtil;
import io.swagger.annotations.Api;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author geekerstar
 * @date 2020/2/9 21:31
 * @description
 */
@Api(tags = "即时通讯Json")
@RestController()
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true")
public class JSONController {
    @GetMapping("/json/getList")
    public String getList() throws IOException {
        ClassPathResource jsonFile = new ClassPathResource("json/getList.json");
        String str = IOUtils.toString(new InputStreamReader(jsonFile.getInputStream(),"UTF-8"));
        return str;
    }


    @GetMapping("/json/getMembers")
    public String getMembers() throws IOException {
        ClassPathResource jsonFile = new ClassPathResource("json/getMembers.json");
        String str = IOUtils.toString(new InputStreamReader(jsonFile.getInputStream(),"UTF-8"));
        return str;
    }


}


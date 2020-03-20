package com.geekerstar.tool;

import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/3/20 18:02
 * @description
 */
@RestController
public class UploadController {
    private static final String UPLOAD_PATH = "geek";

    @RequestMapping("/upload")
    public String  upload(MultipartFile file) {
        String result = "";
        try {
            InputStreamResource isr = new InputStreamResource(file.getInputStream(),
                    file.getOriginalFilename());

            Map<String, Object> params = new HashMap<>();
            params.put("file", isr);
            params.put("path", "86501729");
            params.put("output", "json");
            String resp = HttpUtil.post(UPLOAD_PATH, params);
            Console.log("resp: {}", resp);
            result = resp;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}

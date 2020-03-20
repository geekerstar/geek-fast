package com.geekerstar.tool;

import cn.hutool.core.io.resource.InputStreamResource;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.PostMapping;
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
    private static final String UPLOAD_PATH = "http://39.107.25.229:8080/group1/upload";

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        String result = "";
        try {
            InputStreamResource isr = new InputStreamResource(file.getInputStream(),
                    file.getOriginalFilename());

            Map<String, Object> params = new HashMap<>();
            params.put("file", isr);
            params.put("path", "image");
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

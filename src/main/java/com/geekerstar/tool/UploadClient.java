package com.geekerstar.tool;

import cn.hutool.http.HttpUtil;

import java.io.File;
import java.util.HashMap;

/**
 * @author geekerstar
 * @date 2020/3/20 17:38
 * @description
 */
public class UploadClient {
    public static void main(String[] args) {
        //文件地址
        File file = new File("/Users/geekerstar/work/ideaprojects/geek-fast/files/img/favicon.ico");
        //声明参数集合
        HashMap<String, Object> paramMap = new HashMap<>();
        //文件
        paramMap.put("file", file);
        //输出
        paramMap.put("output", "json");
        //自定义路径
        paramMap.put("path", "image");
        //场景
        paramMap.put("scene", "image");
        //上传
        String result = HttpUtil.post("http://39.107.25.229:8080/group1/upload", paramMap);
        //输出json结果
        System.out.println(result);
    }
}

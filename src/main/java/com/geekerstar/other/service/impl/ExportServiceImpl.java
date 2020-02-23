package com.geekerstar.other.service.impl;

import com.geekerstar.other.service.ExportService;
import com.geekerstar.other.util.ExportTools;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/2/19 13:54
 * @description 导出工具类
 */
@Service
public class ExportServiceImpl implements ExportService {


    @Override
    public void exportWord(Map<String, Object> data, String templateName, String newFileName, HttpServletResponse response) {
        try {
            OutputStream outputStream = response.getOutputStream();
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment; fileName=" + new String((newFileName + ".doc").getBytes("utf-8"), "ISO8859-1"));
            OutputStream out = ExportTools.generateWord(data, templateName, outputStream);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void exportPdf(Map<String, Object> data, String templateName, String newFileName, HttpServletResponse response) {
        try {
            OutputStream outputStream = response.getOutputStream();
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            response.addHeader("Content-Disposition", "attachment; fileName=" + new String((newFileName + ".pdf").getBytes("utf-8"), "ISO8859-1"));
            OutputStream out = ExportTools.generatePdf(data, templateName, outputStream);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

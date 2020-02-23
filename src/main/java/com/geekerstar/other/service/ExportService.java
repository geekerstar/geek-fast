package com.geekerstar.other.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/2/19 13:54
 * @description 导出工具
 */
@Service
public interface ExportService {

    void exportWord(Map<String, Object> data, String templateName, String newFileName, HttpServletResponse response);

    void exportPdf(Map<String, Object> data, String templateName, String newFileName, HttpServletResponse response);
}

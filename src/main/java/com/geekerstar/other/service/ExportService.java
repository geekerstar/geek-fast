package com.geekerstar.other.service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/2/19 13:54
 * @description 导出工具
 */
public interface ExportService {

    void exportWord(Map<String, Object> data, String templateName, String newFileName, HttpServletResponse response);

    void exportPdf(Map<String, Object> data, String templateName, String newFileName, HttpServletResponse response);
}

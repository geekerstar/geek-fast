package com.geekerstar.other.util;


import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author geekerstar
 * @date 2020/2/19 09:07
 * @description
 */
@Slf4j
public class ExportTools {


    /**
     * 导出WORD
     *
     * @param data         数据源
     * @param templateName 模板名
     * @param outputStream 输出流
     * @return
     * @throws Exception
     */
    public static OutputStream generateWord(Map<String, Object> data, String templateName, OutputStream outputStream)
            throws Exception {
        Configuration configuration = new Configuration(Configuration.getVersion());
        String templateFilePath = getPath(null);
        configuration.setDirectoryForTemplateLoading(new File(templateFilePath));
        configuration.setDefaultEncoding("utf-8");
        Template template = null;
        try {
            template = configuration.getTemplate(templateName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), 10240);
        assert template != null;
        template.process(data, out);
        return outputStream;
    }


    /**
     * 导出PDF
     *
     * @param data         数据源
     * @param templateName 模板名
     * @param outputStream 输出流
     * @return
     * @throws Exception
     */
    public static OutputStream generatePdf(Map<String, Object> data, String templateName, OutputStream outputStream)
            throws Exception {
        Configuration configuration = new Configuration(Configuration.getVersion());
        String templateFilePath = getPath(null);
        configuration.setDirectoryForTemplateLoading(new File(templateFilePath));
        configuration.setDefaultEncoding("utf-8");
        Template template = null;
        try {
            template = configuration.getTemplate(templateName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 生成html
        String localHtmlUrl = templateFilePath + "temp.html";
        File outHtmFile = new File(localHtmlUrl);
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outHtmFile)));
        template.process(data, writer);
        writer.close();

        // 生成pdf
        File htmFile = new File(localHtmlUrl);
        String url = htmFile.toURI().toURL().toString();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        org.xhtmlrenderer.pdf.ITextFontResolver fontResolver = renderer
                .getFontResolver();
        try {
            fontResolver.addFont(templateFilePath + "simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } catch (com.itextpdf.text.DocumentException e) {
            e.printStackTrace();
        }
        renderer.layout();
        renderer.createPDF(outputStream);

        // 删除html格式
        File file = new File(localHtmlUrl);
        file.delete();

        Writer out = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), 10240);
        template.process(data, out);
        return outputStream;
    }

    /**
     * 获取文件或文件夹路径
     *
     * @param fileName 文件名
     * @return
     */
    private static String getPath(String fileName) throws FileNotFoundException {
        String systemName = System.getProperty("os.name");
        String rootPath = System.getProperty("user.dir");

        if (!systemName.contains("Window")) {
            File file = new File(rootPath);
            rootPath = file.getParent();
        }
        if (rootPath.endsWith("target")) {
            File file = new File(rootPath);
            rootPath = file.getParent();
        }
       if (systemName.contains("Mac OS X")){
           rootPath +=File.separator+"fsga2-project" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "freemarker" + File.separator;
       } else {
           rootPath += File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "freemarker" + File.separator;
       }
        if (fileName == null) {
            return rootPath;
        }
        return  rootPath + fileName;
    }


}

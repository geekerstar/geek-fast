package com.geekerstar.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author geekerstar
 * date: 2019/12/15 19:51
 * description: Geek-Fast 工具类
 */
public class GeekUtil {

    /**
     * 正则校验
     * @param regex 正则表达式字符串
     * @param value 要匹配的字符串
     * @return 正则校验结果
     */
    public static boolean match(String regex,String value){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * 判断是否包含中文
     * @param value 内容
     * @return 结果
     */
    public static boolean containChinese(String value){
        Pattern compile = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher matcher = compile.matcher(value);
        return matcher.find();
    }
}

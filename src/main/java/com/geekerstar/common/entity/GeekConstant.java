package com.geekerstar.common.entity;

/**
 * @author geekerstar
 * @date 2020/1/31 15:10
 * @description
 */
public class GeekConstant {

    // 注册用户角色ID
    public static final Long REGISTER_ROLE_ID = 2L;

    // 排序规则：降序
    public static final String ORDER_DESC = "desc";
    // 排序规则：升序
    public static final String ORDER_ASC = "asc";

    // 前端页面路径前缀
    public static final String VIEW_PREFIX = "febs/views/";

    // 验证码 Session Key
    public static final String CODE_PREFIX = "febs_captcha_";

    // 允许下载的文件类型，根据需求自己添加（小写）
    public static final String[] VALID_FILE_TYPE = {"xlsx", "zip"};

    /**
     * getDataTable 中 HashMap 默认的初始化容量
     */
    public static final int DATA_MAP_INITIAL_CAPACITY = 4;

    /**
     * 异步线程池名称，保存操作日志
     */
    public static final String ASYNC_POOL = "geekAsyncThreadPool";
}

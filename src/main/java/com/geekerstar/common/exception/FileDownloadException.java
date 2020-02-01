package com.geekerstar.common.exception;

/**
 * @author geekerstar
 * @date 2020/2/1 11:41
 * @description 文件下载异常
 */
public class FileDownloadException extends Exception {

    private static final long serialVersionUID = -3472032235047014410L;

    public FileDownloadException(String message) {
        super(message);
    }

}

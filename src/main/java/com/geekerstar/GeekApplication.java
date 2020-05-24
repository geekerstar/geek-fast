package com.geekerstar;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author geekerstar
 * date: 2020/1/31 15:33
 * description:
 */
@Slf4j
@EnableCaching
@MapperScan(basePackages = "com.geekerstar.*.mapper")
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class GeekApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(GeekApplication.class, args);
        Environment env = application.getEnvironment();
        // 线上服务器地址
        String onlineIp = InetAddress.getLocalHost().getHostAddress();
        String localIp = "127.0.0.1";
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info(
                "\n====================================================================================================\n\t"
                        + "程序启动成功! 访问路径如下:\n\t"
                        + "本地官方Swagger地址：http://" + localIp + ":" + port + path + "swagger-ui.html\n\t"
                        + "本地新版Swagger地址：http://" + localIp + ":" + port + path + "doc.html\n\t"
                        + "本地Druid连接池地址：http://" + localIp + ":" + port + path + "druid/index.html\n"
                        + "----------------------------------------------------------------------------------------------------\n\t"
                        + "线上官方Swagger地址：http://" + onlineIp + ":" + port + path + "swagger-ui.html\n\t"
                        + "线上新版Swagger地址：http://" + onlineIp + ":" + port + path + "doc.html\n\t"
                        + "线上Druid连接池地址：http://" + onlineIp + ":" + port + path + "druid/index.html" + "\n"
                        + "====================================================================================================");
    }
}

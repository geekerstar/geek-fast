package com.geekerstar.monitor.service;

import com.geekerstar.monitor.entity.ActiveUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author geekerstar
 * @date 2020/2/1 12:24
 * @description
 */
@Service
public interface ISessionService {

    /**
     * 获取在线用户列表
     *
     * @param username 用户名
     * @return List<ActiveUser>
     */
    List<ActiveUser> list(String username);

    /**
     * 踢出用户
     *
     * @param sessionId sessionId
     */
    void forceLogout(String sessionId);

}

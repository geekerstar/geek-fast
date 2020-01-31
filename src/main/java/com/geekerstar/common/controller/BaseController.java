package com.geekerstar.common.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.geekerstar.common.entity.GeekConstant;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Map;

/**
 * @author geekerstar
 * date: 2020/1/31 15:00
 * description:
 */
public class BaseController {

    private Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    protected Session getSession(){
        return getSubject().getSession();
    }

    protected Session getSession(Boolean flag){
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token){
        getSubject().login(token);
    }

    protected Map<String,Object> getDataTable(IPage<?> page){
        return getDataTable(page, GeekConstant.DATA_MAP_INITIAL_CAPACITY);
    }

    protected Map<String,Object> getDataTable(IPage<?> page,int dataMapInitialCapacity){
        Map<String,Object> data = Maps.newHashMapWithExpectedSize(dataMapInitialCapacity);
        data.put("rows",page.getRecords());
        data.put("total",page.getTotal());
        return data;
    }
}

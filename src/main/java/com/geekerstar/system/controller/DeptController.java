package com.geekerstar.system.controller;


import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.entity.DeptTree;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.system.entity.Dept;
import com.geekerstar.system.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门表 DeptController 前端控制器
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Slf4j
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("select/tree")
    @ControllerEndPoint(exceptionMessage = "获取部门树失败")
    public List<DeptTree<Dept>> getDeptTree() throws GeekException {
        return this.deptService.findDepts();
    }
}

package com.geekerstar.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.annotation.Weblog;
import com.geekerstar.common.entity.DeptTree;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.system.entity.Dept;
import com.geekerstar.system.service.IDeptService;
import com.wuwenze.poi.ExcelKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 部门表 DeptController 前端控制器
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Api(tags = "部门管理")
@Slf4j
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("select/tree")
    @Weblog(description = "获取部门树信息(下拉列表)")
    @ControllerEndPoint(exceptionMessage = "获取部门树失败")
    @ApiOperation(value = "获取部门树信息(下拉列表）", notes = "获取部门树信息(下拉列表)")
    public List<DeptTree<Dept>> getDeptTree() throws GeekException {
        return this.deptService.findDepts();
    }

    @GetMapping("tree")
    @Weblog(description = "获取部门树信息")
    @ControllerEndPoint(exceptionMessage = "获取部门树失败")
    @ApiOperation(value = "获取部门树信息", notes = "获取部门树信息")
    public GeekResponse getDeptTree(Dept dept) throws GeekException {
        List<DeptTree<Dept>> deptTreeList = this.deptService.findDepts(dept);
        return new GeekResponse().success().data(deptTreeList);
    }

    @PostMapping
    @Weblog(description = "新增部门")
    @RequiresPermissions("dept:add")
    @ControllerEndPoint(operation = "新增部门", exceptionMessage = "新增部门失败")
    @ApiOperation(value = "新增部门", notes = "新增部门")
    public GeekResponse addDept(@Valid Dept dept) {
        this.deptService.createDept(dept);
        return new GeekResponse().success();
    }

    @GetMapping("delete/{deptIds}")
    @Weblog(description = "删除部门")
    @RequiresPermissions("dept:delete")
    @ControllerEndPoint(operation = "删除部门", exceptionMessage = "删除部门失败")
    @ApiOperation(value = "删除部门", notes = "删除部门")
    public GeekResponse removeDepts(@NotBlank(message = "{required}") @PathVariable String deptIds) {
        String[] ids = deptIds.split(StringPool.COMMA);
        this.deptService.removeDepts(ids);
        return new GeekResponse().success();
    }

    @PostMapping("update")
    @Weblog(description = "修改部门")
    @RequiresPermissions("dept:update")
    @ControllerEndPoint(operation = "修改部门", exceptionMessage = "修改部门失败")
    @ApiOperation(value = "修改部门", notes = "修改部门")
    public GeekResponse updateDept(@Valid Dept dept) throws GeekException {
        this.deptService.updateDept(dept);
        return new GeekResponse().success();
    }

    @GetMapping("excel")
    @Weblog(description = "导出Excel")
    @RequiresPermissions("dept:export")
    @ControllerEndPoint(operation = "导出Excel", exceptionMessage = "导出Excel失败")
    @ApiOperation(value = "导出Excel", notes = "导出Excel")
    public void export(Dept dept, QueryRequest request, HttpServletResponse response) throws GeekException {
        List<Dept> deptList = this.deptService.findDepts(dept, request);
        ExcelKit.$Export(Dept.class, response).downXlsx(deptList, false);
    }

}

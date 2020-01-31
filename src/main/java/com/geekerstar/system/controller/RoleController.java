package com.geekerstar.system.controller;


import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.system.entity.Role;
import com.geekerstar.system.service.IRoleService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * 角色表 RoleController 前端控制器
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {
    @Autowired
    private IRoleService roleService;

    @GetMapping
    public GeekResponse getAllRoles(Role role) {
        return new GeekResponse().success().data(roleService.findRoles(role));
    }

    @GetMapping("list")
    @RequiresPermissions("role:view")
    public GeekResponse roleList(Role role, QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(this.roleService.findRoles(role, request));
        return new GeekResponse().success().data(dataTable);
    }

    @PostMapping
    @RequiresPermissions("role:add")
    @ControllerEndPoint(operation = "新增角色", exceptionMessage = "新增角色失败")
    public GeekResponse addRole(@Valid Role role) {
        this.roleService.createRole(role);
        return new GeekResponse().success();
    }

    @GetMapping("delete/{roleIds}")
    @RequiresPermissions("role:delete")
    @ControllerEndPoint(operation = "删除角色", exceptionMessage = "删除角色失败")
    public GeekResponse deleteRoles(@NotBlank(message = "{required}") @PathVariable String roleIds) {
        this.roleService.deleteRoles(roleIds);
        return new GeekResponse().success();
    }

    @PostMapping("update")
    @RequiresPermissions("role:update")
    @ControllerEndPoint(operation = "修改角色", exceptionMessage = "修改角色失败")
    public GeekResponse updateRole(Role role) {
        this.roleService.updateRole(role);
        return new GeekResponse().success();
    }

    @GetMapping("excel")
    @RequiresPermissions("role:export")
    @ControllerEndPoint(exceptionMessage = "导出Excel失败")
    public void export(QueryRequest queryRequest, Role role, HttpServletResponse response) throws GeekException {
        List<Role> roles = this.roleService.findRoles(role, queryRequest).getRecords();
        ExcelKit.$Export(Role.class, response).downXlsx(roles, false);
    }
}

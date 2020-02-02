package com.geekerstar.system.controller;

import com.geekerstar.common.annotation.ControllerEndpoint;
import com.geekerstar.common.utils.GeekUtil;
import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import ${basePackage}.${entityPackage}.${className};
import ${basePackage}.${servicePackage}.I${className}Service;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
* ${tableComment} Controller
*
* @author ${author}
* @date ${date}
*/
@Slf4j
@Validated
@Controller
public class ${className}Controller extends BaseController {

@Autowired
private I${className}Service ${className?uncap_first}Service;

@GetMapping(GeekConstant.VIEW_PREFIX + "${className?uncap_first}")
public String ${className?uncap_first}Index(){
return GeekUtil.view("${className?uncap_first}/${className?uncap_first}");
}

@GetMapping("${className?uncap_first}")
@ResponseBody
@RequiresPermissions("${className?uncap_first}:list")
public GeekResponse getAll${className}s(${className} ${className?uncap_first}) {
return new GeekResponse().success().data(${className?uncap_first}Service.find${className}s(${className?uncap_first}));
}

@GetMapping("${className?uncap_first}/list")
@ResponseBody
@RequiresPermissions("${className?uncap_first}:list")
public GeekResponse ${className?uncap_first}List(QueryRequest request, ${className} ${className?uncap_first}) {
Map
<String, Object> dataTable = getDataTable(this.${className?uncap_first}Service.find${className}s(request, ${className?uncap_first}));
return new GeekResponse().success().data(dataTable);
}

@ControllerEndpoint(operation = "新增${className}", exceptionMessage = "新增${className}失败")
@PostMapping("${className?uncap_first}")
@ResponseBody
@RequiresPermissions("${className?uncap_first}:add")
public GeekResponse add${className}(@Valid ${className} ${className?uncap_first}) {
this.${className?uncap_first}Service.create${className}(${className?uncap_first});
return new GeekResponse().success();
}

@ControllerEndpoint(operation = "删除${className}", exceptionMessage = "删除${className}失败")
@GetMapping("${className?uncap_first}/delete")
@ResponseBody
@RequiresPermissions("${className?uncap_first}:delete")
public GeekResponse delete${className}(${className} ${className?uncap_first}) {
this.${className?uncap_first}Service.delete${className}(${className?uncap_first});
return new GeekResponse().success();
}

@ControllerEndpoint(operation = "修改${className}", exceptionMessage = "修改${className}失败")
@PostMapping("${className?uncap_first}/update")
@ResponseBody
@RequiresPermissions("${className?uncap_first}:update")
public GeekResponse update${className}(${className} ${className?uncap_first}) {
this.${className?uncap_first}Service.update${className}(${className?uncap_first});
return new GeekResponse().success();
}

@ControllerEndpoint(operation = "修改${className}", exceptionMessage = "导出Excel失败")
@PostMapping("${className?uncap_first}/excel")
@ResponseBody
@RequiresPermissions("${className?uncap_first}:export")
public void export(QueryRequest queryRequest, ${className} ${className?uncap_first}, HttpServletResponse response) {
List<${className}> ${className?uncap_first}s = this.${className?uncap_first}Service.find${className}s(queryRequest, ${className?uncap_first}).getRecords();
ExcelKit.$Export(${className}.class, response).downXlsx(${className?uncap_first}s, false);
}
}

package com.geekerstar.other.controller;


import com.geekerstar.common.annotation.ControllerEndPoint;
import com.geekerstar.common.controller.BaseController;
import com.geekerstar.common.entity.GeekResponse;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.common.exception.GeekException;
import com.geekerstar.other.entity.Eximport;
import com.geekerstar.other.service.IEximportService;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * <p>
 * excel导入导出测试 前端控制器
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
@Api(tags = "导入导出")
@RestController
@RequestMapping("/eximport")
public class EximportController extends BaseController {


    @Autowired
    private IEximportService eximportService;

    @GetMapping
    @RequiresPermissions("others:eximport:view")
    @ApiOperation(value = "导入导出列表", notes = "导入导出列表")
    public GeekResponse findEximports(QueryRequest request) {
        Map<String, Object> dataTable = getDataTable(eximportService.findEximports(request, null));
        return new GeekResponse().success().data(dataTable);
    }


    @GetMapping("template")
    @RequiresPermissions("eximport:template")
    @ApiOperation(value = "生成 Excel导入模板", notes = "生成 Excel导入模板")
    public void generateImportTemplate(HttpServletResponse response) {
        // 构建数据
        List<Eximport> list = new ArrayList<>();
        IntStream.range(0, 20).forEach(i -> {
            Eximport eximport = new Eximport();
            eximport.setField1("字段1");
            eximport.setField2(i + 1);
            eximport.setField3("geekerstar" + i + "@gmail.com");
            list.add(eximport);
        });
        // 构建模板
        ExcelKit.$Export(Eximport.class, response).downXlsx(list, true);
    }


    @PostMapping("import")
    @RequiresPermissions("eximport:import")
    @ControllerEndPoint(exceptionMessage = "导入Excel数据失败")
    @ApiOperation(value = "导入Excel数据，并批量插入 sys_eximport表", notes = "导入Excel数据，并批量插入 sys_eximport 表")
    public GeekResponse importExcels(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new GeekException("导入数据为空");
        }
        String filename = file.getOriginalFilename();
        if (!StringUtils.endsWith(filename, ".xlsx")) {
            throw new GeekException("只支持.xlsx类型文件导入");
        }
        // 开始导入操作
        Stopwatch stopwatch = Stopwatch.createStarted();
        final List<Eximport> data = Lists.newArrayList();
        final List<Map<String, Object>> error = Lists.newArrayList();
        ExcelKit.$Import(Eximport.class).readXlsx(file.getInputStream(), new ExcelReadHandler<Eximport>() {
            @Override
            public void onSuccess(int sheet, int row, Eximport eximport) {
                // 数据校验成功时，加入集合
                eximport.setCreateTime(LocalDateTime.now());
                data.add(eximport);
            }

            @Override
            public void onError(int sheet, int row, List<ExcelErrorField> errorFields) {
                // 数据校验失败时，记录到 error集合
                error.add(ImmutableMap.of("row", row, "errorFields", errorFields));
            }
        });
        if (CollectionUtils.isNotEmpty(data)) {
            // 将合法的记录批量入库
            this.eximportService.batchInsert(data);
        }
        ImmutableMap<String, Object> result = ImmutableMap.of(
                "time", stopwatch.stop().toString(),
                "data", data,
                "error", error
        );
        return new GeekResponse().success().data(result);
    }

    @GetMapping("excel")
    @RequiresPermissions("eximport:export")
    @ControllerEndPoint(exceptionMessage = "导出Excel失败")
    @ApiOperation(value = "导出Excel", notes = "导出Excel")
    public void export(QueryRequest queryRequest, Eximport eximport, HttpServletResponse response) {
        List<Eximport> eximports = this.eximportService.findEximports(queryRequest, eximport).getRecords();
        ExcelKit.$Export(Eximport.class, response).downXlsx(eximports, false);
    }

}

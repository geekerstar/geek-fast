package com.geekerstar.generator.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.generator.entity.Column;
import com.geekerstar.generator.entity.Table;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author geekerstar
 * @date 2020/2/2 13:12
 * @description
 */
@Service
public interface IGeneratorService {

    /**
     * 获取数据库列表
     *
     * @param databaseType databaseType
     * @return 数据库列表
     */
    List<String> getDatabases(String databaseType);

    /**
     * 获取数据表
     *
     * @param tableName    tableName
     * @param request      request
     * @param databaseType databaseType
     * @param schemaName   schemaName
     * @return 数据表分页数据
     */
    IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName);

    /**
     * 获取数据表列属性
     *
     * @param databaseType databaseType
     * @param schemaName   schemaName
     * @param tableName    tableName
     * @return 数据表列属性
     */
    List<Column> getColumns(String databaseType, String schemaName, String tableName);
}

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

    List<String> getDatabases(String databaseType);

    IPage<Table> getTables(String tableName, QueryRequest request, String databaseType, String schemaName);

    List<Column> getColumns(String databaseType, String schemaName, String tableName);
}

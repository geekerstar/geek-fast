package com.geekerstar.generator.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.geekerstar.generator.entity.Column;
import com.geekerstar.generator.entity.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author geekerstar
 * @date 2020/2/2 13:12
 * @description
 */
public interface GeneratorMapper {
    List<String> getDatabases(@Param("databaseType") String databaseType);

    <T> IPage<Table> getTables(Page<T> page, @Param("tableName") String tableName, @Param("databaseType") String databaseType, @Param("schemaName") String schemaName);

    List<Column> getColumns(@Param("databaseType") String databaseType, @Param("schemaName") String schemaName, @Param("tableName") String tableName);
}

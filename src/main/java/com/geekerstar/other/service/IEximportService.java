package com.geekerstar.other.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.other.entity.Eximport;

import java.util.List;

/**
 * <p>
 * excel导入导出测试 服务类
 * </p>
 *
 * @author Geekerstar
 * @since 2020-02-02
 */
public interface IEximportService extends IService<Eximport> {

    /**
     * 查询（分页）
     *
     * @param request  QueryRequest
     * @param eximport eximport
     * @return IPage<Eximport>
     */
    IPage<Eximport> findEximports(QueryRequest request, Eximport eximport);


    /**
     * 批量插入
     *
     * @param list List<Eximport>
     */
    void batchInsert(List<Eximport> list);

}

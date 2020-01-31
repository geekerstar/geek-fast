package com.geekerstar.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekerstar.common.entity.DeptTree;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.system.entity.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门表 IDeptService 服务类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
public interface IDeptService extends IService<Dept> {


    /**
     * 获取部门树（下拉使用）
     *
     * @return 部门树集合
     */
    List<DeptTree<Dept>> findDepts();

    /**
     * 获取部门列表（树形列表）
     *
     * @param dept 部门对象（传递查询参数）
     * @return 部门树
     */
    List<DeptTree<Dept>> findDepts(Dept dept);

    /**
     * 获取部门树（供Excel导出）
     *
     * @param dept    部门对象（传递查询参数）
     * @param request QueryRequest
     * @return List<Dept>
     */
    List<Dept> findDepts(Dept dept, QueryRequest request);

    /**
     * 新增部门
     *
     * @param dept 部门对象
     */
    void createDept(Dept dept);

    /**
     * 删除部门
     *
     * @param ids 部门 ID集合
     */
    void removeDepts(String[] ids);

    /**
     * 修改部门
     *
     * @param dept 部门对象
     */
    void updateDept(Dept dept);
}

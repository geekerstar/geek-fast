package com.geekerstar.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.geekerstar.common.entity.DeptTree;
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
}

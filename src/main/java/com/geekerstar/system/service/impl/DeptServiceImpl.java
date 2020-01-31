package com.geekerstar.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekerstar.common.entity.DeptTree;
import com.geekerstar.common.util.TreeUtil;
import com.geekerstar.system.entity.Dept;
import com.geekerstar.system.mapper.DeptMapper;
import com.geekerstar.system.service.IDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表 DeptServiceImpl 服务实现类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    public List<DeptTree<Dept>> findDepts() {
        List<Dept> deptList = this.baseMapper.selectList(new QueryWrapper<>());
        List<DeptTree<Dept>> treeList = this.convertDepts(deptList);
        return TreeUtil.buildDeptTree(treeList);
    }

    private List<DeptTree<Dept>> convertDepts(List<Dept> depts) {
        List<DeptTree<Dept>> trees = new ArrayList<>();
        depts.forEach(dept -> {
            DeptTree<Dept> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getDeptId()));
            tree.setParentId(String.valueOf(dept.getParentId()));
            tree.setName(dept.getDeptName());
            tree.setData(dept);
            trees.add(tree);
        });
        return trees;
    }

}

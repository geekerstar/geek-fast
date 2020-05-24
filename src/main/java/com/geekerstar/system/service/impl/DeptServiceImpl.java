package com.geekerstar.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geekerstar.common.entity.DeptTree;
import com.geekerstar.common.entity.GeekConstant;
import com.geekerstar.common.entity.QueryRequest;
import com.geekerstar.common.util.SortUtil;
import com.geekerstar.common.util.TreeUtil;
import com.geekerstar.system.entity.Dept;
import com.geekerstar.system.mapper.DeptMapper;
import com.geekerstar.system.service.IDeptService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 部门表 DeptServiceImpl 服务实现类
 *
 * @author Geekerstar
 * @since 2020-01-31
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Override
    @Cacheable(value = {"Cache:findDepts"}, keyGenerator = "simpleKeyGenerator")
    public List<DeptTree<Dept>> findDepts() {
        List<Dept> deptList = this.baseMapper.selectList(new QueryWrapper<>());
        List<DeptTree<Dept>> treeList = this.convertDepts(deptList);
        return TreeUtil.buildDeptTree(treeList);
    }

    @Override
    @Cacheable(value = {"Cache:findDepts1"}, keyGenerator = "simpleKeyGenerator")
    public List<DeptTree<Dept>> findDepts(Dept dept) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dept.getDeptName())) {
            queryWrapper.lambda().eq(Dept::getDeptName, dept.getDeptName());
        }
        queryWrapper.lambda().orderByAsc(Dept::getOrderNum);
        List<Dept> deptList = this.baseMapper.selectList(queryWrapper);
        List<DeptTree<Dept>> treeList = this.convertDepts(deptList);
        return TreeUtil.buildDeptTree(treeList);
    }

    @Override
    @Cacheable(value = {"Cache:findDepts2"}, keyGenerator = "simpleKeyGenerator")
    public List<Dept> findDepts(Dept dept, QueryRequest request) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(dept.getDeptName())) {
            queryWrapper.lambda().eq(Dept::getDeptName, dept.getDeptName());
        }
        SortUtil.handleWrapperSort(request, queryWrapper, "orderNum", GeekConstant.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDept(Dept dept) {
        Long parentId = dept.getParentId();
        if (parentId == null) {
            dept.setParentId(0L);
        }
        dept.setCreateTime(LocalDateTime.now());
        this.save(dept);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeDepts(String[] ids) {
        this.delete(Arrays.asList(ids));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDept(Dept dept) {
        dept.setModifyTime(LocalDateTime.now());
        this.baseMapper.updateById(dept);
    }

    private List<DeptTree<Dept>> convertDepts(List<Dept> deptList) {
        List<DeptTree<Dept>> treeList = Lists.newArrayList();
        deptList.forEach(dept -> {
            DeptTree<Dept> tree = new DeptTree<>();
            tree.setId(String.valueOf(dept.getDeptId()));
            tree.setParentId(String.valueOf(dept.getParentId()));
            tree.setName(dept.getDeptName());
            tree.setData(dept);
            treeList.add(tree);
        });
        return treeList;
    }

    private void delete(List<String> deptIds) {
        removeByIds(deptIds);
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dept::getParentId, deptIds);
        List<Dept> depts = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(depts)) {
            List<String> deptIdList = Lists.newArrayList();
            depts.forEach(d -> deptIdList.add(String.valueOf(d.getDeptId())));
            this.delete(deptIdList);
        }
    }

}

package com.chen.wechat.service.impl;

import com.chen.wechat.mapper.DeptMapper;
import com.chen.wechat.model.Dept;
import com.chen.wechat.service.DeptService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * Department service implement
 * <p>
 * @Author LeifChen
 * @Date 2019-01-25
 */
@Service
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean save(Dept dept) {
        if (!Strings.isBlank(dept.getName())) {
            dept.setGmtCreate(new Date());
            int effectedNum = deptMapper.insert(dept);
            if (effectedNum > 0) {
                return true;
            } else {
                throw new RuntimeException("保存部门信息失败");
            }
        } else {
            throw new RuntimeException("部门信息不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public boolean update(Dept dept) {
        if (dept.getId() != null && dept.getId() > 0 && !Strings.isBlank(dept.getName())) {
            int effectedNum = deptMapper.updateByPrimaryKeySelective(dept);
            if (effectedNum > 0) {
                return true;
            } else {
                throw new RuntimeException("更新部门信息失败");
            }
        } else {
            throw new RuntimeException("部门信息不能为空");
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public boolean delete(int id) {
        if (id > 0) {
            int effectedNum = deptMapper.deleteByPrimaryKey(id);
            if (effectedNum > 0) {
                return true;
            } else {
                throw new RuntimeException("删除部门信息失败");
            }
        } else {
            throw new RuntimeException("部门ID不能为空");
        }
    }

    @Override
    public Dept getById(int id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Dept> listByPage(Dept dept, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        Example example = new Example(Dept.class);
        Example.Criteria criteria = example.createCriteria();
        if (dept != null && !StringUtils.isBlank(dept.getName())) {
            criteria.andLike("name", "%" + dept.getName() + "%");
        }
        example.orderBy("seq").asc();
        return deptMapper.selectByExample(example);
    }
}

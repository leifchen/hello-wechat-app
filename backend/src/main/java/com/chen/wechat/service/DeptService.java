package com.chen.wechat.service;

import com.chen.wechat.model.Dept;

import java.util.List;

/**
 * Department service
 * <p>
 * @Author LeifChen
 * @Date 2019-01-25
 */
public interface DeptService {

    /**
     * 新增
     * @param dept 部门
     * @return
     */
    boolean save(Dept dept);

    /**
     * 删除
     * @param id 主键
     * @return
     */
    boolean delete(int id);

    /**
     * 更新
     * @param dept 部门
     * @return
     */
    boolean update(Dept dept);

    /**
     * 根据主键查询单一部门
     * @param id 主键
     * @return
     */
    Dept getById(int id);

    /**
     * 分页查询部门列表
     * @param dept     参考部门
     * @param page     页码
     * @param pageSize 每页条数
     * @return
     */
    List<Dept> listByPage(Dept dept, int page, int pageSize);
}

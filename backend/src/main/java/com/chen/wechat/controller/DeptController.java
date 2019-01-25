package com.chen.wechat.controller;

import com.chen.wechat.common.JsonResult;
import com.chen.wechat.model.Dept;
import com.chen.wechat.service.DeptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Department controller
 * <p>
 * @Author LeifChen
 * @Date 2019-01-25
 */
@RestController
@RequestMapping("/api/depts")
public class DeptController {

    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    @PostMapping
    public JsonResult save(@RequestBody Dept dept) {
        if (deptService.save(dept)) {
            return JsonResult.ok();
        } else {
            return JsonResult.error("保存部门信息失败");
        }
    }

    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable(name = "id") int id) {
        if (deptService.delete(id)) {
            return JsonResult.ok();
        } else {
            return JsonResult.error("删除部门信息失败");
        }
    }

    @PutMapping("/{id}")
    public JsonResult update(@PathVariable(name = "id") int id, @RequestBody Dept dept) {
        if (deptService.update(dept)) {
            return JsonResult.ok();
        } else {
            return JsonResult.error("更新部门信息失败");
        }
    }

    @GetMapping("/{id}")
    public JsonResult getDeptInfo(@PathVariable("id") int id) {
        Dept dept = deptService.getById(id);
        if (dept != null) {
            return JsonResult.ok(dept);
        } else {
            return JsonResult.error("该部门不存在");
        }
    }

    @GetMapping
    public JsonResult page(@RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {

        List<Dept> userList = deptService.listByPage(null, page, pageSize);
        return JsonResult.ok(userList);
    }
}

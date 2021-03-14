package com.lian.system.controller;

import com.lian.common.controller.BaseController;
import com.lian.common.entity.PageResult;
import com.lian.common.entity.Result;
import com.lian.common.entity.ResultCode;
import com.lian.domain.system.Role;
import com.lian.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 角色控制层
 */
// 解决跨域问题
@CrossOrigin

@RestController

// 设置请求父路径
@RequestMapping("/sys")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色api
     */
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public Result add(@RequestBody Role role) throws Exception {
        role.setCompanyId(parseCompanyId());
        roleService.add(role);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除角色api
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        roleService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 更新角色信息
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody Role role) throws Exception {
        roleService.update(role);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据ID获取角色信息
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "id") String id) throws Exception {
        Role role = roleService.queryById(id);
        return new Result(ResultCode.SUCCESS, role);
    }

    /**
     * 分页查询角色
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public Result findByPage(int page, int pageSize, Role role) throws Exception {
        Page<Role> roles = roleService.queryAll(parseCompanyId(), page, pageSize);
        PageResult<Role> pageResult = new PageResult(roles.getTotalElements(), roles.getContent());
        return new Result(ResultCode.SUCCESS, pageResult);
    }

}

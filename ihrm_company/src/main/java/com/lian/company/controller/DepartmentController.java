package com.lian.company.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.lian.common.controller.BaseController;
import com.lian.common.entity.Result;
import com.lian.common.entity.ResultCode;
import com.lian.company.service.CompanyService;
import com.lian.company.service.DepartmentService;
import com.lian.domain.company.Company;
import com.lian.domain.company.Department;
import com.lian.domain.company.response.DeptListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

/**
 * 部门控制层
 *
 */
//  1. 解决跨域
@CrossOrigin
//  2. 声明restController
@RestController
//  3. 设置请求父路径
@RequestMapping(value = "/company")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CompanyService companyService;

    /**
     * 添加部门api
     */
    @RequestMapping(value = "/department", method = RequestMethod.POST)
    public Result add(@RequestBody Department department) {
        // 1. 设置保存的企业id
        // 2. 调用service完成保存企业
        // 3. 构造返回结果
        department.setCompanyId(companyId);
        departmentService.add(department);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 修改部门信息
     */
    @RequestMapping(value = "/department/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id,@RequestBody Department department) {

        department.setId(id);
        departmentService.update(department);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据ID获取部门信息
     */
    @RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
    public Result queryById(@PathVariable(value = "id") String id){
        Department department = departmentService.queryById(id);
        return new Result(ResultCode.SUCCESS, department);
    }

    /**
     * 根据ID删除部门信息
     */
    @RequestMapping(value = "/department/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) {
        departmentService.delete(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询企业的部门列表
     *      指定企业ID
     */
    @RequestMapping(value = "/department", method = RequestMethod.GET)
    public Result queryAll() {
        // 1. 指定企业id
        Company company = companyService.queryById(companyId);
        // 2. 完成查询
        List<Department> list = departmentService.queryAll(companyId);
        // 3. 构造返回结果
        DeptListResult deptListResult = new DeptListResult(company, list);

        return new Result(ResultCode.SUCCESS, deptListResult);
    }



}

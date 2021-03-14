package com.lian.company.controller;

import com.lian.common.entity.Result;
import com.lian.common.entity.ResultCode;
import com.lian.company.service.CompanyService;
import com.lian.domain.company.Company;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    // 保存企业信息
    // @RequestBody可以自动将对象转换为字符串
    // @RequestBody的作用是将前端传来的json格式的数据转为自己定义好的javabean对象
    // @ResponseBody的作用是将后端以return返回的javabean类型数据转为json类型数据
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Result add(@RequestBody Company company) {
        // 业务操作
        companyService.add(company);

        return new Result(ResultCode.SUCCESS);
    }

    // 根据id更新企业信息
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "id") String id, @RequestBody Company company) {
        company.setId(id);

        companyService.update(company);

        return new Result(ResultCode.SUCCESS);
    }

    // 根据id删除企业信息
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) {
        companyService.deleteById(id);
        return new Result(ResultCode.SUCCESS);

    }

    // 根据id查询企业信息
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result queryById(@PathVariable(value = "id") String id) {
        Company company = companyService.queryById(id);
        Result result = new Result(ResultCode.SUCCESS);
        result.setData(company);
        return result;
    }

    // 查询所有的企业信息
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result queryAll() {
        List<Company> companies = companyService.queryAll();

        Result result = new Result(ResultCode.SUCCESS);
        result.setData(companies);
        return result;
    }

}

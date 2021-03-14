package com.lian.system.controller;

import com.lian.common.controller.BaseController;
import com.lian.common.entity.PageResult;
import com.lian.common.entity.Result;
import com.lian.common.entity.ResultCode;
import com.lian.domain.system.User;
import com.lian.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户控制层
 *
 */
//  1. 解决跨域
@CrossOrigin
//  2. 声明restController
@RestController
//  3. 设置请求父路径
@RequestMapping(value = "/sys")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**
     * 添加用户api
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Result add(@RequestBody User user) throws Exception{

        user.setCompanyId(parseCompanyId());
        user.setCompanyName(parseCompanyName());
        userService.add(user);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody User user) throws Exception{

        user.setId(id);
        userService.update(user);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据ID获取用户信息
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Result queryById(@PathVariable(name = "id") String id) throws Exception{
        User user = userService.queryById(id);
        return new Result(ResultCode.SUCCESS, user);
    }

    /**
     * 根据ID删除用户信息
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception{
        userService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 查询用户列表
     *      指定用户ID
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Result queryByPage(int page, int pageSize, @RequestParam Map<String, Object> map) throws Exception{
        // 1. 获取当前的企业ID
        map.put("companyId", parseCompanyId());
        // 2. 完成查询
        Page<User> userPage = userService.queryAll(map, page, pageSize);
        // 3. 构造返回结果
        PageResult<User> pageResult = new PageResult(userPage.getTotalElements(), userPage.getContent());

        return new Result(ResultCode.SUCCESS, pageResult);
    }
}
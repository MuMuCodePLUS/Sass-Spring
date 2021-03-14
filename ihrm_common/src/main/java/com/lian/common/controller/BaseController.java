package com.lian.common.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共controller
 *      获取request，response
 *      获取企业id,获取企业名称
 */
public class BaseController {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String companyId;
    protected String companyName;

    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        // 以后再去解决
    }

    //企业id，(暂时使用1,以后会动态获取)
    public String parseCompanyId() {
        return "1";
    }
    public String parseCompanyName() {
        return "江苏练练股份有限公司";
    }

}

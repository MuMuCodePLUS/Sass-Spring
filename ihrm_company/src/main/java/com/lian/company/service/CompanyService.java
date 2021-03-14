package com.lian.company.service;

import com.lian.common.utils.IdWorker;
import com.lian.company.dao.CompanyDao;
import com.lian.domain.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存企业信息
     *  1. 配置idWorker到工程中
     *  2. 在service中注入IDWorker
     *  3. 通过IDWorker生成id
     *  4. 保存企业
     */
    public void add(Company company) {
        // 基本属性的设置
        String id = idWorker.nextId() + "";  // 后面的 ""是将long类型转换为String类型
        company.setId(id);

        // 默认的状态
        company.setAuditState("0"); // 0: 未审核  1: 已审核
        company.setState("1"); // 0: 未激活 1: 已激活

        companyDao.save(company);
    }

    /**
     * 更新企业信息
     *  1. 参数: Company
     *  2. 根据id查询出企业的对象
     *  3. 设置修改的属性
     *  4. 调用dao完成更新
     */
    public void update(Company company) {

        Company temp = companyDao.findById(company.getId()).get();
        temp.setName(company.getName());
        temp.setCompanyPhone(company.getCompanyPhone());
        companyDao.save(temp);  // 这里SpringDataJpa中会自动判断是新增还是更新


    }
    /**
     * 删除企业信息
     */
    public void deleteById(String id) {

        companyDao.deleteById(id);
    }

    /**
     * 根据ID查询企业
     */
    public Company queryById(String id) {

        return companyDao.findById(id).get();
    }

    /**
     * 查询所有企业信息
     */
    public List<Company> queryAll() {

        return companyDao.findAll();
    }

}

package com.lian.company;


import com.lian.company.dao.CompanyDao;
import com.lian.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyDaoTest {

    @Autowired
    private CompanyDao companyDao;

    @Test
    public void test() {

        // companyDao.save(company);   保存或者更新（id）有id就更新，没有id就新增
        // companyDao.deleteById(id) : 根据id删除
        // companyDao.findById(id): 根据id查询
        // companyDao.findAll(): 查询全部



        Company company = companyDao.findById("1").get();

        System.out.println(company);
    }

}

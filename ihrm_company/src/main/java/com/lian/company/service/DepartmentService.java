package com.lian.company.service;


import com.lian.common.utils.IdWorker;
import com.lian.company.dao.DepartmentDao;
import com.lian.domain.company.Department;
import com.lian.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Date;
import java.util.List;

/**
 * 部门操作业务逻辑层
 */
@Service
public class DepartmentService extends BaseService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 添加部门信息
     */
    public  void add(Department department) {
        department.setId(idWorker.nextId() + "");
        department.setCreateTime(new Date());
        departmentDao.save(department);
    }

    /**
     * 更新部门信息(根据ID找出需要修改信息的部门)
     */
    public void update(Department department) {

        /**
         * 注意：部门编码、部门类别和城市是不可修改的(前端输出框需要作出对应处理)
         */
        Department sourceDepartment = departmentDao.findById(department.getId()).get();
        // 修改部门名称
        sourceDepartment.setName(department.getName());
        // 修改父级部门ID
        sourceDepartment.setParentId(department.getParentId());
        // 修改负责人ID
        sourceDepartment.setManagerId(department.getManagerId());
        // 修改介绍
        sourceDepartment.setIntroduce(department.getIntroduce());
        // 修改部门负责人
        sourceDepartment.setManager(department.getManager());

        departmentDao.save(sourceDepartment);
    }

    /**
     * 根据ID获取对应部门信息
     */
    public Department queryById(String id) {

        return departmentDao.findById(id).get();
    }

    /**
     * 删除部门信息
     * @param id 部门ID
     */
    public void delete(String id) {

        departmentDao.deleteById(id);
    }

    /**
     * 获取所有部门列表
     * @param companyId 企业ID
     * @return 某个企业所有的部门列表
     */
    public List<Department> queryAll(String companyId) {

//        Specification<Department> spec = new Specification<Department>() {
//            /**
//             * 用户构造查询条件
//             * @param root : 包含了所有的对象数据
//             * @param criteriaQuery : 一般不用
//             * @param cb : 构造查询条件
//             * @return
//             */
//            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//                // 根据企业ID进行查询
//                return cb.equal(root.get("companyId").as(String.class), companyId);
//            }
//        };

        return departmentDao.findAll(getSpecification(companyId));
    }

}

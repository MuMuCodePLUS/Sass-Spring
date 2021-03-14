package com.lian.system.service;


import com.lian.common.service.BaseService;
import com.lian.common.utils.IdWorker;
import com.lian.domain.system.Role;
import com.lian.system.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class RoleService extends BaseService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RoleDao roleDao;

    /**
     * 添加角色
     */
    public void add(Role role) {
        String id = idWorker.nextId() + "";
        role.setId(id);
        roleDao.save(role);
    }

    /**
     * 更新角色
     */
    public void update(Role role) {
        Role target = roleDao.getOne(role.getId());
        target.setDescription(role.getDescription());
        target.setName(role.getName());
        roleDao.save(target);
    }

    /**
     * 根据ID查询角色
     */
    public Role queryById(String id) {
        return roleDao.findById(id).get();
    }

    /**
     * 根据id删除角色
     */
    public void deleteById(String id) {
        roleDao.deleteById(id);
    }

    /**
     * 查询所有信息
     */
    public List<Role> findAll(String companyId) {
        return roleDao.findAll(getSpecification(companyId));
    }


    /**
     * 查询所有用户信息列表
     */
    public Page<Role> queryAll(String companyId, int page, int size) {

        return roleDao.findAll(getSpecification(companyId), PageRequest.of(page - 1, size));
    }


}

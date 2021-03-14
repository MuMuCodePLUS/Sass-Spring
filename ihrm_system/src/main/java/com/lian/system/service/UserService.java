package com.lian.system.service;

import com.lian.common.utils.IdWorker;
import com.lian.domain.system.User;
import com.lian.system.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 保存用户
     */
    public void add(User user) {
        // 基本属性的设置
        String id = idWorker.nextId() + "";  // 后面的 ""是将long类型转换为String类型
        user.setCreateTime(new Date());
        user.setPassword("123456"); // 设置初始密码
        user.setEnableState(1);  // 默认设置为启用
        user.setId(id);

        userDao.save(user);
    }

    /**
     * 更新用户
     */
    public void update(User user) {

        User temp = userDao.findById(user.getId()).get();

        temp.setUsername(user.getUsername());
        temp.setPassword(user.getPassword());
        temp.setDepartmentId(user.getDepartmentId());

        userDao.save(temp);  // 这里SpringDataJpa中会自动判断是新增还是更新


    }
    /**
     * 删除用户
     */
    public void deleteById(String id) {

        userDao.deleteById(id);
    }

    /**
     * 根据ID查询用户
     */
    public User queryById(String id) {

        return userDao.findById(id).get();
    }

    /**
     * 查询所有用户信息列表
     *      参数：（意思是可以以哪个参数来查询用户）map集合的形式
     *          hasDept: 是否分配
     *          departmentId: 部门ID
     *          companyId: 公司ID
     */
    public Page<User> queryAll(Map<String, Object> map, int page, int size) {

        return userDao.findAll(createSpecification(map), PageRequest.of(page-1, size));

        // 1. 需要查询条件
//        Specification<User> spec = new Specification<User>() {
            /**
             * 动态拼接查询条件
             * @return
             */
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//
//                /**
//                 * 查询条件的构造
//                 */
//                List<Predicate> list = new ArrayList<>();
//                // 根据请求的companyId是否为空 来构造查询条件
//                if (StringUtils.isEmpty(map.get("companyId"))) {
//                    list.add(criteriaBuilder.equal(root.get("companyId").as(String.class), (String)map.get("companyId")));
//                }
//                // 根据 请求的部门ID 构造查询条件
//                if (StringUtils.isEmpty(map.get("departmentId"))) {
//                    list.add(criteriaBuilder.equal(root.get("departmentId").as(String.class), (String)map.get("departmentId")));
//                }
//
//                // 根据请求的 hasDept 判断 是否分配部门 0 未分配（departmentId == null） 1 已分配 （departmentId != null）
//                if (StringUtils.isEmpty(map.get("hasDept")) || "0".equals((String) map.get("hasDept"))) {
//                    list.add(criteriaBuilder.isNull(root.get("departmentId")));
//                } else {
//                    list.add(criteriaBuilder.isNotNull(root.get("departmentId")));
//                }
//                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
//            }
//        };
//
//        // 2. 分页
//        Page<User> pageUser = userDao.findAll(spec, PageRequest.of(page - 1, size));
//
//        return pageUser;
    }

    private Specification<User> createSpecification(Map searchMap) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();

                // ID
                if (searchMap.get("id") !=null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.equal(root.get("id").as(String.class),
                            (String)searchMap.get("id")));
                }
                // 手机号码
                if (searchMap.get("mobile")!=null &&
                        !"".equals(searchMap.get("mobile"))) {
                    predicateList.add(cb.equal(root.get("mobile").as(String.class),
                            (String)searchMap.get("mobile")));
                }
                // 用户ID
                if (searchMap.get("departmentId")!=null &&
                        !"".equals(searchMap.get("departmentId"))) {
                    predicateList.add(cb.like(root.get("departmentId").as(String.class),
                            (String)searchMap.get("departmentId")));
                }

                // 标题
                if (searchMap.get("formOfEmployment")!=null &&
                        !"".equals(searchMap.get("formOfEmployment"))) {
                    predicateList.add(cb.like(root.get("formOfEmployment").as(String.class),
                            (String)searchMap.get("formOfEmployment")));
                }
                if (searchMap.get("companyId")!=null &&
                        !"".equals(searchMap.get("companyId"))) {
                    predicateList.add(cb.like(root.get("companyId").as(String.class),
                            (String)searchMap.get("companyId")));
                }
                if (searchMap.get("hasDept")!=null &&
                        !"".equals(searchMap.get("hasDept"))) {
                    if("0".equals((String)searchMap.get("hasDept"))) {
                        predicateList.add(cb.isNull(root.get("departmentId")));
                    }else{
                        predicateList.add(cb.isNotNull(root.get("departmentId")));
                    }
                }

                return cb.and( predicateList.toArray(new
                        Predicate[predicateList.size()]));
            }
        };
    }

}

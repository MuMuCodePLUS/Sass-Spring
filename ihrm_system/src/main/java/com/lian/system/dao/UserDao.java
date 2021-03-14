package com.lian.system.dao;

import com.lian.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 用户数据访问接口
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {


}

package com.lian.system.dao;

import com.lian.domain.system.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 角色数据访问接口
 */
public interface RoleDao extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
}

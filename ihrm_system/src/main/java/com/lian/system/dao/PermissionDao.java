package com.lian.system.dao;

import com.lian.domain.system.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionDao extends JpaRepository<Permission, String>, JpaSpecificationExecutor<Permission> {
}

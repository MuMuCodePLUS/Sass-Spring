package com.lian.system.dao;

import com.lian.domain.system.PermissionApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionApiDao extends JpaRepository<PermissionApi, String>, JpaSpecificationExecutor<PermissionApi> {
}

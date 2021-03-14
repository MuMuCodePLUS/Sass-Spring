package com.lian.system.dao;

import com.lian.domain.system.PermissionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionPointDao extends JpaRepository<PermissionPoint, String>, JpaSpecificationExecutor<PermissionPoint> {
}

package com.lian.domain.system;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.websocket.server.ServerEndpoint;
import java.io.Serializable;

/**
 * 权限类型 -- 按钮
 */
@Entity
@Table(name = "pe_permission_point")
@Getter
@Setter
public class PermissionPoint implements Serializable {

    private static final long serialVersionUID = -1002411490113957485L;
    /**
     * 主键id  和权限共享一个ID
     */
    @Id
    private String id;

    /**
     * 按钮类型
     */
    private String pointClass;

    /**
     * 按钮icon
     */
    private String pointIcon;

    /**
     * 状态
     */
    private Integer pointStatus;
}

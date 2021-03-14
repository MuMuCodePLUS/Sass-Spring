package com.lian.domain.system;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.omg.CORBA.INTERNAL;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 权限实体类
 */
@Entity
@Table(name = "pe_permission")
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert(true)
@DynamicUpdate(true)
public class Permission implements Serializable {
    private static final long serialVersionUID = -4990810027542971546L;
    /**
     * 权限ID
     */
    @Id
    private String id;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限类型 1为菜单 2为功能 3为API
     */
    private Integer type;

    /**
     * 父主键，一个权限可能有一个菜单（父） -- 按钮（子）
     */
    private String parent_id;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 企业可见性  0：不可见，1：可见
     */
    private Integer enVisible;

    public Permission(String name, Integer type, String code, String description) {
        this.name = name;
        this.type = type;
        this.code = code;
        this.description = description;
    }
}

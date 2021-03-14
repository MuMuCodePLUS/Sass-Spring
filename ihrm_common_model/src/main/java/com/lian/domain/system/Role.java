package com.lian.domain.system;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pe_role")
@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 594829320797158219L;
    /**
     * 角色id
     */
    @Id
    private String id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 说明
     */
    private String description;

    /**
     * 企业ID
     */
    private String companyId;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")   // 不维护中间表
    private Set<User> users = new HashSet<User>(0); // 角色与用户 多对多

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "pe_role_permission",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
    private Set<Permission> permissions = new HashSet<Permission>(0); // 角色与模块 多对多
}

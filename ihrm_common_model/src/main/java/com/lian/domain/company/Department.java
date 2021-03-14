package com.lian.domain.company;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Department 实体类
 */
@Entity
@Table(name = "co_department")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {

    private static final long serialVersionUID = -9084332495284489553L;

    // ID
    @Id
    private String id;

    // 父级ID
    private String parentId;

    // 企业ID
    private String companyId;

    // 部门名称
    private String name;

    // 部门编码
    private String code;

    // 部门类别
    private String category;

    // 负责人ID
    private String managerId;

    // 城市
    private String city;

    // 介绍
    private String introduce;

    // 创建时间
    private Date createTime;

    // 部门负责人
    private String manager;


}

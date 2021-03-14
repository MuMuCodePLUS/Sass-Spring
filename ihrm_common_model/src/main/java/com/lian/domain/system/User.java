package com.lian.domain.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 */
@Entity
@Table(name = "bs_user")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 4297464181093070302L;

    /**
     * id
     */
    @Id
    private String id;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 启用状态  0: 禁用  1: 启用
     */
    private Integer enableState;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 部门ID
     */
    private String departmentId;
    /**
     * 入职时间
     */
    private Date timeOfEntry;
    /**
     * 聘用形式
     */
    private Integer formOfEmployment;
    /**
     * 工号
     */
    private String workNumber;
    /**
     * 管理形式
     */
    private String formOfManagement;
    /**
     * 工作城市
     */
    private String workingCity;
    /**
     * 转正时间
     */
    private Date correctionTime;
    /**
     * 在职状态 1.在职 2.离职
     */
    private Integer inServiceStatus;
    /**
     * 公司编号
     */
    private String companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 用户和角色多对多的关系
     * JsonIgnore: 忽略json转换
     *
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "pe_user_role", joinColumns = {@JoinColumn(name="user_id",referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id",referencedColumnName="id")})
    private Set<Role> roles = new HashSet<Role>();

}

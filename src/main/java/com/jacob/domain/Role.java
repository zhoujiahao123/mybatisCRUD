package com.jacob.domain;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
    private Integer roleId;
    private String roleName;
    private String roleDesc;
    //多对多关系映射
    private List<UserForRole> users;
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<UserForRole> getUsers() {
        return users;
    }

    public void setUsers(List<UserForRole> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", users=" + users +
                '}';
    }
}

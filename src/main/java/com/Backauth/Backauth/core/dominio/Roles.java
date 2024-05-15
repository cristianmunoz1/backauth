package com.Backauth.Backauth.core.dominio;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "role_permissions")
    private String rolePermissions;

    @OneToMany(mappedBy = "roles")
    private List<User> users;

//    @ManyToMany
//    @JoinColumn(name = "permission_id", insertable = false, updatable = false)
//    private List<Permissions> permissions;
}

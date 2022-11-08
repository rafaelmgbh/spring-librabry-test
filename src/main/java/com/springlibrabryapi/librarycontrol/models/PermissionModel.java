package com.springlibrabryapi.librarycontrol.models;

import org.springframework.security.core.GrantedAuthority;



import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permission")
public class PermissionModel implements GrantedAuthority , Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 180)
    private String description;

    public PermissionModel() {}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    @Override
    public String getAuthority() {
        return this.description;
    }
}

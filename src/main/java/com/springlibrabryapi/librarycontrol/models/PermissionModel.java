//package com.springlibrabryapi.librarycontrol.models;
//
//import org.springframework.security.core.GrantedAuthority;
//
//
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@Table(name = "permission")
//public class PermissionModel implements GrantedAuthority , Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false, length = 180)
//    private String description;
//
//    public PermissionModel() {}
//
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//
//
//    @Override
//    public String getAuthority() {
//        return this.description;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        PermissionModel that = (PermissionModel) o;
//
//        if (id != that.id) return false;
//        return Objects.equals(description, that.description);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = (int) (id ^ (id >>> 32));
//        result = 31 * result + (description != null ? description.hashCode() : 0);
//        return result;
//    }
//}

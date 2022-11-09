package com.springlibrabryapi.librarycontrol.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;




import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users")
public class UserModel implements UserDetails , Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_user;

    @Column(name="user_name", unique = true)
    private String userName;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="full_name")
    private String fullName;

    @Column(name="password")
    private String password;

    @Column(name="account_non_expired")
    private boolean accountNonExpired;

    @Column(name="account_non_locked")
    private boolean accountNonLocked;

    @Column(name="credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name="enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "users_permission", joinColumns = {@JoinColumn(name = "id_user")},
            inverseJoinColumns ={ @JoinColumn(name = "id_permission")})
    private List<PermissionModelV2> roles;


    public UserModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public UUID getId_user() {
        return this.id_user;
    }

    public void setId_user(UUID id) {
        this.id_user = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<PermissionModelV2> getPermissions() {
        return roles;
    }

    public void setPermissions(List<PermissionModelV2> permissions) {
        this.roles = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (accountNonExpired != userModel.accountNonExpired) return false;
        if (accountNonLocked != userModel.accountNonLocked) return false;
        if (credentialsNonExpired != userModel.credentialsNonExpired) return false;
        if (enabled != userModel.enabled) return false;
        if (!Objects.equals(id_user, userModel.id_user)) return false;
        if (!Objects.equals(userName, userModel.userName)) return false;
        if (!Objects.equals(email, userModel.email)) return false;
        if (!Objects.equals(fullName, userModel.fullName)) return false;
        if (!Objects.equals(password, userModel.password)) return false;
        return Objects.equals(roles, userModel.roles);
    }

    public List<PermissionModelV2> getRoles() {
        return roles;
    }

    public void setRoles(List<PermissionModelV2> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int result = id_user != null ? id_user.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (accountNonExpired ? 1 : 0);
        result = 31 * result + (accountNonLocked ? 1 : 0);
        result = 31 * result + (credentialsNonExpired ? 1 : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

}

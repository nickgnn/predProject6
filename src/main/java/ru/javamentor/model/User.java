package ru.javamentor.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users", schema = "db_example")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private Integer age;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "role_id")
    private Long role_id;

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    public User() {}

    public User(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.username = name;
        this.password = password;
    }

    public User(String name, String password, Integer age, String role) {
        this.username = name;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String username, String password, Integer age, String role) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String username, String password, Integer age, String role, Long role_id) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.password = password;
        this.role = role;
        this.role_id = role_id;
    }

    public User(String username, String password, Integer age, String role, Long role_id) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.role = role;
        this.role_id = role_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", role_id=" + role_id +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }
}

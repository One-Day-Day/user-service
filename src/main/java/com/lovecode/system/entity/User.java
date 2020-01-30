package com.lovecode.system.entity;

import com.lovecode.system.enums.UserStatus;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Data
@Entity
@Table(name = "user")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @Column(name = "role")
    private String role;

    @Column(
            name = "last_login_time"
    )
    private Timestamp lastLoginTime;

    @Column(
            name = "created_at"
    )
    protected Timestamp createdAt;

    @Column(
            name = "updated_at"
    )
    protected Timestamp updatedAt;

    @Column(
            name = "created_by"
    )
    protected String createdBy;

    @Column(
            name = "updated_by"
    )
    protected String updatedBy;


    public List<SimpleGrantedAuthority> getRole() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Arrays.stream(role.split(",")).forEach(item ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + item)));
        return authorities;
    }

}

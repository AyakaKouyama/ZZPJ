package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
@NamedQueries( {
        @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
        @NamedQuery(name = "User.existsByLogin", query = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.login = :login"),
        @NamedQuery(name = "User.existsByEmail", query = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email = :email"),
})

public class User extends BaseEntity{

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @ManyToOne
    @JoinColumn(name = "role_id",
            foreignKey = @ForeignKey(name = "FK__user_role_id"))
    private Role role;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userDetails_id",
            foreignKey = @ForeignKey(name = "FK__user_userDetails_id"))
    private UserDetails userDetails;

}

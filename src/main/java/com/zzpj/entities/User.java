package com.zzpj.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "User.existsByName",
                query = "SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM User u WHERE u.login = :login"),
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.login = :login")
})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "role_id",
            foreignKey = @ForeignKey(name = "FK__user_role_id"))
    private Role role;

    @OneToOne
    @JoinColumn(name = "userDetails_id",
            foreignKey = @ForeignKey(name = "FK__user_userDetails_id"))
    private UserDetails userDetails;
}

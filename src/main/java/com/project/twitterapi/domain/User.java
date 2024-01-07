package com.project.twitterapi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    @NonNull
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank(message = "Firstname is mandatory")
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @NotBlank(message = "LastName is mandatory")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Follow> followings;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "following")
    private List<Follow> followers;
}

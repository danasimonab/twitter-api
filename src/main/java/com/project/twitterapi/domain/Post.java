package com.project.twitterapi.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    @NonNull
    private Long id;


    @Column(name = "message", nullable = false)
    @NonNull
    private String message;


    @Column(name = "timestamp", nullable = false)
    @NonNull
    private Long timestamp;
    @ManyToOne
    @JoinColumn(name = "user", nullable = false, referencedColumnName = "username")
    private User user;
}

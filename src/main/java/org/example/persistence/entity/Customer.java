package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
//@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 50)
    String firstname;

    @Column(nullable = false, length = 50)
    String lastname;

    @Column(nullable = false, unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Column(unique = true, length = 150)
    String email;

    @Column(nullable = false, unique = true, length = 15)
    String phoneNumber;

    @Column(nullable = false, updatable = false)
    LocalDateTime registerDateTime;
}

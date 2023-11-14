package com.EventHorizon.EventHorizon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "information_tbl")
public class Information {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name", unique = true)
    public String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "pay_pal_Account", unique = true)
    private String payPalAccount;
    @Column(name = "role")
    private String role;
    @Column(name = "sign_in_with_email")
    private int signInWithEmail;
}

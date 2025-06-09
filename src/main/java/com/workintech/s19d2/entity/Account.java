package com.workintech.s19d2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account", schema = "bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}

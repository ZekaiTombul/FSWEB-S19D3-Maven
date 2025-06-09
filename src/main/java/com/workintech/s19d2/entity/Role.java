package com.workintech.s19d2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "role", schema = "bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private List<Member> members;

    @Override
    public String getAuthority() {
        return authority;
    }
}

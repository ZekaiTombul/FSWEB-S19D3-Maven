package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Member;
import com.workintech.s19d2.entity.Role;
import com.workintech.s19d2.repository.MemberRepository;
import com.workintech.s19d2.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Member register(String email, String password) {
        Optional<Member> existingUser = memberRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new RuntimeException("User with given email already exist");
        }

        Role userRole = roleRepository.findByAuthority("ADMIN")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        Member member = new Member();
        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password));
        member.setRoles(Collections.singletonList(userRole));

        return memberRepository.save(member);
    }
}

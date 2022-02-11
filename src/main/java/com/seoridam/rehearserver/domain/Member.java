package com.seoridam.rehearserver.domain;

import com.seoridam.rehearserver.global.common.JobEnum;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 20, nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private boolean ventureBefore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobEnum job;

    @Column(nullable = false)
    private String businessCategory;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public Member() {

    }

    @Builder
    public Member(Long id, String email, String password, String nickname, String name, int age, boolean ventureBefore, JobEnum job, String businessCategory, List<String> roles){
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.age = age;
        this.ventureBefore = ventureBefore;
        this.job = job;
        this.businessCategory = businessCategory;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.ssafy.nomnom.model.dto.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final int userNo;
    private final String userEmail;
    private final String userPw;
    private final String role;

    public CustomUserDetails(int userNo, String userEmail, String userPw, String role) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userPw = userPw;
        this.role = role;
    }

    public int getUserNo() {
        return userNo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	return Collections.singletonList((GrantedAuthority) () -> "ROLE_" + role);
    }

    @Override
    public String getPassword() {
        return userPw;
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}

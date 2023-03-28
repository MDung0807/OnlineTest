package com.iotstar.onlinetest.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotstar.onlinetest.models.Account;
import com.iotstar.onlinetest.models.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Slf4j
public class AccountDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long accountId;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String role;
    private Long userId;

    private List<GrantedAuthority> authorities;

    public AccountDetailsImpl(Long accountId,
                              String email,
                              String phoneNumber,
                              String username,
                              String password,
                              String role,
                              Long userId,
                              List<GrantedAuthority> authorities) {
        this.accountId = accountId;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
        this.username = username;
        this.password = password;

        if (authorities == null) {
            this.authorities = null;
        } else {
            this.authorities = new ArrayList<>(authorities);
        }
    }

    public static AccountDetailsImpl create(Account account){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+account.getRole().getRoleName()));
        return new AccountDetailsImpl(
                account.getAccountId(),
                account.getUser().getEmail(),
                account.getUser().getPhoneNumber(),
                account.getUsername(),
                account.getPassword(),
                account.getRole().getRoleName(),
                account.getUser().getUserId(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null ? null : new ArrayList<>(this.authorities);
    }



    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        AccountDetailsImpl that = (AccountDetailsImpl) object;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}

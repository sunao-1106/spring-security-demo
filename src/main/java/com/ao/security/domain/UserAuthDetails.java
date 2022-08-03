package com.ao.security.domain;

import com.ao.security.entity.Menu;
import com.ao.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserAuthDetails implements UserDetails {

    private User user;
    private List<Menu> menus;   // 封装了user的权限列表信息

    public UserAuthDetails(User user, List<Menu> perms) {
        this.user = user;
        this.menus = perms;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 封装权限信息
        return menus.stream().filter(menu -> menu.getPerm() != null).map(menu -> {
            // 获取该user对应的权限 封装到GrantedAuthority对象中
            return new SimpleGrantedAuthority(menu.getPerm());
        }).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

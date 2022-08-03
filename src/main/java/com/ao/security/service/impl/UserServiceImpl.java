package com.ao.security.service.impl;

import com.ao.security.dao.UserMapper;
import com.ao.security.entity.Menu;
import com.ao.security.entity.User;
import com.ao.security.exception.PasswordNotMatchException;
import com.ao.security.service.UserService;
import com.ao.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public List<Menu> getPermsByUserId(Integer userId) {
        return userMapper.getPermsByUserId(userId);
    }

    @Override
    public String login(User user) {
        String token = null;
        String username = user.getUsername();
        String password = user.getPassword();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!Objects.isNull(userDetails)) {
            // 校验密码
            boolean matches = passwordEncoder.matches(password, userDetails.getPassword());
            if (!matches) {
                throw new PasswordNotMatchException("用户名或密码错误");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 生成token返回
            token = jwtTokenUtil.generateToken(userDetails);
        }
        return token;
    }
}

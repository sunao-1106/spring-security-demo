package com.ao.security;

import com.ao.security.domain.UserAuthDetails;
import com.ao.security.entity.Menu;
import com.ao.security.entity.User;
import com.ao.security.service.UserService;
import com.ao.security.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        List<Menu> perms = userService.getPermsByUserId(2);
        System.out.println(perms);
    }

    @Test
    void createToken() {
        ArrayList<Menu> menus = new ArrayList<>();
        Menu menu = new Menu();
        menu.setPerm("delete");
        menus.add(menu);
        User user1 = new User();
        user1.setUsername("admin");
        UserAuthDetails user = new UserAuthDetails(user1, menus);
        String s = jwtTokenUtil.generateToken(user);
        System.out.println(s);
        // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOm51bGwsImNyZWF0ZWQiOjE2NTk0NDY1NTIwNjUsImV4cCI6MTY2MDY1NjE1Mn0.z-mu-hyCmN8yj4oYz4kBLmIKIRGLEJunRCKoGw7Em0yaqsqzK9Fts5alLu1W5UA7SF63GHQbDDfzRRS0OKsH4Q
    }

    @Test
    void createToken02() {
        ArrayList<Menu> menus = new ArrayList<>();
        Menu menu = new Menu();
        menu.setPerm("list");
        menus.add(menu);
        UserAuthDetails user = new UserAuthDetails(new User(), menus);
        String s = jwtTokenUtil.generateToken(user);
        System.out.println(s);
        // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOm51bGwsImNyZWF0ZWQiOjE2NTk0NDY2MDUyMjcsImV4cCI6MTY2MDY1NjIwNX0.XauqTWaYFVM_WCG9oXtIx1WbyOICas8x3VQUVINPBbWxXcruUQL6XEzEjdeaKBhWDsNkd_jEeQg6P83QjP_VZQ
    }

    @Test
    void encodePass() {
        System.out.println(passwordEncoder.encode("123"));
        System.out.println(passwordEncoder.encode("1234"));
    }

    @Test
    void getUsernameByToken() {
        String username = jwtTokenUtil.getUserNameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4aWFvbWluZyIsImNyZWF0ZWQiOjE2NTk0ODY5MDM3ODMsImV4cCI6MTY2MDY5NjUwM30.PvplpwyhfXVgXv9R8Yiiqq0pZ6ho08HPFrkEo1yD9yyEYGkDHoirpuqCm_CA06DA-Q88lgEKMXVhs8OZti5r2A");
        System.out.println(username);
    }

    @Test
    void valid() {
        User user = new User();
        user.setUsername("xiaoming");
        boolean b = jwtTokenUtil.validateToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4aWFvbWluZyIsImNyZWF0ZWQiOjE2NTk0ODY5MDM3ODMsImV4cCI6MTY2MDY5NjUwM30.PvplpwyhfXVgXv9R8Yiiqq0pZ6ho08HPFrkEo1yD9yyEYGkDHoirpuqCm_CA06DA-Q88lgEKMXVhs8OZti5r2A", new UserAuthDetails(user, null));
        System.out.println(b);
    }

}

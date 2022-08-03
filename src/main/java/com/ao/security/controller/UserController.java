package com.ao.security.controller;

import com.ao.security.common.R;
import com.ao.security.entity.User;
import com.ao.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody User user) {
        String token = null;
        try {
            token = userService.login(user);
        } catch (Exception e) {
            return R.error(10001, e.getMessage());
        }
        log.info("the token is created: {}", token);
        return R.ok().setData(token);
    }

    @PostMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('list')")
    public R list() {
        return R.ok();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('delete')")
    public R delete() {
        return R.ok();
    }

}

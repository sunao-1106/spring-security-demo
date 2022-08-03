package com.ao.security.service;

import com.ao.security.entity.Menu;
import com.ao.security.entity.User;

import java.util.List;

public interface UserService {

    List<Menu> getPermsByUserId(Integer userId);

    /**
     * 登录
     * @param user username & password
     * @return token
     */
    String login(User user);
}

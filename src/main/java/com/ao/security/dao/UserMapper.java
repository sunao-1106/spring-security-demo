package com.ao.security.dao;

import com.ao.security.entity.Menu;
import com.ao.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    User getUserByUsername(@Param("username") String username);

    List<Menu> getPermsByUserId(@Param("userId") Integer userId);
}

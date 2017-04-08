package com.my.mybatis.mapper;

import java.util.List;

import com.my.mybatis.model.User;

public interface UserMapper {
	User findUserById(int id);
	List<User> findUserByName(String  name);
}

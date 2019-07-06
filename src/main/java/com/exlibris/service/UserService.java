package com.exlibris.service;

import com.exlibris.domain.model.user.User;
import com.exlibris.domain.model.user.UserDto;

import java.util.Optional;

/**
 * @Author tomirszulc on 2019-06-29
 */
public interface UserService {
    User save(UserDto user);
    User findOne(String username);
    User deleteUser(long id);
    Optional<User> findById(long id);
}
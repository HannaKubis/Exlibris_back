package com.exlibris.domain.mapper;

import com.exlibris.domain.model.user.User;
import com.exlibris.domain.model.user.UserDto;
import org.springframework.stereotype.Service;

/**
 * @Author tomirszulc on 2019-06-29
 */
@Service
public class UserMapper {

    public UserDto maptToUserDto(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getBooks(),
                user.getFriends(),
                user.getRentals(),
                user.isActivated());
    }

    public User maptToUser(UserDto userDto) {
        return new User(userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getBooks(),
                userDto.getFriends(),
                userDto.getRentals(),
                userDto.isActivated());
    }
}

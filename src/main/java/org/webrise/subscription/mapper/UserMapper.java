package org.webrise.subscription.mapper;

import org.springframework.stereotype.Component;
import org.webrise.subscription.dto.user.UserCreateDto;
import org.webrise.subscription.dto.user.UserDto;
import org.webrise.subscription.dto.user.UserListDto;
import org.webrise.subscription.dto.user.UserUpdateDto;
import org.webrise.subscription.model.User;

import java.util.List;

@Component
public class UserMapper {

    public UserListDto toUserListDto(User user) {
        return new UserListDto(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public List<UserListDto> toUserListDto(List<User> users) {
        return users.stream()
            .map(this::toUserListDto)
            .toList();
    }

    public UserDto toUserDto(User user) {
        return new UserDto(
            user.getId(),
            user.getCreatedAt(),
            user.getUpdatedAt(),
            user.getUsername(),
            user.getEmail()
        );
    }

    public User toUser(UserCreateDto userCreateDto) {
        return User.builder()
            .username(userCreateDto.username())
            .email(userCreateDto.email())
            .build();
    }

    public User toUser(UserUpdateDto userUpdateDto) {
        return User.builder()
            .id(userUpdateDto.id())
            .username(userUpdateDto.username())
            .email(userUpdateDto.email())
            .build();
    }
}

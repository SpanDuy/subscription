package org.webrise.subscription.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.webrise.subscription.dto.ResponseDto;
import org.webrise.subscription.dto.user.UserCreateDto;
import org.webrise.subscription.dto.user.UserDto;
import org.webrise.subscription.dto.user.UserListDto;
import org.webrise.subscription.dto.user.UserUpdateDto;
import org.webrise.subscription.mapper.UserMapper;
import org.webrise.subscription.model.User;
import org.webrise.subscription.service.UserService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final UserMapper userMapper;
    
    @GetMapping
    public ResponseDto<List<UserListDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserListDto> userListDtos = userMapper.toUserListDto(users);
        return ResponseDto.success(userListDtos);
    }
    
    @GetMapping("/{id}")
    public ResponseDto<UserDto> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDto userDto = userMapper.toUserDto(user);
        return ResponseDto.success(userDto);
    }
    
    @GetMapping("/email/{email}")
    public ResponseDto<UserDto> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        UserDto userDto = userMapper.toUserDto(user);
        return ResponseDto.success(userDto);
    }
    
    @PostMapping
    public ResponseDto<?> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        User user = userMapper.toUser(userCreateDto);
        User createdUser = userService.createUser(user);
        UserDto userDto = userMapper.toUserDto(createdUser);
        return ResponseDto.success(userDto);
    }
    
    @PutMapping("/{id}")
    public ResponseDto<?> updateUser(
            @PathVariable Long id, 
            @Valid @RequestBody UserUpdateDto userUpdateDto) {
        User user = userMapper.toUser(userUpdateDto);
        User updatedUser = userService.updateUser(id, user);
        UserDto userDto = userMapper.toUserDto(updatedUser);
        return ResponseDto.success(userDto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseDto<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseDto.success(null, "User deleted successfully");
    }
} 
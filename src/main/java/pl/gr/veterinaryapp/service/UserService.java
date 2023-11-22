package pl.gr.veterinaryapp.service;

import pl.gr.veterinaryapp.model.dto.UserDto;
import pl.gr.veterinaryapp.model.dto.UserResponseDto;
import pl.gr.veterinaryapp.model.entity.VetAppUser;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getAllUsers();
    UserResponseDto getUser(Long id);
    UserResponseDto createUser(UserDto user);
    void deleteUser(Long id);
}


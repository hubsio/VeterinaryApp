package pl.gr.veterinaryapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gr.veterinaryapp.exception.IncorrectDataException;
import pl.gr.veterinaryapp.exception.ResourceNotFoundException;
import pl.gr.veterinaryapp.mapper.VetAppUserMapper;
import pl.gr.veterinaryapp.model.dto.UserDto;
import pl.gr.veterinaryapp.model.dto.UserResponseDto;
import pl.gr.veterinaryapp.model.entity.Role;
import pl.gr.veterinaryapp.model.entity.VetAppUser;
import pl.gr.veterinaryapp.repository.UserRepository;
import pl.gr.veterinaryapp.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final VetAppUserMapper mapper;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUser(Long id) {
        VetAppUser user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wrong id."));
        return mapper.mapToDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto createUser(UserDto user) {
        userRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new IncorrectDataException("Username exists.");
                });
        VetAppUser newVetAppUser = new VetAppUser();
        newVetAppUser.setUsername(user.getUsername());
        newVetAppUser.setPassword(encoder.encode(user.getPassword()));
        newVetAppUser.setRole(new Role(user.getRole()));
        userRepository.save(newVetAppUser);
        return mapper.mapToDto(newVetAppUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Wrong id."));
        userRepository.delete(user);
    }
}

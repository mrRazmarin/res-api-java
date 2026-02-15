package org.example.resapijava.service.serviceimpl;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.resapijava.dto.user.IsCreateDto;
import org.example.resapijava.dto.user.UserCreateDto;
import org.example.resapijava.dto.user.UserDto;
import org.example.resapijava.dto.user.UserUpdateDto;
import org.example.resapijava.dto.mapping.UserMapping;
import org.example.resapijava.entity.Roles;
import org.example.resapijava.entity.User;
import org.example.resapijava.entity.enums.Role;
import org.example.resapijava.repository.RoleRepository;
import org.example.resapijava.repository.UserRepository;
import org.example.resapijava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final UserMapping userMapping;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapping userMapping, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapping = userMapping;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<UserDto> getUser(Long id) {
        return userRepository.findById(id)
                .map(userMapping::toDto);
    }

    @Override
    @Transactional
    public IsCreateDto addUser(UserCreateDto userCreateDto) {
        if (userRepository.findByEmail(userCreateDto.getEmail()).isPresent()) {
            return IsCreateDto.builder()
                    .id(null)
                    .message("Пользователь с таким email-адресом уже существует!")
                    .isCreated(false)
                    .build();
        }

        if (userRepository.findByLogin(userCreateDto.getLogin()).isPresent()) {
            return IsCreateDto.builder()
                    .id(null)
                    .message("Пользователь с таким логином уже существует!")
                    .isCreated(false)
                    .build();
        }

        try {
            Roles defaultRole = roleRepository.findByName(Role.USER.getValue())
                    .orElseThrow(() -> new RuntimeException("Роль user не найдена! Выполните инициализацию БД."));


            User user = userMapping.toEntityCreate(userCreateDto);
            user.getRoles().add(defaultRole);
            User savedUser = userRepository.save(user);

            return IsCreateDto.builder()
                    .id(savedUser.getId())
                    .message("Пользователь создан!")
                    .isCreated(true)
                    .build();
        } catch (Exception exception){
            return IsCreateDto.builder()
                    .id(null)
                    .message("Ошибка при сохранении: " + exception.getMessage())
                    .isCreated(false)
                    .build();
        }
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapping::toDto);
    }

    @Override
    public UserDto updateUser(Long id, UserUpdateDto userUpdateDto) {
        User userFind = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Пользователь не найден!"));

        if (userUpdateDto.getLogin() != null && !userUpdateDto.getLogin().isBlank()) {
            userFind.setLogin(userUpdateDto.getLogin());
        }

        if (userUpdateDto.getEmail() != null && !userUpdateDto.getEmail().isBlank()) {
            userFind.setEmail(userUpdateDto.getEmail());
        }

        if (userUpdateDto.getPassword() != null && !userUpdateDto.getPassword().isBlank()) {
            userFind.setPassword(userUpdateDto.getPassword());
        }

        return userMapping.toDto(userRepository.save(userFind));
    }
}

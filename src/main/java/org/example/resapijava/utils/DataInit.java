package org.example.resapijava.utils;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.resapijava.entity.Roles;
import org.example.resapijava.entity.enums.Role;
import org.example.resapijava.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DataInit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    //private final UserRepository userRepository; <- на будущее, когда нужно будет предсоздавать учетку админа

    //Создание ролей, если их нет
    @Override
    @Transactional
    public void run(String... args) {
        log.info("Запуск инициализации данных...");

        if (!roleRepository.existsByName(Role.ROLE_USER.getValue())) {
            Roles userRole = new Roles();
            userRole.setName(Role.ROLE_USER.getValue());
            roleRepository.save(userRole);
        }

        if (!roleRepository.existsByName(Role.ROLE_ADMIN.getValue())) {
            Roles adminRole = new Roles();
            adminRole.setName(Role.ROLE_ADMIN.getValue());
            roleRepository.save(adminRole);
        }

        if (!roleRepository.existsByName(Role.ROLE_MODERATOR.getValue())) {
            Roles moderatorRole = new Roles();
            moderatorRole.setName(Role.ROLE_MODERATOR.getValue());
            roleRepository.save(moderatorRole);
        }

        log.info("Инициализация данных завершена!");
    }
}

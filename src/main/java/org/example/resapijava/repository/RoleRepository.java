package org.example.resapijava.repository;

import org.example.resapijava.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {

    Boolean existsByName(String name);

    Optional<Roles> findByName(String name);
}

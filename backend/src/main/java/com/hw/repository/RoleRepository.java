package com.hw.repository;

import com.hw.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findById(Long id);

    Role findByName(String name);
}

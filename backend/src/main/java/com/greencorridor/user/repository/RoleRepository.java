package com.greencorridor.user.repository;

import com.greencorridor.user.domain.Role;
import com.greencorridor.user.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}

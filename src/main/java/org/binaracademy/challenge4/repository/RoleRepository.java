package org.binaracademy.challenge4.repository;

import org.binaracademy.challenge4.model.ERole;
import org.binaracademy.challenge4.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole name);

    @Query (nativeQuery = true, value = "select * from roles r where r.role_name = :name")
    Role queryFindRole(@Param("name") ERole name);
}

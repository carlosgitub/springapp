package com.banc.springapp.repository;

import com.banc.springapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @project spring-bootbank
 * @autor carlosd on 2022-05-18
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);
}

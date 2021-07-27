package com.crmbackend.allService.userService.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.crmbackend.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Optional<Role> findByName(String name);

}

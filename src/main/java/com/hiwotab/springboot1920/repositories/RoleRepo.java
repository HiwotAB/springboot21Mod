package com.hiwotab.springboot1920.repositories;
import com.hiwotab.springboot1920.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role,Long>{
    Role findByRole(String role);
}

package com.hiwotab.springboot1920.repositories;
import com.hiwotab.springboot1920.model.NRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<NRole,Long>{
    NRole findByUrole(String role);
}

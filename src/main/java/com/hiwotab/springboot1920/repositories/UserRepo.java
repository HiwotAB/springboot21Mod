package com.hiwotab.springboot1920.repositories;

import com.hiwotab.springboot1920.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Long> {
    User findByUsername(String username);
}

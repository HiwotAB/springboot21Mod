package com.hiwotab.springboot1920.repositories;

import com.hiwotab.springboot1920.model.User;
import org.springframework.data.repository.CrudRepository;

public interface NUserRepo extends CrudRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);


}

package com.hiwotab.springboot1920.repositories;

import com.hiwotab.springboot1920.model.NUser;
import org.springframework.data.repository.CrudRepository;

public interface NUserRepo extends CrudRepository<NUser,Long> {
    NUser findByUsername(String username);
    NUser findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);


}

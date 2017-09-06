package com.hiwotab.springboot1920;

import com.hiwotab.springboot1920.model.NUser;
import com.hiwotab.springboot1920.repositories.RoleRepo;
import com.hiwotab.springboot1920.repositories.NUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class UserService {
    @Autowired
    NUserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    public UserService(NUserRepo userRepo){
        this.userRepo=userRepo;
    }
    public NUser findByEmail(String email){
        return userRepo.findByEmail(email);

    }
    public Long countByEmail(String email){
        return userRepo.countByEmail(email);

    }
    public NUser findByUsername(String username){
        return userRepo.findByUsername(username);

    }
    public void saveUser(NUser user){
        user.setRoles(Arrays.asList(roleRepo.findByUrole("USER")));
        user.setEnabled(true);
        userRepo.save(user);
    }
    public void saveAdmin(NUser user){
        user.setRoles(Arrays.asList(roleRepo.findByUrole("ADMIN")));
        user.setEnabled(true);
        userRepo.save(user);
    }

}

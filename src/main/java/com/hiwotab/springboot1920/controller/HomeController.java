package com.hiwotab.springboot1920.controller;

import com.hiwotab.springboot1920.model.Role;
import com.hiwotab.springboot1920.model.User;
import com.hiwotab.springboot1920.repositories.RoleRepo;
import com.hiwotab.springboot1920.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
@Controller
public class HomeController {

    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
            return "login";
        }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
    @RequestMapping("/secure")
    public String secure() {
        return "secure";
    }
    @RequestMapping("/testRoles")
    public @ResponseBody String showRoles()
    {
        Iterable <Role> r = roleRepo.findAll();
        String x="<h2>ROLE DETAILS</h2>";
        for(Role item:r)
        {
            x+="Role details:"+item.getRole()+" has an ID of "+item.getId()+"<br/>";
        }

        Role findR = roleRepo.findByRole("ADMIN");
        x+=findR.getRole()+" was found with an ID of "+findR.getId();
        return x;

    }
    @GetMapping("/signUpForm")
    public String addUser(Model model) {
        model.addAttribute("newUser", new User());
        return "signUpForm";
    }
    @PostMapping("/signUpForm")
    public String addUserConfirm( @ModelAttribute("newUser") User user ) {

//        Role role=roleRepo.findOne(new Long(1));
//        Set<Role> roleSet = new HashSet<Role>();
//        roleSet.add(role);
//        user.setRoles(roleSet);
//        userRepo.save(user);
//        return "signUpConfirm";

//        Set<Role> roleSet = new HashSet<Role>();
//        Role byRole = roleRepo.findByRole("USER");
//        roleSet.add(byRole);
//        user.setRoles(roleSet);

        user.setEnabled(true);
        user.addRole(roleRepo.findByRole("USER"));
        userRepo.save(user);
        return "signUpConfirm";
    }

    }

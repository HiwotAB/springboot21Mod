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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("/signUpForm")
    public String addUser(Model model) {
        model.addAttribute("newUser", new User());
        return "signUpForm";
    }
    @PostMapping("/signUpForm")
    public String addUserConfirm(@Valid @ModelAttribute("newUser") User user, BindingResult bindingResult , Model model) {
        if (bindingResult.hasErrors()) {
            return "signUpForm";
        }

//        user.setEnabled(true);
//        Role role=roleRepo.findOne(new Long(1));
//        Set<Role> roleSet = new HashSet<Role>();
//        roleSet.add(role);
//        user.setRoles(roleSet);
//        userRepo.save(user);
//        return "signUpConfirm";

        Set<Role> roleSet = new HashSet<Role>();
        Role byRole = roleRepo.findByRole("USER");
        roleSet.add(byRole);
        user.setRoles(roleSet);
        roleRepo.save(byRole);
        userRepo.save(user);
        return "signUpConfirm";
    }
    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    }

package com.hiwotab.springboot1920.controller;

import com.hiwotab.springboot1920.model.Role;
import com.hiwotab.springboot1920.model.User;
import com.hiwotab.springboot1920.repositories.RoleRepo;
import com.hiwotab.springboot1920.repositories.NUserRepo;
import com.hiwotab.springboot1920.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    NUserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    private UserService userService;
//    @RequestMapping(value="/register",method=RequestMethod.GET)
//    public String showRegistrationPage(Model model) {
//        model.addAttribute("user",new User());
//        return "registration";
//    }
//    @RequestMapping(value="/register",method=RequestMethod.POST)
//    public String showRegistrationPage(@Valid @ModelAttribute("user") User user,BindingResult bindingResult, Model model) {
//        model.addAttribute("user",user);
//        if(bindingResult.hasErrors()){
//            return "registration";
//        }
//        else
//        {
//            userService.saveUser(user);
//            model.addAttribute("message","User Account Successfully Created");
//        }
//        return "index";
//    }

    @RequestMapping("/")
    public String showHomePage() {
        return "homePage";
    }
    @RequestMapping("/index")
    public String index() {
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

    @RequestMapping("/listRoles")
    public String listRoles(Model model){
        model.addAttribute("userRoles", roleRepo.findAll());

        return "listRoles";
    }
    @RequestMapping("/testRoles")
    public @ResponseBody String showRoles()
    {
        Iterable <Role> r = roleRepo.findAll();
        String x="<h2>ROLE DETAILS</h2>";
        for(Role item:r)
        {
            x+="Role details:"+item.getUrole()+" has an ID of "+item.getId()+"<br/>";
        }

        Role findR = roleRepo.findByUrole("ADMIN");
        x+=findR.getUrole()+" was found with an ID of "+findR.getId();
        return x;

    }
    @RequestMapping(value="/signUpForm",method=RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("newUser", new User());
        return "signUpForm";
    }
    @RequestMapping(value="/signUpForm",method=RequestMethod.POST)
    public String addUserConfirm(@Valid @ModelAttribute("newUser") User user,BindingResult bindingResult, Model model) {
        model.addAttribute("newUser",user);
        if(bindingResult.hasErrors()){
            return "signUpForm";
        }
//        else if(user.getSelectVal().contains("USER"))        {
//
//            userService.saveUser(user);
//            model.addAttribute("message","User Account Successfully Created");
//        }
        else
            {
            userService.saveAdmin(user);
            model.addAttribute("message","User Account Successfully Created");
        }
        return "signUpConfirm";

    }

    }

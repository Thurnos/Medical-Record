package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.model.User;
import com.informatics.medical_record_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountWebController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public String accountPage(Model model) {
//
//        User currentUser = userService.getUserById();
//        if (currentUser != null) {
//            model.addAttribute("user", currentUser);
//        } else {
//            model.addAttribute("error", "User not logged in.");
//        }
//        return "account";
//    }
}

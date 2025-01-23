package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dto.RoleDTO;
import com.informatics.medical_record_spring.dto.UserDTO;
import com.informatics.medical_record_spring.model.Role;
import com.informatics.medical_record_spring.model.User;
import com.informatics.medical_record_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserWebController {


    @Autowired
    public UserWebController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/users")
//    public String viewUsers(Model model){
//        return "users";
//    }

    private final UserService userService;
    @GetMapping("/users")
    public String viewAllUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "users";  // returns users.html
    }


    // Create a new user (handled by the JavaScript addUser function)
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(mapToDTO(createdUser));
    }

    // Update an existing user (handled by the JavaScript updateUser function)
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        User user = mapToEntity(userDTO);
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(mapToDTO(updatedUser));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/account")
    public String showAccountPage() {
        return "account";
    }

    // Helper methods to map DTOs to Entities
    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPasswordHash(user.getPasswordHash());

        if (user.getRole() != null) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleid(user.getRole().getRoleid());
            roleDTO.setRolename(user.getRole().getRolename());
            roleDTO.setPermissions(user.getRole().getPermissions());
            userDTO.setRole(roleDTO);
        }

        return userDTO;
    }

    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(userDTO.getPasswordHash());

        if (userDTO.getRole() != null) {
            Role role = new Role();
            role.setRoleid(userDTO.getRole().getRoleid());
            role.setRolename(userDTO.getRole().getRolename());
            role.setPermissions(userDTO.getRole().getPermissions());
            user.setRole(role);
        }

        return user;
    }
}

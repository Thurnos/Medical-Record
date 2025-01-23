package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dto.RoleDTO;
import com.informatics.medical_record_spring.dto.UserDTO;
import com.informatics.medical_record_spring.model.Role;
import com.informatics.medical_record_spring.model.User;
import com.informatics.medical_record_spring.repository.RoleRepository;
import com.informatics.medical_record_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapToDTO(user));
    }
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            // Fetch the role from the database using the roleid from the userDTO
            Role role = roleRepository.findById(userDTO.getRole().getRoleid())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid role ID"));

            // Map UserDTO to User entity
            User user = mapToEntity(userDTO);
            user.setRole(role);  // Set the fetched role to the user

            User createdUser = userService.createUser(user);

            // Return the created user as UserDTO
            return ResponseEntity.ok(mapToDTO(createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping("/api/users")
    public ResponseEntity<User> getUserProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal(); // Assuming Spring Security
        return ResponseEntity.ok(user);
    }

        // Update an existing user by ID
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        try {
            User user = mapToEntity(userDTO);
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(mapToDTO(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an existing user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


//    @PostMapping
//    public UserDTO createUser(@RequestBody UserDTO userDTO) {
//        User user = mapToEntity(userDTO);
//        User createdUser = userService.createUser(user);
//        return mapToDTO(createdUser);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
//        try {
//            User user = mapToEntity(userDTO);
//            User updatedUser = userService.updateUser(id, user);
//            return ResponseEntity.ok(mapToDTO(updatedUser));
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }

    // Helper method to map User to UserDTO
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

    // Helper method to map UserDTO to User
    private User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(userDTO.getPasswordHash());

        // Map the role if it's provided
        if (userDTO.getRole() != null) {
            Role role = new Role();
            role.setRoleid(userDTO.getRole().getRoleid());
            role.setRolename(userDTO.getRole().getRolename());
            role.setPermissions(userDTO.getRole().getPermissions());
            user.setRole(role);  // Set the role for the user
        }

        return user;
    }
}

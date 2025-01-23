package com.informatics.medical_record_spring.service;

import com.informatics.medical_record_spring.dao.UserDAO;
import com.informatics.medical_record_spring.model.User;
import com.informatics.medical_record_spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    private final UserDAO userDAO;

//    @Autowired
//    private final UserDAO userDao;

//    @Autowired
//    public UserService(UserDao userDao) {
//        this.userDao = userDao;
//    }


    @Autowired
    public UserService(UserRepository userRepository, UserDAO userDAO) {
        this.userRepository = userRepository;
        this.userDAO = userDAO;
    }

    public void testUserFetching() {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            System.out.println("User: " + user.getName());
            if (user.getRole() != null) {
                System.out.println("Role: " + user.getRole().getRolename());
            } else {
                System.out.println("Role: None");
            }
        });
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    public User createUser(User user) {
        userDAO.createUser(user); // Save user to DB
        return user; // Return created user
    }
    // Update user
    public User updateUser(int id, User user) {
        user.setId(id);  // Ensure the ID is set on the user object.
        int rowsAffected = userDAO.updateUser(user);
        if (rowsAffected > 0) {
            return user;  // Return the updated user.
        } else {
            throw new RuntimeException("Failed to update user");
        }
    }

    // Delete user
    public void deleteUser(int id) {
        int rowsAffected = userDAO.deleteUser(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("Failed to delete user");
        }

    }


//    public User createUser(User user){
//        return userRepository.save(user);
//    }
//
//    public User updateUser(Integer id, User updatedUser) {
//        return userRepository.findById(id)
//                .map(existingUser -> {
//                    existingUser.setName(updatedUser.getName());
//                    existingUser.setEmail(updatedUser.getEmail());
//                    existingUser.setPasswordHash(updatedUser.getPasswordHash());
//                    existingUser.setRole(updatedUser.getRole());
//                    return userRepository.save(existingUser);
//                })
//                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
//    }
//    public void deleteUser(Integer id){
//        userRepository.deleteById(id);
//    }



}

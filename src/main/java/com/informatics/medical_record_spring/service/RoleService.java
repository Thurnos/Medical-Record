package com.informatics.medical_record_spring.service;

import com.informatics.medical_record_spring.model.Role;
import com.informatics.medical_record_spring.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    public Role getRoleById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public Role saveRole(Role role) {
        return repository.save(role);
    }

    public void deleteRole(Integer id) {
        repository.deleteById(id);
    }
}

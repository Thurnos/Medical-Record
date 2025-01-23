package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dto.RoleDTO;
import com.informatics.medical_record_spring.model.Role;
import com.informatics.medical_record_spring.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Integer id) {
        try {
            Role role = service.getRoleById(id);
            RoleDTO roleDTO = mapToDTO(role);
            return ResponseEntity.ok(roleDTO);
        } catch (RuntimeException e) {
            System.err.println("Error fetching role with ID " + id + ": " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return service.getAllRoles().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        Role role = mapToEntity(roleDTO);
        Role savedRole = service.saveRole(role);
        return mapToDTO(savedRole);
    }

    @PutMapping("/{id}")
    public RoleDTO updateRole(@PathVariable Integer id, @RequestBody RoleDTO roleDTO) {
        Role existingRole = service.getRoleById(id);
        existingRole.setRolename(roleDTO.getRolename());
        existingRole.setPermissions(roleDTO.getPermissions());
        Role updatedRole = service.saveRole(existingRole);
        return mapToDTO(updatedRole);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Integer id) {
        service.deleteRole(id);
    }

    // Helper method to map Role to RoleDTO
    private RoleDTO mapToDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleid(role.getRoleid());
        roleDTO.setRolename(role.getRolename());
        roleDTO.setPermissions(role.getPermissions());
        return roleDTO;
    }

    // Helper method to map RoleDTO to Role
    private Role mapToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleid(roleDTO.getRoleid());
        role.setRolename(roleDTO.getRolename());
        role.setPermissions(roleDTO.getPermissions());
        return role;
    }
}

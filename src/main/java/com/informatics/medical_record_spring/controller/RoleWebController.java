package com.informatics.medical_record_spring.controller;

import com.informatics.medical_record_spring.dao.RoleDAO;
import com.informatics.medical_record_spring.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleWebController {
    private final RoleDAO roleDAO;

    public RoleWebController(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @GetMapping
    public String getAllRoles(Model model) {
        model.addAttribute("roles", roleDAO.getAllRoles());
        return "roles";
    }

    @GetMapping("/{id}")
    public String getRoleById(@PathVariable int id, Model model) {
        Role role = roleDAO.getRoleById(id);
        model.addAttribute("role", role);
        return "role-details";
    }

    @PostMapping
    public String createRole(@ModelAttribute Role role) {
        roleDAO.createRole(role);
        return "redirect:/roles";
    }

    @PostMapping("/{id}")
    public String updateRole(@PathVariable int id, @ModelAttribute Role role) {
        roleDAO.updateRole(id, role);
        return "redirect:/roles";
    }

    @PostMapping("/delete/{id}")
    public String deleteRole(@PathVariable int id) {
        roleDAO.deleteRole(id);
        return "redirect:/roles";
    }
}

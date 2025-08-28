package com.example.hrms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.hrms.model.Role;
import com.example.hrms.model.enums.RoleType;
import com.example.hrms.repository.RoleRepository;

@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        for (RoleType type : RoleType.values()) {
            roleRepository.findByName(type).orElseGet(() -> {
                Role role = new Role();
                role.setName(type);
          //      System.out.println("Inserting role: " + type); // Debug
                return roleRepository.save(role);
            });
        }
    }
}

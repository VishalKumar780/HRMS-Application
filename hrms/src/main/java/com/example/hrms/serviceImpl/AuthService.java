package com.example.hrms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.hrms.config.JWTUtil;
import com.example.hrms.dto.AuthRequestDTO;
import com.example.hrms.dto.AuthResponseDTO;
import com.example.hrms.dto.RegisterDTO;
import com.example.hrms.model.Role;
import com.example.hrms.model.User;
import com.example.hrms.model.enums.RoleType;
import com.example.hrms.repository.RoleRepository;
import com.example.hrms.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

	@Autowired
    private UserRepository userRepo;
	@Autowired
    private RoleRepository roleRepo;
	@Autowired
    private AuthenticationManager authManager;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private JWTUtil jwtUtil;

    public void register(RegisterDTO request) {
        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Set<Role> userRoles = request.getRoles().stream()
                .map(roleStr -> RoleType.valueOf(roleStr.toUpperCase())) // Convert to UPPERCASE
                .map(roleType -> roleRepo.findByName(roleType)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleType)))
                .collect(Collectors.toSet());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(userRoles);

        userRepo.save(user);
    }

    public AuthResponseDTO login(AuthRequestDTO request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getEmail());
        return new AuthResponseDTO(token);
    }
}

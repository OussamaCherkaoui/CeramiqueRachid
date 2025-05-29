package com.CeramiqueRachid.controller;

import com.CeramiqueRachid.config.AuthenticationResponse;
import com.CeramiqueRachid.exception.DatabaseEmptyException;
import com.CeramiqueRachid.exception.UserNotFoundException;
import com.CeramiqueRachid.model.Admin;
import com.CeramiqueRachid.model.AuthenticationRequest;
import com.CeramiqueRachid.service.AdminService;
import com.CeramiqueRachid.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {
    private final AuthenticationManager authenticationManager;
    private final AdminService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;


    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return new ResponseEntity<>(userService.saveAdmin(admin), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            boolean deleteAdmin = userService.deleteAdmin(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteAdmin);
        } catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity<?> getAllAdmin() {
        try {
            List<Admin> admins = adminService.getAllAdmins();
            return ResponseEntity.ok(admins);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping("/getIdByUserName/{username}")
    public ResponseEntity<?> getIdByUserName(@PathVariable("username") String username) {
        try {
            Admin user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user.getId());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/getAdminByIdUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            Admin admin = userService.getUserById(id);
            return ResponseEntity.ok(admin);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/countRegistredAdmin")
    public ResponseEntity<?> countRegistredAdmin() {
        return new ResponseEntity<>(adminService.getCountAdminRegistred(), HttpStatus.OK);
    }
}

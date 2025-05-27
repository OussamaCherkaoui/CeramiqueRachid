package com.CeramiqueRachid.service;

import com.CeramiqueRachid.model.Admin;
import com.CeramiqueRachid.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin)
    {
        return adminRepository.save(admin);
    }



    public Admin getUserByUsername(String username) {
        Admin user = adminRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Admin not found");
        }
        return user;
    }

    public Admin getUserById(Long id) {
        Optional<Admin> user = adminRepository.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Admin not found");
        }
        return user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = adminRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}

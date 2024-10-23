package com.project.card.Service;

import com.project.card.Entity.Admin;
import com.project.card.Repository.AdminRepository;
import com.project.card.Entity.User;
import com.project.card.Model.JwtRequest;
import com.project.card.Model.JwtResponse;
import com.project.card.Repository.UserRepository;
import com.project.card.Security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;


@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private JwtHelper jwtUtil;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String email = jwtRequest.getEmail();
        String password = jwtRequest.getPassword();
        String otp = jwtRequest.getOtp();

        authenticate(email, password, otp);

        UserDetails userDetails = loadUserByUsername(email);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        // Determine if the authenticated entity is a user, vendor, or driver
        User user = userDao.findByEmail(email);
        if (user != null) {
            return new JwtResponse(email, newGeneratedToken);
        } else {
                    Admin admin = adminRepository.findByEmail(email);
                    if (admin != null) {
                        return new JwtResponse(email, newGeneratedToken);
                    } else {
                        throw new Exception("User, Vendor, or Driver not found with email: " + email);
                    }
                }
            }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        User user = userDao.findByEmail(email);

        Admin admin = adminRepository.findByEmail(email);

        if (user == null && admin == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Collection<GrantedAuthority> authorities = new HashSet<>();
        String password = null;

        if (user != null) {
            user.getRole().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            });
            password = user.getPassword();
        }else if (admin != null) {
            admin.getRole().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            });
            password = admin.getPassword();
        }

        return new org.springframework.security.core.userdetails.User(email, password, authorities);
    }


    private void authenticate(String email, String password, String otp) throws Exception {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            } catch (DisabledException e) {
                throw new Exception("USER_DISABLED", e);
            } catch (BadCredentialsException e) {
                throw new Exception("INVALID_CREDENTIALS", e);
            }
        }}


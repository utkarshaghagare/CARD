package com.project.card.Service;

import com.project.card.Entity.Admin;
import com.project.card.Entity.UserNfcDetails;
import com.project.card.Repository.AdminRepository;
import com.project.card.Entity.Role;
import com.project.card.Entity.User;
import com.project.card.Repository.RoleRepository;
import com.project.card.Repository.UserNfcDetailsRepository;
import com.project.card.Repository.UserRepository;
import com.project.card.Security.JwtAuthenticationFilter;
import com.project.card.Security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;

    public User registerNewUser(User user) throws Exception{

        if(userRepository.findByEmail(user.getEmail())!=null){
            throw new Exception("user present with email");
        }
	        Role role = roleDao.findById("User").get();
	        Set<Role> userRoles = new HashSet<>();
	        userRoles.add(role);
	        user.setRole(userRoles);
	        user.setPassword(getEncodedPassword(user.getPassword()));

	        return userRepository.save(user);
	    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }


    public Admin registerNewAdmin(Admin admin) throws Exception {
        if(adminRepository.findByEmail(admin.getEmail())!=null|| userRepository.findByEmail(admin.getEmail())!=null){
            throw new Exception("user present with email");
        }
        Role role = roleDao.findById("Admin").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        admin.setRole(userRoles);
        admin.setPassword(getEncodedPassword(admin.getPassword()));

        return adminRepository.save(admin);
    }


@Autowired
private CardService cardService;
    @Autowired
    private UserNfcDetailsRepository userNfcDetailsRepository;

    public UserNfcDetails addUserNfcDetails(UserNfcDetails userNfcDetails) {
        userNfcDetails.setUser(userRepository.findByEmail(JwtAuthenticationFilter.CURRENT_USER));
        return userNfcDetailsRepository.save(userNfcDetails);
    }
}

package com.project.card.Service;

import com.project.card.Entity.Role;
import com.project.card.Repository.RoleRepository;
import com.project.card.Security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private RoleRepository roleDao;
    public void initRoleAndUser() {


        // Create roles
        if (!roleDao.existsById("Admin")) {
            Role adminRole = new Role();
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin role");
            roleDao.save(adminRole);
        }

        if (!roleDao.existsById("User")) {
            Role userRole = new Role();
            userRole.setRoleName("User");
            userRole.setRoleDescription("Default role for newly created User");
            roleDao.save(userRole);
        }

    }

//	    public User registerNewUser(User user) {
//	        Role role = roleDao.findById("User").get();
//	        Set<Role> userRoles = new HashSet<>();
//	        userRoles.add(role);
//	        user.setRole(userRoles);
//	        user.setPassword(getEncodedPassword(user.getPassword()));
//
//	        return userDao.save(user);
//	    }

}

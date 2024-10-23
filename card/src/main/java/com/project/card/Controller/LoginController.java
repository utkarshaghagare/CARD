package com.project.card.Controller;

import com.project.card.Entity.Admin;
import com.project.card.Entity.User;
import com.project.card.Model.JwtRequest;
import com.project.card.Model.JwtResponse;
import com.project.card.Service.JwtService;
import com.project.card.Service.RoleService;
import com.project.card.Service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void initRoleAndUser() {
        roleService.initRoleAndUser();
    }
    @PostMapping({"/registerUser"})
    public User registerNewUser(@RequestBody User user) throws Exception {
        return userService.registerNewUser(user);
    }
    @PostMapping({"/registerAdmin"})
    public Admin registerAdmin(@RequestBody Admin admin) throws Exception {
        return userService.registerNewAdmin(admin);
    }

    @PostMapping({"/login"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}

package com.steven.cns.auth.resource.server.controller;

import com.steven.cns.auth.resource.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author steven.cao
 */
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public User getCurrentUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        return user;
    }
}

package com.steven.cns.auth.resource.server.service;


import org.springframework.security.core.userdetails.User;

public interface UserService {
//    extends IService<User>
//} {

    User findByUsername(String username);
}

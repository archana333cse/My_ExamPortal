package com.exam1.service;

import com.exam1.model.User;
import com.exam1.model.UserRole;

import java.util.Set;

public interface UserService {
    //creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    //get user by username
    public User getUser(String username);
    //delete user by id
    public void deleteUser(Long userId);
}

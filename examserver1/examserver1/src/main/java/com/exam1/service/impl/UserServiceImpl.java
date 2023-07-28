package com.exam1.service.impl;

import com.exam1.helper.UserFoundException;
import com.exam1.model.User;
import com.exam1.model.UserRole;
import com.exam1.repo.RoleRepository;
import com.exam1.repo.UserRepository;
import com.exam1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local=this.userRepository.findByUsername(user.getUsername());
        if(local!=null){
            System.out.println("User is already there!!");
            throw new UserFoundException();
        } else{
            //create user
            for(UserRole ur: userRoles){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local= this.userRepository.save(user);

        }
        return local;
    }
   //getting user by username
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }
}

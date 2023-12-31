package com.exam1.service.impl;

import com.exam1.model.User;
import com.exam1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = this.userRepository.findByUsername(username);
         if(user==null){
             System.out.println("user not found!!");
             throw new UsernameNotFoundException("no user found !!");
         }
        return user;
    }
}

package org.binaracademy.challenge4.service;

import org.binaracademy.challenge4.model.User;
import org.binaracademy.challenge4.model.UserDetailsImpl;
import org.binaracademy.challenge4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findByUname(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+username));
        return UserDetailsImpl.build(user);
    }
}

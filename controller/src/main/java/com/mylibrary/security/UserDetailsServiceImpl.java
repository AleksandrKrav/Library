package com.mylibrary.security;

import com.mylibrary.impl.UserService;
import com.mylibrary.PersistException;
import com.mylibrary.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        userService = new UserService();
        User user = null;
        try {
            user = userService.loadUserByUsername(s);
        } catch (PersistException e) {
            throw new UsernameNotFoundException("Oops!");
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().getName()));
        userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(), roles);
        return userDetails;
    }
}

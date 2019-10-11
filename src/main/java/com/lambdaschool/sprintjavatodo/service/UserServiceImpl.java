package com.lambdaschool.sprintjavatodo.service;

import com.lambdaschool.sprintjavatodo.model.User;
import com.lambdaschool.sprintjavatodo.repository.RoleRepository;
import com.lambdaschool.sprintjavatodo.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRespository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userrepos.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password, please try again.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());

    }

}

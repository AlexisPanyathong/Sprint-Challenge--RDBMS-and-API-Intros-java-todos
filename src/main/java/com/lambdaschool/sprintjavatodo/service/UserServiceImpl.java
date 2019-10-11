// I am stuck on this one....
package com.lambdaschool.sprintjavatodo.service;

import com.lambdaschool.sprintjavatodo.model.ToDo;
import com.lambdaschool.sprintjavatodo.model.User;
import com.lambdaschool.sprintjavatodo.model.UserRoles;
import com.lambdaschool.sprintjavatodo.repository.RoleRepository;
import com.lambdaschool.sprintjavatodo.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Transactional
    public User findUserById(long id) throws EntityNotFoundException {

        return userrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    public List<User> findAll() {

        List<User> list = new ArrayList<>();
        userrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {

        if (userrepos.findById(id).isPresent()) {
            userrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }

    }

    @Transactional
    @Override
    public User save(User user) {

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPasswordNoEncrypt(user.getPassword());

        ArrayList<UserRoles> newRoles = new ArrayList<>();

        for (UserRoles ur : user.getUserRoles()) {
            newRoles.add(new UserRoles(newUser, ur.getRole()));
        }
        newUser.setUserRoles(newRoles);

        for (ToDo t : user.getTodos()) {
            newUser.getTodos().add(new ToDo(t.getDescription(), new Date(), newUser));
        }
        return userrepos.save(newUser);
    }

    @Override
    public User findUSerByName(String name) {

        User currentUser = userrepos.findByUserName(name);

        if (currentUser != null) {
            return  currentUser;
        } else {
            throw new EntityNotFoundException(name);
        }
    }

    @Transactional
    @Override
    public User update(User user, long id) {

        User currentUser = userrepos.findById().orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (user.getUsername() != null) {

            currentUser.setUsername(user.getUsername());
        }

        if (user.getPassword() != null) {

            currentUser.setPasswordNoEncrypt(user.getPassword());
        }
//
//        // Add new user
//        for (UserRoles ur : user.getTodos()) {
//
//            rolerepos.insertUserRoles((id, ur.getRole().getRoleid()));
//        }
        }

//         if (user.getTodos().size() > 0)
//    {
//        for (ToDo t : user.getTodos())
//        {
//            currentUser.getTodos().add(new ToDo(t.getDescription(), new Date(), currentUser));
//        }
//    }
//        return userrepos.save(currentUser);

}

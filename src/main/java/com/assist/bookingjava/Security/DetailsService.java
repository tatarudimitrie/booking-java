package com.assist.bookingjava.Security;

import com.assist.bookingjava.model.Admin;
import com.assist.bookingjava.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17.07.2017.
 */
@Component
public class DetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin user = adminRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException(email + " was not found");
        }return new org.springframework.security.core.userdetails.User(
               user.getName(),
               user.getPass(),
               AuthorityUtils.createAuthorityList(new String[] {"USER"})
        );

    }
}
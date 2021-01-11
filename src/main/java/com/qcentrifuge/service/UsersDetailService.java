package com.qcentrifuge.service;

import com.qcentrifuge.db.users.Users;
import com.qcentrifuge.db.users.UsersRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersDetailService implements UserDetailsService {

    @Autowired
    private UsersRep rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = null;
        try {
            users = rep.findByLogin(username);
        }catch (InternalAuthenticationServiceException e){

        }
        if (users != null) {
            Users finalUsers = users;
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    List<GrantedAuthority> list = new ArrayList<>();

                    list.add(new SimpleGrantedAuthority("ROLE_" + finalUsers.getRole()));
                    return list;
                }

                @Override
                public String getPassword() {
                    return finalUsers.getPassword();
                }

                @Override
                public String getUsername() {
                    return finalUsers.getLogin();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    if (finalUsers.isBanned()) return false;
                    else return true;
                }

            };
        }else {
            throw new RuntimeException("");
        }
    }


}

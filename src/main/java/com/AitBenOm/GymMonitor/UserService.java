package com.AitBenOm.GymMonitor;

import com.AitBenOm.GymMonitor.entities.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserService  implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String EMail) throws UsernameNotFoundException {
        User user = loadUserByEmail(EMail);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPwd(),
                AuthorityUtils.createAuthorityList("USER"));
    }
    public User loadUserByEmail(String EMail){
        return new User("omar","ait benaissa", "omar.benaissa@outlook.com","123456");
    }
}

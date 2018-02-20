package com.AitBenOm.GymMonitor.Service;

import com.AitBenOm.GymMonitor.DAO.UserRepository;
import com.AitBenOm.GymMonitor.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService  implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String EMail) throws UsernameNotFoundException {
        System.out.println(EMail);
        User user = loadUserByEmail(EMail);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPwd(),
                AuthorityUtils.createAuthorityList("USER"));
    }
    public User loadUserByEmail(String EMail){
     return userRepository.getUserByEmail(EMail);

        //return new User("omar","ait benaissa", "omar.benaissa@outlook.com","123456");
    }
    public User loadUserByEmailAndPwd(String EMail, String Pwd){
        System.out.println("Email :"+EMail+" Pwd :"+Pwd);
     return userRepository.getUserByEmailPassword(EMail,Pwd);

        //return new User("omar","ait benaissa", "omar.benaissa@outlook.com","123456");
    }
}

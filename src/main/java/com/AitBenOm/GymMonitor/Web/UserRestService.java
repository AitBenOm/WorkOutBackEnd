package com.AitBenOm.GymMonitor.Web;


import com.AitBenOm.GymMonitor.DAO.UserRepository;
import com.AitBenOm.GymMonitor.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "Users")
@CrossOrigin("*")

public class UserRestService {
    @Autowired
    private UserRepository userRepository ;


    public User getUser(String userMail){
        System.out.println(userMail);

        System.out.println( userRepository.getUserByEmail(userMail));
        return userRepository.getUserByEmail(userMail);
    }
    @RequestMapping(value = "/SingUp", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user){
        if(getUser(user.getEmail())!=null){
            System.out.println("UserExist "+user.getEmail());
            return null;
        }else{
            System.out.println("Uset not existe "+user.getEmail());
            return this.userRepository.save(user);
        }

    }



}

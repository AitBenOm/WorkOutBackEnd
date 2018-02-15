package com.AitBenOm.GymMonitor.Web;


import com.AitBenOm.GymMonitor.DAO.ExerciseRepository;
import com.AitBenOm.GymMonitor.DAO.UserRepository;
import com.AitBenOm.GymMonitor.entities.Exercise;
import com.AitBenOm.GymMonitor.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "Users")
@CrossOrigin("*")

public class UserRestService {
    @Autowired
    private UserRepository userRepository ;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User getUser(){
        String email="omar.benaissa@outlook.com";
        return userRepository.getUserByEmailPassword(email);
    }



}

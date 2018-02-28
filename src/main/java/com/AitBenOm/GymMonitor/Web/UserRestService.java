package com.AitBenOm.GymMonitor.Web;


import com.AitBenOm.GymMonitor.DAO.UserRepository;
import com.AitBenOm.GymMonitor.entities.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


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

    @RequestMapping(value = "/imgProfil", method = RequestMethod.GET)
    public JSONObject getImage(@RequestParam(name = "id") int id) throws IOException {
        JSONObject image = new JSONObject();

        System.out.println("*******************************************");
        System.out.println(System.getProperty("user.dir"));
        File file = new ClassPathResource("/resources/images/user"+id + "/gym.jpg").getFile();

        String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));

        Map<String, String> jsonMap = new HashMap<>();
        image.put("image",jsonMap);

        jsonMap.put("content", encodeImage);

        return image;
    }



}

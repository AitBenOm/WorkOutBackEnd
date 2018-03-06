package com.AitBenOm.GymMonitor.Web;


import com.AitBenOm.GymMonitor.DAO.UserRepository;
import com.AitBenOm.GymMonitor.entities.Program;
import com.AitBenOm.GymMonitor.entities.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
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
//
//    @RequestMapping(value = "/saveFile", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
//    public String getImage(@RequestParam(name = "id") int id, @RequestBody File file) throws IOException {
        //        JSONObject image = new JSONObject();
//
//        System.out.println(id);
//        System.out.println(file.getName());
//        System.out.println("*******************************************");
//        System.out.println(System.getProperty("user.dir"));
//        File file = new ClassPathResource("/resources/images/user"+id + "/gym.jpg").getFile();
//
//        String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
//
//        Map<String, String> jsonMap = new HashMap<>();
//        image.put("image",jsonMap);
//
//        jsonMap.put("content", encodeImage);
//
//        return image;
     //   return file.getName();
  //  }

 @PostMapping(value = "/saveFile",headers = "content-type=multipart/*")
 public ResponseEntity<String> handleFileUpload(@RequestParam(value="file") MultipartFile file) throws IOException {
        File convertFile= new File("C:\\Users\\HWKR0452\\Documents\\Perso\\Project\\Tuto\\"+file.getOriginalFilename());
        convertFile.createNewFile();
     FileOutputStream fout= new FileOutputStream(convertFile);
     fout.write(file.getBytes());
     fout.close();
     return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
 }



}

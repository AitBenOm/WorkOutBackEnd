package com.AitBenOm.GymMonitor.Web;


import com.AitBenOm.GymMonitor.DAO.UserRepository;
import com.AitBenOm.GymMonitor.entities.Program;
import com.AitBenOm.GymMonitor.entities.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping(value = "Users")
@CrossOrigin("*")

public class UserRestService {
    @Autowired
    private UserRepository userRepository;


    public User getUser(String userMail) {
        System.out.println(userMail);

        System.out.println(userRepository.getUserByEmail(userMail));
        return userRepository.getUserByEmail(userMail);
    }

    @RequestMapping(value = "/SingUp", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) throws IOException {
        User newUser = new User();
        if (getUser(user.getEmail()) != null) {
            System.out.println("UserExist " + user.getEmail());
            return null;
        } else {

            newUser = this.userRepository.save(user);
            ClassPathResource imgFile = new ClassPathResource("");
            System.out.println(imgFile);
            String userDirectory = (System.getProperty("user.dir") + "/src/main/resources/usersDirectory/user_" + newUser.getIdUser());
            System.out.println(userDirectory);
            Path path = Paths.get(userDirectory);
            //if directory exists?
            if (!Files.exists(path)) {
                try {
                    Files.createDirectory(path);
                } catch (IOException e) {
                    //fail to create directory
                    e.printStackTrace();
                }
            }
        }
        return newUser;
    }

    @PostMapping(value = "/saveFile/{id}", headers = "content-type=multipart/*")
    public ResponseEntity<String> handleFileUpload(@RequestParam(value = "file") MultipartFile file, @PathVariable int id) throws IOException {
        String userDirectory = (System.getProperty("user.dir") + "/src/main/resources/usersDirectory/user_" + id);
        ClassPathResource imgFile = new ClassPathResource("/usersDirectory/user_"+id );
        Path currentRelativePath = Paths.get(userDirectory);
        File convertFile = new File(currentRelativePath + "/" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        User user=  userRepository.getOne(id);
        user.setAvatarPath(imgFile.getPath()+"/"+file.getOriginalFilename());
        userRepository.save(user);
        return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
    }


    @RequestMapping(value = "/getImage/{IdUser}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getImage(@PathVariable int IdUser) throws IOException {
        String userDirectory = (System.getProperty("user.dir") + "/src/main/resources/"+userRepository.getOne(IdUser).getAvatarPath());
        System.out.println(userDirectory);
      //  ClassPathResource imgFile = new ClassPathResource(userRepository.getOne(IdUser).getAvatarPath() );
      //  System.out.println(System.getProperty("user.dir") + "/src/main/resources/"+imgFile.getPath());
        File file = new File(userDirectory);
        System.out.println(file.getPath());

        String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));

        Map<String, String> jsonMap = new HashMap<>();

        jsonMap.put("content", encodeImage);

        return jsonMap;
    }


}

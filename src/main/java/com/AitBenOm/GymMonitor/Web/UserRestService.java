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
    public User registerUser(@RequestBody User user) {
        User newUser = new User();
        if (getUser(user.getEmail()) != null) {
            System.out.println("UserExist " + user.getEmail());
            return null;
        } else {
            System.out.println("Uset not existe " + user.getEmail());
            newUser = this.userRepository.save(user);
            String userDirectory = (System.getProperty("user.dir") + "\\src\\main\\resources\\usersDirectory\\user_" + newUser.getIdUser());
            Path currentRelativePath = Paths.get(userDirectory);
            try {
                Files.createDirectory(currentRelativePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return newUser;
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

    @PostMapping(value = "/saveFile/{id}", headers = "content-type=multipart/*")
    public ResponseEntity<String> handleFileUpload(@RequestParam(value = "file") MultipartFile file, @PathVariable int id) throws IOException {
        String userDirectory = (System.getProperty("user.dir") + "\\src\\main\\resources\\usersDirectory\\user_" + id);
        Path currentRelativePath = Paths.get(userDirectory);
        File convertFile = new File(currentRelativePath + "\\" + file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return new ResponseEntity<>("File is uploaded successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/getAvatar/{IdUser}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getMyAvatar(@PathVariable int IdUser) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("user_" + IdUser + "/Capture.PNG");
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

    @RequestMapping(value = "/getImage/{IdUser}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getImage(@PathVariable String IdUser) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("user_" + IdUser );
        System.out.println(imgFile);
        File currentDirectory = new File(new File(".").getAbsolutePath());
        System.out.println(currentDirectory.getCanonicalPath());
        System.out.println(currentDirectory.getAbsolutePath());
        String userDirectory = (System.getProperty("user.dir") + "\\src\\main\\resources\\user_" + IdUser);
        File file = new ClassPathResource(imgFile.getPath() + "\\Capture.PNG").getFile();
        System.out.println(file.getPath());

        String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));

        Map<String, String> jsonMap = new HashMap<>();

        jsonMap.put("content", encodeImage);

        return jsonMap;
    }


}

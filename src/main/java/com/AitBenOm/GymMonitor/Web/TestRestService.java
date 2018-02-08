package com.AitBenOm.GymMonitor.Web;

import com.AitBenOm.GymMonitor.DAO.ExerciseRepository;
import com.AitBenOm.GymMonitor.DAO.LoadRepository;
import com.AitBenOm.GymMonitor.DAO.ProgramRepository;
import com.AitBenOm.GymMonitor.DAO.TestRepo;
import com.AitBenOm.GymMonitor.entities.Program;
import com.AitBenOm.GymMonitor.entities.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")

public class TestRestService {


    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private LoadRepository loadRepository;

    @Autowired
    private ProgramRepository programRepository;


    @RequestMapping(value = "/MyTest", method = RequestMethod.POST, consumes = "application/json")
    @JsonFormat
    public Program saveTest(@RequestBody Program program){


        System.out.println("Program To Save : "+program.getExercises().size());
        User user = new User("omar","ait benaissa","omar.benaissa@outlook.com","123456");
        user.setIdUser(1);

        program.setUser(user);
        programRepository.save(program);
//        System.out.println(" Exercises" +program.getExercises());
//        program.getExercises().get(0).setProgram(program);
//        exerciseRepository.save( program.getExercises().get(0));
//        program.getExercises().get(0).getCharges().get(0).setExercise(  program.getExercises().get(0));
//        loadRepository.save(  program.getExercises().get(0).getCharges().get(0));

        return  programRepository.save(program);
    }


}

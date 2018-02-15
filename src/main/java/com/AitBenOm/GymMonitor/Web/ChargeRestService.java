package com.AitBenOm.GymMonitor.Web;

import com.AitBenOm.GymMonitor.DAO.ExerciseRepository;
import com.AitBenOm.GymMonitor.DAO.LoadRepository;
import com.AitBenOm.GymMonitor.entities.Charge;
import com.AitBenOm.GymMonitor.entities.Exercise;
import com.AitBenOm.GymMonitor.entities.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "Loads")
@CrossOrigin("*")

public class ChargeRestService {

    @Autowired
    private LoadRepository loadRepository;

@RequestMapping(value = "/MyLoads", method = RequestMethod.GET)
    public List<Charge> getLoads(@RequestParam(name = "idExercise") int idExercise){
    return loadRepository.getLoadsByExercise(idExercise);
}
@RequestMapping(value = "/loads", method = RequestMethod.GET)
    public List<Charge> getLoads(){
    return loadRepository.findAll();
}
@RequestMapping(value = "/MyLoad/{idLoad}", method = RequestMethod.GET)
    public Charge getLoadById(@PathVariable int idLoad){
    return loadRepository.findOne(idLoad);
}


    @RequestMapping(value = "/MyLoad", method = RequestMethod.POST)
    public Charge saveExercise(@RequestParam(name = "idExercise") String idExercise ,@RequestBody Charge charge){
        Exercise exercise  = new Exercise();
        exercise.setIdExercise(Integer.parseInt(idExercise));
        charge.setExercise(exercise);
        return  loadRepository.save(charge);
    }


    @RequestMapping(value = "/MyLoad", method = RequestMethod.PUT)
    public Charge updateLoad(@RequestBody Charge charge){

        return  loadRepository.save(charge);
    }
    @RequestMapping(value = "/MyLoads/{idLoad}", method = RequestMethod.DELETE)
    public boolean  deleteLoad(@PathVariable int idLoad){

        Charge charge= loadRepository.findOne(idLoad);
        charge.setExercise(null);
        System.out.println(idLoad);
         loadRepository.delete(charge);
        return true;
    }



}

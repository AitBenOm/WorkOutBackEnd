package com.AitBenOm.GymMonitor.DAO;

import com.AitBenOm.GymMonitor.entities.Charge;
import com.AitBenOm.GymMonitor.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoadRepository extends JpaRepository<Charge,Integer> {

    @Query("select c from Charge c where c.exercise.idExercise=:x")
    List<Charge> getLoadsByExercise(@Param("x") int idExercise);

    @Query("select c from Charge c where c.exercise.program.user.idUser=:x")
    List<Charge> getLoadsByUser(@Param("x") int idUser);


    @Query("delete from Charge c where c.idLoad=:x ")
    boolean deleteLoad(@Param("x") int idLoad);
}

package com.AitBenOm.GymMonitor.DAO;

import com.AitBenOm.GymMonitor.entities.Exercise;
import com.AitBenOm.GymMonitor.entities.Program;
import com.AitBenOm.GymMonitor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise,Integer> {

    @Query("select e from Exercise e where e.program.idProgram =:x")
     List<Exercise> getExercisesByProgram(@Param("x") int idProgram);

    @Query("select e from Exercise e where e.program.user.idUser =:x")
     List<Exercise> getExercisesByUser(@Param("x") int idUser);

}


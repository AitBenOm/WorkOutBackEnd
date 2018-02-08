package com.AitBenOm.GymMonitor.DAO;

import com.AitBenOm.GymMonitor.entities.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProgramRepository extends JpaRepository<Program,Integer> {

    @Query("select p from Program p where p.user.idUser = :x")
    public List<Program> getProgramsByUser(@Param("x") int idUser);

    @Query("select p from Program p where p.idProgram = :x")
    public Program getProgramsById(@Param("x") int idProgram);


}


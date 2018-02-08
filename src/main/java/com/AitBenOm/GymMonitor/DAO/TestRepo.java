package com.AitBenOm.GymMonitor.DAO;

import com.AitBenOm.GymMonitor.entities.Program;
import com.AitBenOm.GymMonitor.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestRepo extends JpaRepository<Test, Integer> {

    @Query("select t from Test t where t.user.idUser = :x")
    public List<Test> getProgramsByUser(@Param("x") int idUser);
}

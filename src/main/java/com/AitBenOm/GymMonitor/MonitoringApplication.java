package com.AitBenOm.GymMonitor;

import com.AitBenOm.GymMonitor.DAO.ExerciseRepository;
import com.AitBenOm.GymMonitor.DAO.LoadRepository;
import com.AitBenOm.GymMonitor.DAO.ProgramRepository;
import com.AitBenOm.GymMonitor.DAO.UserRepository;
import com.AitBenOm.GymMonitor.entities.Charge;
import com.AitBenOm.GymMonitor.entities.Exercise;
import com.AitBenOm.GymMonitor.entities.Program;
import com.AitBenOm.GymMonitor.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class MonitoringApplication implements CommandLineRunner{

	@Autowired
	private ProgramRepository programRepository;

	@Autowired
	private ExerciseRepository exerciseRepository;

	@Autowired
	private LoadRepository loadRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MonitoringApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		DateFormat df = new SimpleDateFormat(("dd/MM/yyyy"));
		User user = new User("omar","ait benaissa","omar.benaissa@outlook.com","123456");
		Charge  charge = new Charge("18", df.parse("26/06/2018"));
		Program program = new Program("PEC",df.parse("12/12/2014"),df.parse("12/12/2014"));
		Exercise exercise = new Exercise("Dips");
		userRepository.save(user);
//		program.setUser(user);
//		programRepository.save(program);
//		exercise.setProgram(program);
//
//		exerciseRepository.save(exercise);
//		charge.setExercise(exercise);
//		loadRepository.save(charge);


	}
}

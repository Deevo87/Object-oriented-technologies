package pl.edu.agh.to.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class SchoolApplication   {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}

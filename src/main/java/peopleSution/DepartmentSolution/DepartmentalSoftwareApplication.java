package peopleSution.DepartmentSolution;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class DepartmentalSoftwareApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(DepartmentalSoftwareApplication.class, args);
		System.out.println("main method");

		// call and use write class
		//writerClass toWrite = new writerClass();
		//toWrite.writeToFile("lets try writing to file test 2");

	}

}

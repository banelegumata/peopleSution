package peopleSolution.DepartmentSolution.Config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import peopleSolution.DepartmentSolution.Entities.CreateNewEmp;
import peopleSolution.DepartmentSolution.Entities.UserCredentials;
import peopleSolution.DepartmentSolution.Repositories.EmployeeRepository;
import peopleSolution.DepartmentSolution.Repositories.UserCredentialsRepository;

import java.time.LocalDateTime;

@Configuration
public class AdminSetup{

    @Bean
    CommandLineRunner createAdmin (UserCredentialsRepository userCredentialsRepository,
                                   EmployeeRepository employeeRepository)
    {
        return args -> {

            System.out.println("reached admin set up stage " + LocalDateTime.now());
            // setting default admin user fNumber
            String defaultfNumber = "F0001";

            /**checking to see if superuser with fnumber mentioned above exists.
             if not we create a new user row to cater for the fNumber and give it fnumber F0001**/
            if(employeeRepository.findByfNumber(defaultfNumber).isEmpty()) {
                CreateNewEmp superAdminEmp  = new CreateNewEmp();

                // place holders for super user - auto generated
                superAdminEmp .setEmpID("0000000000000");
                superAdminEmp .setEmpFName("Super");
                superAdminEmp .setEmpLName("Admin");
                superAdminEmp .setEmpDepartment("Other");
                superAdminEmp .setEmpActiveStatus(Boolean.valueOf("true"));
                superAdminEmp.setEmpJobTitle("other");
                superAdminEmp.setEmpJobDescription("other");
                superAdminEmp .setFNumber(defaultfNumber);

                employeeRepository.save(superAdminEmp); // save superAdmin user

                //print out for log purposes
                System.out.println("✅ Super admin created successfully in employee repository. " + LocalDateTime.now());


                // new creating superuser username and password to save in userCredentials table
                if (userCredentialsRepository.findByfNumber(defaultfNumber).isEmpty())
                {

                    // admin credentials:-
                    UserCredentials admin = new UserCredentials(defaultfNumber, "admin123", "ADMIN");
                    userCredentialsRepository.save(admin);
                    System.out.println("Super user Admin user and pass created and saved successfully in user credentials repository " + LocalDateTime.now());
                }
            }

            System.out.println("everything went well " + LocalDateTime.now());
        };
    }
}

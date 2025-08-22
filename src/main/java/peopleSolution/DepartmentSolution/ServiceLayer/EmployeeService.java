package peopleSolution.DepartmentSolution.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peopleSolution.DepartmentSolution.Entities.EmployeeEntity;
import peopleSolution.DepartmentSolution.Repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //save employee to db
    public EmployeeEntity saveEmployee(EmployeeEntity emp) {
            String nextFNumber = generateNextFNumber();
            emp.setfNumber(nextFNumber);
            return employeeRepository.save(emp);
    }

    // generates new fnumber to be saved
    private String generateNextFNumber() {
            Integer max = employeeRepository.findMaxFNumber();
            int next;
            // checks the highest value previously stored on the DB before generating a new one.
            if (max != null){
                    next = max + 1;
                }else{
                    next = 1001;
                }
            return String.format("F%04d", next);
    }

    //find employee by Employee ID
    public Optional<EmployeeEntity> findByEmpID (String empID){
        return employeeRepository.findByEmpID(empID);
    }

    //Find employee by FNumber
    public Optional<EmployeeEntity> findByfNumber (String fNumber){
        return employeeRepository.findByfNumberIgnoreCase(fNumber);
    }

    // find employee by First name
    public List<EmployeeEntity> searchByFName(String keyword){
        return employeeRepository.findByEmpFNameContainingIgnoreCase(keyword);
    }

    // View fetch all records
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    //update employee details
    public EmployeeEntity updateEmployee (EmployeeEntity employee){
        return employeeRepository.save(employee);
    }

    // delete user
    public Boolean deleteEmployeeByEmpID(String empID){
        // we check the DB if user exists and stores state and 'existinUser'
        Optional<EmployeeEntity> existinUser = findByEmpID(empID);

        // we then check the state and execute then delete if 'existingUser.isPresent()
        if(existinUser.isPresent()){
            employeeRepository.delete(existinUser.get());
            //we return TRUE to the boolean listed under delete functionality on employee management control functionality so that it displays on the jsp
            return true;
        }
        //we return FALSE to the boolean listed under delete functionality on employee management control functionality so that it displays on the jsp
        return  false;
    }

}

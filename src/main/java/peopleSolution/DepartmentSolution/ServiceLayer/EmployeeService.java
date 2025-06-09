package peopleSolution.DepartmentSolution.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peopleSolution.DepartmentSolution.Entities.CreateNewEmp;
import peopleSolution.DepartmentSolution.Repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //save employee to db
    public CreateNewEmp saveEmployee(CreateNewEmp emp) {
            String nextFNumber = generateNextFNumber();
            emp.setFNumber(nextFNumber);
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
            return "F" + next;
    }

    //===================================

    //find employee by Employee ID
    public Optional<CreateNewEmp> findByEmpID (String empID){
        return employeeRepository.findByEmpID(empID);
    }
    //Find employee by FNumber


    //===================================

    // find employee by First name
    public List<CreateNewEmp> searchByFName(String keyword){
        return employeeRepository.findByEmpFNameContainingIgnoreCase(keyword);
    }
}

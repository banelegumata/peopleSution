package peopleSolution.DepartmentSolution.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peopleSolution.DepartmentSolution.Entities.CreateNewEmp;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<CreateNewEmp, String> {

    // Search by empID
        Optional<CreateNewEmp> findByEmpID(String empID);

    // Search by First name (case-sensitive)
        List<CreateNewEmp> findByEmpFNameContainingIgnoreCase(String empFName);

    // Search by fNumber (exact match)
      Optional<CreateNewEmp> findByfNumber(String fNumber);

    //get the highest numeric part of fNumber


    @Query("SELECT MAX(CAST(SUBSTRING(e.fNumber, 2) AS int)) FROM CreateNewEmp e")
    Integer findMaxFNumber();

}

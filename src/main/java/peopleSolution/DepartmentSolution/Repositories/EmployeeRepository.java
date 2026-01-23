package peopleSolution.DepartmentSolution.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peopleSolution.DepartmentSolution.Entities.EmployeeEntity;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    // Search by empID
        Optional<EmployeeEntity> findByEmpID(String empID);

    // Search by First name (case-sensitive)
        List<EmployeeEntity> findByEmpFNameContainingIgnoreCase(String empFName);

    // Search by fNumber (exact match)
        Optional<EmployeeEntity> findByfNumberIgnoreCase(String fNumber);

    //get the highest numeric part of fNumber
    @Query("SELECT MAX(CAST(SUBSTRING(e.fNumber, 2) AS int)) FROM EmployeeEntity  e")
    Integer findMaxFNumber();

}

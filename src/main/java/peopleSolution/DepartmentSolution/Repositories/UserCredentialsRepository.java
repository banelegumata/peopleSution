package peopleSolution.DepartmentSolution.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peopleSolution.DepartmentSolution.Entities.UserCredentials;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long>{
    //Look up user by fNumber
    Optional<UserCredentials> findByfNumber (String fNumber);
}
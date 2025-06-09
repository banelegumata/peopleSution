package peopleSolution.DepartmentSolution.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peopleSolution.DepartmentSolution.Entities.UserCredentials;
import peopleSolution.DepartmentSolution.Repositories.UserCredentialsRepository;

import java.util.Optional;

@Service
public class UserLoginService {
    private final UserCredentialsRepository userCredentialsRepository;
    @Autowired
    public UserLoginService(UserCredentialsRepository userCredentialsRepository){
        this.userCredentialsRepository = userCredentialsRepository;
    }
    public Optional<UserCredentials> validateLogin(String fNumber, String password) {
        Optional<UserCredentials> user = userCredentialsRepository.findByfNumber(fNumber);

        // For now, plain text check. Later, use password hashing.
        return user.filter(u -> u.getPassword().equals(password));
    }

    public Optional<UserCredentials> getByFNumber(String fNumber) {
        return userCredentialsRepository.findByfNumber(fNumber);
    }

}

package peopleSolution.DepartmentSolution.ServiceLayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import peopleSolution.DepartmentSolution.Entities.UserCredentials;
import peopleSolution.DepartmentSolution.Repositories.UserCredentialsRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserLoginServiceTest {

    private UserCredentialsRepository mockRepo;
    private UserLoginService loginService;


    @BeforeEach
    void setupMockRepositoryToTest(){
        mockRepo = mock(UserCredentialsRepository.class);
        loginService = new UserLoginService(mockRepo);
    }

    @Test // test if password matches
    void shouldReturnUserWhenPasswordPatches(){
        UserCredentials user = new UserCredentials("F123", "Password123", "USER");

        when(mockRepo.findByfNumber("F123")).thenReturn(Optional.of(user));

        Optional<UserCredentials> results = loginService.validateLogin("F123", "Password123");
        assertTrue(results.isPresent());
        assertEquals("F123", results.get().getfNumber());
    }

    @Test
    void shouldReturnEmptyIfPasswordDoesNotMatch(){
        UserCredentials user = new UserCredentials("F123", "Password123", "USER");

        when(mockRepo.findByfNumber("F123")).thenReturn(Optional.of(user));
        Optional<UserCredentials> result = loginService.validateLogin("F123", "wrongPassword");

        assertTrue(result.isEmpty());
    }

    @Test
     void shouldReturnUserByFNumber() {
        UserCredentials user = new UserCredentials("F123", "Password123", "USER");

        when(mockRepo.findByfNumber ("F123")).thenReturn(Optional.of(user));

        Optional<UserCredentials> result = loginService.getByFNumber("F123");
        assertTrue(result.isPresent());
        assertEquals("F123", result.get().getfNumber());
    }


    @Test
    void shouldReturnEmptyIfUserNotFoundByFNumber() {
        when(mockRepo.findByfNumber("F999")).thenReturn(Optional.empty());

        Optional<UserCredentials> result = loginService.getByFNumber("F999");

        assertTrue(result.isEmpty());

    }
}

package peopleSolution.DepartmentSolution.Config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import peopleSolution.DepartmentSolution.Entities.EmployeeEntity;
import peopleSolution.DepartmentSolution.Entities.UserCredentials;
import peopleSolution.DepartmentSolution.Repositories.EmployeeRepository;
import peopleSolution.DepartmentSolution.Repositories.UserCredentialsRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = "admin.setup.run=false") // prevent CommandLineRunner auto-run. REMEMBER, this toddle was sent on the main file
class AdminSetupTest {
    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private AdminSetup adminSetup;

    @Test
    void shouldCreateSuperAdminIfNotExists() throws Exception {
        // Arrange - sets up the test
        when(employeeRepository.findByfNumberIgnoreCase("F0001")).thenReturn(Optional.empty());
        when(userCredentialsRepository.findByfNumber("F0001")).thenReturn(Optional.empty());

        // Act - actions the test
        adminSetup.ensureSuperAdmin(userCredentialsRepository, employeeRepository);

        // Assert - verfies if we successfully got what we wanted
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
        verify(userCredentialsRepository, times(1)).save(any(UserCredentials.class));
    }

    @Test
    void shouldNotCreateSuperAdminIfAlreadyExists() throws Exception {
        // Arrange - sets up the test
        EmployeeEntity existingAdmin = new EmployeeEntity();
        existingAdmin.setfNumber("F0001");

        when(employeeRepository.findByfNumberIgnoreCase("F0001")).thenReturn(Optional.of(existingAdmin));

        // Act - actions the test
        adminSetup.ensureSuperAdmin(userCredentialsRepository, employeeRepository);

        // Assert - verfies if we successfully got what we wanted
        verify(employeeRepository, never()).save(any(EmployeeEntity.class));
        verify(userCredentialsRepository, never()).save(any(UserCredentials.class));
    }
}

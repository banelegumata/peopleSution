
package peopleSolution.DepartmentSolution.Config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import peopleSolution.DepartmentSolution.Entities.EmployeeEntity;
import peopleSolution.DepartmentSolution.Entities.UserCredentials;
import peopleSolution.DepartmentSolution.Repositories.EmployeeRepository;
import peopleSolution.DepartmentSolution.Repositories.UserCredentialsRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminSetupUnitTest { // enables and allows Mokito for JUnit5 to work

    @Mock // mock allows for us to control return values and verify interections unlike depending on mockbeans where you test spring boot intead of the logic its self
    private EmployeeRepository employeeRepository;

    @Mock
    private UserCredentialsRepository userCredentialsRepository;

    // What: We instantiate AdminSetup directly.
    // Why: Pure unit test—no @SpringBootTest, no auto-wiring.
    private final AdminSetup adminSetup = new AdminSetup();


    // below creates SuperUser if super user is not found
    @Test
    void createsSuperAdminWhenMissing() {
        // Arrange - creates when does not exist
        when(employeeRepository.findByfNumberIgnoreCase("F0001")).thenReturn(Optional.empty());
        when(userCredentialsRepository.findByfNumber("F0001")).thenReturn(Optional.empty());

        // Act - action the above
        adminSetup.ensureSuperAdmin(userCredentialsRepository, employeeRepository);

        // Assert - verfies that the above action was only done onces
        verify(employeeRepository, times(1)).save(any(EmployeeEntity.class));
        verify(userCredentialsRepository, times(1)).save(any(UserCredentials.class));
        verifyNoMoreInteractions(employeeRepository, userCredentialsRepository);
    }

    @Test
    void doesNothingWhenSuperAdminExists() {
        // Arrange - checks if super user exists
        EmployeeEntity existing = new EmployeeEntity();
        existing.setfNumber("F0001");
        when(employeeRepository.findByfNumberIgnoreCase("F0001")).thenReturn(Optional.of(existing));

        // Act -action the above
        adminSetup.ensureSuperAdmin(userCredentialsRepository, employeeRepository);

        // Assert - verifies the above
        verify(employeeRepository, never()).save(any(EmployeeEntity.class));
        verify(userCredentialsRepository, never()).save(any(UserCredentials.class));
        verifyNoMoreInteractions(employeeRepository, userCredentialsRepository);
    }
}

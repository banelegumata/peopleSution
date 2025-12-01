package peopleSolution.DepartmentSolution.ServiceLayer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import peopleSolution.DepartmentSolution.Entities.EmployeeEntity;
import peopleSolution.DepartmentSolution.Repositories.EmployeeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest
{
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUpMockBeforeEachTest(){
        MockitoAnnotations.openMocks(this);
    }
    //testing save funtionality and the fact that the code will only save once.
    @Test
    void TestSaveEmployee(){
        //Arrange
        EmployeeEntity emp = new EmployeeEntity();
            //arrange for findmaxFunction stored
            when(employeeRepository.findMaxFNumber()).thenReturn(1001);
            //Arrange for Save function testing
            when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(emp);
        //Act
        EmployeeEntity saveEmp = employeeService.saveEmployee(emp); // this is how we tell the code to save

        //Assert
        assertNotNull(saveEmp);

        //telling code to accept that the below function is true
        assertTrue(saveEmp.getfNumber().startsWith("F"));
            //test code only saves once.
            verify(employeeRepository,times(1)).save(emp);
    }


    //testing if code can search code by EmpID
    @Test
    void testFindEmpID(){
        //Arrange
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmpID("EMP123");  // we set our own ID for code to use in trying to find emp number

        when(employeeRepository.findByEmpID("EMP123")).thenReturn(Optional.of(emp));

        //Act
        // we perform test action
        Optional<EmployeeEntity> result = employeeService.findByEmpID("EMP123");

        //Assert
        //tell code to assert the below so that the test can be successful
        assertTrue(result.isPresent());
        assertEquals("EMP123",result.get().getEmpID());
    }


    // test if code can delete without failing
    @Test
    void testDeleteEmployee(){
        //Arrange
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmpID("EMP123");

        when(employeeRepository.findByEmpID("EMP123")).thenReturn(Optional.of(emp));

        //Act
        boolean deleted = employeeService.deleteEmployeeByEmpID(("EMP123"));

        //Assert
        assertTrue(deleted);
            //checks if the delete function worked and how many times it has been called
            verify(employeeRepository,times(1)).delete(emp);
    }

    @Test
    void TestDeleteEmployee_UserNotExists(){
        // continuation of the delete functionality, however we are now creating a test on what should happen if user does not exist

        //Arrange
        when(employeeRepository.findByEmpID("WrongEMPID999")).thenReturn(Optional.empty());

        //act
        boolean deleted = employeeService.deleteEmployeeByEmpID("WrongEMPID999");

        //assert
        assertFalse(deleted); // check db to verify if the empID exists or not. ideally its wrong so it shouldnt exist which means the code should not do anything
            verify(employeeRepository, never()).delete(any());// because the empID does not exit, we should not attempt. here we check if the were any attempts tried. idealy there should be nothing.

    }
}

package peopleSolution.DepartmentSolution.Entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class EmployeeEntityTest {
    private Validator validator;
    @BeforeEach
    void setupValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailWhenEmpIDIsNot13Characters(){
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmpID(("123456789"));
        Set<ConstraintViolation<EmployeeEntity>> violations = validator.validate(emp);

        assertTrue(violations.stream().anyMatch(v
                -> v.getPropertyPath().toString().equals("empID")));
    }

    @Test
    void shouldPassWhenAllFieldsAreValid (){
        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmpID("1234567890123");
        emp.setEmpFName("John");
        emp.setEmpLName("Doe");
        emp.setEmpDepartment("IT");
        emp.setEmpJobTitle("IT Tester");
        emp.setEmpJobDescription("handling support");
        emp.setEmpActiveStatus(true);
        emp.setfNumber("F1234");

        Set<ConstraintViolation<EmployeeEntity>> violations = validator.validate(emp);
        assertTrue(violations.isEmpty());
    }

}


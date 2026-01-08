package peopleSolution.DepartmentSolution.Entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserCredentialsTest
{
        private Validator validator;

    @BeforeEach
    void setupValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = (Validator) factory.getValidator();
    }

    @Test
    void shouldFailWhenFNumberIsBlank(){// test for blank fnumber

        //arrange
        UserCredentials creds = new UserCredentials(); // instantiate /call UserCrendtials class so we can access its properties and assign it the name creds
        creds.setfNumber("");// blank password
        creds.setPassword("Strong password");
        creds.setRole("Admin");

        //act
        Set<ConstraintViolation<UserCredentials>> violations = validator.validate(creds);

        //assert
        assertTrue(violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("fNumber")));
    }


    @Test
    void shouldPassWhenAllFieldsAreValid(){

        //arrange
        UserCredentials creds = new UserCredentials();
        creds.setfNumber("F123");
        creds.setPassword("StrongPassword");
        creds.setRole("Admin");


        //act
        Set<ConstraintViolation<UserCredentials>> violations = validator.validate(creds);

        //assert
        assertTrue(violations.isEmpty());

    }

}

package peopleSolution.DepartmentSolution.Entities;

import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;

import javax.xml.validation.Validator;

public class UserCredentialsTest
{

    private Validator validator;

    @BeforeEach
    void setupValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = (Validator) factory.getValidator();
    }

}

package peopleSolution.DepartmentSolution.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class EmployeeEntity {


    // Default constructor for JPA
    public EmployeeEntity() {
    }

    // EMPLOYEE ID NUMBER
    @Id
    @Size(min = 13, max = 13, message = "ID must be exactly 13 characters long")
    private String empID; // Use wrapper type to avoid binding error if empty
    //EMPLOYEE FIRST NAME
    @NotBlank(message = "First name is required")
    private String empFName;
    //EMPLOYEE LAST NAME
    @NotBlank(message = "Last name is required")
    private String empLName;

    //EMPLOYEE DEPARTMENT
    @NotBlank(message = "Department is required")
    private String empDepartment;

    //EMPLOYEE JOB TITTLE
    @NotBlank(message = "Employee Job Title is required")
    private String empJobTitle;


    //EMPLOYEE JOB DESCRIPTION
    @NotBlank(message = "Job Description is required")
    private String empJobDescription;

    //EMPLOYEE ACTIVE STATUS - to be deleted.
    @NotNull(message = "Active status is required")
    private Boolean empActiveStatus;


    //profile picture path finder to allow profile pictures to apear per user.
    private String profileImagePath;

    //EMPLOYEE FNUMBER
    @Column(unique = true)
    private String fNumber;

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    public String getfNumber() {
        return fNumber;
    }

}
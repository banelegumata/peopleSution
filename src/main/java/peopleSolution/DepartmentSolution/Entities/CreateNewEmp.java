package peopleSolution.DepartmentSolution.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;


@Entity
public class CreateNewEmp {
    // Default constructor for JPA
    public CreateNewEmp() {
    }

    // EMPLOYEE ID NUMBER
    @Id
    @Size(min = 13, max = 13, message = "ID must be exactly 13 characters long")
    private String empID; // Use wrapper type to avoid binding error if empty
        public String getEmpID() {
            return empID;
        }
        public void setEmpID(String empID) {
            this.empID = empID;
        }
    //====================================


    //EMPLOYEE FIRST NAME
    @NotBlank(message = "First name is required")
    private String empFName;
        public String getEmpFName() {
            return empFName;
        }
        public void setEmpFName(String empFName) {
            this.empFName = empFName;
        }
    //====================================


    //EMPLOYEE LAST NAME
    @NotBlank(message = "Last name is required")
    private String empLName;
        public String getEmpLName() {
            return empLName;
        }
        public void setEmpLName(String empLName) {
            this.empLName = empLName;
        }
    //====================================


    //EMPLOYEE DEPARTMENT
    @NotBlank(message = "Department is required")
    private String empDepartment;
        public String getEmpDepartment() {
            return empDepartment;
        }
        public void setEmpDepartment(String empDepartment) {
            this.empDepartment = empDepartment;
        }
    //====================================


    //EMPLOYEE JOB TITTLE
    @NotBlank(message = "Employee Job Title is required")
    private String empJobTitle;
        public String getEmpJobTitle() {
            return empJobTitle;
        }
        public void setEmpJobTitle(String empJobTitle) {
            this.empJobTitle = empJobTitle;
        }
//====================================


    //EMPLOYEE JOB DESCRIPTION
    @NotBlank(message = "Job Description is required")
    private String empJobDescription;
        public String getEmpJobDescription() {
            return empJobDescription;
        }
        public void setEmpJobDescription(String empJobDescription) {
            this.empJobDescription = empJobDescription;
        }
    //====================================


    //EMPLOYEE ACTIVE STATUS - to be deleted.
    @NotNull(message = "Active status is required")
    private Boolean empActiveStatus;

        public Boolean getEmpActiveStatus() {
            return empActiveStatus;
        }
        public void setEmpActiveStatus(Boolean empActiveStatus) {
            this.empActiveStatus = empActiveStatus;
        }
    //====================================


    //EMPLOYEE FNUMBER
    @Column(unique = true)
    private String fNumber;
        public String getFNumber() {
            return fNumber;
        }
        public void setFNumber(String fNumber) {
            this.fNumber = fNumber;
        }
    //====================================
}

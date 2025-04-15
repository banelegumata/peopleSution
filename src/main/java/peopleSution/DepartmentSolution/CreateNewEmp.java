package peopleSution.DepartmentSolution;

import org.springframework.web.bind.annotation.RequestParam;

public class CreateNewEmp {
    private int empID;
    private String empFName;
    private String empLName;
    private String empDepartment;
    private String empJobTitle;
    private String empJobDescription;
    private Boolean empActiveStatus;
    // @RequestParam String empReportingManager,

    //Getters and Setters for above-mentioned variables


    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getEmpFName() {
        return empFName;
    }

    public void setEmpFName(String empFName) {
        this.empFName = empFName;
    }

    public String getEmpLName() {
        return empLName;
    }

    public void setEmpLName(String empLName) {
        this.empLName = empLName;
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment;
    }

    public String getEmpJobTitle() {
        return empJobTitle;
    }

    public void setEmpJobTitle(String empJobTitle) {
        this.empJobTitle = empJobTitle;
    }

    public String getEmpJobDescription() {
        return empJobDescription;
    }

    public void setEmpJobDescription(String empJobDescription) {
        this.empJobDescription = empJobDescription;
    }

    public Boolean getEmpActiveStatus() {
        return empActiveStatus;
    }

    public void setEmpActiveStatus(Boolean empActiveStatus) {
        this.empActiveStatus = empActiveStatus;
    }

    @Override
    public String toString() {
        return "CreateNewEmp{" +
                "empID=" + empID +
                ", empFName='" + empFName + '\'' +
                ", empLName='" + empLName + '\'' +
                ", empDepartment='" + empDepartment + '\'' +
                ", empJobTitle='" + empJobTitle + '\'' +
                ", empJobDescription='" + empJobDescription + '\'' +
                ", empActiveStatus=" + empActiveStatus +
                '}';
    }
}

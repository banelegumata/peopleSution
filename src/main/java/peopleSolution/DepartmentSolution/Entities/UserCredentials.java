package peopleSolution.DepartmentSolution.Entities;

import jakarta.persistence.*;

@Entity
public class UserCredentials {
    // Row ID for each user auto generates
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }
    //=====================================


    // FNumber as unique USERNAME for each user
    @Column(name = "fNumber", unique = true, nullable = false)
    private String fNumber;
        public String getfNumber() {
            return fNumber;
        }
        public void setfNumber(String id) {
            this.fNumber = fNumber;
        }

    //=====================================
    @Column(nullable = false)
    private String password;
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    //=====================================

    @Column(nullable = false)
    private String role;
        public String getRole() {
            return role;
        }
        public void setRole(String role) {
            this.role = role;
        }
    //=====================================

    //creates a link to the employee table by fNumber
    @OneToOne
    @JoinColumn(name = "fNumber", referencedColumnName = "fNumber", insertable = false, updatable = false)
    private EmployeeEntity employee;

    // constructors
    public UserCredentials() {}
    public UserCredentials(String fNumber, String password, String role) {
        this.fNumber = fNumber;
        this.password = password;
        this.role = role;
    }
        public EmployeeEntity getEmployee() {
            return employee;
        }

        public void setEmployee(EmployeeEntity employee) {
            this.employee = employee;
        }
    //=====================================
}

package peopleSolution.DepartmentSolution.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import peopleSolution.DepartmentSolution.Entities.CreateNewEmp;

import java.time.LocalDateTime;

import static java.time.LocalTime.now;

@Controller
public class HomeController {

    // views mapping.

    // Jump to index page from anywhere.
    @GetMapping({"/", "/index"})
    public String home() {
        System.out.println("home method called time: " + now());
        return "index";
    }

    // Jump to login page
    @GetMapping("/login")
    public String showLoginPage() {
        System.out.println("Log in page called at: " + LocalDateTime.now());
        return "login";
    }



    // jump to dashboard
    @GetMapping("/dashboard")
    public String showDashboardPage (Model model){
        System.out.println("dashboard page called from controller " + LocalDateTime.now());
        return "dashboard";
    }


    /** ===================================================
     *  manage employee controller section **/

    // Jump to Manage Users
    @GetMapping("/ManageUsers")
    public String showManageUsersPage (Model model){
        System.out.println("ManageUsers page called from controller " + LocalDateTime.now());
        return "ManageUsers";
    }


    //Jump to for Employee search Page
    @GetMapping("/search")
    public String showSearchPage() {
        System.out.println("search page called at: " + LocalDateTime.now());
        return "search";
    }

    // Jump to uploadNewEmployee
    @GetMapping("/uploadNewEmployee")
    public String showUploadForm(Model model) {
        model.addAttribute("employee", new CreateNewEmp());
        System.out.println("uploadNewEmployee form requested at: " + LocalDateTime.now());
        return "uploadNewEmployee";
    }

    @GetMapping("/ViewAllUsers")
    public String showViewAllUsersPage() {
        System.out.println("ViewAllUsers page called at: " + LocalDateTime.now());
        return "ViewAllUsers";
    }

    /** ===================================================
     * Manage Departments controller section **/

    //Jump to Manage Departments
    @GetMapping("/ManageDepartments")
    public String showManageDepartmentsPage (Model model){
        System.out.println("Manage Departments page called from controller " + LocalDateTime.now());
        return "ManageDepartments";
    }

    // jump to create New department page
    @GetMapping("/uploadNewDepartment")
    public String showuploadNewDepartmentPage (Model model) {
        System.out.println("uploadNewDepartment page called from controller " + LocalDateTime.now());
        return "uploadNewDepartment";
    }

    // Jump to view all departments page
    @GetMapping("/ViewAllDepartments")
    public String showViewAllDepartmentsPage (Model model) {
        System.out.println("ViewAllDepartments page called from controller " + LocalDateTime.now());
        return "ViewAllDepartments";
    }
}

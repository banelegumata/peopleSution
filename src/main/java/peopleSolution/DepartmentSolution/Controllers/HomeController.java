package peopleSolution.DepartmentSolution.Controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import peopleSolution.DepartmentSolution.Entities.EmployeeEntity;

import java.time.LocalDateTime;

import static java.time.LocalTime.now;
@Slf4j
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
    public String showDashboardPage (Model model,HttpSession session){
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            System.out.println("session checker: redirecting has user has not logged in. " + LocalDateTime.now());
            return "redirect:/login";
        }
        System.out.println("session check passed" + LocalDateTime.now());

        //code continues since session check was successful
        System.out.println("dashboard page called from controller " + LocalDateTime.now());
        return "dashboard";
    }


    /** ===================================================
     *  manage employee controller section **/

    // Jump to Manage Users
    @GetMapping("/ManageUsers")
    public String showManageUsersPage (Model model,HttpSession session){
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            System.out.println("session checker: redirecting has user has not logged in. " + LocalDateTime.now());
            return "redirect:/login";
        }
        System.out.println("session check passed" + LocalDateTime.now());

        //code continues since session check was successful
        System.out.println("ManageUsers page called from controller " + LocalDateTime.now());
        return "ManageUsers";
    }


    //Jump to for Employee search Page
    @GetMapping("/search")
    public String showSearchPage(HttpSession session, Model model) {
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            System.out.println("session checker: redirecting has user has not logged in. " + LocalDateTime.now());
            return "redirect:/login";
        }
        System.out.println("session check passed: " + LocalDateTime.now());

        model.addAttribute("employee", new EmployeeEntity());

        //code continues since session check was successful
        System.out.println("search page called at: " + LocalDateTime.now());
        return "search";
    }

    // Jump to uploadNewEmployee
    @GetMapping("/uploadNewEmployee")
    public String showUploadForm(HttpSession session, Model model) {
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            System.out.println("session checker: redirecting has user has not logged in. " + LocalDateTime.now());
            return "redirect:/login";
        }
        System.out.println("session check passed: " + LocalDateTime.now());

        //code continues since session check was successful
        model.addAttribute("employee", new EmployeeEntity());
        System.out.println("uploadNewEmployee form requested at: " + LocalDateTime.now());
        return "uploadNewEmployee";
    }

    /** ===================================================
     * Manage Departments controller section **/

    //Jump to Manage Departments
    @GetMapping("/ManageDepartments")
    public String showManageDepartmentsPage (HttpSession session, Model model){
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            System.out.println("session checker: redirecting has user has not logged in. " + LocalDateTime.now());
            return "redirect:/login";
        }
        System.out.println("session check passed: " + LocalDateTime.now());

        //code continues since session check was successful
        System.out.println("Manage Departments page called from controller " + LocalDateTime.now());
        return "ManageDepartments";
    }

    // jump to create New department page
    @GetMapping("/uploadNewDepartment")
    public String showuploadNewDepartmentPage (HttpSession session, Model model) {
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            System.out.println("session checker: redirecting has user has not logged in. " + LocalDateTime.now());
            return "redirect:/login";
        }
        System.out.println("session check passed: " + LocalDateTime.now());

        //code continues since session check was successful
        System.out.println("uploadNewDepartment page called from controller " + LocalDateTime.now());
        return "uploadNewDepartment";
    }

    // Jump to view all departments page
    @GetMapping("/ViewAllDepartments")
    public String showViewAllDepartmentsPage (HttpSession session, Model model) {
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            System.out.println("session checker: redirecting has user has not logged in. " + LocalDateTime.now());
            return "redirect:/login";
        }
        System.out.println("session check passed: " + LocalDateTime.now());

        //code continues since session check was successful
        System.out.println("ViewAllDepartments page called from controller " + LocalDateTime.now());
        return "ViewAllDepartments";
    }
}

package peopleSolution.DepartmentSolution.Controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import peopleSolution.DepartmentSolution.Entities.EmployeeEntity;
import peopleSolution.DepartmentSolution.ServiceLayer.EmployeeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
//@NoArgsConstructor
@RequestMapping("/manage-employee")
public class EmployeeManagementController {
    // deals with onboarding new users

    private final EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public String addEmployee(HttpSession session,
            @Valid @ModelAttribute("employee") EmployeeEntity createNewEmp,BindingResult bindingResult, Model model) {
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            log.warn("session checker: redirecting has user has not logged in. {}", LocalDateTime.now());
            return "redirect:/login";
        }
        log.info("session check passed {}", LocalDateTime.now());

        //code continues since session check was successful
        log.info("addEmployee POST request received at: {}", LocalDateTime.now());

        // ✅ Validation check
        if (bindingResult.hasErrors()) {
            if (createNewEmp.getfNumber() == null) {
                createNewEmp.setfNumber("");
            }
            model.addAttribute("message", "Please fix the errors in the form.");
            return "uploadNewEmployee"; // Return the form view to let user fix errors
        }

        // check if user exists on DB using the supplied emp ID Number
        Optional<EmployeeEntity> existingEmployee = employeeService.findByEmpID(createNewEmp.getEmpID());
        if (existingEmployee.isPresent()) {
            model.addAttribute("message", "Employee with this ID already exists.");
            model.addAttribute("employee", existingEmployee.get()); // show existing employee
            log.warn("employee already exists");
            return "userExists";
        }
        // Save To DB
        EmployeeEntity savedUser = employeeService.saveEmployee(createNewEmp);

        /* Add a success message or the saved user to the model
            - These will be used as referencing that hold the information
                to display on the view pages */
        model.addAttribute("message", "Employee saved successfully!");
        model.addAttribute("employee", savedUser);


        log.info("new user uploaded : {}", LocalDateTime.now());
        // Return the JSP page to show the result
        return "NewUserUpload-Success";
    }
    // onboarding new user function ends here...

    //View all employees using view employees button

    @GetMapping("/ViewAllUsers")
    public String viewAllUsers(@RequestParam(value = "error", required = false) String error, Model model,HttpSession session) {
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            log.warn("session checker: redirecting has user has not logged in. {}", LocalDateTime.now());
            return "redirect:/login";
        }
        System.out.println("session check passed" + LocalDateTime.now());

        // code continues since session check was successful
        List<EmployeeEntity> employees = employeeService.getAllEmployees();

        if (employees != null && !employees.isEmpty()) {
            model.addAttribute("employees", employees);
        }

        model.addAttribute("error", error);
        return "ViewAllUsers";
    }

    //searching for employees
    @PostMapping("/searchForEmp")
    public String searchEmployee(@RequestParam("empFName") String keyword, Model model,HttpSession session){
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            log.info("session checker: redirecting has user has not logged in. " + LocalDateTime.now());
            return "redirect:/login";
        }
        log.info("session check passed ✅ ", LocalDateTime.now());

        //code continues since session check was successful
        log.info("search user functionality called ✅ : ", LocalDateTime.now());

        log.info("Searching by : {} ", keyword);

        // Try searching by fNumber first

        if (keyword != null && !keyword.trim().isEmpty()){
            //search using fnumber
                Optional<EmployeeEntity> byfNumber =  employeeService.findByfNumber(keyword);
                if (byfNumber.isPresent()){
                    model.addAttribute("results", List.of(byfNumber.get()));
                    log.info("found by FNUMBER {}", byfNumber);
                    return "searchResults";
                }
            //Search using first name
                List<EmployeeEntity> resultsByFirstName = employeeService.searchByFName(keyword);
                if(!resultsByFirstName.isEmpty()){
                    model.addAttribute("results", resultsByFirstName);
                    log.info("found by First Name {}", resultsByFirstName);
                    return "searchResults";
                }
        }
        log.error("no results found on both fnumber and first name");
        model.addAttribute("message", "No matching results");
        return "searchResults";
    }


    // Jumps to view single user page to display a single user.
    @PostMapping("/viewUser")
    public String viewUser(@RequestParam("empID") String empID, Model model, HttpSession session) {
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            log.warn("session checker: redirecting has user has not logged in.{} ",LocalDateTime.now());
            return "redirect:/login";
        }
        System.out.println("session check passed" + LocalDateTime.now());

        //code continues since session check was successful

        log.info("searching Emp ID to view single user: " +empID + " "+ LocalDateTime.now());
        Optional<EmployeeEntity> emp = employeeService.findByEmpID(empID);
        if (emp.isPresent()) {
            model.addAttribute("employee", emp.get());
            log.info("Found  user ID with Emp ID: {}, time {}", empID, LocalDateTime.now());
            return "ViewSingleUser";
        } else {
            // handle not found
            log.error("Could not find user ID with Emp ID " +empID );
            return "redirect:/ViewAllUsers?error=notfound";
        }
    }

    // user editing handler
    @PostMapping("/editUser")
    public String showEditForm(@RequestParam("empID")String empID, Model model, HttpSession session){
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            log.warn("session checker: redirecting has user has not logged in. " + LocalDateTime.now());
            return "redirect:/login";
        }
        log.info("session check passed" + LocalDateTime.now());

        //code continues since session check was successful

        log.info("reached Fetch employee code");

        Optional<EmployeeEntity> empFoundOnDB  = employeeService.findByEmpID(empID);
        if (empFoundOnDB.isPresent())
        {
            log.warn("search attempted and found employee using EmpID");
            model.addAttribute("employee", empFoundOnDB.get());
            return "editUser";
        }else {
            log.error("search failed, could not find employee using EmpID");
            model.addAttribute("message","Emp ID could not be found, therefore no employee to edit");
            return "redirect:/manage-employee/ViewAllUsers?error=notfound";
        }
    }

    // update user

    @PostMapping("updateUser")
    public String updateEmployee(@ModelAttribute ("employee") EmployeeEntity employee, HttpSession session, Model model){
        // check for log in session
        if (session.getAttribute("loggedInUser") == null) {
            log.warn("session checker: redirecting has user has not logged in. {}", LocalDateTime.now());
            return "redirect:/login";
        }
        log.info("session check passed {}" , LocalDateTime.now());

        //code continues since session check was successful

        log.info("now reached UPDATE section");

        log.warn("Updating employee with fNumber: {} time: {}" , employee.getfNumber(), LocalDateTime.now());
        employeeService.updateEmployee(employee);
        model.addAttribute("Message", "Employee details updated Successfully.");
        model.addAttribute("employee", employee);
        return "ViewSingleUser";
    }
        // delete user
    @PostMapping("/deleteEmployee")
    public  String deleteEmployee(@RequestParam("empID") String empID, HttpSession session, Model model){
        //Session Checker
        if (session.getAttribute("loggedInUser") == null) {
            log.warn("session checker: redirecting as user has not logged in. {}", LocalDateTime.now());
            return "redirect:/login";
        }
        log.info("session check passed ✅ {} ", LocalDateTime.now());
        log.warn("Delete functionality reached, Attempting to delete employee with ID: {}.. time: {}" ,empID , LocalDateTime.now());

        // Delete function logic

        // requests DB to delete then stores the transaction state after attempting.
        boolean deleted = employeeService.deleteEmployeeByEmpID(empID);

        // below confirms the above and displays message based on what happened.
        if (deleted){
            // if successful we send this message jsp
            log.info("successfully deleted user {}",LocalDateTime.now());
            model.addAttribute("message", "Employee Deleted Successfully");
        }else {
            // if failed we send this message jsp
            log.warn("failed to delete user from db {}" ,LocalDateTime.now());
            model.addAttribute("message", "Failed to delete user as user was not found on the db");
        }

        // Redirect to view all users after deletion
        return "redirect:/manage-employee/ViewAllUsers";

    }

}

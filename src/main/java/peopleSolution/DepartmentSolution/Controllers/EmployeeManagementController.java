package peopleSolution.DepartmentSolution.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import peopleSolution.DepartmentSolution.Entities.CreateNewEmp;
import peopleSolution.DepartmentSolution.ServiceLayer.EmployeeService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Controller
public class EmployeeManagementController {
    @Autowired
    private final EmployeeService employeeService;

    //constructor for the above final EmployeeRepository
    public EmployeeManagementController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmployee")
    public String addEmployee(
            @Valid @ModelAttribute("employee") CreateNewEmp createNewEmp,
            BindingResult bindingResult, Model model) {
        System.out.println("addEmployee POST request received at: "
                + LocalDateTime.now());

        // ✅ Validation check
        if (bindingResult.hasErrors()) {
            if (createNewEmp.getFNumber() == null) {
                createNewEmp.setFNumber("");
            }
            model.addAttribute("message", "Please fix the errors in the form.");
            return "uploadNewEmployee"; // Return the form view to let user fix errors
        }

        // check if user exists on DB using the supplied emp ID Number
        Optional<CreateNewEmp> existingEmployee = employeeService.findByEmpID(createNewEmp.getEmpID());
        if (existingEmployee.isPresent()) {
            model.addAttribute("message", "Employee with this ID already exists.");
            model.addAttribute("employee", existingEmployee.get()); // show existing employee
            System.out.println("employee already exists");
            return "userExists";
        }
        // Save To DB
        CreateNewEmp savedUser = employeeService.saveEmployee(createNewEmp);

        /* Add a success message or the saved user to the model
            - These will be used as referencings that hold the information
                to display on the view pages */
        model.addAttribute("message", "Employee saved successfully!");
        model.addAttribute("employee", savedUser);


        System.out.println("new user uploaded : " + LocalDateTime.now());
        // Return the JSP page to show the result
        return "NewUserUpload-Success";
    }

    @PostMapping("/searchForEmp")
    public String searchEmployee(@RequestParam("keyword") String keyword, Model model){

        System.out.println("search user functionality called  : " + LocalDateTime.now());

        List<CreateNewEmp> results = employeeService.searchByFName(keyword);

        if (results.isEmpty()){
            model.addAttribute("message", "no matching results");
            System.out.println(" We are here, No results found: " + results);
        }else {
            model.addAttribute("results", results);
            System.out.println("we are here. See results found: "+results);
        }
        // jsp page to display results.
        return "searchResults";
    }

}

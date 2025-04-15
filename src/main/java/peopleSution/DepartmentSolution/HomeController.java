package peopleSution.DepartmentSolution;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static java.time.LocalTime.now;

@Controller
public class HomeController {// responsible for calling home page
    @RequestMapping({"/","index"})
    public String home(){
        System.out.println("home method called time:"+ now());
        return "index";
    }

//    @RequestMapping("add")// handlings mapping between webbrowser and servlet
//    public ModelAndView add( @RequestParam int num1, @RequestParam int num2, ModelAndView mv){
//        // ^ @RequestParam tag helps spring map values from jsp page to values in java code
//
//        System.out.println("add page called time:"+ now());
//        int result = num1 + num2;
//        mv.addObject("result", result);
//        mv.setViewName("result");
//        return mv;
//    }

    @RequestMapping("addEmployee")// handlings mapping between webbrowser and servlet
    public String addEmployee(@ModelAttribute("result") CreateNewEmp createNewEmp){
            System.out.println("add employee controller called time:"+ now());

            return "result";
            //created new object to use
//                CreateNewEmp createNewEmp = new CreateNewEmp();
//
//                //setting values for above-mentioned object
//                createNewEmp.setEmpID(empID);
//                createNewEmp.setEmpFName(empFName);
//                createNewEmp.setEmpLName(empLName);
//                createNewEmp.setEmpDepartment(empDepartment);
//                createNewEmp.setEmpJobTitle(empJobTitle);
//                createNewEmp.setEmpDepartment(empJobDescription);
//                createNewEmp.setEmpActiveStatus(Boolean.valueOf(empActiveStatus));


            // returning data back to client to view browser using Model And View (MV)
//                mv.addObject("result", createNewEmp);
//                mv.setViewName("result");
        }
        @RequestMapping("uploadNewEmployee")
        public String uploadNewEmployee(){
        return "uploadNewEmployee";
        }

}

package peopleSution.DepartmentSolution;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {// responsible for calling home page
    @RequestMapping("/")
    public String home(){
        System.out.println("home method called");
        return "index";
    }

    @RequestMapping("add")// handlings mapping between webbrowser and servlet
    public String add( @RequestParam int num1, @RequestParam int num2, Model model){
        // ^ @RequestParam tag helps spring map values from jsp page to values in java code

        //   System.out.println("add page called");
        int result = num1 + num2;
        model.addAttribute("result", result);
        return "result";
    }
}

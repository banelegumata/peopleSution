package peopleSolution.DepartmentSolution.Controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import peopleSolution.DepartmentSolution.Entities.UserCredentials;
import peopleSolution.DepartmentSolution.ServiceLayer.UserLoginService;

import java.time.LocalDateTime;
import java.util.Optional;
@Slf4j
@Controller
public class LoginController {
     @Autowired
    private UserLoginService loginService;

    @PostMapping("/loginUserIn")// collect information from login form
    public String login(@RequestParam String fNumber,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        log.info("Received information from log in form to search {}", LocalDateTime.now());

        //check user credentials table to see if user exists
        Optional<UserCredentials> validUser = loginService.validateLogin(fNumber, password);

        // log user in if information exists
        if (validUser.isPresent()) {
            session.setAttribute("loggedInUser", validUser.get());
            session.setAttribute("role", validUser.get().getRole());
            log.info("user found, now logging in {} at {}", fNumber, LocalDateTime.now());
            return "redirect:/dashboard"; // Replace with your actual landing page
        }
        // invalid credentials user should try again
        model.addAttribute("error", "Invalid credentials, please try again!");
        log.warn("user NOT found {}", LocalDateTime.now());
        return "login";// redirect to login page
    }
    // when user clicks on log out.
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        log.info("user clicked logout {}", LocalDateTime.now());
        return "redirect:/login";
    }
}

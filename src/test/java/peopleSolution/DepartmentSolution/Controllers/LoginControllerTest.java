package peopleSolution.DepartmentSolution.Controllers;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import peopleSolution.DepartmentSolution.Entities.UserCredentials;
import peopleSolution.DepartmentSolution.ServiceLayer.UserLoginService;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(LoginController.class)
class LoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserLoginService loginService;

    @Test
    void shouldLoginSuccessfully() throws Exception{

        //arrange
        UserCredentials mockUser = new UserCredentials();
        mockUser.setfNumber("1234");
        mockUser.setPassword("pass");
        mockUser.setRole("Admin");

        //act
        Mockito.when(loginService.validateLogin("123", "pass")).thenReturn(Optional.of(mockUser));
        mockMvc.perform(post("/loginUserIn")
                        .param("fNumber", "123")
                        .param("password", "pass"))

        //assert
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));

    }
    @Test
    void shouldLoginWithInvalidCredentials() throws Exception
    {
        Mockito.when(loginService.validateLogin("wrongFNumber", "wrongPass")).thenReturn(Optional.empty());
        mockMvc.perform((post("/loginUserIn"))
                .param("fNumber","wrongFNumber")
                .param("password", "wrongPass"))
                .andExpect(status().isOk()) // should stay on Login page
                .andExpect(view().name("login")) //confirms log in page is returned
                .andExpect(model().attributeExists("error"));//error message shown
    }

    @Test
    void shouldLogoutSuccessfully() throws Exception{
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

}

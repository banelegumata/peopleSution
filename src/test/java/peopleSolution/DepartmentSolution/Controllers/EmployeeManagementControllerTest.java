package peopleSolution.DepartmentSolution.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import peopleSolution.DepartmentSolution.Entities.EmployeeEntity;
import peopleSolution.DepartmentSolution.ServiceLayer.EmployeeService;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class EmployeeManagementControllerTest {
   // @WebMvcTest(controllers = EmployeeManagementController.class)

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeManagementController controller;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        // Build MockMvc around the controller directly
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Nested
    class EmployeeControllerTest{


        // testing no session, this should redirect to login page if no session exits

        @Test
        @DisplayName("POST /addEmployee without session should redirect to /login")

        void addEnployee_no_session_redirectsToLogin() throws Exception {
        /** Arrange **/
            EmployeeEntity employee = new EmployeeEntity();
            employee.setEmpID("e001");

        /** Act **/
            mockMvc.perform(
                            post("manage-employee/addEmployee").flashAttr("employee", employee))
        /** Assert **/
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/login"));
        }

        // testing binding errors
        @Test
        @DisplayName("POST /addEmployee with binding error should return uploadEmployee view")

        void addEmployee_withBindingError_returnsUploadEmloyee() throws Exception{
            // simulate logged - in session
        /** Act **/
            mockMvc.perform
                (
            post("/addEmployee")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .sessionAttr("loggedInUser", "adminUser")
                    .param("empID", "")
                    .param("fNumber", "")
                )
        /** Assert **/
                .andExpect(status().isOk())
                .andExpect(view().name("uploadNewEmployee"))
                .andExpect(model().attributeExists("message"))

                .andExpect(model().attributeHasFieldErrors("employee","empID"));
        }

        // testing for existing employee.

        @Test
        @DisplayName("POST /addEmployee where employee exists should return userExist view")
        void addEmployee_existingEmployee_returnsUserExist () throws Exception{
        /** Arrange **/
            EmployeeEntity existing = new EmployeeEntity();
            existing.setEmpID("0000000000001");
            existing.setfNumber("F0002");

            when(employeeService.findByEmpID("0000000000001")).thenReturn(Optional.of(existing));

        /** Act **/
            mockMvc.perform(
        post("/addEmployee")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .sessionAttr("loggedInUser", "adminUser")
                .param("empID", "0000000000001")
                .param("fNumber", "F0002")

                )
        /** Assert **/
                .andExpect(status().isOk())
                .andExpect(view().name("userExists"))
                .andExpect(model().attributeExists("message"))
                .andExpect(model().attributeExists("employee"));

            verify(employeeService, times(1)).findByEmpID("0000000000001");
            verifyNoMoreInteractions(employeeService);
        }

        //Happy path -> save and return NewUserUpload-Success with message & saved employee

        @Test
        @DisplayName("POST /addEmployee with valid data should save and return success view")
        void addEmployee_valid_saveAndReturnSuccess() throws Exception{
        /** Arrange **/
            // No existing Employee
            when(employeeService.findByEmpID("0000000000001")).thenReturn(Optional.empty());
                EmployeeEntity saved  = new EmployeeEntity();
                saved .setEmpID("00000000000001");
                saved .setfNumber("F0002");

                when(employeeService.saveEmployee(any(EmployeeEntity.class))).thenReturn(saved);

        /** Act **/
                    mockMvc.perform
                    (post("/addEmployee")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .sessionAttr("loggedInUser", "adminUser")
                            .param("empID", "0000000000001")
                            .param("fNumber", "F0002")
                    )
        /** Assert **/
                            .andExpect(status().isOk())
                            .andExpect(view().name("NewUserUpload-Success"))
                            .andExpect(model().attributeExists("message"))
                            .andExpect(model().attributeExists("employee"));

                verify(employeeService, times(1)).findByEmpID("0000000000001");
                verify(employeeService, times(1)).saveEmployee(any(EmployeeEntity.class));
                verifyNoMoreInteractions(employeeService);





        }
    }

    @Nested
    class ViewAllUsersTests{

        @Autowired
        private MockMvc mockMvc;

        // Testing redirect when no user is logged in
        @Test
        @DisplayName("Redirects to /login when not logged in")
        void redirectToLogin_whenNoSession() throws  Exception {

            /** act **/
            mockMvc.perform(get("/manage-employee/ViewAllUsers"))

            /** assert **/
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/login"));

        }


        // testing rendering when employee list is not empty
        @Test
        @DisplayName("Renders ViewAllUsers and includes employee when list is not empy")
        void rendersView_withEmployees() throws Exception{
            /** arrange **/
            EmployeeEntity e1 = new EmployeeEntity();
               e1.setEmpID("0000000000001");
            EmployeeEntity e2 = new EmployeeEntity();
                e2.setEmpID("0000000000002");

            Mockito.when(employeeService.getAllEmployees()).thenReturn(List.of(e1,e2));

            /** act **/
            mockMvc.perform(get("/manage-employee/ViewAllUsers")
                    .sessionAttr("loggedInUser", "Luthando"))

                    /** assert **/
                    .andExpect(status().isOk())
                    .andExpect(view().name("ViewAllUsers"))
                    .andExpect(model().attributeExists("employee"))
                    .andExpect(model().attributeDoesNotExist("error"));

        }
            // Renders ViewAllUsers without employees attribute when list is empty
        @Test
        @DisplayName("Renders ViewAllUsers without employees attribute when list is empty")
        void rendersView_withoutEmployees_whenEmptyList() throws  Exception{
            /** Act **/
            mockMvc.perform(get("/manage-employee/ViewAllUsers")
                    .sessionAttr("loggedInUser", "Luthando"))

                    /** assert **/
                    .andExpect(status().isOk())
                    .andExpect(view().name("ViewAllUsers"))
                    .andExpect(model().attributeDoesNotExist("employee"))
                    .andExpect(model().attributeDoesNotExist("error"));
        }
        //Renders ViewAllUsers with employee list and includes error
        @Test
        @DisplayName("Renders ViewAllUsers with employee list and includes error")
        void includesErrorAttribute_whenProvided() throws Exception{

            /** arrange **/
            Mockito.when(employeeService.getAllEmployees()).thenReturn(List.of());

            /** act **/
            mockMvc.perform(get("/manage-employee/ViewAllUsers")
                    .sessionAttr("loggedInUser", "Luthando")
                    .param("error", "something went wrong"))

            /** assert **/
                    .andExpect(status().isOk())
                    .andExpect(view().name("ViewAllUsers"))
                    .andExpect(model().attributeDoesNotExist("employee"))
                    .andExpect(model().attribute("error","something went wrong"));
        }
    }
}


package peopleSolution.DepartmentSolution.Repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import peopleSolution.DepartmentSolution.Entities.EmployeeEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest (showSql = true)
public class EmployeeRepositoryTest {

// injecting repositories that we want to test

    @Autowired
    private EmployeeRepository employeeRepository;

    private EmployeeEntity employeeEntity;

/** below can count as arrange as we are preparing the date to test, then after each method
     we test will contain most likely act and assert stage for all AAA **/

    @BeforeEach
    void setUp(){

        employeeEntity = new EmployeeEntity();

        employeeEntity.setEmpID("0000000000001");
        employeeEntity.setfNumber("F123");
        employeeEntity.setEmpFName("banele");
        employeeEntity.setEmpDepartment("IT");
        employeeEntity.setEmpActiveStatus(true);
        employeeEntity.setEmpLName("gumata");
        employeeEntity.setEmpJobTitle("Dev");
        employeeEntity.setEmpJobDescription("Best to ever do it");

        employeeRepository.save(employeeEntity);
    }

/** Testing FNumber **/
    // tests existing fNumber
    @Test
    void givenEmpID_whenFindByEmpID_thenEmployeeIsFound(){

        //act
        Optional<EmployeeEntity> found = employeeRepository.findByEmpID("0000000000001");

        // assert
        assertTrue(found.isPresent());
        assertEquals("banele", found.get().getEmpFName() , found.get().getEmpFName());
    }

    @Test
    void givenEmpID_whenFindByEmpID_thenEmployeeNotFound(){
        //act
        Optional<EmployeeEntity> notFound = employeeRepository.findByEmpID("1111111111111");
        assertTrue(notFound.isEmpty());
    }

    @Test
    void givenNullEmpID_whenFindByEmpID_thenThrowsException(){
        //act
        Optional <EmployeeEntity> results = employeeRepository.findByEmpID(null);
        //assert
        assertTrue(results.isEmpty());
    }


/** Testing findByEmpFNameContainingIgnoreCase **/

    @Test
    void givenEmpFName_whenfindByEmpFNameContainingIgnoreCase_thenFoundEmployee (){

        //act
        List<EmployeeEntity> results = employeeRepository.findByEmpFNameContainingIgnoreCase("banele");

        //assert
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getEmpFName()).isEqualTo("banele");
    }

    @Test
    void givenWrongEmpName_whenFindByEmpFNameContainingIgnoreCase_thenNoEmployeeFound(){
        //act
        List<EmployeeEntity> results = employeeRepository.findByEmpFNameContainingIgnoreCase("wrongname");

        //assert
        assertThat(results).isEmpty();
    }

    @Test
    void givenUpperCaseEmpFName_whenFindByEmpFNameContainingIgnoreCase_thenFoundEmployee(){
        List<EmployeeEntity> results = employeeRepository.findByEmpFNameContainingIgnoreCase("BANELE");

        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getEmpFName()).isEqualTo("banele");
    }

    @Test
    void givenMixedCaseEmpFName_whenFindByEmpFNameContainingIgnoreCase_thenFoundEmployee(){
        List<EmployeeEntity> results = employeeRepository.findByEmpFNameContainingIgnoreCase("BaNeLe");

        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getEmpFName()).isEqualTo("banele");
    }


    @Test
    void givenPartialMatchEmpFName_whenFindByEmpFNameContainingIgnoreCase_thenFound(){
        List<EmployeeEntity> results = employeeRepository.findByEmpFNameContainingIgnoreCase("ban");

        assertThat(results).isNotEmpty();
    }

    @Test
    void givenSpecialCharactorsonEmpFName_whenFindByEmpFNameContainingIgnoreCase_thenEmployeeNotFound(){
        List<EmployeeEntity> results = employeeRepository.findByEmpFNameContainingIgnoreCase("B@nele");

        assertThat(results).isEmpty();
    }

 /** Testing findByfNumberIgnoreCase **/

    @Test
    void givenEmpfNumber_whenfNumberContainingIgnoreCase_thenEmployeeFNumberfound() {
        Optional<EmployeeEntity> results = employeeRepository.findByfNumberIgnoreCase("F123");

        assertThat(results).isNotEmpty();
        assertThat(results.get().getfNumber()).isEqualTo("F123");
    }



    /** making sure we delete all the changes we made on the DB **/
    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }
}
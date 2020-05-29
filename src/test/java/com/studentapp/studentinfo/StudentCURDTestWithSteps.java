package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Jay
 */

//@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentCURDTestWithSteps extends TestBase {


    static String firstName = "PrimeUser" + TestUtils.getRandomValue();
    static String lastName = "Testing" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String programme = "API Testing";
    static int studentId;          // for test method 003

    @Steps
    StudentSteps studentSteps;

    @Title("This test will create a new student")
    @Test
    public void test001() {
        List<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("API");
        studentSteps.createStudent(firstName, lastName, email, programme, courses)
                .statusCode(201);

//      Alternately, use
//      ValidatableResponse response = studentSteps.createStudent(firstName, lastName,email, programme, courses);
//        response.statusCode(201);
    }

    @Title("Verify if student was added to the application ")
    @Test
    public void test002() {

        HashMap<String, Object> value = studentSteps.getStudentInfoByFirstName(firstName);
        assertThat(value, hasValue(firstName));
        studentId = (int) value.get("id");

    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {

        firstName = firstName + "_Updated";

        List<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("API");
        studentSteps.updateStudent(studentId, firstName, lastName, email, programme, courses).statusCode(200);
        HashMap<String, Object> value = studentSteps.getStudentInfoByFirstName(firstName);
        System.out.println(value);
        assertThat(value, hasValue(firstName));

    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {

        studentSteps.deleteStudent(studentId).statusCode(204);
        studentSteps.getStudentById(studentId).statusCode(404);
        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/" + studentId)
                .then()
                .statusCode(404);

    }
}

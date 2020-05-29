package com.studentapp.studentinfo;

import com.studentapp.model.StudentPojo;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
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

// Run the methods in a fixed order.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentCURDTest extends TestBase {

    static String firstName = "PrimeUser" + TestUtils.getRandomValue();
    static String lastName = "Testing" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static String programme = "API Testing";

    static int studentId;          // for test method 003

    @Title("This test will create a new student")
    @Test
    public void test001() {
        List<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("API");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courses);

        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(studentPojo)
                .post()
                .then().log().all().statusCode(201);
    }

    @Title("Verify if student was added to the application ")
    @Test
    public void test002() {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
 //      .path("findAll{it.firstName=='"+firstName+ "'}.get(0)");
        .path(p1 + firstName + p2);
        assertThat(value, hasValue(firstName));
        studentId = (int) value.get("id");
        // casting student id as integer
        System.out.println(value);
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003() {

        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        firstName = firstName + "_Updated";

        List<String> courses = new ArrayList<>();
        courses.add("JAVA");
        courses.add("API");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courses);

        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(studentPojo)
                .put("/" + studentId)                  // put method for update the student ID
                .then().log().all().statusCode(200);

        HashMap<String, Object> value = SerenityRest.rest()
                .given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + firstName + p2);
        System.out.println(value);
        assertThat(value, hasValue(firstName));

    }

    @Title("Delete the student and verify if the student is deleted!")
    @Test
    public void test004() {

        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/"+studentId)
                .then()
                .statusCode(204);

        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/"+studentId)
                .then()
                .statusCode(404);

    }
}

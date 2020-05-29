package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import com.studentapp.model.StudentPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jay
 */
public class StudentSteps {

    @Step("Creating student with firstName: {0}, lastName: {1}, email: {2}, programme: {3} and courses: {4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email,
                                             String programme, List<String> courses) {

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courses);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(studentPojo)
                .post()
                .then();
    }

    @Step("Getting the student information by    firstName: {0}")
    public HashMap<String, Object> getStudentInfoByFirstName(String firstName) {

            String p1 = "findAll{it.firstName=='";
            String p2 = "'}.get(0)";

        HashMap<String, Object> value = SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_STUDENTS)
                .then()
                //               .statusCode(200)
                .extract()
                .path(p1 + firstName + p2);
        return value;
    }

    @Step("Updating student information with studentID : {0}, firstName: {1}, lastName: {2}, email: {3}, programme: {4} and courses: {5}")
    public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email, String programme, List<String> courses) {

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courses);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("studentId", studentId)  // using pathParam and method variable
                .log().all()
                .when()
                .body(studentPojo)
                .put(EndPoints.UPDATE_STUDENT_BY_ID) // from constant package/Endpoints Class
                .then();
    }

    @Step("Delete Student Information by student ID :{0}")
    public ValidatableResponse deleteStudent(int studentId) {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("studentId", studentId)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();
    }

    @Step("Getting student information with student ID :{0}")
    public ValidatableResponse getStudentById(int studentID) {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("studentId", studentID)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then();
    }

    @Step("Getting all students information ")
    public ValidatableResponse getAllStudent() {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_STUDENTS)
                .then();
    }

}
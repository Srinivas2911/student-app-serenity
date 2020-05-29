package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Jay
 */

//@RunWith(SerenityRunner.class)
public class FirstSerenityTest extends TestBase {

    @Test
    public void getAllStudents() {
        // replacing RestAssured ---> by SerenityRest.rest to wrap the Rest Assured
        SerenityRest.rest()
                .given()
                .when()
                .get("/list")
                .then()
//                .log()
//                .all()
                .statusCode(200);

    }

    @Test
    public void thisIsFailingTest() {
        // replacing RestAssured.given ---> by SerenityRest.rest to wrap the Rest Assured
        SerenityRest.rest()
                .given()
                .when()
                .get("/list")
                .then()
                .statusCode(400);

    }

    @Pending            // annotation for pending a test
    @Test
    public void thisIsPendingTest() {

    }

    @Ignore            // annotation for ignoring/skipping a test
    @Test
    public void thisIsSkippedTest() {

    }

    @Test
    public void thisIsATestWithError() {
        System.out.println("This is an error " +(5/0));

    }

    @Test
    public void thisIsCompromisedTest() throws FileNotFoundException {
    File file = new File("E://file.txt");
        FileReader fr = new FileReader(file);
    }

    @Manual
    @Test
    public void thisIsManualTest(){

    }

    @Title("Test will get the information of all students from student app")
    @Test
    public void test001() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);

    }
}

package com.studentapp.studentinfo;

import com.studentapp.constants.EndPoints;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Jay
 */

/*
 * Using tags for grouping the tests like regression packs, negative packs, etc
 */
@RunWith(SerenityRunner.class)
public class TagsTest {


    @WithTag("studentfeature:NEGATIVE")
    @Title("Provide a 405 status code when incorrect HTTP method is used to access a resource")
    @Test
    public void inValidMethod() {
        SerenityRest.rest().given()
                .when()
                .post(EndPoints.GET_ALL_STUDENTS)
                .then()
                .statusCode(405)
                .log().all();

    }

    @WithTags({
            @WithTag("studentfeature:POSITIVE"),
            @WithTag("studentfeature:SMOKE")
    })

    @Test
    @Title("This test will verify if a status code of 200 is returned for GET request")
    public void verifyIfTheStatusCodeIs200() {

        SerenityRest.rest().given()
                .when()
                .get(EndPoints.GET_ALL_STUDENTS)
                .then()
                .statusCode(200)
                .log().all();
    }

  @WithTags({
          @WithTag("studentfeature:NEGATIVE"),
          @WithTag("studentfeature:SMOKE")
  })

    @Title("This test will provide an error code of 400 when user tries to access an invalid resource")
    @Test
    public void incorrectResource() {

        SerenityRest.rest().given()
                .when()
                .get("/list1233")       // checking with wrong list
                .then()
                .statusCode(400)
                .log().all();

    }

}

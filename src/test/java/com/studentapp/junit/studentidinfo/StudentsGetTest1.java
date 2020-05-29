package com.studentapp.junit.studentidinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Jay
 */

//@RunWith(SerenityRunner.class)
public class StudentsGetTest1 extends TestBase {


    //@Title("This method will get all students information")

    @Test
    public void getAllStudents() {
                SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
               .statusCode(200);
    }

  //  @Title("This method will get all students information by ID")
    @Test
    public void test01() {

    }

}

package com.studentapp.studentinfo;

import com.studentapp.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay
 */

/*
 * running multiple threads
 */
//@Concurrent(threads = "4x")
//@UseTestDataFrom("src/test/java/resources/testdata/studentinfo.csv")
//@RunWith(SerenityParameterizedRunner.class)

//Run tests from class level as data is provided from csv file path
public class CreateStudentDataDrivenTest extends TestBase {

    private String firstName;
    private String lastName;
    private String email;
    private String programme;
    private String course1;
    private String course2;

    @Steps
    StudentSteps studentSteps;

    @Title(("DataDriven Test for adding multiple Student to the application"))
    @Test
    public void createMultipleStudents() {

        List<String> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        studentSteps.createStudent(firstName, lastName, email, programme, courses);
    }
}

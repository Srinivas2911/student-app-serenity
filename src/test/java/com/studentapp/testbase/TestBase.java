package com.studentapp.testbase;

import com.studentapp.constants.Path;
import com.studentapp.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

/**
 * Created by Jay
 */
public class TestBase {

    //create member variable
    public static PropertyReader propertyReader;


    @BeforeClass
    public static void inIt() {


/**
 *      For port it is Integer, we can cast Integer to string using either using
 *         RestAssured.port = Integer.valueOf(propertyReader.getProperty("port"));
 *         RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
 */


        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.valueOf(propertyReader.getProperty("port"));
        RestAssured.basePath = Path.STUDENT_APP;
    }
}

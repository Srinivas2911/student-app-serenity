package com.studentapp.utils;

/*
Created by SP
*/

// Class similar to load property in the page object mode

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyReader {

    /* Rules for implementing singleton design pattern
     *  ---> for restrction of the object of the class to save memory
     * 1. Make the constructor private
     *2. Create a static method to get the instance
     * 3. Create a static member variable
     */

    // Declare the Propertyreader private instance variable
    // volatile keyword in member variable to avoid multi threading issue
    // Create private constrtuctor because to prevent the Instantiation of class

    private static volatile PropertyReader propInstance;

    private PropertyReader() {

    }

    /**
     * create public method to return the instance of the Property Readerclass if it is not null.
     * synchronised keyword in method to avoid multi threading
     * @return
     */

    public static synchronized PropertyReader getInstance() {
        if (propInstance == null) {
            propInstance = new PropertyReader();
        }
        return propInstance;
    }


    /**
     *
     * @param propertyName
     * @return
     *
     * this method will get the properties from the property file
     */
    public String getProperty(String propertyName) {

        Properties prop = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/propertyfile/application.properties");
            prop.load(inputStream);
            if (prop.getProperty(propertyName) != null) {
                return prop.getProperty(propertyName);
            }
        } catch (Exception e) {

            System.out.println("Property not found");
        }
        return null;
    }

}

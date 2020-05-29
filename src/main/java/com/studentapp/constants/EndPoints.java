package com.studentapp.constants;

/*
Created by SP
*/


/**
 * Class to store all the endpoints
 */

public class EndPoints {

    /**
     * Student Application end points for micro serve
     */
    public static final String GET_ALL_STUDENTS = "/list";
    public static final String GET_SINGLE_STUDENT_BY_ID = "/{studentId}";  // path parameter to send some value
    public static final String UPDATE_STUDENT_BY_ID = "/{studentId}";  // path parameter to send some value
    public static final String DELETE_STUDENT_BY_ID = "/{studentId}";  // path parameter to send some value

}

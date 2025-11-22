package com.kfh.assessment.soapmock;


import com.kfh.assessment.soapmock.ws.Course;
import com.kfh.assessment.soapmock.ws.GetCoursesRequest;
import com.kfh.assessment.soapmock.ws.GetCoursesResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;


@Endpoint
public class CourseEndpoint {

    @PayloadRoot(namespace = "http://kfh.com/education", localPart = "GetCoursesRequest")
    @ResponsePayload
    public GetCoursesResponse getCourses(@RequestPayload GetCoursesRequest request) {

        GetCoursesResponse response = new GetCoursesResponse();

        // Hardcoded courses for sample — you can replace with DB
        Course course1 = new Course();
        course1.setId(1L);
        course1.setCode("CS101");
        course1.setName("Intro to CS");
        course1.setDescription("Basics of programming");

        Course course2 = new Course();
        course2.setId(2L);
        course2.setCode("MATH201");
        course2.setName("Linear Algebra");
        course2.setDescription("Matrix theory");

        response.getCourses().add(course1);
        response.getCourses().add(course2);

        return response;
    }
}

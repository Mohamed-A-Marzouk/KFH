package com.kfh.assessment.education.soap;

import com.kfh.assessment.education.dto.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoapCourseClient {


    public List<CourseDto> getCourses() {

        List<CourseDto> courseDtos = new ArrayList<>();
        CourseDto courseDto = new  CourseDto();
        courseDto.setCode("CS101");
        courseDto.setName("Intro to CS");
        courseDto.setDescription("Basics of programming");
        courseDtos.add(courseDto);

        CourseDto courseDto2 = new  CourseDto();
        courseDto2.setCode("CS102");
        courseDto2.setName("advanced CS");
        courseDto2.setDescription("advanced programming");
        courseDtos.add(courseDto2);
        return  courseDtos;

    }


}

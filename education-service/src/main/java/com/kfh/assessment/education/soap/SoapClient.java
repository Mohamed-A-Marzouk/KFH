package com.kfh.assessment.education.soap;

import com.kfh.assessment.education.soap.client.GetCoursesResponse;
import jakarta.xml.bind.JAXBElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

@Service
public class SoapClient {

    private final WebServiceTemplate webServiceTemplate;

    @Autowired
    public SoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public GetCoursesResponse sendRequest(Object request) {
        Object result = webServiceTemplate.marshalSendAndReceive(
            "http://localhost:8085/ws",
            request
        );
        if (result instanceof JAXBElement) {
            JAXBElement<?> jaxbElement = (JAXBElement<?>) result;
            return (GetCoursesResponse) jaxbElement.getValue();
        } else {
            throw new IllegalStateException("Unexpected response type: " + result.getClass());
        }
    }
}

package com.soap.test.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;

@RestController
@RequestMapping("/soap1")
public class SOAPController {

    @PostMapping(produces = "application/xml")
    public String handleSoapRequest(HttpServletRequest request) throws IOException {
        // 요청 읽기
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println("SOAP 요청: \n" + requestBody);

        // 간단한 SOAP 응답 생성
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                "xmlns:ex=\"http://example.com/\">" +
                "<soapenv:Body>" +
                "<ex:Response>" +
                "<ex:Message>SOAP 요청을 정상적으로 받았습니다.</ex:Message>" +
                "</ex:Response>" +
                "</soapenv:Body>" +
                "</soapenv:Envelope>";
    }
}
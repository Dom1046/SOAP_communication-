package com.soap.test.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/soap")
public class SOAPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 요청 읽기
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        System.out.println("SOAP 요청: \n" + requestBody.toString());

        // 간단한 SOAP 응답 생성
        String soapResponse =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                        "xmlns:ex=\"http://example.com/\">" +
                        "<soapenv:Body>" +
                        "<ex:Response>" +
                        "<ex:Message>SOAP 요청을 정상적으로 받았습니다.</ex:Message>" +
                        "</ex:Response>" +
                        "</soapenv:Body>" +
                        "</soapenv:Envelope>";

        // 응답 설정
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(soapResponse);
        out.flush();
    }
}


package com.soap.test.wsdl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/soap/wsdl")
public class WsdlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // `resources/WEB-INF/wsdl/soap.wsdl` 파일 경로
        String wsdlPath = getServletContext().getRealPath("/WEB-INF/wsdl/soap.wsdl");

        // WSDL 파일을 읽어들여 응답
        try (FileInputStream wsdlStream = new FileInputStream(wsdlPath);
             OutputStream out = response.getOutputStream()) {

            response.setContentType("application/xml");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = wsdlStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "WSDL file not found.");
        }
    }
}


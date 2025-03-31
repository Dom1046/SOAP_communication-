package com.soap.test.client;

import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SOAPClientServlet {
    public static void main(String[] args) {
        try {
            //절대경로
//            sendSoapRequest("D:\\toyproject\\testSOAP\\src\\main\\resources\\soapfiles\\soap.xml");
            //ResourceLoader
            Path filePath = Paths.get(Objects.requireNonNull(
                    ResourceLoader.class.getClassLoader().getResource("soapfiles/soap.xml")).toURI()
            );
            sendSoapRequest(filePath.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendSoapRequest(String filePath) throws Exception {
        // 요청을 보낼 SOAP 서버의 URL
        String urlString = "http://localhost:8080/soap";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // HTTP 요청 설정
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
        connection.setRequestProperty("SOAPAction", "some-action"); // SOAPAction은 필요 시 설정

        // XML 파일을 읽어서 요청 본문으로 사용
        byte[] xmlBytes = Files.readAllBytes(Paths.get(filePath));

        // 요청 데이터 전송
        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(xmlBytes);
            outputStream.flush();
        }

        // 서버 응답 받기
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK 응답 처리
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("SOAP 응답: \n" + response);
            }
        } else {
            System.out.println("HTTP 오류 코드: " + responseCode);
        }

        // 연결 종료
        connection.disconnect();
    }
}


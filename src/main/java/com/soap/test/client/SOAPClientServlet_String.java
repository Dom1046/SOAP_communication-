package com.soap.test.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SOAPClientServlet_String {
    public static void main(String[] args) throws IOException {

        // SOAP 서버 URL
        URL url = new URL("http://localhost:8080/soap");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // HTTP 요청 설정
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
        connection.setRequestProperty("SOAPAction", "some-action"); // SOAPAction은 필요에 따라 설정

        // SOAP 요청 XML
        String soapXml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                        "xmlns:ex=\"http://example.com/\">" +
                        "<soapenv:Body>" +
                        "<ex:Request>" +
                        "<ex:Message>Hello, SOAP Server!</ex:Message>" +
                        "</ex:Request>" +
                        "</soapenv:Body>" +
                        "</soapenv:Envelope>";

        // 요청 데이터 전송
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(soapXml.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();

        // 서버 응답 받기
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK 응답 처리
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("SOAP 응답: \n" + response);
        } else {
            System.out.println("HTTP 오류 코드: " + responseCode);
        }

        // 연결 종료
        connection.disconnect();
    }
}

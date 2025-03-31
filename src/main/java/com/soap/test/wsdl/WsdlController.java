package com.soap.test.wsdl;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/wsdl")
public class WsdlController {

    @GetMapping(produces = "application/xml")
    public String getWsdl() throws IOException {
        ClassPathResource wsdlFile = new ClassPathResource("static/soap.wsdl");
        return StreamUtils.copyToString(wsdlFile.getInputStream(), StandardCharsets.UTF_8);
    }
}

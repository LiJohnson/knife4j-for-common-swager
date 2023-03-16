package com.xiaominfo.swagger.service.doc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@Controller
public class ServiceDocApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDocApplication.class, args);
    }

    @RequestMapping({"", "/"})
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/doc.html");
    }
}

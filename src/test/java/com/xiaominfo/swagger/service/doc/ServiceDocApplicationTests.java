package com.xiaominfo.swagger.service.doc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ServiceDocApplicationTests {

    @Autowired
    CustomerNacosService customerNacosService;
    @Test
    void contextLoads() {
        log.info("{}", this.customerNacosService.getAllServices());
    }
    @Test
    void testDocs() {
        log.info("{}", this.customerNacosService.getServiceInstance("public", "api-docs").getUri());
    }
}

package com.integrationTestDemo.demo.boxers;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class
)
@TestPropertySource(locations = "classpath:config/application-test.properties")
public abstract class BaseTests {

    @LocalServerPort
    protected int port;

    HttpHeaders headers = new HttpHeaders();

    TestRestTemplate restTemplate = new TestRestTemplate();

    protected String createURLWithPort(String URI) {
        return "http://localhost:" + port + "/api" + URI;
    }



}

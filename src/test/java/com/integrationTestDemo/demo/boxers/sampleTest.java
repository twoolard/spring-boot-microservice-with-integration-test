package com.integrationTestDemo.demo.boxers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import com.integrationTestDemo.demo.boxers.entity.Boxer;


@SqlGroup({

        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestSql.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestSql.sql")

})
public class sampleTest extends BaseTests {

    @Test
    public void testGetAllBoxers() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<List<Boxer>> result = restTemplate.exchange(
                createURLWithPort("/boxers"),
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Boxer>>(){});

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }


    @Test
    public void testGetBoxer() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Boxer> result = restTemplate.exchange(
                createURLWithPort("/boxers/" + 1),
                HttpMethod.GET, entity, Boxer.class);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testAddBoxer() {
        Boxer tysonFurry = new Boxer();

        tysonFurry.setFirstName("Tyson");
        tysonFurry.setLastName("Furry");
        tysonFurry.setFights("29");

        ResponseEntity<Boxer> result = restTemplate.exchange(
                createURLWithPort("/boxers/"),
                HttpMethod.POST, new HttpEntity<>(tysonFurry, headers), Boxer.class);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testUpdateBoxer() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Boxer> responseEntity = restTemplate.exchange(
                createURLWithPort("/boxers/" + 2),
                HttpMethod.GET, entity, Boxer.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Boxer mikeTyson = responseEntity.getBody();
        mikeTyson.setFights("58");


        ResponseEntity<Boxer> result = restTemplate.exchange(
                createURLWithPort("/boxers/"),
                HttpMethod.PUT, new HttpEntity<>(mikeTyson, headers), Boxer.class);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        Boxer updatedFights = result.getBody();
        assertEquals(updatedFights.getFights(), "58");
    }

    @Test
    public void testDeleteBoxer() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> result = restTemplate.exchange(
                createURLWithPort("/boxers/" + 2),
                HttpMethod.DELETE, entity, String.class);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

}

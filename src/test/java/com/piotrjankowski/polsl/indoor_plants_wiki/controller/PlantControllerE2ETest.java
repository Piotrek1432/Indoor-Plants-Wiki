package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlantControllerE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    PlantRepository repo;

    @Test
    void httpGet_returnAllTasks(){
        //given:
        repo.save(new Plant("test1","Some description.."));
        repo.save(new Plant("test2","Foo.."));

        //when:
        Plant[] result = restTemplate.getForObject("http://localhost:" + port + "/plants",Plant[].class);

        //then:
        assertThat(result).hasSize(2);
    }
}
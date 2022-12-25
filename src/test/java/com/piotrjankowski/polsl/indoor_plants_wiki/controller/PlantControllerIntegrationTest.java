package com.piotrjankowski.polsl.indoor_plants_wiki.controller;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**integration test**/
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integration")
public class PlantControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlantRepository repo;

/*    @Test
    void httpGet_returnsGivenPlant() throws Exception {
        //given
        int id = repo.save(new Plant("test plant", "test desc")).getId();

        //when + then
        mockMvc.perform(get("/plants/" + id))
                .andExpect(status().is2xxSuccessful());
    }*/
}

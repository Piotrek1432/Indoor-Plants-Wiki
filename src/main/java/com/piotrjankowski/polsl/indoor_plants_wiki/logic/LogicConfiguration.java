package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.CategoryRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogicConfiguration {
    @Bean
    PlantService plantService(
            final PlantRepository plantRepository
            ){
        return new PlantService( plantRepository);
    }

    @Bean
    CategoryPlantService categoryPlantService(
            final CategoryRepository categoryRepository,
            final PlantRepository plantRepository
            ){
        return new CategoryPlantService(categoryRepository, plantRepository);
    }
}

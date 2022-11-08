package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Plant;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.*;

@Configuration
public class TestConfiguration {
    @Bean
    @Primary
    @Profile("integration")
    PlantRepository testRepo(){
        return new PlantRepository(){
            private Map<Integer, Plant> plants = new HashMap<>();

            @Override
            public List<Plant> findAll() {
                return new ArrayList<>(plants.values());
            }

            @Override
            public Page<Plant> findAll(Pageable page) {
                return null;
            }

            @Override
            public Optional<Plant> findById(Integer id) {
                return Optional.ofNullable(plants.get(id));
            }

            @Override
            public List<Plant> findByName(String name) {
                return null;
            }

            @Override
            public boolean existsById(Integer id) {
                return plants.containsKey(id);
            }

            @Override
            public boolean existsByCategory_id(Integer categoryId) {
                return false;
            }

            @Override
            public Plant save(Plant entity) {
                int key = plants.size() + 1;
                try{
                    var field = Plant.class.getDeclaredField("id");
                    field.setAccessible(true);
                    field.set(entity,key);
                }catch (NoSuchFieldException | IllegalAccessException e){
                    throw new RuntimeException(e);
                }
                plants.put(key, entity );
                return plants.get(key);
            }
        };
    }

    @Bean
    @Primary
    @Profile("!integration")
    DataSource e2eTestDataSource(){
        var result = new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1","","");
        result.setDriverClassName("org.h2.Driver");
        return result;
    }
}

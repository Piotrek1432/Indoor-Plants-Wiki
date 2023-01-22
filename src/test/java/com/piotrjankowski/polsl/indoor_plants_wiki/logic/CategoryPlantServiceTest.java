package com.piotrjankowski.polsl.indoor_plants_wiki.logic;

import com.piotrjankowski.polsl.indoor_plants_wiki.model.Category;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.CategoryRepository;
import com.piotrjankowski.polsl.indoor_plants_wiki.model.PlantRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryPlantServiceTest {

    @Test
    @DisplayName("Should throw IllegalArgumentException when Category with given ID does not exist")
    void changeDescriptionThrowsIllegalArgumentException() {
        //given:
        var mockCategoryRepository = mock(CategoryRepository.class);
        when(mockCategoryRepository.findById(500)).thenReturn(Optional.empty());

        //system under test:
        var toTest = new CategoryPlantService(mockCategoryRepository, null);

        //when:
        Exception exception = (Exception) catchThrowable(() ->toTest.changeDescription(500, "desc"));

        //then:
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Category with ID: 500 does not exist");
    }

    @Test
    @DisplayName("Test positive scenario of Description change")
    void changeDescriptionPositiveResult(){
        //given:
        InMemoryCategoryRepository inMemoryCategoryRepo = inMemoryCategoryRepository();

        //system under test:
        var toTest = new CategoryPlantService(inMemoryCategoryRepo, null);

        //when:
        toTest.changeDescription(1,"new");

        //then:
        inMemoryCategoryRepo.findById(1)
                .ifPresent(category -> {
                    assertThat(category.getDescription()).isEqualTo("new");
                });
    }

    private InMemoryCategoryRepository inMemoryCategoryRepository(){return new InMemoryCategoryRepository();}

    private static class InMemoryCategoryRepository implements CategoryRepository{
        private final Map<Integer, Category> map = new HashMap<Integer, Category>();
            InMemoryCategoryRepository(){
                Category testCategory = new Category();
                testCategory.setDescription("old");
                map.put(1, testCategory);
            }
            @Override
            public List<Category> findAll(){
                return new ArrayList<>(map.values());
            }

        @Override
        public Boolean existsByName(String name) {
            return null;
        }

        @Override
            public Optional<Category> findById(Integer id){
                return Optional.ofNullable(map.get(1));
            }
            @Override
            public Category save(final Category categoryToSave){
                if(categoryToSave.getId() != 0 ){
                    map.put(categoryToSave.getId(),categoryToSave);
                }
                return categoryToSave;
            }
    }
}
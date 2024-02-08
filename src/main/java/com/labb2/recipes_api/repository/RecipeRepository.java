package com.labb2.recipes_api.repository;

import com.labb2.recipes_api.models.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepository extends MongoRepository<Recipe, String> {

//så att vi kan filtrerea taggar
// behöver vi en metod signatur

    List<Recipe> findByTagsIn(List<String> tags);
    List<Recipe> findByIngredienserIn(List<String> ingredienser);



}

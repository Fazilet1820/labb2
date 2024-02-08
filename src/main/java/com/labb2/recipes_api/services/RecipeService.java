package com.labb2.recipes_api.services;


import com.labb2.recipes_api.exception.EntityNotFoundException;
import com.labb2.recipes_api.models.Comment;
import com.labb2.recipes_api.models.Recipe;
import com.labb2.recipes_api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;


    // skapa ett recept
    public Recipe addRecipe(Recipe recipe){
        return recipeRepository.save(recipe);

    }

    //lägga till kommentar till recept
    public Recipe addCommentToRecipe(String recipeId, Comment comment){
     //asagidaki objectId  iliskisine örnek
        // Recipe recipe= recipeRepository.findById(recipeId).orElseThrow((()->new RuntimeException("Recipe can not found"));
     //hangi tarife yorum atayacagimizi belirtmek icin bunu yazdik
        // eger tarifi bulamazsa hata firkatacak ve bu mesaji verecek

// referens id ile örnek yapcaz buraya
        return recipeRepository.findById(recipeId)
                .map(recipe ->{
                    recipe.adddComment(comment);
                    return recipeRepository.save(recipe);
                })

                .orElseThrow(()->new EntityNotFoundException("Recipe with id:" + recipeId + " was not found"));


    }










}

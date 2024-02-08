package com.labb2.recipes_api.services;


import com.labb2.recipes_api.exception.EntityNotFoundException;
import com.labb2.recipes_api.models.Comment;
import com.labb2.recipes_api.models.Recipe;
import com.labb2.recipes_api.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    private CommentService commentService;


    // skapa ett recept
    public Recipe addRecipe(Recipe recipe){
        return recipeRepository.save(recipe);

    }

    //hämta alla recept
    public List<Recipe> getAllRecipes(){
      return recipeRepository.findAll();
    }


    //hämta specifikt recept med id
    //iki sekil var
    //sekil 2

    public Optional<Recipe> getRecipeById(String id){
        return recipeRepository.findById(id);

    }

    /* sekil 1
    public Recipe getRecipeById(String id){
        return recipeRepository.findById(id).get();
    }*/



    //uppdatera specifikt recept
    public Recipe uppdateRecipe(String id, Recipe uppdatedRecipe){
        return recipeRepository.findById(id)
                .map(existingRecipe ->{
                    if(uppdatedRecipe.getTitle() != null){
                        existingRecipe.setTitle(uppdatedRecipe.getTitle());
                    }
                      if(uppdatedRecipe.getDescription() != null){
                        existingRecipe.setDescription(uppdatedRecipe.getDescription());
                    }

                    if(uppdatedRecipe.getIngredienser() != null){
                        existingRecipe.setIngredienser(uppdatedRecipe.getIngredienser());
                    }
                     if(uppdatedRecipe.getTags() != null){
                        existingRecipe.setTags(uppdatedRecipe.getTags());
                    }

                    return recipeRepository.save(existingRecipe);


                })

                        .orElseThrow(()-> new EntityNotFoundException("Recipe with id " + id + "was not found" ));
    }


//delete

    public void deleteRecipe(String id ){
        recipeRepository.deleteById(id);
    }


    //filtrera på taggar
    public List<Recipe> findRecipesByTags(List<String> tags){
        return recipeRepository.findByTagsIn(tags);
        //interface deki metod signatur kullandik
    }



    //lägga till kommentar till recept med ObjectId referens
    public Recipe commentToRecipe(String recipeId, Comment comment ){
        Recipe recipe= recipeRepository.findById(recipeId).orElseThrow((()->new RuntimeException("Recipe can not found")));
        Comment savedComment= commentService.saveComment(comment);
        recipe.getComments().add(savedComment);
        return recipeRepository.save(recipe);
    }






    //lägga till kommentar till recept inbäddat dokument
    /*public Recipe addCommentToRecipe(String recipeId, Comment comment){

        //
     //hangi tarife yorum atayacagimizi belirtmek icin bunu yazdik
        // eger tarifi bulamazsa hata firkatacak ve bu mesaji verecek

// referens id ile örnek yapcaz buraya
        return recipeRepository.findById(recipeId)
                .map(recipe ->{
                    recipe.adddComment(comment);
                    return recipeRepository.save(recipe);
                })

                .orElseThrow(()->new EntityNotFoundException("Recipe with id:" + recipeId + " was not found"));

*/
    }


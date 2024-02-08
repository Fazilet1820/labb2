package com.labb2.recipes_api.controllers;

import com.labb2.recipes_api.exception.EntityNotFoundException;
import com.labb2.recipes_api.models.Comment;
import com.labb2.recipes_api.models.Recipe;
import com.labb2.recipes_api.services.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // POST
    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe){
        Recipe newRecipe = recipeService.addRecipe(recipe);
        return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);

    }

    //GET ALL
    @GetMapping("/all")
    public List<Recipe> getAllRecipes(){
        return recipeService.getAllRecipes();
    }


    //GET by Id
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable String id){
        Optional<Recipe> recipe=recipeService.getRecipeById(id);
        return recipe.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }


    //PUT  finns två typer  (generiska typ )
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable String id, @Valid @RequestBody Recipe recipeDetails){
        try{
            Recipe updatedRecipe= recipeService.uppdateRecipe(id, recipeDetails);
            return ResponseEntity.ok(updatedRecipe);

        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }



    }

//DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String id){
        recipeService.deleteRecipe(id);
        return ResponseEntity.ok("Recipe with id "+ id + " has been deleted");
    }


    //GET filtrera på taggar
    @GetMapping("/search")
    public List<Recipe> findRecipeByTags(@RequestParam List<String> tags){

        return recipeService.findRecipesByTags(tags);
    }


//POST lägga till kommentar till recept med ObjectId referens
    @PostMapping("/{recipeId}/comments")
       public ResponseEntity<Recipe> addCommentToRecipe(@PathVariable String recipeId, @RequestBody Comment comment)
    {
        //felhantering ekle
        Recipe uppdatedRecipe=recipeService.commentToRecipe(recipeId, comment);
        return ResponseEntity.ok(uppdatedRecipe);

    }



}





    //POST lägga till mommentar till recept med inbäddad dokument
   /* @PostMapping("/{recipeId}/comments")
    public ResponseEntity<Recipe> addCommentToRecipe(@PathVariable String recipeId, @RequestBody Comment comment){
try{
    Recipe uppdatedRecipe= recipeService.addCommentToRecipe(recipeId, comment);
    return ResponseEntity.ok(uppdatedRecipe);
}
catch (EntityNotFoundException e)
{
return ResponseEntity.notFound().build();
}}*/
















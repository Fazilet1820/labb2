package com.labb2.recipes_api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection="recipes")
public class Recipe {

    @Id
    private String id;

    //validering
    @NotBlank(message="Field can not be blank") // bos birakilamaz
    private String title;

    @NotBlank(message= "Field can not be blank") //string typ icin kullanilir bu anatasyon sadece
    private String description;

    @NotEmpty(message= "Field can nor be blank") // collections, array, listeler hashamap vs icin kullanilir
    private List<String> ingredienser;
    private List<String > tags=new ArrayList<>();
    //bos olmasi sorun olmaz

    //inbäddade kommentarer i dokumentet

    //private List <Comment> comments= new ArrayList<>();
    //datatypen comment - våran egen comment model, klass

    // referens med ObjectId
    @DBRef    //ref till en annan collection i databasen
    private List <Comment> comments= new ArrayList<>();

    // tom constructor
    // getter setter för alla förutom id
    // bara getter id
    public Recipe() {
    }

// metod för att lägga till en kommentar
    public void adddComment(Comment comment){
        this.comments.add(comment);
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredienser() {
        return ingredienser;
    }

    public void setIngredienser(List<String> ingredienser) {
        this.ingredienser = ingredienser;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    // det kan vara tom array



}

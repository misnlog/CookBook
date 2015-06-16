package cookbook;


import java.util.List;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mišel
 */
public class Recipe {

    private String name;
    private String ingredients;
    private String directions;
    private String timeOfPreparation;
    private String timeOfCooking;
    private String image;
    private String[] hashtags;
    private long timeStamp;
    private String category;
    /*public enum Category {
        Appetizers, Beef, Beverages, Desserts, Fish, Pasta, Pork, Poultry, Salads, Soups;
    }*/
    
    //private Category category;

    public Recipe(String name, String category, String ingredients, String directions, String timeOfPreparation, String timeOfCooking, String image, String[] hashtags, long timeStamp) {
        this.name = name;
        this.ingredients = ingredients;
        this.directions = directions;
        this.timeOfPreparation = timeOfPreparation;
        this.timeOfCooking = timeOfCooking;
        this.image = image;
        this.hashtags = hashtags;
        this.category = category;
    }

    public Recipe() {
    }    
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getTimeOfPreparation() {
        return timeOfPreparation;
    }

    public void setTimeOfPreparation(String timeOfPreparation) {
        this.timeOfPreparation = timeOfPreparation;
    }

    public String getTimeOfCooking() {
        return timeOfCooking;
    }

    public void setTimeOfCooking(String timeOfCooking) {
        this.timeOfCooking = timeOfCooking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getHashtags() {
        return hashtags;
    }

    public void setHashtags(String[] hashtags) {
        this.hashtags = hashtags;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }    

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
     
    

    @Override
    public String toString() {
        return "Recept [name=" + name + ", ingrediencia=" + ingredients + "]";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.ingredients);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recipe other = (Recipe) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.ingredients, other.ingredients)) {
            return false;
        }
        return true;
    }
    
      
    

}

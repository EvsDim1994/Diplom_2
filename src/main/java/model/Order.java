package model;

import java.util.List;

public class Order {
    public List<String> getIngredients() {
        return ingredients;
    }

    public Order(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    List<String> ingredients;
}

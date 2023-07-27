package com.example.foodrecipeapp.Listener;

import com.example.foodrecipeapp.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFatch(RecipeDetailsResponse response,String message);
    void didError(String message);
}

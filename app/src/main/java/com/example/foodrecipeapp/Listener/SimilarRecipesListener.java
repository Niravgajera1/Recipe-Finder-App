package com.example.foodrecipeapp.Listener;

import com.example.foodrecipeapp.Models.SimilarRecipeResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFatch(List<SimilarRecipeResponse> response, String message);
    void didError(String message);
}

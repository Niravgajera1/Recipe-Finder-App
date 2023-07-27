package com.example.foodrecipeapp.Listener;

import com.example.foodrecipeapp.Models.RamdomRecipeApiResponse;

public interface RandomRecipeResponseListener {

    void didFatch(RamdomRecipeApiResponse response, String message);
    void didError(String message);
}
